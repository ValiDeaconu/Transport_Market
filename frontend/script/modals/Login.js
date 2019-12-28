/**
 * Script behind Login Modal
 * Global vars used: _lm_
 * @author Valentin Deaconu
 */ 
var _lm_ = new ModalWrapper("login-modal", "login-button", "login-close");

_lm_.addCacheItem("username", false);

function usernameChange() {
    _lm_.cache["login-username"] = document.getElementById("login-username").value;

    if (!/^([a-zA-Z0-9\_\.]+)$/.test(_lm_.cache["login-username"])) {
        document.getElementById("login-username-message").style = "visibility: visible; opacity: 1;";
        _lm_.cache["username"] = false;
    } else {
        document.getElementById("login-username-message").style = "";
        _lm_.cache["username"] = true;
    }
}

_lm_.addCacheItem("login-username", document.getElementById("login-username").value);

if (_lm_.cache["login-username"] != "") {
    usernameChange();
}

_lm_.addFormItem("login-username", usernameChange);


_lm_.addCacheItem("login-password", document.getElementById("login-password").value);
_lm_.addFormItem("login-password", function(){
    _lm_.cache["login-password"] = document.getElementById("login-password").value;
});

_lm_.addButtonItem("login-submit", function() {
    var username = _lm_.cache["login-username"];
    var password = _lm_.cache["login-password"];

    if (username == "" || password == "" || !_lm_.cache["username"]) {
        console.log(username == "");
        console.log(password == "");
        console.log(_lm_.cache["username"]);
        alert("Campurile sunt completate eronat.");
    } else {
        const sr = new ServerRequest(function(responseText) {
            if (Object.keys(responseText).length == 0) {
                alert("Numele de utilizator sau parola sunt gresite.");
            } else {
                var response = JSON.parse(responseText);
                AuthManager.logInUser(response);
                location.reload();
            }
        }, function(status, responseText) {
            console.log("HTTPStatus: " + status);
            console.log("Server response: " + responseText);
        });
    
        var api = "/api/v1/user/" + username + "/" + password;
        sr.send(RequestMethod.GET, api);
    }
});