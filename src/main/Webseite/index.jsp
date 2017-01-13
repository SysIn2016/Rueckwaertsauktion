<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rueckwaertsauktion</title>
<link rel="stylesheet" type="text/css" href="Rueckwaertsauktion.css">
<link rel="stylesheet" type="text/css" href="Popup.css">
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
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
        %>
            Name:<%= request.getParameter("Name") %>
            Passwort: 
            <%= request.getParameter("Passwort") %>
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
				<button class="button">
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
				<span class="close">&times;</span>
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

	<!-- Javascript zu den Popup -->
	<script>
// Get the modal
var modal = document.getElementById('login');

// Get the button that opens the modal
var btn = document.getElementById("loginButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function einloggen(){
	document.form1.Name.value = document.getElementById("AnmeldenameText").value;
	document.form1.Passwort.value = document.getElementById("PasswortText").value;
    form1.submit();
}
</script>
</body>
</html>