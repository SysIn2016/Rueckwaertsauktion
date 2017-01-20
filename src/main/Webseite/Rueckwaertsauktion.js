function einloggen(){
	document.form1.Name.value = document.getElementById("AnmeldenameText").value;
	document.form1.Passwort.value = document.getElementById("PasswortText").value;
    form1.submit();
}

function ausloggen(){
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

function produktEinstellen(){
	document.form2.Produktname.value = document.getElementById("ProduktnameText").value;
	document.form2.Produktbild.value = document.getElementById("ProduktBild").value;
	document.form2.Produktbeschreibung.value = document.getElementById("ProduktBeschreibungText").value;
    form2.submit();
}

function registrieren(){
	var p1 = document.getElementById("UserPasswort").value;
	var p2 = document.getElementById("UserPasswortkontrolle").value;
	if (p1.length >= 8 && p1 === p2){
		document.form4.UVorname.value = document.getElementById("UserVorname").value;
		document.form4.UNachname.value = document.getElementById("UserNachname").value;
		document.form4.UUsername.value = document.getElementById("UserUsername").value;
		document.form4.UEMail.value = document.getElementById("UserEMail").value;
		document.form4.UPasswort.value = p1;
		document.form4.UKontonummer.value = document.getElementById("UserKontonummer").value;
		document.form4.UIBAN.value = document.getElementById("UserIBAN").value;
		document.form4.UBIC.value = document.getElementById("UserBIC").value;
		form4.submit();
	} else {
		alert("Das Passwort muss 2 mal gleich eingegeben werden und mindestens 8 Zeichen lang sein.");
	}
}

function gebotAbgeben(){
	
}