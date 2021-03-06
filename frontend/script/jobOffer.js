
//var jobId = window.location.hash.substring(1,  window.location.hash.length);

const urlParams = new URLSearchParams(window.location.search);
var jobId = urlParams.get("jobId")

var job;
var jobDetailsFirst;
var priceHtml;

function parseDate(date) {
	var d = new Date(date);
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

	return months[d.getMonth() - 1] + " " + d.getDate() + " " + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes();
}

function listJobRoute(cvadruple){
    var job = cvadruple.first;
    var routeList = job.route.split(";");
	
	var html = 	"<table style='width:100%'>" +
				"<tr>" +
					"<th>Opriri</th>" +
				"</tr>";

    for (var i = 1; i < routeList.length - 1; i++){
        html += "<tr>" +
					"<td>" + routeList[i] + "</td>" + 
                "</tr>";
	}
	
    html += "</table>";
	
	return html;
}

function listJobDetails(cvadruple) {
	var job = cvadruple.first;
	var user = cvadruple.second;
	var userRate = cvadruple.third;
	var photos = cvadruple.fourth;

	if (photos.length == 0) {
		photos = [];
		photos.push("images/exemple_car.jpg");
	}

	var routeList = job.route.split(";");
	var startingLocation = routeList[0]
	var destination = routeList[routeList.length - 1];

	var others = "";
	if (routeList.length - 2 > 0) {
		for (var i = 1; i < routeList.length - 1; ++i) {
			others += routeList[i] + ", ";
		}
		others = others.substring(0, others.length - 2);
	}
    
    var tags = job.tags.split(";");
    var tagsHtml = "";
    for (var i = 0; i < tags.length; ++i){
        tagsHtml += "<li><a href='#'>" + tags[i] + "</a></li>";
    }
    
    var priceAfterSale = 0;
    if (job.sale > 0){
        priceAfterSale = job.price - ((job.sale / 100) * job.price);
    }
    priceHtml = (job.sale > 0) ?  ("<del>" + job.price + "</del>" + "<ins>" + priceAfterSale + "</ins") : job.price;
	
	var html = "<article class='post'>" +
		"<header>" + 
			"<div class='title'>" +
				"<h2>" + startingLocation + " -> " + destination + "</h2>" +
			"</div>" +
			"<div class='meta'>" +
				"<time class='published' datetime='" + parseDate(job.postDate) + "'>" + parseDate(job.postDate) + "</time>" +
				"<a href='#' class='author'>" + 
					"<span class='name'>" + user.username + "</span>" + 
					"<img src='" + user.profilePictureLink + "' alt='' width='50px' height='50px'/>" + 
				"</a>" +
			"</div>" +
		"</header>" +
		"<a class='image featured'>" +
			"<img src='" + photos[0].link + "' alt='' width='100%' height='350px'/>" + 
		"</a>" +
		"<table style=" +"width:100%" + ">" +
        "<tr>" +
            "<th>Descriere</th>" +
            "<td>" + job.description + "</td>" +
        "</tr>" +
        "<tr>" +
            "<th>Pret</th>" +
            "<td>" + priceHtml + "</td>" +
        "</tr>" +
        "<tr>" +
            "<th>Data plecarii</th>" +
            "<td>" + job.departureDate + "</td>" +
        "</tr>" +
        "<tr>" +
            "<th>Data sosirii</th>" +
            "<td>" + job.arrivalDate + "</td>" +
        "</tr>" +
        "</table>" +
		"<footer>" +
			"<ul class='actions'>" +
				"<li><button onclick='addOrder()'>Cumpara</button></li>" +
			"</ul>" + 
			"<ul class='stats'>" +
				tagsHtml +
				"<li><a href='#' class='icon solid fa-star'>" + userRate + "</a></li>" +
			"</ul>" +
		"</footer>" +
	"</article>";
    


	return html;
}


function clearHtml() {
	document.getElementById("job_post").innerHTML = "";
    document.getElementById("job_route").innerHTML = "";
}

function updateHtml(item) {
	document.getElementById("job_post").innerHTML = listJobDetails(item);
    document.getElementById("job_route").innerHTML = listJobRoute(item);
}


function renderJob() {
	clearHtml();
    updateHtml(job);
}


function addOrder(){
        var authUser = AuthManager.getAuthentificatedUser();
        if (authUser == null)
            console.log("Niciun user autentificat.");
        else {
            var orderJSON = '{' +
                '"userId":' + authUser.id + ', ' +
                '"jobId":' + job.first.id +
                '}';
            console.log(orderJSON);
            const xhrOrd = new XMLHttpRequest();

            xhrOrd.onreadystatechange = function() {
                console.log(this.readyState, this.status);
                if (this.readyState == 4 && this.status == 200) {
                    if (Object.keys(this.responseText).length == 0) {
                        alert("Campurile sunt completate invalid");
                    } else {
                        var response = JSON.parse(this.responseText);
                        if (response.code != 0) {
                            alert("Server response: " + response.message);
                        } else {
                           alert("Comanda adaugata cu succes!.");    
                        }
                    }
                } else if (this.status == 400) {
                        console.log(this.responseText);
                }
            }

            xhrOrd.open('post', SERVER_LINK + "/api/v1/order", true);
            xhrOrd.setRequestHeader("Accept", "application/json");
            xhrOrd.setRequestHeader("Content-type", "application/json");
            xhrOrd.send(orderJSON);
            var extrJSON = '{' +
                '"balance":' + priceHtml + ', ' +
                '"userId":' + authUser.id +
                '}';
            
            const xhrExtr = new XMLHttpRequest();

            xhrExtr.onreadystatechange = function() {
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

            xhrExtr.open('put', SERVER_LINK + "/api/v1/wallet/extragere/", true);
            xhrExtr.setRequestHeader("Accept", "application/json");
            xhrExtr.setRequestHeader("Content-type", "application/json");
            xhrExtr.send(extrJSON);
             var depJSON = '{' +
                '"balance":' + priceHtml + ', ' +
                '"userId":' + job.first.ownerId +
                '}';
            
            const xhrDep = new XMLHttpRequest();

            xhrDep.onreadystatechange = function() {
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

            xhrDep.open('put', SERVER_LINK + "/api/v1/wallet/depunere/" + job.first.ownerId, true);
            xhrDep.setRequestHeader("Accept", "application/json");
            xhrDep.setRequestHeader("Content-type", "application/json");
            xhrDep.send(depJSON);
        }
    
}
const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if (Object.keys(this.responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
		} else {
			job = JSON.parse(this.responseText);
            jobDetailsFirst = job.first;
			renderJob();
		}
	}
}

xhr.open('get', SERVER_LINK + '/api/v1/job/' + jobId, true);
xhr.send();