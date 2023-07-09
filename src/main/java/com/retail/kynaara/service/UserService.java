package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserCustomRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
import com.retail.kynaara.utility.UserPermissionsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    //create operations
    public ResponseEntity<Object> addUser(Map<String, Object> userMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            ArrayList<User> users = new ArrayList<>();
            User userToAdd = new User(
                    (String) userMap.get(appUtilConstants.USER_FULL_NAME),
                    (String) userMap.get(appUtilConstants.USER_NAME),
                    (String) userMap.get(appUtilConstants.USER_EMAIL),
                    (String) userMap.get(appUtilConstants.USER_PASSWORD),
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

    //get operations
    public ResponseEntity<Object> getUsers(int start, int size, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            return appResponse.successResponse(userCustomRepository.getUsers(start, size, user.getUser_user_level()), null);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    public ResponseEntity<Object> getUsersByUserId(int userId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            List<User> userList = userCustomRepository.getUserByUserId(userId);
            if(userList.isEmpty()){
                return appResponse.successResponse(userList, error.userDoesNotExist);
            }else{
                return appResponse.successResponse(userList, null);
            }

        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    public ResponseEntity<Object> getUsersByFullName(int start, int size, String q, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            return appResponse.successResponse(userCustomRepository.getUsersByFullName(start, size, q, user.getUser_user_level()), null);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    public ResponseEntity<Object> getUsersByUserName(int start, int size, String q, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            return appResponse.successResponse(userCustomRepository.getUsersByUserName(start, size, q, user.getUser_user_level()), null);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    //update operations
    public ResponseEntity<Object> updateUser(Map<String, Object> userMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            User userToUpdate = new User();

            userToUpdate.setUser_id((int) userMap.get(appUtilConstants.USER_ID));
            userToUpdate.setUser_email((String) userMap.get(appUtilConstants.USER_EMAIL));
            userToUpdate.setUser_password((String) userMap.get(appUtilConstants.USER_PASSWORD));
            userToUpdate.setUser_full_name((String) userMap.get(appUtilConstants.USER_FULL_NAME));

            userCustomRepository.updateUser(userToUpdate);

            return appResponse.successResponse(success.userUpdated);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.userNotUpdated);
        }
    }

    //delete operations
    public ResponseEntity<Object> deleteUser(int userId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            List<User> userList = userCustomRepository.getUserByUserId(userId);
            if(userList.isEmpty()){
                return appResponse.successResponse(userList, error.userDoesNotExist);
            }else{
                User userToDelete = userList.get(0);

                if(userToDelete.getUser_user_level() <= user.getUser_user_level()){
                    return appResponse.failureResponse(error.permissionDenied);
                }

                userCustomRepository.deleteUser(userId);

                return appResponse.successResponse(success.userDeleted);
            }
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.userNotDeleted);
        }
    }
}
