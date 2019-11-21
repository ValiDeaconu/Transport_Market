package org.transexpress.snap.dao;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.model.User;

import java.util.*;

@Repository("postgres")
public class UserDataAccessService implements UserDao{
    @Override
    public int insertUser(UUID id, User user) {
        return 0;
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList((Collection) new User(UUID.randomUUID(), "FROM POSTGRES DB"));
    }

    @Override
    public Optional<User> selectUserByID(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteUserByID(UUID id) {
        return 0;
    }

    @Override
    public int updateUserByID(UUID id, User user) {
        return 0;
    }
}
