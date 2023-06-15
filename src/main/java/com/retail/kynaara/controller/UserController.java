package com.retail.kynaara.controller;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserRepository;
import com.retail.kynaara.service.UserService;
import com.retail.kynaara.utility.AppResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Object> map){
        return userService.addUser(map);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return null;
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteAllUsers(Request){
//
//    }
}
