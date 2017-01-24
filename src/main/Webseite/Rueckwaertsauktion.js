function einloggen() {
	document.form1.Name.value = document.getElementById("AnmeldenameText").value;
	document.form1.Passwort.value = document.getElementById("PasswortText").value;
	form1.submit();
}

function ausloggen() {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "Ausloggen");
	hiddenField.setAttribute("value", "true");
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}

function produktEinstellen() {
	document.form2.Produktname.value = document
			.getElementById("ProduktnameText").value;
	document.form2.Produktbild.value = document.getElementById("ProduktBild").value;
	document.form2.Produktbeschreibung.value = document
			.getElementById("ProduktBeschreibungText").value;
	form2.submit();
}

function registrieren() {
	var p1 = document.getElementById("UserPasswort").value;
	var p2 = document.getElementById("UserPasswortkontrolle").value;

	var vorname = document.getElementById("UserVorname").value;
	var nachname = document.getElementById("UserNachname").value;
	var username = document.getElementById("UserUsername").value;
	var email = document.getElementById("UserEMail").value;
	var kontonummer = document.getElementById("UserKontonummer").value;
	var iban = document.getElementById("UserIBAN").value;
	var bic = document.getElementById("UserBIC").value;

	// 1. Passwort pruefen
	if (p1.length >= 8 && p1 === p2) {
		// 2. Pruefen ob zumindest alles gefuellt ist
		if (vorname.length > 0 && nachname.length > 0 && username.length > 0
				&& email.length > 0 && kontonummer.length > 0
				&& iban.length > 0 && bic.length > 0) {
			document.form4.UVorname.value = vorname;
			document.form4.UNachname.value = nachname;
			document.form4.UUsername.value = username;
			document.form4.UEMail.value = email;
			document.form4.UPasswort.value = p1;
			document.form4.UKontonummer.value = kontonummer;
			document.form4.UIBAN.value = iban;
			document.form4.UBIC.value = bic;
			form4.submit();
		} else {
			alert("Das Formular muss komplett ausgefüllt sein!");
		}
	} else {
		alert("Das Passwort muss 2 mal gleich eingegeben werden und mindestens 8 Zeichen lang sein.");
	}
}

function gebotAbgeben() {

}