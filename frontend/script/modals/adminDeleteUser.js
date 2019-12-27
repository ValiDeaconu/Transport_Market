var delmodal = document.getElementById("delete-modal");
var delbtn = document.getElementById("delete-button");
var delspan = document.getElementById("delete-close");


delbtn.onclick = function() {
    delmodal.style.display = "block";
    loginmodal.style.display = "none";
    forgotmodal.style.display = "none";
    addmodal.style.display = "none";
}

delspan.onclick=function(){
    delmodal.style.display = "none";
}

window.onclick = function(event){
    if (event.target == delmodal){
        delmodal.style.display = "none";
    }
}

var d_username = document.getElementById("delete-user-username");

var _d_username_v=false;
var isCorect=false;

d_username.onchange = function(){
    var text = d_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("del-username-message").style = "visibility: visible; opacity: 1;";
        _d_username_v = false;
    } else {
        document.getElementById("del-username-message").style = "";
        _d_username_v = true;
    }
}

var deletesubmit = document.getElementById("delete-submit");
deletesubmit.onclik = function(){
    console.log("12345");
    var username = d_username.value;
    
    if (username = "" || ! _d_username_v){
        alert("Campurile sunt completate eronat");
    }else{
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if( this.readyState == 4 && this.status == 200){
            if (Object.keys(this.responseText).length == 0) {
                alert("Numele utilizatorului nu exista.");
            } else {
                var response = JSON.parse(this.responseText);
                if (response.code != 0) {
                    alert("Server response: " +response.message);
                } else {
                   alert("Stergere efectuata cu succes!");    
                }
              }
            }else if (this.status == 400){
                var response = this.responseText;
                var message = response.errors[0].defaultMessage;
                alert("Server response: " + message);
            }
        }
        
        xhr.open('delete', SERVER_LINK + "/api/v1/user/" + username, true);
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(userJSON);
        
    }
}



