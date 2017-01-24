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
	var pname = document.getElementById("ProduktnameText").value;
	var pbeschreibung = document.getElementById("ProduktBeschreibungText").value;
	var pbild = document.getElementById("ProduktBild").value;
	if(pname.length > 0 && pbeschreibung.length > 0 && pbild.length > 0){
	document.form2.Produktname.value = pname;
	document.form2.Produktbild.value = pbild;
	document.form2.Produktbeschreibung.value = pbeschreibung;
	form2.submit();
	} else{
	alert("Bitte alle Felder ausfüllen.");
	}
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