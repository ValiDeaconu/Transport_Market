package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.UserDal;
import org.transexpress.snap.misc.Checker;
import org.transexpress.snap.misc.Formatter;
import org.transexpress.snap.model.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserDal userDal;

    @Autowired
    public UserService(@Qualifier("mysql_users") UserDal userDal) {
        this.userDal = userDal;
    }

    public int addUser(User user) {
        if (!user.isWellFormed())
            return -1;

        User bracedUser = braceUser(user);

        return userDal.insertUser(bracedUser);
    }

    public List<User> getAllUsers() {
        return userDal.selectAllUsers();
    }

    public Optional<User> getUserByID(int id) {
        if (!Checker.getInstance().checkId(id))
            return Optional.empty();

        return userDal.selectUserByID(id);
    }

    public int deleteUser(int id) {
        if (!Checker.getInstance().checkId(id))
            return -1;

        return userDal.deleteUserByID(id);
    }

    public int updateUser(int id, User newUser) {
        Checker checker = Checker.getInstance();

        if (!checker.checkId(id) || !newUser.isWellFormed())
            return -1;

        User bracedUser = braceUser(newUser);

        return userDal.updateUserByID(id, bracedUser);
    }

    private User braceUser(User user) {
        Formatter formatter = Formatter.getInstance();
        String bracedPassword = formatter.secureQuotes(user.getPassword());
        String bracedDescription = formatter.secureQuotes(user.getDescription());

        return new User(user.getId(),
                user.getUsername(),
                bracedPassword,
                user.getPhone(),
                user.getEmail(),
                bracedDescription,
                user.getProfilePictureLink(),
                user.isProvider(),
                user.isAdmin());
    }

}
