var d_delmodal = document.getElementById("delete-modal");
var d_delbtn = document.getElementById("delete-button");
var d_delspan = document.getElementById("delete-close");


d_delbtn.onclick = function() {
    d_delmodal.style.display = "block";
}

d_delspan.onclick=function(){
    d_delmodal.style.display = "none";
}

window.onclick = function(event){
    if (event.target == d_delmodal){
        d_delmodal.style.display = "none";
    }
}

var d_username = document.getElementById("delete-user-username");

var _d_username_v=false;

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


deletesubmit.onclick = function(){
    
    var dd_username = d_username.value;
    if (dd_username = "" || ! _d_username_v){
        alert("Campurile sunt completate eronat");
    }else{
        const xhr = new XMLHttpRequest();

        xhr.open('DELETE', SERVER_LINK + "/api/v1/user/deleteByUsername/" + dd_username, true);
        xhr.onload = function(){
            var users = JSON.parse(xhr.responseText);
            if( this.readyState == 4 && this.status == 200){
                console.table(users);
            }else{
                console.error(users);
            }
        }

        xhr.send();
        
    }
}



