/**
 * Script behind Login Modal
 * Global vars used: _rm_
 * @author Valentin Deaconu
 */ 
var _rm_ = new ModalWrapper("register-modal", "register-button", "register-close");

_rm_.addFormItem("register-username", function() {
    var text = document.getElementById("register-username").value;
    _rm_.cache["username"] = text;

    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("register-username-message").style = "visibility: visible; opacity: 1;";
        
        _rm_.cache["v_username"] = false;
    } else {
        document.getElementById("register-username-message").style = "";
        
        _rm_.cache["v_username"] = true;
    }
});

_rm_.addFormItem("register-re-password", function() {
    var pw = document.getElementById("register-password").value;
    var rpw = document.getElementById("register-re-password").value;

    _rm_.cache["password"] = pw;

    if (pw != rpw) {
        document.getElementById("register-re-password-message").style = "visibility: visible; opacity: 1;";
        
        _rm_.cache["v_password"] = false;
    } else {
        document.getElementById("register-re-password-message").style = "";
        
        _rm_.cache["v_password"] = true;
    }
});

_rm_.addFormItem("register-phone", function() {
    var text = document.getElementById("register-phone").value;
    _rm_.cache["phone"] = text;

    if (!/^([0-9]{10})$/.test(text)) {
        document.getElementById("register-phone-message").style = "visibility: visible; opacity: 1;";
        
        _rm_.cache["v_phone"] = false;
    } else {
        document.getElementById("register-phone-message").style = "";
        
        _rm_.cache["v_phone"] = true;
    }
});

_rm_.addFormItem("register-email", function() {
    var text = document.getElementById("register-email").value;
    _rm_.cache["email"] = text;

    if (!/^([a-zA-Z0-9\_\.]+[\@][a-zA-Z0-9]+[\.][a-zA-Z]+)$/.test(text)) {
        document.getElementById("register-email-message").style = "visibility: visible; opacity: 1;";
        
        _rm_.cache["v_email"] = false;
    } else {
        document.getElementById("register-email-message").style = "";
        
        _rm_.cache["v_email"] = true;
    }
});

_rm_.addFormItem("register-description", function() {
    _rm_.cache["description"] = document.getElementById("register-description").value;
});

_rm_.addFormItem("register-is-provider", function() {
    _rm_.cache["isProvider"] = document.getElementById("register-is-provider").checked;
});

_rm_.addButtonItem("register-submit", function() {
    if (!_rm_.cache["v_username"] || !_rm_.cache["v_password"] || !_rm_.cache["v_phone"] || !_rm_.cache["v_email"]) {
        alert("Campurile sunt completate invalid.");
    } else {
        var userJSON = '{' +
            '"username":"' + _rm_.cache["username"] + '", ' +
            '"password":"' + _rm_.cache["password"] + '", ' +
            '"phone":"' + _rm_.cache["phone"] + '", ' +
            '"email":"' + _rm_.cache["email"] + '", ' +
            '"description":"' + _rm_.cache["description"] + '", ' +
            '"profile_picture_link":"http://localhost/images/avatar.jpg", ' +
            '"isProvider":' + _rm_.cache["isProvider"] + ', ' +
            '"isAdmin":false ' +
        '}';

        const sr = new ServerRequest(function(responseText) {
            if (Object.keys(responseText).length == 0) {
                alert("Campurile sunt completate invalid");
            } else {
                var response = JSON.parse(responseText);
                if (response.code != 0) {
                    alert("Server response: " + response.message);
                } else {
                   alert("Cont creat cu succes! Te poti autentifica.");    
                }
            }
        }, function (status, responseText) {
            console.log("HTTPStatus: " + status);
            console.log("Server response: " + responseText);
        });

        sr.send(RequestMethod.POST, "/api/v1/user/", userJSON);
    }
});