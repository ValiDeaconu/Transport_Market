/**
 * Script behind Main Page of the web platform
 * Frontend available at 'index.php'
 * @author Valentin Deaconu
 */
class MainPage {
	constructor(jobsPerPage) {
		// Constants
		this.jobsPerPage = jobsPerPage;

		// Vars
		this.totalPages = 0;
		this.page = 0;
		this.jobList = [];
		
		// Filters
		this.availability = "";
		this.min_price = 0;
		this.max_price = 30000;
        this.transport_type = "";
        
        this.request = new ServerRequest(__mainpage_response_success_func, __mainpage_response_failure_func);

        // Page Linking
        var headerSearch = document.getElementById("header-search");
        headerSearch.addEventListener("keyup", function(event) {
            if (event.keyCode == 13) {
                // if enter is pressed
                event.preventDefault();

                __mainpage_filter_func(headerSearch.value);

                window.setTimeout(function() {
                    document.getElementById("search").classList.remove("visible");
                }, 100);
            }
        });

        var menuSearch = document.getElementById("menu-search");
        menuSearch.addEventListener("keyup", function(event) {
            if (event.keyCode == 13) {
                // if enter is pressed
                event.preventDefault();

                __mainpage_filter_func(menuSearch.value);
            }
        });

        document.getElementById("filter-submit").onclick = function() { __mainpage_filter_func(); };

        document.getElementById("pagination_prev_button").onclick = function() { __mainpage_prev_page_func(); };
        document.getElementById("pagination_next_button").onclick = function() { __mainpage_next_page_func(); };

        document.getElementById("header-search-block").style = "visibility: visible;";
        document.getElementById("menu-search-block").style = "display: block;";
        
        this.request.send(RequestMethod.GET, "/api/v1/job");
    }

    filterResults(searchFilters = "__empty__") {
        var calendar = document.getElementById("filter-calendar");
        var priceMin = document.getElementById("filter-price-min");
        var priceMax = document.getElementById("filter-price-max");
        var transportType = document.getElementById("filter-type");
    
        this.availability = calendar.value;
        if (this.availability == "")
            this.availability = "__empty__";
    
        this.min_price = priceMin.value;
        if (this.min_price == "")
            this.min_price = 0;
    
        this.max_price = priceMax.value;
        if (this.max_price == "")
            this.max_price = 30000;
    
        switch (transportType.value) {
            case "0":
                this.transport_type = "__empty__";
                break;
            case "1":
                this.transport_type = "carpooling";
                break;
            case "2":
                this.transport_type = "ride-sharing";
                break;
            case "3": 
                this.transport_type = "freight";
                break;
            default:
                this.transport_type = "__empty__";
                break;
        }
    
        if (searchFilters == "")
            searchFilters = "__empty__";
    
        var api = "/api/v1/job/" + this.availability + "/" + this.min_price + "/" + this.max_price + "/" + this.transport_type + "/" + searchFilters;
        this.request.send(RequestMethod.GET, api);
    }

    responseSuccess(responseText) {
        if (Object.keys(responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";	
		} else {
			var prevButton = document.getElementById("pagination_prev_button");
			var nextButton = document.getElementById("pagination_next_button");
			
			if (!prevButton.classList.contains("disabled")) 
				prevButton.classList.add("disabled");

			if (!nextButton.classList.contains("disabled")) 
				nextButton.classList.add("disabled");

            this.jobList = JSON.parse(responseText);
			this.totalPages = this.jobList.length / this.jobsPerPage;
            
            this.render(0);

			if (this.totalPages > 1) {
				nextButton.classList.remove("disabled");
			}
		}
    }

    responseFailure(httpStatus, responseText) {
        console.log("HTTPStatus: " + httpStatus);
        console.log("Server response: " + responseText);
    }

    write(item) {
        var job = item.first;
        var user = item.second;
        var userRate = item.third;
        var photos = item.fourth;

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
        
        var htmlCode = "<article class='post'>" +
            "<header>" + 
                "<div class='title'>" +
                    "<h2><a href='jobOffer.php?jobId=" + job.id + "'>" + startingLocation + " -> " + destination + "</a></h2>" +
                    "<p>" + others + "<p>" +
                "</div>" +
                "<div class='meta'>" +
                    "<time class='published' datetime='" + parseDate(job.postDate) + "'>" + parseDate(job.postDate) + "</time>" +
                    "<a href='userProfile.php?userId=" + user.id + "' class='author'>" + 
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
                    "<li><a>" + job.tags.split(";")[0] + "</a></li>" +
                    "<li><a class='icon solid fa-star'>" + userRate + "</a></li>" +
                "</ul>" +
            "</footer>" +
        "</article>";

        document.getElementById("main_posts").innerHTML += htmlCode;
    }

    clear() {
        document.getElementById("main_posts").innerHTML = "";
    }

    render(pageIndex) {
        this.clear();

        var max = function(a, b) { return a > b ? a : b; }
        var min = function(a, b) { return a <= b ? a : b; }

        var start = max(this.jobsPerPage * pageIndex, 0);
        var end = min(this.jobsPerPage * pageIndex + this.jobsPerPage, this.jobList.length);

        for (var i = start; i < end; ++i) {
            this.write(this.jobList[i]);
        }
    }

    processNextPage() {
        this.page++;

        var prevButton = document.getElementById("pagination_prev_button");
        var nextButton = document.getElementById("pagination_next_button");

        if (prevButton.classList.contains("disabled"))
            prevButton.classList.remove("disabled");

        if (this.page == this.totalPages - 1) 
            nextButton.classList.add("disabled");

        this.render(this.page);
    }

    processPrevPage() {
        this.page--;
	
        var prevButton = document.getElementById("pagination_prev_button");
        var nextButton = document.getElementById("pagination_next_button");

        if (nextButton.classList.contains("disabled"))
            nextButton.classList.remove("disabled");

        if (this.page == 0) 
            prevButton.classList.add("disabled");

        this.render(this.page);
    }
}

// Class Init
var _mainpage_Entity = new MainPage(10);

// Wrapper functions
function __mainpage_response_success_func(responseText) {
    _mainpage_Entity.responseSuccess(responseText);
}

function __mainpage_response_failure_func(httpStatus, responseText) {
    _mainpage_Entity.responseFailure(httpStatus, responseText);
}

function __mainpage_filter_func(searchFilters = "__empty__") {
    _mainpage_Entity.filterResults(searchFilters);
}

function __mainpage_next_page_func() {
    _mainpage_Entity.processNextPage();
}

function __mainpage_prev_page_func() {
    _mainpage_Entity.processPrevPage();
}