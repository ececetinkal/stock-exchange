package com.stock.exchange.controller;

import com.stock.exchange.domain.user.User;
import com.stock.exchange.exception.UserNotFoundException;
import com.stock.exchange.service.UserService;
import com.stock.exchange.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(value = "UserControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/api/")
@RestController
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value = "Get All Users", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> getAllUsers() {

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Retrieved Users", userService.getAllUsers());
    }

    @GetMapping("/users/{username}")
    @ApiOperation(value = "Get User By Username", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> retrieveUser(@PathVariable String username) throws UserNotFoundException {
        User retrievedUser;

        try {
            retrievedUser = userService.getUser(username);
        } catch (UserNotFoundException ex) {
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND,
                    "User could not be retrieved : " + ex.getMessage());
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Retrieved User", retrievedUser);
    }

    @PostMapping("/users")
    @ApiOperation(value = "Create User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        return ApiResponseHelper.getResponseEntity(HttpStatus.CREATED, "Created User", createdUser);
    }

    @DeleteMapping("/users/{code}")
    @ApiOperation(value = "Delete User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        User deletedUser = userService.deleteUser(id);

        if (deletedUser == null)
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND, "User with the given id does not exist");

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Deleted User");
    }

    @PutMapping("/users/{code}")
    @ApiOperation(value = "Update User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updateUser(user, id);

        if (updatedUser == null)
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND, "User with the given code does not exist");

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Updated User");
    }
}
