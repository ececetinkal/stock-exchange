package com.stock.exchange.controller;

import com.stock.exchange.domain.user.User;
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
    public ResponseEntity<Object> retrieveUser(@PathVariable String username) {
        User retrievedUser = userService.getUser(username);

        if (retrievedUser == null) {
            return getUserNotFoundResponse();
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Retrieved User", retrievedUser);
    }

    @PostMapping("/users")
    @ApiOperation(value = "Create User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        if (createdUser == null) {
            return getUserAlreadyExistsResponse();
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.CREATED, "Created User", createdUser);
    }

    @DeleteMapping("/users/{username}")
    @ApiOperation(value = "Delete User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        User deletedUser = userService.deleteUser(username);

        if (deletedUser == null)
            return getUserNotFoundResponse();

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Deleted User");
    }

    @PutMapping("/users/{username}")
    @ApiOperation(value = "Update User", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable String username) {
        User updatedUser = userService.updateUser(user, username);

        if (updatedUser == null)
            return getUserNotFoundResponse();

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Updated User");
    }

    private ResponseEntity<Object> getUserNotFoundResponse() {
        return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND, "User with the given username does not exist");
    }

    private ResponseEntity<Object> getUserAlreadyExistsResponse() {
        return ApiResponseHelper.getResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, "User with the given username already exists.");
    }
}
