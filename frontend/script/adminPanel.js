var a_authUser = AuthManager.getAuthentificatedUser();
var a_adminId = a_authUser.id;

function listAdmin(admin){
    var a_html='<a href="#" class="author"><span class="name">' + admin.username + '</span><img src="' + admin.profilePictureLink +'" alt="" /></a>'
    return a_html;
}

function listUsersNames(usersInfo){
    var a1_html = " ";
    for(var i=0; i<usersInfo.length; ++i){
        var a_user_name = usersInfo[i].username;
        var a_user_email = usersInfo[i].email;
        var a_user_phone = usersInfo[i].phone;
        var a_user_description = usersInfo[i].description;
        var a_user_provider = usersInfo[i].provider;
        if (a_user_provider) a_user_provider="da";
        else a_user_provider = "nu";
        var a_user_admin = usersInfo[i].admin;
        if (a_user_admin) a_user_admin="da";
        else a_user_admin = "nu";
        a1_html +='<tr> '+
                '<th>' + a_user_name +
                '</th> '+
                '<th>' + a_user_email +
                '</th> '+
                '<th>' + a_user_phone +
                '</th> '+
                '<th>' + a_user_description +
                '</th> '+
                '<th>' + a_user_provider +
                '</th> '+
                '<th>' + a_user_admin +
                '</th> '+
                ' </tr>'
    }
    return a1_html;
}

function updateAdminHtml(item){
    document.getElementById("admin_info").innerHTML = listAdmin(item);
}

function updateUsersNamesInfoHtml(item){
    document.getElementById("users-table").innerHTML=listUsersNames(item);
}

function clearAdminHtml() {
	document.getElementById("admin_info").innerHTML = "";
}
/*
function clearUsersInfoHtml(){
    document.getElementById("container").innerHTML = "";
}
*/

function renderAdminInfo(value) {
    clearAdminHtml();
    updateAdminHtml(value);
}

function renderUsersInfoSearch(value){
   //clearUsersInfoHtml()
    updateUsersNamesInfoHtml(value);
}

var a_adminInfo;
var a_usersNamesInfo

const xhrAdminInfo = new XMLHttpRequest();
const xhrUsersNamesInfo = new XMLHttpRequest();

xhrAdminInfo.onreadystatechange = function() {
if (this.readyState == 4 && this.status == 200) {
   // console.log(this.status);
		if (Object.keys(this.responseText).length == 0) {
			document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>";
		} else {
			a_adminInfo = JSON.parse(this.responseText);
			renderAdminInfo(a_adminInfo);
		}
	}
}

xhrUsersNamesInfo.onreadystatechange = function(){
    if (this.readyState == 4 && this.status ==200){
        //console.log(this.status);
        if (Object.keys(this.responseText).length == 0){
            document.getElementById("main").innerHTML = "<h2>Could not fetch any service.</h2>"
        }else{
            a_usersNamesInfo = JSON.parse(this.responseText);
            renderUsersInfoSearch(a_usersNamesInfo);
        }
    }
}


xhrAdminInfo.open('get', SERVER_LINK + '/api/v1/user/' + a_adminId, true);
xhrAdminInfo.send();

xhrUsersNamesInfo.open('get', SERVER_LINK + '/api/v1/user', true);
xhrUsersNamesInfo.send();

