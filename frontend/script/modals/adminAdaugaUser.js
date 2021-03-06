// adaugare utlizator MODAL
var addmodal = document.getElementById("add-modal");
var addbtn = document.getElementById("add-button");
var addspan = document.getElementById("add-close");

addbtn.onclick = function() {
    addmodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
    delmodal.style.display = "none";
}

addspan.onclick = function() {
    addmodal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == addmodal) {
        addmodal.style.display = "none";
    }
}

var _r_username = document.getElementById("add-username");
var _r_password = document.getElementById("add-password");
var _r_repassword = document.getElementById("add-re-password");
var _r_phone = document.getElementById("add-phone");
var _r_email = document.getElementById("add-email");
var _r_description = document.getElementById("add-description");
var _r_isProvider = document.getElementById("add-is-provider");
var _r_isAdmin = document.getElementById("add-is-admin");

var _r_username_v = false;
var _r_password_v = false;
var _r_phone_v = false;
var _r_email_v = false;

_r_username.onchange = function() {
    var text = _r_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("add-username-message").style = "visibility: visible; opacity: 1;";
        _r_username_v = false;
    } else {
        document.getElementById("add-username-message").style = "";
        _r_username_v = true;
    }
}

_r_repassword.onchange = function() {
    var pw = _r_password.value;
    var rpw = _r_repassword.value;
    if (pw != rpw) {
        document.getElementById("add-re-password-message").style = "visibility: visible; opacity: 1;";
        _r_password_v = false;
    } else {
        document.getElementById("add-re-password-message").style = "";
        _r_password_v = true;
    }
}

_r_phone.onchange = function() {
    var text = _r_phone.value;
    if (!/^([0-9]{10})$/.test(text)) {
        document.getElementById("add-phone-message").style = "visibility: visible; opacity: 1;";
        _r_phone_v = false;
    } else {
        document.getElementById("add-phone-message").style = "";
        _r_phone_v = true;
    }
}

_r_email.onchange = function() {
    var text = _r_email.value;
    if (!/^([a-zA-Z0-9\_\.]+[\@][a-zA-Z0-9]+[\.][a-zA-Z]+)$/.test(text)) {
        document.getElementById("add-email-message").style = "visibility: visible; opacity: 1;";
        _r_email_v = false;
    } else {
        document.getElementById("add-email-message").style = "";
        _r_email_v = true;
    }
}

var addsubmit = document.getElementById("add-submit");
addsubmit.onclick = function() {
    if (!_r_username_v || !_r_password_v || !_r_phone_v || !_r_email_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var userJSON = '{' +
            '"username":"' + _r_username.value + '", ' +
            '"password":"' + _r_password.value + '", ' +
            '"phone":"' + _r_phone.value + '", ' +
            '"email":"' + _r_email.value + '", ' +
            '"description":"' + _r_description.value + '", ' +
            '"profile_picture_link":"http://localhost/images/avatar.jpg", ' +
            '"isProvider":' + _r_isProvider.checked + ', ' +
            '"isAdmin":' + _r_isAdmin.checked +
        '}';

        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (Object.keys(this.responseText).length == 0) {
                    alert("Campurile sunt completate invalid");
                } else {
                    var response = JSON.parse(this.responseText);
                    if (response.code != 0) {
                        alert("Server response: " + response.message);
                    } else {
                       alert("Cont creat cu succes!");
                    }
                }
            } else if (this.status == 400) {
                var response = this.responseText;
                var message = response.errors[0].defaultMessage;
                alert("Server response: " + message);
            }
        }

        xhr.open('post', SERVER_LINK + "/api/v1/user/", true);
        xhr.setRequestHeader("Accept", "application/json");

        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(userJSON);
    }
}