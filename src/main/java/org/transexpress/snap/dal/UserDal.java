package org.transexpress.snap.dal;

import org.transexpress.snap.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDal {

    int insertUser(User user);

    List<User> selectAllUsers();

    List<String> selectAllUsersNamesByProvider(boolean isProvider);

    Optional<User> selectUserByID(int id);

    Optional<User> selectUserByUsernameAndPassword(String username, String password);

    int deleteUserByID(int id);

    int deleteUserByUsername(String username);

    int updateUserByID(int id, User user);
}
