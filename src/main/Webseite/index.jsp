<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="logik" class="Sysint2016.Rueckwaertsauktion.AuktionLogik"/>
<jsp:useBean id="anmeldung" class="Sysint2016.Rueckwaertsauktion.Anmeldung"/>
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
	<div id="main">
		<div id="oben">
			<div id="Registrierung">
				<button class="button">
					<span>Registrieren</span>
				</button>
				<button class="button" id="loginButton">
					<span>Anmelden</span>
				</button>
			</div>
			<div id="Infotext">
				Diese Webseite ist ein Projekt im Rahmen einer Lehrveranstaltung im
				Studium.<br> Es werden keine echten Auktionen angeboten!!!<br>
				<% 
            if(request.getParameter("Name") != null && request.getParameter("Passwort") != null) {
        %>
            Angemeldet: <% out.print(anmeldung.anmelden(request.getParameter("Name"), request.getParameter("Passwort"))); %>
            
        <%
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
				<img src="Platzhalter.png" width="900"
					alt="Produktbild ist nicht sichtbar">
			</div>
			<div id="Registrierung">
				<button class="button" id="produktEinstellenButton" ONCLICK = "new PopupFenster('login', 'loginButton', 'schliessenAnmelden')">
					<span>Produkt einstellen</span>
				</button>
				<button class="button">
					<span>Gebot abgeben</span>
				</button>
			</div>
		</div>
	</div>


	<!--  Popup Login -->
	<div id="login" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenAnmelden">&times;</span>
				<h2>Login</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form1" METHOD="POST">
					<INPUT TYPE="HIDDEN" NAME="Name"> 
					<INPUT Type="Hidden" Name="Passwort">
					<INPUT TYPE="text" placeholder="Nutzername" id="AnmeldenameText"> <br>
					<INPUT TYPE="password" placeholder="Passwort" id="PasswortText"> <br>
					<INPUT TYPE="button" VALUE="einloggen" ONCLICK="einloggen()">
				</FORM>
			</div>
			<div class="modal-footer">
				<h3>Bitte loggen Sie sich ein, um an der Auktion teilzunehmen.</h3>
			</div>
		</div>
	</div>
	
	<!--  Popup Login -->
	<div id="produktEinstellen" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" id="schliessenProduktEinstellen">&times;</span>
				<h2>Login</h2>
			</div>
			<div class="modal-body">
				<FORM NAME="form2" METHOD="POST">
					<INPUT TYPE="text" placeholder="Produktname" id="ProduktnameText"> <br>
					<TEXTAREA rows="20" cols="200" id="ProduktbeschreibungText">Produktbeschreibung bitte eingeben. Bedenken Sie, je besser ihre Produktbeschreibung, desto wahrscheinlicher sind viele Gebote.</TEXTAREA> <br>
					<INPUT TYPE="file" accept="image/*">
					<INPUT TYPE="button" VALUE="Produkt einstellen" ONCLICK="produktEinstellen()">
				</FORM>
			</div>
			<div class="modal-footer">
				<h3>Stellen Sie ihr Produkt ein.</h3>
			</div>
		</div>
	</div>
<script type="text/javascript" src="Popups.js"></script>
</body>
</html>