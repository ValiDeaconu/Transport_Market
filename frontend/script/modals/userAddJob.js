// EDIT PROFILE MODAL
var addJobmodal = document.getElementById("add-job-modal");
var addJobbtn = document.getElementById("add-job-button");
var addJobspan = document.getElementById("add-job-close");

addJobbtn.onclick = function() {
    addJobmodal.style.display = "block";
    //loginmodal.style.display = "none";
    //forgotmodal.style.display = "none";
}
addJobspan.onclick = function() {
    addJobmodal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == addJobmodal) {
        addJobmodal.style.display = "none";
    }
}	

var _aj_description = document.getElementById("add-job-description");
var _aj_tag1 = document.getElementById("add-job-tag1");
var _aj_price = document.getElementById("add-job-price");
var _aj_tags = document.getElementById("add-job-tags");
var _aj_route = document.getElementById("add-job-route");
var _aj_links = document.getElementById("add-job-photo-links");
var _aj_departure = document.getElementById("add-job-departure-date");
var _aj_arrival = document.getElementById("add-job-arrival-date");

var _aj_price_v = true;

/*_r_price.onchange = function() {
    var text = _r_price.value;
    console.log(text);
    if (!/^([0-9]{10})$/.test(text)) {
        document.getElementById("add-job-price").style = "visibility: visible; opacity: 1;";
        _r_price_v = false;
        
        //console.log(aici);
    } else {
        document.getElementById("add-job-price-message").style = "";
        _r_price_v = true;
    }
}*/

var userInfo;
var addJobsubmit = document.getElementById("submit-add-job");
var jobPhotos;
var jobTags;
var tagsList;
var jobRoute;
var routeList;
var jobJSON;
var lastId;
var g2g = false;
addJobsubmit.onclick = function() {
    
    if ( !_aj_price_v) {
        alert("Campurile sunt completate invalid.");
    } else {
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {
            var response;
            var today = new Date();
            var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            var dateTime = date+' '+time;
                                    

            //jobPhotos = _aj_links.value;

            
            if (_aj_tag1.value == 1) jobTags = "Carpooling;";
            else if (_aj_tag1.value == 2) jobTags = "Ride-sharing;";
            else jobTags = "Freight transport;"
            tagsList = _aj_tags.value.split("\n");
            for(var i = 0; i < tagsList.length - 1; ++i){
                jobTags += tagsList[i] + ";";
            }
            jobTags += tagsList[tagsList.length - 1];
            

            jobRoute = "";
            routeList = _aj_route.value.split("\n");
            for(var i = 0; i < routeList.length - 1; ++i){
                jobRoute += routeList[i] + ";";
            }
            jobRoute += routeList[routeList.length - 1];
            
            jobPhotos = "";
            photosList = _aj_links.value.split("\n");
            for(var i = 0; i < routeList.length - 1; ++i){
                jobPhotos += photosList[i] + ";";
            }
            jobPhotos += photosList[photosList.length - 1];


             jobJSON = '{' +
            '"description":"' + _aj_description.value + '", ' +
            '"price":' + _aj_price.value + ', ' +
            '"route":"' + jobRoute + '", ' +
            '"tags":"' + jobTags + '", ' +
            '"postDate":"' + dateTime + '", ' +
            '"departureDate":"' + _aj_departure.value + '", ' +
            '"arrivalDate":"' + _aj_arrival.value + '", '+
            '"sale": 0 ,' +
            '"ownerId":' + authUser.id + 
            '}';
            console.log(jobJSON);
            const xhr = new XMLHttpRequest();
            
            


            xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (Object.keys(this.responseText).length == 0) {
                    alert("Campurile sunt completate invalid");
                } else {
                   response = JSON.parse(this.responseText);
                    console.log(response.code);
                    if (response.code != 0) {
                        alert("Server response: " + response.message);
                    } else {
                       g2g = true;
                       alert("Job adaugat cu succes! Te poti autentifica."); 
                       lastId = response.code;
                        xhrPhotos.open('post', SERVER_LINK + "/api/v1/photos/" + authUser.id, true);
                        xhrPhotos.setRequestHeader("Accept", "application/json");
                        xhrPhotos.setRequestHeader("Content-type", "application/json");
                        xhrPhotos.send(photoJSON);
                    }
                }
            } else if (this.status == 400) {
                        console.log(this.responseText);
                }
            }
            
            xhr.open('post', SERVER_LINK + "/api/v1/job/", true);
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(jobJSON);
            
            

            const xhrPhotos = new XMLHttpRequest();
            var photoJSON = '{' +
            '"link":"' + jobPhotos + '", ' + 
             '"jobId":' + lastId + 
            '}';
            console.log(photoJSON);
            
             xhrPhotos.onreadystatechange = function() {
                 console.log(this.readyState, this.status);
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.readyState, this.status);
                    if (Object.keys(this.responseText).length == 0) {
                        console.log(3434);
                        alert("Campurile sunt completate invalid");
                    } else {

                        console.log(photoJSON);
                        var response = JSON.parse(this.responseText);
                        if (response.code != 0) {
                            alert("Server response: " + response.message);
                        } else {
                           alert("Serviciu adaugat cu succes cu succes!");    
                        }
                    }
                }  else {
                        console.log(this.responseText);
                }
            }
            
            
            

    
    }
    }
}