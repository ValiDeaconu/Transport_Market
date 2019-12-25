package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.model.User;
import org.transexpress.snap.model.UserReview;
import org.transexpress.snap.service.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserByID(@PathVariable("id") int id) {
        return userService.getUserByID(id).orElse(null);
    }

    @GetMapping(path = "{view_profile_id}")
    public Pair<User, List<UserReview>> getUserViewProfile(@PathVariable("view_profle_id") int id) {
        return userService.getUserViewProfile(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserByID(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUserByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody User userToUpdate) {
        userService.updateUser(id, userToUpdate);
    }
}
