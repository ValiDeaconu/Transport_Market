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

var loginsubmit = document.getElementById("login-submit");
loginsubmit.onclick =  function() {
    var username = document.getElementById("login-username").value;
    var password = document.getElementById("login-password").value;

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

// REGISTER MODAL
var registermodal = document.getElementById("register-modal");
var registerbtn = document.getElementById("register-button");
var registerspan = document.getElementById("register-close");

registerbtn.onclick = function() {
    registermodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
}
registerspan.onclick = function() {
    registermodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == registermodal) {
        registermodal.style.display = "none";
    }
}	

var _r_username = document.getElementById("register-username");
var _r_password = document.getElementById("register-password");
var _r_repassword = document.getElementById("register-re-password");
var _r_phone = document.getElementById("register-phone");
var _r_email = document.getElementById("register-email");
var _r_description = document.getElementById("register-description");
var _r_isProvider = document.getElementById("register-is-provider");

var registersubmit = document.getElementById("register-submit");
registersubmit.onclick = function() {
    if (_r_password.value != _r_repassword.value) {
        alert("Parolele nu coincid.");
    } else {
        var userJSON = '{' +
            '"username":"' + _r_username.value + '", ' +
            '"password":"' + _r_password.value + '", ' +
            '"phone":"' + _r_phone.value + '", ' +
            '"email":"' + _r_email.value + '", ' +
            '"description":"' + _r_description.value + '", ' +
            '"profile_picture_link":"http://localhost/images/avatar.jpg", ' +
            '"isProvider":' + _r_isProvider.checked + ', ' +
            '"isAdmin":false ' +
        '}';

        console.log(userJSON);

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
                       alert("Cont creat cu succes! Te poti autentifica.");    
                    }
                }
            } else if (this.status == 400) {
                var response = this.responseText;
                var message = response.errors[0].defaultMessage;
                alert("Server response: " + message);
            } else {
                console.log(this.status);
            }
        }

        xhr.open('post', SERVER_LINK + "/api/v1/user/", true);
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(userJSON);
    }
}



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