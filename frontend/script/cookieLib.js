class CookieManager {

    /**
     * Method used to set a cookie with a value, which expires after a number of days
     * @param {String} name name of the cookie 
     * @param {String} value value of the cookie
     * @param {Integer} days number of days after cookie expires
     */
    static createCookie(name, value, days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "expires=" + date.toUTCString();
        document.cookie = name + "=" + (value || "") + ";" + expires + ";path=/";
    }

    /**
     * Method used to read the value of an already set cookie
     * @param {String} name name of the cookie 
     * @returns {String} value of the cookie
     */
    static readCookie(name) {
        var nameWithEq = name + "=";
        var cookies = document.cookie.split(";");
        for (var i = 0; i < cookies.length; ++i) {
            var cookie = cookies[i];
            while (cookie.charAt(0) == ' ')
                cookie = cookie.substring(1);

            if (cookie.indexOf(nameWithEq) == 0)
                return cookie.substring(nameWithEq.length, cookie.length);
        }

        return null;
    }

    /**
     * Boolean function which verifies if a cookie is set
     * @param {String} name name of the cookie
     * @returns {Boolean} true if the cookie is set and non-empty
     */
    static isCookieSet(name) {
        return getCookie(name) != null;
    }
    
    /**
     * Remove an existing cookie
     * @param {String} name name of the cookie
     */
    static removeCookie(name) {
        document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    }

    /**
     * Method used to clear all cookies used by this web platform
     */
    static clearAllCookies() {
        const cookieList = [ 
            "user.name",
            "user.id",
            "user.isAdmin",
            "user.isProvider"
        ];

        for (var i = 0; i < cookieList.length; ++i) {
            CookieManager.removeCookie(cookieList[i]);
        }

        location.reload();
    }
}


class AuthManager {
    /**
     * Method used to craete useful cookies and log a user in
     */
    static logInUser(user) {
        CookieManager.createCookie("user.id", user.id, 1);
        CookieManager.createCookie("user.name", user.username, 1);
        CookieManager.createCookie("user.isProvider", user.provider, 1);
        CookieManager.createCookie("user.isAdmin", user.admin, 1);
    }
    
    /**
     * Method used to remove cookies and log out authentificated user
     */
    static logOutUser() {
        CookieManager.removeCookie("user.id");
        CookieManager.removeCookie("user.name");
        CookieManager.removeCookie("user.isProvider");
        CookieManager.removeCookie("user.isAdmin");
    }

    static getAuthentificatedUser() {
        var userId = CookieManager.readCookie("user.id");
        var userName = CookieManager.readCookie("user.name");
        var userIsProvider = CookieManager.readCookie("user.isProvider");
        var userIsAdmin = CookieManager.readCookie("user.isAdmin");

        if (userId != null && userName != null && userIsProvider != null && userIsAdmin != null) {
            var user = User.createUser(userId, userName, "", "", "", "", userIsProvider, userIsAdmin, "");
            return user;
        }
    
        return null;
    }
}



