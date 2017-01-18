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
	
}

function registrieren(){
	
}

function gebotAbgeben(){
	
}