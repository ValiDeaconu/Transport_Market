// WITHDRAW MODAL
var withdrawmodal = document.getElementById("withdraw-modal");
var withdrawbtn = document.getElementById("withdraw-button");
var withdrawspan = document.getElementById("withdraw-close");

withdrawbtn.onclick = function() {
    withdrawmodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
}
withdrawspan.onclick = function() {
    withdrawmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == withdrawmodal) {
        withdrawmodal.style.display = "none";
    }
}	

var _r_wowner = document.getElementById("withdraw-owner");
var _r_wcard_number = document.getElementById("withdraw-card-number");
var _r_wCVV = document.getElementById("withdraw-CVV");
var _r_wamount = document.getElementById("withdraw-amount");

var _r_wowner_v = false;
var _r_wcard_number_v = false;
var _r_wCVV_v = false;
var _r_wamount_v = false;

_r_wowner.onchange = function() {
    console.log(12);
    var text = _r_wowner.value;
    _r_wowner_v = true;

}

_r_wcard_number.onchange = function() {
    _r_wcard_number_v = true;
}

_r_wCVV.onchange = function() {
   _r_wCVV_v = true;
}

_r_wamount.onchange = function() {
    _r_wamount_v = true;
}

var withdrawsubmit = document.getElementById("submit-withdraw");
withdrawsubmit.onclick = function() {
    console.log(_r_wowner.value, _r_wamount.value, _r_wCVV.value, _r_wcard_number.value);
    if (!_r_wowner_v || !_r_wCVV_v || !_r_wcard_number_v || !_r_wamount_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {
            var walletJSON = '{' +
                '"balance":' + _r_wamount.value + ', ' +
                '"userId":' + authUser.id +
                '}';
            console.log(walletJSON);

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
                        console.log(this.responseText);
                }
            }

            xhr.open('put', SERVER_LINK + "/api/v1/wallet/extragere/", true);
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(walletJSON);
        }
    }
}