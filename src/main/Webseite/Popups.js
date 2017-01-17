// Get the modal
var einloggenPopup = document.getElementById('login');

// Get the button that opens the modal
var einlogButton = document.getElementById("loginButton");

// Get the <span> element that closes the modal
var x = document.getElementById("schliessenAnmelden");

// When the user clicks the button, open the modal
einlogButton.onclick = function() {
	einloggenPopup.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
x.onclick = function() {
	einloggenPopup.style.display = "none";
}




// Get the modal
var produktEinstellenPopup = document.getElementById('produktEinstellen');

// Get the button that opens the modal
var produktEinstellenButton = document.getElementById("produktEinstellenButton");

// Get the <span> element that closes the modal
var span = document.getElementById("schliessenProduktEinstellen");

// When the user clicks the button, open the modal
produktEinstellenButton.onclick = function() {
	produktEinstellenPopup.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	produktEinstellenPopup.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == produktEinstellenPopup) {
    	produktEinstellenPopup.style.display = "none";
    }
    if (event.target == einloggenPopup) {
    	einloggenPopup.style.display = "none";
    }
}