var authUser = AuthManager.getAuthentificatedUser();
var adminId=authUser.id;
var adminName=authUser.username;

var adminGo=document.getElementById("");

function listAdmin(admin){
    //console.log(admin);
    var html='<a href="#" class="author"><span class="name">' + admin.username + '</span><img src="' + admin.profilePictureLink +'" alt="" /></a>'
    return html;
}

function updateAdminHtml(item){
    document.getElementById("admin_info").innerHTML = listAdmin(item);
}

function clearAdminHtml() {
	document.getElementById("admin_info").innerHTML = "";
}

function renderAdminInfo(value) {
	clearAdminHtml();
    updateAdminHtml(value);
}

var adminInfo;

const xhrAdminInfo = new XMLHttpRequest();

xhrAdminInfo.onreadystatechange = function() {
if (this.readyState == 4 && this.status == 200) {
    //console.log(this.status);
		if (Object.keys(this.responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";
		} else {
			adminInfo = JSON.parse(this.responseText);
			renderAdminInfo(adminInfo);
		}
	}
}

xhrAdminInfo.open('get', SERVER_LINK + '/api/v1/user/' + adminId, true);
xhrAdminInfo.send();

