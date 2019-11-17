package org.transexpress.snap.dao;

import org.transexpress.snap.model.User;

import java.util.UUID;

public interface UserDao {

    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

}
