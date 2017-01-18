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
	
}

function gebotAbgeben(){
	
}