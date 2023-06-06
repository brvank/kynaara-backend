package com.retail.kynaara.controller;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public String addUser(@RequestBody Map<String, Object> map){
        try{
            userRepository.save(new User((String) map.get("name"), (String) map.get("username"), (String) map.get("email"), (String) map.get("password"),(int) map.get("level")));
            return "Saved";
        }catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/all-users")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
