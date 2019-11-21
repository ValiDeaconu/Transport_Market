package org.transexpress.snap.dao;

import org.transexpress.snap.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserByID(UUID id);

    int deleteUserByID(UUID id);

    int updateUserByID(UUID id, User user);

}
