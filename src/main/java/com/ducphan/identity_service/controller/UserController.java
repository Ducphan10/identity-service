package com.ducphan.identity_service.controller;

import com.ducphan.identity_service.dto.request.UserCreationRequest;
import com.ducphan.identity_service.dto.request.UserUpdateRequest;
import com.ducphan.identity_service.entity.User;
import com.ducphan.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping()
    List<User> getUsers() {
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId,@RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId,request);

    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
         userService.deleteUser(userId);
         return "delete successs";
    }

}
