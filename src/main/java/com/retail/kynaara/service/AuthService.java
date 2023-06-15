package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public static final String TOKEN = "token";

    public ResponseEntity<Object> login(Map<String, String> requestMap){
        String userName = requestMap.getOrDefault(UserService.USER_NAME, null);
        String password = requestMap.getOrDefault(UserService.PASSWORD, null);

        if(userName != null && password != null){
            ArrayList<User> users = new ArrayList<>();
            userRepository.findByUserName(userName).forEach(new Consumer<User>() {
                @Override
                public void accept(User user) {
                    users.add(user);
                }
            });

            String token = TokenUtil.generateToken(users.get(0));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(TOKEN, token);
            return AppResponse.successResponse(jsonObject, AppMessages.Success.loggedIn);
        }else{
            return AppResponse.failureResponse(AppMessages.Error.provideAllFields);
        }
    }
}
