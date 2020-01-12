var as_modal = document.getElementById("search-update-modal");
var as_btn = document.getElementById("search-update-button");
var as_span = document.getElementById("search-update-close");
var as_update = document.getElementById("update-submit");
var as_modInfo = document.getElementById("load-data-update");
var as_search = document.getElementById("search-submit");

as_btn.onclick = function(){
    as_modal.style.display = "block";
    as_update.style.display = "none";
    as_modInfo.style.display = "none";
    as_search.style.display = "block";
}

as_span.onclick = function(){
    as_modal.style.display = "none";
}

window.onclick = function(event){
    if(event.target == as_modal){
        as_modal.style.display = "none";
    }
}

var as_username = document.getElementById("search-update-username");
var as_username_b=true;

as_username.onchange = function(){
    var text = as_username.value;
    if (!/^([a-zA-Z0-9\_\.]+)$/.test(text)) {
        document.getElementById("search-username-message").style = "visibility: visible; opacity: 1;";
        as_username_b = false;
    } else {
        document.getElementById("search-username-message").style = "";
        as_username_b = true;
    }
}

var as_userInformation;

function listUserInfos(item){
        var au_html = '<div class="row gtr-uniform"> ' +
                '<div class="col-6 col-12-xsmall"> ' +
                '<div class="tooltip">' +
                '<input type="text" id="update-phone" placeholder=" '+ item.phone +' " />' +
                '<span class="tooltiptext" id="update-phone-message">'+
                'Numarul de telefon nu este valid.' +
                '</span> ' +
                '</div> '+
                '</div> '+
                '<div class="col-6 col-12-xsmall">' +
                '<div class="tooltip">' +
                '<input type="email" id="update-email" placeholder="'+item.email+'" />' +
                '<span class="tooltiptext" id="update-email-message">Adresa de email nu este valida.'+
                '</span> '+
                '</div> '+
                '</div> '+
                '</div> '+
                ' <li> '+
                '<textarea id="update-description" placeholder="'+ item.description +'"></textarea>'+
                '</li> '+
                '<li> '+
                '<div class="row gtr-uniform"> '+
                '<div class="col-6 col-12-xsmall"> ';
    
    if (!item.provider) au_html +='<input type="checkbox" id="update-is-provider" /> ';
    else au_html += '<input type="checkbox" id="update-is-provider" checked >';
    
    au_html +=  '<label for="update-is-provider">Functie de furnizor</label> '+
                '</div> ' +
                '<div class="col-6 col-12-xsmall"> ' ;
    
    if (!item.admin) au_html +='<input type="checkbox" id="update-is-admin" /> ';
    else au_html += '<input type="checkbox" id="update-is-admin" checked >';
    
    au_html += '<label for="update-is-admin">Functie de administrator </label> '+
    '</div> '+
    '</div> '+
    '</li> ';
    
    return au_html;
}


as_search.onclick = function(){
    if(!as_username_b) alert("Nume utilizator incorect");
    else{
        as_update.style.display = "block";
        as_search.style.display = "none";
        as_modInfo.style.display = "block";
        
        const xhr = new XMLHttpRequest();
        
        xhr.onreadystatechange = function(){
            if (this.readyState == 4 && this.status ==200){
                if (Object.keys(this.responseText).length == 0){
                    document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>"
                }else{
                     as_userInformation= JSON.parse(this.responseText);
                     document.getElementById("load-data-update").innerHTML = listUserInfos(as_userInformation);
                }
            }
        }
        xhr.open('get', SERVER_LINK + "/api/v1/user/name/" + as_username.value, true);
        xhr.send();
    }
}

as_update.onclick = function(){
    var au_id = as_userInformation.id;
    var au_new_username = document.getElementById("search-update-username").value;
    if( au_new_username == "") au_new_username=as_userInformation.username;
    
    console.log(au_new_username);
    
    var au_new_phone = document.getElementById("update-phone").value;
    if(au_new_phone == "") au_new_phone = as_userInformation.phone;
        
    var au_new_email = document.getElementById("update-email").value;
    if (au_new_email == "") au_new_email = as_userInformation.email;
    
    var au_new_description = document.getElementById("update-description").value;
    if (au_new_description == "") au_new_description = as_userInformation.description;
    
    
    var au_new_isprovider = document.getElementById("update-is-provider").checked;
    var au_new_isadmin = document.getElementById("update-is-admin").checked;
    
    var au_newUser = '{' +
        '"username": "'+au_new_username+'", '+
        '"password": "'+ as_userInformation.password + '", ' +
        '"phone":"'+au_new_phone+'", '+
        '"email":"'+au_new_email+'", '+
     '"description":"'+au_new_description+'", '+
        '"profile_picture_link":"'+as_userInformation.profilePictureLink+'", '+
        '"admin":'+au_new_isadmin+', '+
        '"provider":'+au_new_isprovider+
        '}';

    
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function(){
            
   //console.log(this.status);
        if (this.readyState == 4 && this.status == 200) {
            
            if (Object.keys(this.responseText).length == 0) {
                alert("Campurile sunt completate invalid");
            } else {
                var response = JSON.parse(this.responseText);
                if (response.code != 0) {
                    alert("Server response: " + response.message);
                } else {
                   alert("Cont modificat cu succes");    
                }
            }
        }
    }
   // console.log(au_id);
    xhr.open('put', SERVER_LINK + "/api/v1/user/" + au_id, true);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(au_newUser); 
    
    
}