package org.transexpress.snap.dao;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {
    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserByID(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserByID(UUID id) {
        Optional<User> userMaybe = selectUserByID(id);
        if (userMaybe.isPresent()){
            DB.remove(userMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserByID(UUID id, User update) {
        return selectUserByID(id)
                .map(u -> {
                    int indexOfUserToUpdate = DB.indexOf(u);
                    if (indexOfUserToUpdate >= 0){
                        DB.set(indexOfUserToUpdate, new User(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
