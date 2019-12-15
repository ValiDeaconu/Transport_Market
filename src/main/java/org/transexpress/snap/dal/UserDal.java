package org.transexpress.snap.dal;

import org.transexpress.snap.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDal {

    int insertUser(User user);

    List<User> selectAllUsers();

    Optional<User> selectUserByID(int id);

    int deleteUserByID(int id);

    int updateUserByID(int id, User user);
}
