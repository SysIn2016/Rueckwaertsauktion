<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="logik"
	class="Sysint2016.Rueckwaertsauktion.AuktionLogik" />
<jsp:useBean id="nutzerverwaltung"
	class="Sysint2016.Rueckwaertsauktion.Nutzerverwaltung" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rueckwaertsauktion</title>
<link rel="stylesheet" type="text/css" href="Rueckwaertsauktion.css">
<link rel="stylesheet" type="text/css" href="Popup.css">
<script type="text/javascript" src="Rueckwaertsauktion.js"></script>
</head>
<body bgcolor="#D3FFCE">
	<!--  Popup Login -->
	<%
		if ("true".equals(request.getParameter("Ausloggen"))) {
			session.invalidate();
			session = request.getSession();
		}
		;
		/*
		 * Ueberlegung lieber Typ des requests abfragen!
		 */
		if (request.getParameter("UVorname") != null) {
			if (nutzerverwaltung.registrieren(
					request.getParameter("UVorname"),
					request.getParameter("UNachname"),
					request.getParameter("UUsername"),
					request.getParameter("UEMail"),
					request.getParameter("UPasswort"),
					request.getParameter("UKontonummer"),
					request.getParameter("UIBAN"),
					request.getParameter("UBIC"))) {
				nutzerverwaltung.sendeRegistrationsmail(

				request.getParameter("UUsername"));
			}
		}
		int produktID = logik.getProduktID(logik.getDatumAlsString(null));
		String bildURL = "Platzhalter.png";
		String produktname = "Heute keine Auktion";
		String produktbeschreibung = "F&uuml;r heute ist keine Auktion vorgemerkt.";
		if (produktID != -1) {
			Sysint2016.Rueckwaertsauktion.Produkt p = logik
					.getProduktverwaltung().findeProdukt(produktID);
			bildURL = p.getBildID();
			produktname = p.getProduktname();
			produktbeschreibung = p.getProduktbeschreibung();
		}
	%>
	<div id="login" class="modal"
		<%if (request.getParameter("Name") != null
					&& request.getParameter("Passwort") != null) {
				if (!nutzerverwaltung.anmelden(request.getParameter("Name"),
						request.getParameter("Passwort"))) {
					out.print("style=\"display: block\"");
				} else {
					session.setAttribute("Name", request.getParameter("Name"));
				}
			}%>>
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenAnmelden">&times;</span>
				<h2>Anmelden</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form1" METHOD="POST">
					<INPUT TYPE="HIDDEN" NAME="Name"> <INPUT Type="Hidden"
						Name="Passwort"> <INPUT TYPE="text"
						placeholder="Nutzername" id="AnmeldenameText"> <br> <INPUT
						TYPE="password" placeholder="Passwort" id="PasswortText">
					<br> <INPUT TYPE="button" VALUE="einloggen"
						ONCLICK="einloggen()">
				</FORM>
			</div>
			<div class="modal-footer">
				<h3>Bitte loggen Sie sich ein, um an der Auktion teilzunehmen.</h3>
			</div>
		</div>
	</div>

	<div id="main">
		<div id="oben">
			<div id="Registrierung">
				<button class="button"
					<%if (session.getAttribute("Name") != null) {
				out.print("disabled");
			}%>
					id="registrierenButton">
					<span>Registrieren</span>
				</button>
				<button class="button"
					<%if (session.getAttribute("Name") != null) {
				out.print("id=\"logoutButton\" ONCLICK=\"ausloggen()\"><span>Abmelden</span");
			} else {
				out.print("id=\"loginButton\"><span>Anmelden</span");
			}%>>
				</button>
			</div>
			<div id="Infotext">
				Diese Webseite ist ein Projekt im Rahmen einer Lehrveranstaltung im
				Studium.<br> Es werden keine echten Auktionen angeboten!!!<br>
				<%
					int anzGebote = 0;
					if (session.getAttribute("Name") != null) {
						out.print("Sie sind angemeldet als: "
								+ session.getAttribute("Name"));
						anzGebote = logik.getAnzahlGebote((String) session
								.getAttribute("Name"));
					}
				%>
			</div>
		</div>
		<div id="mitte">
			<div id="Gewinner">
				<button class="button">
					<span>Gewinner</span>
				</button>
			</div>
		</div>
		<div id="unten">
			<div id="bild">
				<h3>
					<%
						out.print(produktname);
					%>
				</h3>
				<br>
				<%
					out.print("Beschreibung: " + produktbeschreibung);
				%><br> <img src=<%out.print("\"" + bildURL + "\"");%>
					width="900" alt="Produktbild ist nicht sichtbar">
			</div>
			<div id="Registrierung">
				<button class="button" id="produktEinstellenButton"
					<%if (session.getAttribute("Name") == null) {%> disabled <%}%>>
					<span>Produkt einstellen</span>
				</button>
				<button class="button" id="geboteAbgebenButton"
					<%if (session.getAttribute("Name") == null) {%> disabled <%}%>>
					<span>Gebote abgeben</span>
				</button>
				<h3>Kommende Auktionen:</h3>
				<br>
				<%
					for (String next : logik.gibNaechsteZehn()) {
						out.println(next + "\t" + "<br>");
					}
				%>
			</div>
		</div>
	</div>

	<!--  Popup Produkt einstellen -->
	<div id="produktEinstellen" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenProduktEinstellen">&times;</span>
				<h2>Produkt einstellen</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form2" METHOD="POST" action="BildUpload.jsp"
					enctype="multipart/form-data">
					<INPUT TYPE="HIDDEN" NAME="Produktname"> <INPUT
						TYPE="HIDDEN" NAME="Produktbild"> <INPUT TYPE="HIDDEN"
						NAME="Produktbeschreibung"> <INPUT TYPE="text"
						placeholder="Produktname" id="ProduktnameText"> <br>
					<TEXTAREA rows="20" cols="200" id="ProduktBeschreibungText">Produktbeschreibung bitte eingeben. Bedenken Sie, je besser ihre Produktbeschreibung, desto wahrscheinlicher sind viele Gebote.</TEXTAREA>
					<br> <INPUT TYPE="file" name="produktBild" accept="image/*"
						id="ProduktBild" size="50"> <br> <INPUT TYPE="button"
						VALUE="Produkt einstellen" ONCLICK="produktEinstellen()">
				</FORM>
			</div>
			<div class="modal-footer">
				<h3>Stellen Sie ihr Produkt ein.</h3>
			</div>
		</div>
	</div>

	<!--  Popup Gebote abgeben -->
	<div id="geboteAbgeben" class="modal"
		<%if (request.getParameter("UGebot") != null) {
				out.print("style=\"display: block\"");
				anzGebote = logik.gebeGebotAb(
						(String) session.getAttribute("Name"),
						Float.parseFloat(request.getParameter("UGebot")));
			}%>>
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenGeboteAbgeben">&times;</span>
				<h2>Gebote abgeben</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form3" METHOD="POST">
					<INPUT TYPE="HIDDEN" NAME="UGebot"> <INPUT TYPE="text"
						placeholder="Gebot" id="Gebot"> <br> <INPUT
						TYPE="button" VALUE="bieten" ONCLICK="gebotAbgeben()">
				</FORM>
				Anzahl der Gebote für das heutige Produkt:
				<%
					out.print(anzGebote);
				%>
			</div>
			<div class="modal-footer">
				<h3>Geben Sie Ihr Gebot ab.</h3>
			</div>
		</div>
	</div>

	<!--  Popup Registrierung -->
	<div id="userRegistrieren" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenUserRegistrieren">&times;</span>
				<h2>Registrieren</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form4" METHOD="POST">
					<INPUT TYPE="HIDDEN" NAME="UVorname"> <INPUT TYPE="HIDDEN"
						NAME="UNachname"> <INPUT TYPE="HIDDEN" NAME="UUsername">
					<INPUT TYPE="HIDDEN" NAME="UPasswort"> <INPUT TYPE="HIDDEN"
						NAME="UEMail"> <INPUT TYPE="HIDDEN" NAME="UKontonummer">
					<INPUT TYPE="HIDDEN" NAME="UBIC"> <INPUT TYPE="HIDDEN"
						NAME="UIBAN"> <INPUT TYPE="text" placeholder="Vorname"
						id="UserVorname"> <br> <INPUT TYPE="text"
						placeholder="Nachname" id="UserNachname"> <br> <INPUT
						TYPE="text" placeholder="Username" id="UserUsername"> <br>
					<INPUT TYPE="email" placeholder="E-Mailadresse" id="UserEMail">
					<br> <INPUT TYPE="password" placeholder="Passwort"
						id="UserPasswort"> <br> <INPUT TYPE="password"
						placeholder="Passwort erneut eingeben" id="UserPasswortkontrolle">
					<br>
					<h3>Kontodaten</h3>
					<INPUT TYPE="number" placeholder="Kontonummer" id="UserKontonummer">
					<br> <INPUT TYPE="text" placeholder="IBAN" id="UserIBAN">
					<br> <INPUT TYPE="text" placeholder="BIC" id="UserBIC">
					<br> <INPUT TYPE="button" VALUE="registrieren"
						ONCLICK="registrieren()">
				</FORM>
			</div>
			<div class="modal-footer">
				<h3>Registrieren Sie sich. Damit Sie für Produkte bieten
					k&ouml;nnen und welche einstellen k&ouml;nnen.</h3>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="Popups.js"></script>
</body>
</html>