package com.retail.kynaara.controller;

import com.retail.kynaara.service.ChannelService;
import com.retail.kynaara.service.ProductService;
import com.retail.kynaara.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/count")
@ResponseBody
public class CountController extends ParentController{

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ProductService productService;

    @GetMapping("/users")
    public ResponseEntity<Object> getCountUsers(HttpServletRequest header){
        return userService.getCountUsers(headerToUser(header));
    }

    @GetMapping("/usersByFullName")
    public ResponseEntity<Object> getCountUsersByFullName(@RequestParam String q, HttpServletRequest header){
        return userService.getCountUsersByFullName(q, headerToUser(header));
    }

    @GetMapping("/usersByUserName")
    public ResponseEntity<Object> getCountUsersByUserName(@RequestParam String q, HttpServletRequest header){
        return userService.getCountUsersByUserName(q, headerToUser(header));
    }


    @GetMapping("/channels")
    public ResponseEntity<Object> getCountChannels(HttpServletRequest header){
        return channelService.getCountChannels(headerToUser(header));
    }

    @GetMapping("/channelsByName")
    public ResponseEntity<Object> getCountChannelsByName(@RequestParam String q, HttpServletRequest header){
        return channelService.getCountChannelsByName(q, headerToUser(header));
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getCountProducts(HttpServletRequest header){
        return productService.getCountProducts(headerToUser(header));
    }

    @GetMapping("/productsByLink")
    public ResponseEntity<Object> getCountProductsByLink(@RequestParam String q, HttpServletRequest header){
        return productService.getCountProductsByLink(q, headerToUser(header));
    }
}
