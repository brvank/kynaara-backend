package com.retail.kynaara.service;

import com.retail.kynaara.model.User;
import com.retail.kynaara.repository.UserCustomRepository;
import com.retail.kynaara.response_model.AuthResponse;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
import com.retail.kynaara.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    UserCustomRepository userCustomRepository;

    @Autowired
    AppResponse appResponse;
    @Autowired
    AppUtil.Constants appUtilConstants;
    @Autowired
    AppMessages.Success successMessages;
    @Autowired
    AppMessages.Error errorMessages;

    @Autowired
    TokenUtil tokenUtil;

    public ResponseEntity<Object> login(Map<String, String> requestMap){
        try{
            String userName = requestMap.getOrDefault(appUtilConstants.USER_NAME, null);
            String password = requestMap.getOrDefault(appUtilConstants.USER_PASSWORD, null);

            if(userName != null && password != null){
                List<User> users = userCustomRepository.getUserByNameAndPassword(userName, password);

                if(users.size() > 0){
                    String token = tokenUtil.generateToken(users.get(0));
                    AuthResponse authResponse = new AuthResponse(token);

                    return appResponse.successResponse(authResponse, successMessages.loggedIn);
                }else{
                    return appResponse.failureResponse(errorMessages.invalidCredentials);
                }
            }else{
                return appResponse.failureResponse(errorMessages.provideAllFields);
            }
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(errorMessages.errorLogging);
        }
    }
}
