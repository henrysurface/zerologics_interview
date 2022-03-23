package com.interview.zerologics.demo.controller;

import com.interview.zerologics.demo.entity.Follow;
import com.interview.zerologics.demo.entity.User;
import com.interview.zerologics.demo.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){

        try{
            userService.createUser(user);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/follow/{fromUser}/to/{toUser}", method = RequestMethod.POST)
    public ResponseEntity<Follow> followUser(@PathVariable(value = "fromUser") Long fromUserId
            , @PathVariable(value = "toUser")  Long toUserId){

        try {
            userService.followUser(fromUserId, toUserId);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
