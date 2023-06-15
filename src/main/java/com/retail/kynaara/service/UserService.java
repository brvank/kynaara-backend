package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final String NAME = "name"
            , USER_NAME = "user_name"
            , EMAIL = "email"
            , PASSWORD = "password"
            , USER_LEVEL = "user_level";

    public static JSONObject userToJsonObject(User user){
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put(NAME, user.getName());
        userJsonObject.put(USER_NAME, user.getUserName());
        userJsonObject.put(EMAIL, user.getEmail());
        userJsonObject.put(USER_LEVEL, user.getUserLevel());
        return userJsonObject;
    }

    public static JSONArray usersListToJsonArray(Iterable<User> users){
        JSONArray userJsonArray = new JSONArray();
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                userJsonArray.put(userToJsonObject(user));
            }
        });
        return userJsonArray;
    }

    public ResponseEntity<Object> addUser(Map<String, Object> userMap){
        try{
            userRepository.save(
                    new User(
                            (String) userMap.get(NAME),
                            (String) userMap.get(USER_NAME),
                            (String) userMap.get(EMAIL),
                            (String) userMap.get(PASSWORD),
                            (int) userMap.get(USER_LEVEL)
                    )
            );

            return AppResponse.successResponse(AppMessages.Success.userAdded);
        }catch (Exception e){
            e.printStackTrace();
            return AppResponse.failureResponse(AppMessages.Error.userNotAdded);
        }
    }

    public ResponseEntity<Object> getAllUsers(){
        try{
            JSONArray userJsonArray = usersListToJsonArray(userRepository.findAll());

            return AppResponse.successResponse(userJsonArray, AppMessages.Success.userAdded);
        }catch (Exception e){
            e.printStackTrace();
            return AppResponse.failureResponse(AppMessages.Error.userNotAdded);
        }
    }
}
