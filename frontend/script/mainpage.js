function parseDate(date) {
	var d = new Date(date);
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

	return months[d.getMonth() - 1] + " " + d.getDate() + " " + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes();
}

function listJob(cvadruple) {
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
				"<h2><a href='jobOffer.php?jobId=" + job.id + "'>" + startingLocation + " -> " + destination + "</a></h2>" +
				"<p>" + others + "<p>" +
			"</div>" +
			"<div class='meta'>" +
				"<time class='published' datetime='" + parseDate(job.postDate) + "'>" + parseDate(job.postDate) + "</time>" +
				"<a href='#' class='author'>" + 
					"<span class='name'>" + user.username + "</span>" + 
					"<img src='" + user.profilePictureLink + "' alt='' width='50px' height='50px'/>" + 
				"</a>" +
			"</div>" +
		"</header>" +
		"<a href='jobOffer.php?jobId=" + job.id + "' class='image featured'>" +
			"<img src='" + photos[0].link + "' alt='' width='100%' height='350px'/>" + 
		"</a>" +
		"<p>" + job.description.substring(0, (job.description.length > 300 ? 300 : job.description.length)) + "</p>" +
		"<footer>" +
			"<ul class='actions'>" +
				"<li><a href='jobOffer.php?jobId=" + job.id + "' class='button large'>See offer</a></li>" +
			"</ul>" + 
			"<ul class='stats'>" +
				"<li><a href='#'>" + job.tags.split(";")[0] + "</a></li>" +
				"<li><a href='#' class='icon solid fa-star'>" + userRate + "</a></li>" +
			"</ul>" +
		"</footer>" +
	"</article>";

	return html;
}

function clearHtml() {
	document.getElementById("main_posts").innerHTML = "";
}

function updateHtml(item, index) {
	document.getElementById("main_posts").innerHTML += listJob(item);
}

function nextPagePressed() {
	page++;
	
	var prevButton = document.getElementById("pagination_prev_button");
	var nextButton = document.getElementById("pagination_next_button");

	if (prevButton.classList.contains("disabled"))
		prevButton.classList.remove("disabled");

	if (page == totalPages - 1) 
		nextButton.classList.add("disabled");

	renderJobList(page);
}

function prevPagePressed() {
	page--;
	
	var prevButton = document.getElementById("pagination_prev_button");
	var nextButton = document.getElementById("pagination_next_button");

	if (nextButton.classList.contains("disabled"))
		nextButton.classList.remove("disabled");

	if (page == 0) 
		prevButton.classList.add("disabled");

	renderJobList(page);
}

function renderJobList(pageIndex) {
	clearHtml();
	jobList.forEach(function (value, index) {
		if (index >= jobsPerPage * pageIndex && index < jobsPerPage * pageIndex + jobsPerPage) {
			updateHtml(value);
		}
	});
}

function filterResults() {
	var calendar = document.getElementById("filter-calendar");
	var priceMin = document.getElementById("filter-price-min");
	var priceMax = document.getElementById("filter-price-max");
	var transportType = document.getElementById("filter-type");

	availability = calendar.value;
	if (availability == "")
		availability = "__empty__";

	min_price = priceMin.value;
	if (min_price == "")
		min_price = 0;

	max_price = priceMax.value;
	if (max_price == "")
		max_price = 30000;

	switch (transportType.value) {
		case "0":
			transport_type = "__empty__";
			break;
		case "1":
			transport_type = "carpooling";
			break;
		case "2":
			transport_type = "ride-sharing";
			break;
		case "3": 
			transport_type = "freight";
			break;
		default:
			transport_type = "__empty__";
			break;
	}

	xhr.open('get', SERVER_LINK + '/api/v1/job/' + availability + '/' + min_price + '/' + max_price + '/' + transport_type + '', true);
	xhr.send();
}

const jobsPerPage = 1;
var totalPages = 0;
var page = 0;
var jobList = [];

var availability = "";
var min_price = 0;
var max_price = 30000;
var transport_type = "";

const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if (Object.keys(this.responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
		} else {
			var prevButton = document.getElementById("pagination_prev_button");
			var nextButton = document.getElementById("pagination_next_button");
			
			if (!prevButton.classList.contains("disabled")) 
				prevButton.classList.add("disabled");

			if (!nextButton.classList.contains("disabled")) 
				nextButton.classList.add("disabled");

			jobList = JSON.parse(this.responseText);
			totalPages = jobList.length / jobsPerPage;
			renderJobList(0);

			if (totalPages > 1) {
				nextButton.classList.remove("disabled");
			}
		}
	}
}

xhr.open('get', SERVER_LINK + '/api/v1/job', true);
xhr.send();