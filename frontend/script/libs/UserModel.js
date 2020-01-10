/**
 * Model for User
 */
class User {
    constructor() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.phone = "";
        this.email = "";
        this.description = "";
        this.provider = false;
        this.admin = false;
        this.profilePictureLink = "";
    }

    static createUser(user) {
        var newUser = new User();

        newUser.id = user.id;
        newUser.username = user.username;
        newUser.password = user.password;
        newUser.phone = user.phone;
        newUser.email = user.email;
        newUser.description = user.description;
        newUser.provider = user.provider;
        newUser.admin = user.admin;
        newUser.profilePictureLink = user.profilePictureLink;

        return newUser;
    }

    static createUser(id, username, password, phone, email, description, provider, admin, profilePictureLink) {
        var newUser = new User();

        newUser.id = id;
        newUser.username = username;
        newUser.password = password;
        newUser.phone = phone;
        newUser.email = email;
        newUser.description = description;
        newUser.provider = provider;
        newUser.admin = admin;
        newUser.profilePictureLink = profilePictureLink;

        return newUser;
    }
}