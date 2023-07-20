package com.retail.kynaara.controller;

import com.retail.kynaara.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/user")
@ResponseBody
public class UserController extends ParentController{
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Object> userMap, HttpServletRequest header){
        return userService.addUser(userMap, headerToUser(header));
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUsers(@RequestParam int start, @RequestParam int size, @RequestParam(required = false) String fullName, @RequestParam(required = false) String userName, @RequestParam(required = false) Integer userLevel, HttpServletRequest header){
        return userService.getUsers(start, size, fullName, userName, userLevel, headerToUser(header));
    }

    //for logged in user
    @GetMapping("/get/info")
    public ResponseEntity<Object> getUserInfo(HttpServletRequest header){
        return userService.getUserByUserId(headerToUser(header));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody Map<String, Object> userMap, HttpServletRequest header){
        return userService.updateUser(userMap, headerToUser(header));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") int userId, HttpServletRequest header){
        return userService.deleteUser(userId, headerToUser(header));
    }
}
