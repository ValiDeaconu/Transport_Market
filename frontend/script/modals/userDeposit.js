// DEPOSIT MODAL
var depositmodal = document.getElementById("deposit-modal");
var depositbtn = document.getElementById("deposit-button");
var depositspan = document.getElementById("deposit-close");

depositbtn.onclick = function() {
    depositmodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
}
depositspan.onclick = function() {
    depositmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == depositmodal) {
        depositmodal.style.display = "none";
    }
}	

var _d_owner = document.getElementById("deposit-owner");
var _d_card_number = document.getElementById("deposit-card-number");
var _d_CVV = document.getElementById("deposit-CVV");
var _d_amount = document.getElementById("deposit-amount");

var _d_owner_v = false;
var _d_card_number_v = false;
var _d_CVV_v = false;
var _d_amount_v = false;

_d_owner.onchange = function() {
    var text = _d_owner.value;
    if (!/^([a-zA-Z\ ]*)$/.test(text)) {
        document.getElementById("deposit-owner-message").style = "visibility: visible; opacity: 1;";
        _d_owner_v = false;
    } else {
        document.getElementById("deposit-owner-message").style = "";
        _d_owner_v = true;
    }

}

_d_card_number.onchange = function() {
    var text = _d_card_number.value;
    if (!/^([0-9]{16})$/.test(text)) {
        document.getElementById("deposit-card-number-message").style = "visibility: visible; opacity: 1;";
        _d_card_number_v = false;
    } else {
        document.getElementById("deposit-card-number-message").style = "";
        _d_card_number_v = true;
    }
}

_d_CVV.onchange = function() {
    var text = _d_CVV.value;
    if (!/^([0-9]{3})$/.test(text)) {
        document.getElementById("deposit-CVV-message").style = "visibility: visible; opacity: 1;";
        _d_CVV_v = false;
    } else {
        document.getElementById("deposit-CVV-message").style = "";
        _d_CVV_v = true;
    }
}

_d_amount.onchange = function() {
    _d_amount_v = true;
}

var depositsubmit = document.getElementById("submit-deposit");
depositsubmit.onclick = function() {
    console.log(_d_owner.value, _d_amount.value, _d_CVV.value, _d_card_number.value);
    if (!_d_owner_v || !_d_CVV_v || !_d_card_number_v || !_d_amount_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {
            var walletJSON = '{' +
                '"balance":' + _d_amount.value + ', ' +
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
                           alert("Depunere realizata cu succes.");    
                        }
                    }
                } else if (this.status == 400) {
                        console.log(this.responseText);
                }
            }

            xhr.open('put', SERVER_LINK + "/api/v1/wallet/depunere/" + authUser.id, true);
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(walletJSON);
        }
    }
}