// EDIT PROFILE MODAL
var editmodal = document.getElementById("edit-modal");
var editbtn = document.getElementById("edit-button");
var editspan = document.getElementById("edit-close");

editbtn.onclick = function() {
    editmodal.style.display = "block";
    //loginmodal.style.display = "none";
    //forgotmodal.style.display = "none";
}
editspan.onclick = function() {
    editmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == editmodal) {
        editmodal.style.display = "none";
    }
}	

var _e_username = document.getElementById("edit-username");
var _e_password = document.getElementById("edit-password");
var _e_repassword = document.getElementById("edit-re-password");
var _e_phone = document.getElementById("edit-phone");
var _e_email = document.getElementById("edit-email");
var _e_profile_pic = document.getElementById("edit-profile-pic");
var _e_description = document.getElementById("edit-description");

var _e_username_v = false;
var _e_password_v = false;
var _e_phone_v = false;
var _e_email_v = false;
var x = 0;

_e_username.onchange = function() {
    x = 7;
    var text = _e_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("edit-username-message").style = "visibility: visible; opacity: 1;";
        _e_username_v = false;
        console.log(aici);
    } else {
        document.getElementById("edit-username-message").style = "";
        _e_username_v = true;
    }
}

_e_repassword.onchange = function() {
    var pw = _e_password.value;
    var rpw = _e_repassword.value;
    if (pw != rpw) {
        document.getElementById("edit-re-password-message").style = "visibility: visible; opacity: 1;";
        _e_password_v = false;
        
        console.log(aici);
    } else {
        document.getElementById("edit-re-password-message").style = "";
        _e_password_v = true;
    }
}

_e_phone.onchange = function() {
    var text = _e_phone.value;
    if (!/^([0-9]{10})$/.test(text)) {
        document.getElementById("edit-phone-message").style = "visibility: visible; opacity: 1;";
        _e_phone_v = false;
        
        console.log(232);
    } else {
        document.getElementById("edit-phone-message").style = "";
        _e_phone_v = true;
    }
}

_e_email.onchange = function() {
    var text = _e_email.value;
    if (!/^([a-zA-Z0-9\_\.]+[\@][a-zA-Z0-9]+[\.][a-zA-Z]+)$/.test(text)) {
        document.getElementById("edit-email-message").style = "visibility: visible; opacity: 1;";
        _e_email_v = false;
        
        console.log(aici);
    } else {
        document.getElementById("edit-email-message").style = "";
        _e_email_v = true;
    }
}

var userInfo;
var editsubmit = document.getElementById("submit-edit");
//console.log(x);
var userJson;
editsubmit.onclick = function() {
        
    //console.log(_r_username.value, _r_phone.value, _r_password.value, _r_email.value);
    //console.log(_r_username_v,_r_password_v,  _r_phone_v, _r_email_v);
    if (!_e_username_v || !_e_password_v || !_e_phone_v || !_e_email_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {

            const xhr = new XMLHttpRequest();
            const xhrUser = new XMLHttpRequest();
            
            
            xhrUser.onreadystatechange = function() {
            //console.log(this.readyState, this.status);
            if (this.readyState == 4 && this.status == 200) {
                if (Object.keys(this.responseText).length == 0) {
                    document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
                   
                } else {	
                    
                    userInfo = JSON.parse(this.responseText);
                    if (_e_username.value == null) _e_username = userInfo.username;
                    if (_e_password.value == null) _e_password = userInfo.password;
                    if (_e_phone.value == null) _e_phone = userInfo.phone;
                    if (_e_email.value == null) _e_email = userInfo.email;
                    if (_e_description.value == null) _e_description = userInfo.description;
                    userJson  = '{' + 
                                '"username":"' + _e_username.value + '", ' +
                                '"password":"' + _e_password.value + '", ' +
                                '"phone":"' + _e_phone.value + '", ' +
                                '"email":"' + _e_email.value + '", ' +
                                '"description":"' + _e_description.value + '", ' +
                                '"isProvider":' + userInfo.provider + ',' +
                                '"isAdmin":' + userInfo.admin + ',' +
                                '"profile_picture_link":"' + _e_profile_pic.value + '"' +
                                '}';
                   
                    //console.log(userJson);
                     xhr.open('put', SERVER_LINK + "/api/v1/user/" + userInfo.id, true);
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-type", "application/json");
                    xhr.send(userJson);
                }
            }
            }


            xhr.onreadystatechange = function() {
                //console.log(this.status);
                if (this.readyState == 4 && this.status == 200) {
                    if (Object.keys(this.responseText).length == 0) {
                        alert("Campurile sunt completate invalid");
                    } else {
                        //console.log(4243);
                        var response = JSON.parse(this.responseText);
                        if (response.code != 0) {
                            alert("Server response: " + response.message);
                        } else {
                           alert("Profil actualizat cu succes!");    
                        }
                    }
                } else {
                    console.log(this.responseText);
                }
                }
            
            xhrUser.open('get', SERVER_LINK + '/api/v1/user/' + userInfo.id, true);
            xhrUser.send();
            
           
        }
    }
    }
