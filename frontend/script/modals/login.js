// LOGIN MODAL
var loginmodal = document.getElementById("login-modal");
var loginbtn = document.getElementById("login-button");
var loginspan = document.getElementById("login-close");

loginbtn.onclick = function() {
    loginmodal.style.display = "block";
    registermodal.style.display = "none";
    forgotmodal.style.display = "none";
}
loginspan.onclick = function() {
    loginmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == loginmodal) {
        loginmodal.style.display = "none";
    }
}	

var _l_username = document.getElementById("login-username");
var _l_password = document.getElementById("login-password");

var _l_username_v = false;
_l_username.onchange = function() {
    var text = _l_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("login-username-message").style = "visibility: visible; opacity: 1;";
        _l_username_v = false;
    } else {
        document.getElementById("login-username-message").style = "";
        _l_username_v = true;
    }
}

var loginsubmit = document.getElementById("login-submit");
loginsubmit.onclick =  function() {
    var username = document.getElementById("login-username").value;
    var password = document.getElementById("login-password").value;

    if (_l_username.value == "" || _l_password.value == "" || !_l_username_v) {
        alert("Campurile sunt completate eronat.");
    } else {
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (Object.keys(this.responseText).length == 0) {
                   alert("Numele de utilizator sau parola sunt gresite.");
                } else {
                    var response = JSON.parse(this.responseText);
                    alert("Bine ai revenit, " + response.username + "!");
                }
            }
        }
    
        xhr.open('get', SERVER_LINK + "/api/v1/user/" + username + "/" + password, true);
        xhr.send();
    }
}