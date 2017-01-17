/*
 * Login Popup
 */

var einloggenPopup = document.getElementById('login');
var einlogButton = document.getElementById("loginButton");
var x = document.getElementById("schliessenAnmelden");

//Funktion beim Klicken des Buttons
einlogButton.onclick = function() {
	einloggenPopup.style.display = "block";
}

//Funktion beim Klicken des x im Popup
x.onclick = function() {
	einloggenPopup.style.display = "none";
}

/*
 * Produkt einstellen Popup
 */

var produktEinstellenPopup = document.getElementById('produktEinstellen');
var produktEinstellenButton = document.getElementById("produktEinstellenButton");
var span = document.getElementById("schliessenProduktEinstellen");

//Funktion beim Klicken des Buttons
produktEinstellenButton.onclick = function() {
	produktEinstellenPopup.style.display = "block";
}

//Funktion beim Klicken des x im Popup
span.onclick = function() {
	produktEinstellenPopup.style.display = "none";
}

/*
 * Registrieren Popup
 */

var userRegistrierenPopup = document.getElementById('userRegistrieren');
var userRegistrierenButton = document.getElementById("registrierenButton");
var userRegistrierenSchliessen = document.getElementById("schliessenUserRegistrieren");

//Funktion beim Klicken des Buttons
userRegistrierenButton.onclick = function() {
	userRegistrierenPopup.style.display = "block";
}

//Funktion beim Klicken des x im Popup
userRegistrierenSchliessen.onclick = function() {
	userRegistrierenPopup.style.display = "none";
}

/*
 * Popups schliessen beim Klick ausserhalb des Popups
 */
window.onclick = function(event) {
    if (event.target == produktEinstellenPopup) {
    	produktEinstellenPopup.style.display = "none";
    }
    if (event.target == einloggenPopup) {
    	einloggenPopup.style.display = "none";
    }
    if (event.target == userRegistrierenPopup) {
    	userRegistrierenPopup.style.display = "none";
    }
}