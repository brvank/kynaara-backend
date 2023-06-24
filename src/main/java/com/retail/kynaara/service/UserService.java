package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
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

    @Autowired
    AppMessages appMessages;

    @Autowired
    AppUtil.Constants appUtilConstants;

    @Autowired
    AppMessages.Success success;

    @Autowired
    AppMessages.Error error;

    @Autowired
    private AppResponse appResponse;

    public JSONObject userToJsonObject(User user){
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put(appUtilConstants.NAME, user.getName());
        userJsonObject.put(appUtilConstants.USER_NAME, user.getUserName());
        userJsonObject.put(appUtilConstants.EMAIL, user.getEmail());
        userJsonObject.put(appUtilConstants.USER_LEVEL, user.getUserLevel());
        return userJsonObject;
    }

    public JSONArray usersListToJsonArray(Iterable<User> users){
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
                            (String) userMap.get(appUtilConstants.NAME),
                            (String) userMap.get(appUtilConstants.USER_NAME),
                            (String) userMap.get(appUtilConstants.EMAIL),
                            (String) userMap.get(appUtilConstants.PASSWORD),
                            (int) userMap.get(appUtilConstants.USER_LEVEL)
                    )
            );

            return appResponse.successResponse(success.userAdded);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.userNotAdded);
        }
    }

    public ResponseEntity<Object> getAllUsers(){
        try{
            JSONArray userJsonArray = usersListToJsonArray(userRepository.findAll());

            return appResponse.successResponse(userJsonArray, success.userAdded);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.userNotAdded);
        }
    }
}
