package com.retail.kynaara.controller;

import com.retail.kynaara.model.User;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ParentController {

    @Autowired
    TokenUtil tokenUtil;
    public User headerToUser(HttpServletRequest header){
        try{
            String token = header.getHeader("Authorization");
            return tokenUtil.extractUserFromToken(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
