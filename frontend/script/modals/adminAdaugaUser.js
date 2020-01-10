// adaugare utlizator MODAL
var a_addmodal = document.getElementById("add-modal");
var a_addbtn = document.getElementById("add-button");
var a_addspan = document.getElementById("add-close");

a_addbtn.onclick = function() {
    a_addmodal.style.display = "block";
}

a_addspan.onclick = function() {
    a_addmodal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == a_addmodal) {
        a_addmodal.style.display = "none";
    }
}

var _a_username = document.getElementById("add-username");
var _a_password = document.getElementById("add-password");
var _a_repassword = document.getElementById("add-re-password");
var _a_phone = document.getElementById("add-phone");
var _a_email = document.getElementById("add-email");
var _a_description = document.getElementById("add-description");
var _a_isProvider = document.getElementById("add-is-provider");
var _a_isAdmin = document.getElementById("add-is-admin");

var _a_username_v = false;
var _a_password_v = false;
var _a_phone_v = false;
var _a_email_v = false;

_a_username.onchange = function() {
    var text = _a_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("add-username-message").style = "visibility: visible; opacity: 1;";
        _a_username_v = false;
    } else {
        document.getElementById("add-username-message").style = "";
        _a_username_v = true;
    }
}

_a_repassword.onchange = function() {
    var pwa = _a_password.value;
    var apwa = _a_repassword.value;
    if (pwa != apwa) {
        document.getElementById("add-re-password-message").style = "visibility: visible; opacity: 1;";
        _a_password_v = false;
    } else {
        document.getElementById("add-re-password-message").style = "";
        _a_password_v = true;
    }
}

_a_phone.onchange = function() {
    var text = _a_phone.value;
    if (!/^([0-9]{10})$/.test(text)) {
        document.getElementById("add-phone-message").style = "visibility: visible; opacity: 1;";
        _a_phone_v = false;
    } else {
        document.getElementById("add-phone-message").style = "";
        _a_phone_v = true;
    }
}

_a_email.onchange = function() {
    var text = _a_email.value;
    if (!/^([a-zA-Z0-9\_\.]+[\@][a-zA-Z0-9]+[\.][a-zA-Z]+)$/.test(text)) {
        document.getElementById("add-email-message").style = "visibility: visible; opacity: 1;";
        _a_email_v = false;
    } else {
        document.getElementById("add-email-message").style = "";
        _a_email_v = true;
    }
}

var _a_addsubmit = document.getElementById("add-submit");
_a_addsubmit.onclick = function() {
    if (!_a_username_v || !_a_password_v || !_a_phone_v || !_a_email_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var userJSON = '{' +
            '"username":"' + _a_username.value + '", ' +
            '"password":"' + _a_password.value + '", ' +
            '"phone":"' + _a_phone.value + '", ' +
            '"email":"' + _a_email.value + '", ' +
            '"description":"' + _a_description.value + '", ' +
            '"profile_picture_link":"http://localhost/images/avatar.jpg", ' +
            '"isProvider":' + _a_isProvider.checked + ', ' +
            '"isAdmin":' + _a_isAdmin.checked +
        '}';

        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
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