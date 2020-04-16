function validateForm(obj) {
	var ucard = document.forms["myForm"]["numcarta"].value;
	var uemail = document.forms["myForm"]["email"].value;
	var valid = false;
	
	if(validateCard(ucard) && validateEmail(uemail)) {
			valid = true;
	}
	return valid;
}
	
function validateCard(ucard) {
	var cardformat = /^([0-9]{16})$/;
	if(ucard.match(cardformat)) {
	return true;
	} else {
		document.getElementById("errorecarta").classList.remove("hidden");
		return false;
		}
}
	
function validateEmail(uemail) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(uemail.match(mailformat)) {
	return true;
	} else {
		document.getElementById("erroremail").classList.remove("hidden");
		return false;
		}
	}