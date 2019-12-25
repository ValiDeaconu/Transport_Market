const SERVER_LINK = 'http://localhost:8080';
var jobId = window.location.hash.substring(1,  window.location.hash.length);
console.log(jobId);
var job = [];

function parseDate(date) {
	var d = new Date(date);
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

	return months[d.getMonth() - 1] + " " + d.getDate() + " " + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes();
}

function listJobRoute(cvadruple){
    var job = cvadruple.first;
    var routeList = job.route.split(";");
    var html = "<header style=" + "padding:20px" + ">" +
               "<table style=" + "width:100%" + ">" +
               "<tr>" +
               "<th>Opriri</th>" +
               "</tr>";              ;
    for (var i = 1; i < routeList.length - 1; i++){
        html += "<tr>" +
                "<td>" + routeList[i] + "</td>" + 
                +"</tr>";
    }
    html += "</table>" +
            "</header>";
    return html;
}

function listJobDetails(cvadruple) {
	var job = cvadruple.first;
	var user = cvadruple.second;
	var userRate = cvadruple.third;
	var photos = cvadruple.fourth;

	if (photos.length == 0) {
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
	
	var html = "<article class='post'>" +
		"<header>" + 
			"<div class='title'>" +
				"<h2><a href='todolink.html?jobid=" + job.id + "'>" + startingLocation + " -> " + destination + "</a></h2>" +
			"</div>" +
			"<div class='meta'>" +
				"<time class='published' datetime='" + parseDate(job.postDate) + "'>" + parseDate(job.postDate) + "</time>" +
				"<a href='#' class='author'>" + 
					"<span class='name'>" + user.username + "</span>" + 
					"<img src='" + user.profilePictureLink + "' alt='' width='50px' height='50px'/>" + 
				"</a>" +
			"</div>" +
		"</header>" +
		"<a href='todolink.html?jobid=" + job.id + "' class='image featured'>" +
			"<img src='" + photos[0].link + "' alt='' width='100%' height='350px'/>" + 
		"</a>" +
		"<table style=" +"width:100%" + ">" +
        "<tr>" +
            "<th>Descriere</th>" +
            "<td>" + job.description + "</td>" +
        "</tr>" +
        "<tr>" +
            "<th>Pret</th>" +
            "<td>" + job.price + "</td>" +
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
			
			"<ul class='stats'>" +
				"<li><a href='#'>" + job.tags.split(";")[0] + "</a></li>" +
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
	document.getElementById("job_post").innerHTML += listJobDetails(item);
    document.getElementById("job_route").innerHTML += listJobRoute(item);
}


function renderJob() {
	clearHtml();
    updateHtml(job);
}

const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if (Object.keys(this.responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
		} else {
			job = JSON.parse(this.responseText);
			renderJob();
		}
	}
}

xhr.open('get', SERVER_LINK + '/api/v1/job/' + jobId, true);
xhr.send();