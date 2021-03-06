var authUser = AuthManager.getAuthentificatedUser();
if (authUser == null) {
	throw new Error("Nu a fost gasit un utilizator autentificat");
} else {
	function parseDate(date) {
		var d = new Date(date);
		const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

		return months[d.getMonth() - 1] + " " + d.getDate() + " " + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes();
	}

	function listUserPic(user){
		var html =  '<img src="' + user.profilePictureLink + '" alt="" width = "100%" height = "300px"/>';
		return html;
	}

	function listUser(user){
		var html = '<header style="padding:20px">' +
				'<table style="width:100%">' +
				'<tr>' +
						'<th>Nume</th>' +
						'<td>' + user.username + '</td>' +
				'</tr>' + 
				'<tr><th>Status</th>' +
				'<td>' + (user.provider == true ? 'Furnizor' : 'Client') + '</td>' + 
				'</tr>' +
				'<tr>' +
						'<th>Descriere</th>' +
						'<td>' + user.description + '</td>' +
				'</tr>' + 
				'<tr>' +
				'<th>Telefon</th>' +
				'<td>' + user.phone + '</td>' +
				'</tr>' +
				'<tr>' +
				'<th>Email</th>' +
				'<td>' + user.email + '</td>' +
				'</tr>' +
				'</table>' +
				'</header>';
		return html;
	}

	function listReviews(review) {
		
		var html = "<p>Nota : ";
		var rate = review.rate;
		for (var i = 0; i < rate; ++i){
			html += "<a class='icon solid fa-star'></a>";
		}
		html +=  "<p>" + review.description + "</p>";

		return html;
	}

	function listOrder(tuple) {
		var job = tuple.first;
		var photos = tuple.second;
		var user = tuple.third;
		

		if (photos.length == 0) {
			photos.push("http://localhost/images/exemple_car.jpg");
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
					"<h2>"+  startingLocation + " -> " + destination + "</a></h2>" +
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
					"<li><a href='jobOffer.php?jobId=" + job.id + "' class='button large'>Mai multe detalii</a></li>" +
				"</ul>" + 
				"<ul class='stats'>" +
					"<li><a href='#'>" + job.tags.split(";")[0] + "</a></li>" +
				"</ul>" +
			"</footer>" +
		"</article>";

		return html;
	}

	function listJobs(cvadruple) {
		var job = cvadruple.first;
		var user = cvadruple.second;
		var userRate = cvadruple.third;
		var photos = cvadruple.fourth;

		if (photos.length == 0) {
			photos.push("http://localhost/images/exemple_car.jpg");
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
					"<h2>"+  startingLocation + " -> " + destination + "</a></h2>" +
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
					"<li><a href='jobOffer.php?jobId=" + job.id + "' class='button large'>Editeaza oferta</a></li>" +
				"</ul>" + 
				"<ul class='stats'>" +
					"<li><a href='#'>" + job.tags.split(";")[0] + "</a></li>" +
					"<li><a href='#' class='icon solid fa-star'>" + userRate + "</a></li>" +
				"</ul>" +
			"</footer>" +
		"</article>";

		return html;
	}



	function clearReviewHtml() {
		document.getElementById("user_reviews").innerHTML = "";
	}

	function updateReviewHtml(item, index) {
		document.getElementById("user_reviews").innerHTML += listReviews(item);
	}


	function clearUserHtml() {
		document.getElementById("user_pic").innerHTML = "";
		document.getElementById("user_info").innerHTML = "";
	}


	function updateUserHtml(item) {
		document.getElementById("user_pic").innerHTML = listUserPic(item);
		document.getElementById("user_info").innerHTML = listUser(item);
	}

	function clearJobHtml() {
		document.getElementById("job_posts").innerHTML = "";
	}

	function updateJobHtml(item, index) {
		if (shouldDrawJobList)
			document.getElementById("job_posts").innerHTML += listJobs(item);
		else
			document.getElementById("job_posts").innerHTML += listOrder(item);
	}


	function nextReviewPressed() {
		reviewPage++;
		
		var prevButton = document.getElementById("review_prev_button");
		var nextButton = document.getElementById("review_next_button");

		if (prevButton.classList.contains("disabled"))
			prevButton.classList.remove("disabled");

		if (reviewPage == totalReviewPages - 1) 
			nextButton.classList.add("disabled");

		renderReviewList(reviewPage);
	}

	function prevReviewPressed() {
		reviewPage--;
		
		var prevButton = document.getElementById("review_prev_button");
		var nextButton = document.getElementById("review_next_button");

		if (nextButton.classList.contains("disabled"))
			nextButton.classList.remove("disabled");

		if (reviewPage == 0) 
			prevButton.classList.add("disabled");

		renderReviewList(reviewPage);
	}

	function nextPagePressed() {
		jobPage++;
		
		var prevButton = document.getElementById("pagination_prev_button");
		var nextButton = document.getElementById("pagination_next_button");

		if (prevButton.classList.contains("disabled"))
			prevButton.classList.remove("disabled");

		if (jobPage == totalJobPages - 1) 
			nextButton.classList.add("disabled");

		renderJobList(jobPage);
	}

	function prevPagePressed() {
		jobPage--;
		
		var prevButton = document.getElementById("pagination_prev_button");
		var nextButton = document.getElementById("pagination_next_button");

		if (nextButton.classList.contains("disabled"))
			nextButton.classList.remove("disabled");

		if (jobPage == 0) 
			prevButton.classList.add("disabled");

		renderJobList(jobPage);
	}

	function renderJobList(pageIndex) {
		clearJobHtml();
		jobList.forEach(function (value, index) {
			if (index >= itemsPerPage * pageIndex && index < itemsPerPage * pageIndex + itemsPerPage) {
				updateJobHtml(value);
			}
		});
	}

	function renderUserInfo(value) {
		clearUserHtml();
		updateUserHtml(value);
	}


	function renderReviewList(pageIndex) {
		clearReviewHtml();
		reviewList.forEach(function (value, index) {
			if (index >= itemsPerPage * pageIndex && index < itemsPerPage * pageIndex + itemsPerPage) {
				updateReviewHtml(value);
			}
		});
	}

	function clearJobListHtml() {
		var prevButton = document.getElementById("pagination_prev_button");
		var nextButton = document.getElementById("pagination_next_button");
		
		if (!prevButton.classList.contains("disabled")) 
			prevButton.classList.add("disabled");

		if (!nextButton.classList.contains("disabled")) 
			nextButton.classList.add("disabled");

		jobList = [];
		totalJobPages = jobList.length / itemsPerPage;
		renderJobList(0);
	}

	function setupJobListViewButtons() {
		var listMyJobs = document.getElementById("list-my-jobs");
		var listMyOrders = document.getElementById("list-my-orders");

		listMyJobs.onclick = function() {
			xhrPosts.open('get', SERVER_LINK + '/api/v1/job/jobsForUserId/' + authUser.id, true);
			xhrPosts.send();

			clearJobListHtml();
			changeJobListViewButton(false);
		}

		listMyOrders.onclick = function() {
			xhrPosts.open('get', SERVER_LINK + '/api/v1/order/userId/' + authUser.id, true);
			xhrPosts.send();

			clearJobListHtml();
			changeJobListViewButton(true);
		}
	}

	function changeJobListViewButton(isListMyJobs) {
		var listMyJobs = document.getElementById("list-my-jobs");
		var listMyOrders = document.getElementById("list-my-orders");

		shouldDrawJobList = !isListMyJobs;

		if (isListMyJobs) {
			listMyJobs.style = "display: block;";
			listMyOrders.style = "display: none;";
		} else {
			listMyJobs.style = "display: none;";
			listMyOrders.style = "display: block;";
		}
	}

	const itemsPerPage = 1;
	var totalReviewPages = 0;
	var totalJobPages = 0;
	var reviewPage = 0;
	var jobPage = 0;
	var reviewList = [];
	var jobList = [];
	var userInfo;
	var shouldDrawJobList = true;

	setupJobListViewButtons();

	const xhrPosts = new XMLHttpRequest();
	const xhrUserInfo = new XMLHttpRequest();
	const xhrUserReviews = new XMLHttpRequest();

	xhrPosts.onreadystatechange = function() {
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
				totalJobPages = jobList.length / itemsPerPage;
				renderJobList(0);

				if (totalJobPages > 1) {
					nextButton.classList.remove("disabled");
				}
			}
		}
	}

	xhrUserInfo.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
			if (Object.keys(this.responseText).length == 0) {
				document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
			} else {	
				userInfo = JSON.parse(this.responseText);
				renderUserInfo(userInfo);
			}
		}
	}

	xhrUserReviews.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (Object.keys(this.responseText).length == 0) {
				document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
			} else {
				var prevButton = document.getElementById("review_prev_button");
				var nextButton = document.getElementById("review_next_button");
				
				if (!prevButton.classList.contains("disabled")) 
					prevButton.classList.add("disabled");

				if (!nextButton.classList.contains("disabled")) 
					nextButton.classList.add("disabled");

				reviewList = JSON.parse(this.responseText);
				totalReviewPages = reviewList.length / itemsPerPage;
				renderReviewList(0);

				if (totalReviewPages > 1) {
					nextButton.classList.remove("disabled");
				}
			}
		}
	}


	xhrPosts.open('get', SERVER_LINK + '/api/v1/job/jobsForUserId/' + authUser.id, true);
	xhrPosts.send();
	xhrUserReviews.open('get', SERVER_LINK + '/api/v1/reviews/' + authUser.id, true);
	xhrUserReviews.send();
	xhrUserInfo.open('get', SERVER_LINK + '/api/v1/user/' + authUser.id, true);
	xhrUserInfo.send();
}