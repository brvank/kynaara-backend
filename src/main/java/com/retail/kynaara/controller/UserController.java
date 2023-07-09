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
    public ResponseEntity<Object> getUsers(@RequestParam int start, @RequestParam int size){
        return userService.getUsers(start, size);
    }

    @GetMapping("/get/byFullName")
    public ResponseEntity<Object> getUsersByFullName(@RequestParam int start, @RequestParam int size, @RequestParam String q){
        return userService.getUsersByFullName(start, size, q);
    }

    @GetMapping("/get/byUserName")
    public ResponseEntity<Object> getUsersByUserName(@RequestParam int start, @RequestParam int size, @RequestParam String q){
        return userService.getUsersByUserName(start, size, q);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteAllUsers(Request){
//
//    }
}
