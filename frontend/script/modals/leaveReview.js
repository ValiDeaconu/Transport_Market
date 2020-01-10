// REVIEW MODAL
var reviewmodal = document.getElementById("review-modal");
var reviewbtn = document.getElementById("review-button");
var reviewspan = document.getElementById("review-close");

var userId = urlParams.get("userId");

reviewbtn.onclick = function() {
    reviewmodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
    addJobmodal.style.display = "none";
    depositmodal.style.display = "none";
    withdrawmodal.style.display = "none";
    editmodal.style.display = "none";
}
reviewspan.onclick = function() {
    reviewmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == reviewmodal) {
        reviewmodal.style.display = "none";
    }
}	

var _rv_description = document.getElementById("review-description");
var _rv_note = document.getElementById("review-note");

var _rv_note_v = false;

_rv_note.onchange = function() {
    var text = _rv_note.value;
    console.log(_rv_note.value);
    var note = parseInt(text, 10)
    if (!/^([1-9]{1})$/.test(text) || note < 1 || note > 5) {
        document.getElementById("review-note-message").style = "visibility: visible; opacity: 1;";
        _rv_note_v = false;
        console.log(343);
    } else {
        document.getElementById("review-note-message").style = "";
        _rv_note_v = true;
        console.log(121);
    }
}


var reviewsubmit = document.getElementById("review-submit");
reviewsubmit.onclick = function() {
    console.log(_rv_note_v, _rv_note.value);
    if (_rv_note_v == false) {
        alert("Campurile sunt completate invalid.");
    } else {
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {
            var reviewJSON = '{' +
                '"description":"' + _rv_description.value + '", ' +
                '"rate":' + _rv_note.value + ', ' +
                '"userId":' + userId +
            '}';
            console.log(reviewJSON);

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
                           alert("Recenzie adaugata cu succes.");    
                        }
                    }
                } else if (this.status == 400) {
                    var response = this.responseText;
                    var message = response.errors[0].defaultMessage;
                    alert("Server response: " + message);
                }
            }

            xhr.open('post', SERVER_LINK + "/api/v1/reviews/", true);
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(reviewJSON);
        }
    }
}