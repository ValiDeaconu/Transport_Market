package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.ResponseMessage;
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
    public ResponseMessage addUser(@Valid @NonNull @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="a/{provider}")
    public List<String> getAllUsersByProvider(@PathVariable ("provider") boolean isProvider){
        return userService.getAllUsersNameByProvider(isProvider);
    }

    @GetMapping(path = "allCostumers")
    public List<User> getAllCostumers(){ return userService.getAllCustomers();}

    @GetMapping(path = "a/allProviders")
    public List<User> getAllProviders(){return userService.getAllProviders();}

    @GetMapping(path = "{username}/{password}")
    public User verifyUser(@PathVariable("username") String username,
                           @PathVariable("password") String password) {
        return userService.verifyUser(username, password).orElse(null);
    }

    @GetMapping(path = "{id}")
    public User getUserByID(@PathVariable("id") int id) {
        return userService.getUserByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserByID(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @DeleteMapping(path = "{username}")
    public void deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
    }
    @PutMapping(path = "{id}")
    public void updateUserByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody User userToUpdate) {
        userService.updateUser(id, userToUpdate);
    }
}
