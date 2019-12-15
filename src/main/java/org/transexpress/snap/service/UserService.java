package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.UserDal;
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

        return userDal.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDal.selectAllUsers();
    }

    public Optional<User> getUserByID(int id) {

        return userDal.selectUserByID(id);
    }

    public int deleteUser(int id) {

        return userDal.deleteUserByID(id);
    }

    public int updateUser(int id, User newUser) {

        return userDal.updateUserByID(id, newUser);
    }

}
