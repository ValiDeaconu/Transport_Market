// PAGE CHANGES BY AUTHENTIFCATED USER STATUS
var authUser = AuthManager.getAuthentificatedUser();

if (authUser != null) {
    document.getElementById("user-profile-button").innerHTML = "<a href='userOwnProfile.php'>" + authUser.username + "</a>";

    document.getElementById("user-available-actions").style = "display: block;";
    document.getElementById("user-view-my-profile-button").innerHTML = "<a href='userOwnProfile.php' class='button large fit'>" + authUser.username + "</a>";
    
    if (authUser.admin != null) {
        document.getElementById("admin-panel").innerHTML = "<a href='adminPanel.php'>Platform Settings</a>";

        document.getElementById("admin-available-actions").style = "display: block;";
        document.getElementById("admin-platform-settings").innerHTML = "<a href='adminPanel.php' class='button large fit'>Platform Settings</a>";
    }
} else {
    document.getElementById("visitor-available-actions").style = "display: block;";
}

function logMeOut() {
    AuthManager.logOutUser();
    location.reload();
}