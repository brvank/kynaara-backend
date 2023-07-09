package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserCustomRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
import com.retail.kynaara.utility.UserPermissionsManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class UserService {
    @Autowired
    UserCustomRepository userCustomRepository;

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

    @Autowired
    private UserPermissionsManager userPermissionsManager;

    public ResponseEntity<Object> addUser(Map<String, Object> userMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            ArrayList<User> users = new ArrayList<>();
            User userToAdd = new User(
                    (String) userMap.get(appUtilConstants.NAME),
                    (String) userMap.get(appUtilConstants.USER_NAME),
                    (String) userMap.get(appUtilConstants.EMAIL),
                    (String) userMap.get(appUtilConstants.PASSWORD),
                    (int) userMap.get(appUtilConstants.USER_LEVEL)
            );

            if(user.getUser_user_level() >= userToAdd.getUser_user_level()){
                if(user.getUser_user_level() > userToAdd.getUser_user_level()){
                    return appResponse.failureResponse(error.permissionDenied);
                }else if((user.getUser_user_level() != 1) && (user.getUser_user_level() == userToAdd.getUser_user_level())){
                    return appResponse.failureResponse(error.permissionDenied);
                }
            }

            users.add(userToAdd);

            userCustomRepository.addMultipleUsers(users);

            return appResponse.successResponse(success.userAdded);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.userNotAdded);
        }
    }

    public ResponseEntity<Object> getUsers(int start, int size){
        return appResponse.successResponse(userCustomRepository.getUsers(start, size), null);
    }

    public ResponseEntity<Object> getUsersByFullName(int start, int size, String q){
        return appResponse.successResponse(userCustomRepository.getUsersByFullName(start, size, q), null);
    }

    public ResponseEntity<Object> getUsersByUserName(int start, int size, String q){
        return appResponse.successResponse(userCustomRepository.getUsersByUserName(start, size, q), null);
    }
}
