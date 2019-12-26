// FORGOT MODAL
var forgotmodal = document.getElementById("forgot-modal");
var forgotbtn = document.getElementById("login-forgot");
var forgotspan = document.getElementById("forgot-close");

forgotbtn.onclick = function() {
    loginmodal.style.display = "none";
    registermodal.style.display = "none";
    forgotmodal.style.display = "block";
}

forgotspan.onclick = function() {
    forgotmodal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == forgotmodal) {
        forgotmodal.style.display = "none";
    }
}	