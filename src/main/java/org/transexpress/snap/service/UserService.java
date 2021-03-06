package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.UserDal;
import org.transexpress.snap.misc.Checker;
import org.transexpress.snap.misc.Formatter;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.model.User;
import org.transexpress.snap.model.UserReview;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserDal userDal;

    //services
    private final UserReviewService userReviewService;

    @Autowired
    public UserService(@Qualifier("mysql_users") UserDal userDal,
                       UserReviewService userReviewService) {
        this.userDal = userDal;
        this.userReviewService = userReviewService;
    }

    public ResponseMessage addUser(User user) {
        if (!user.isWellFormed())
            return new ResponseMessage("User is not well formed.");

        User bracedUser = braceUser(user);

        if (userDal.insertUser(bracedUser) != 1)
            return new ResponseMessage("User could not be written into the database.");

        return new ResponseMessage("User was successfully written into database.", 0);
    }

    public List<User> getAllUsers() {
        return userDal.selectAllUsers();
    }

    public List<User> getAllCustomers(){
        List<User> allCostumers = userDal.selectAllUsers();

        if (allCostumers == null)
                return new ArrayList<>();

        List<User> result = allCostumers.stream()
                .filter(o -> !o.isProvider())
                .collect(Collectors.toList());

        return result;
    }

    public List<User> getAllProviders(){
        List<User> allProviders = userDal.selectAllUsers();

        if (allProviders == null)
            return new ArrayList<>();

        List<User> result = allProviders.stream()
                .filter(User::isProvider)
                .collect(Collectors.toList());

        return result;
    }

    public Optional<User> getUserByID(int id) {
        if (!Checker.getInstance().checkId(id))
            return Optional.empty();
        return userDal.selectUserByID(id);
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> result = userDal.selectUserByName(username);

        if ( result == null)
            return Optional.empty();

        return result;
    }

        public Optional<User> verifyUser(String username, String password) {
        String bracedUsername = Formatter.getInstance().secureQuotes(username);
        String bracedPassword = Formatter.getInstance().secureQuotes(password);

        return userDal.selectUserByUsernameAndPassword(username, password);
    }

    public int deleteUser(int id) {
        if (!Checker.getInstance().checkId(id))
            return -1;

        return userDal.deleteUserByID(id);
    }

    public ResponseMessage deleteUserByUsername(String username){
        int affectedRows = userDal.deleteUserByUsername(username);
        if (affectedRows != 1)
            return new ResponseMessage("Database error", -1);

        return new ResponseMessage("User successfully deleted", 0);
    }

    public ResponseMessage updateUser(int id, User newUser) {
        Checker checker = Checker.getInstance();

        if (!checker.checkId(id) || !newUser.isWellFormed())
            return new ResponseMessage("User is not well formed.", -1);

        User bracedUser = braceUser(newUser);

        if (userDal.updateUserByID(id, bracedUser) != 1)
            return new ResponseMessage("Database error", -1);
        return new ResponseMessage("User updated successfully", 0);
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
