package com.retail.kynaara.utility;

import com.retail.kynaara.model.User;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    private static final String USER_NAME = "user_name";
    private static final String USER_LEVEL = "user_level";
    private static final String VALIDITY = "validity";
    private static final long EXPIRY_TIME = 24 * 60 * 60 * 1000; //one day validity

    public enum TokenValidity{
        INVALID,
        VALID,
        EXPIRED
    }

    public static String generateToken(User user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_NAME, user.getUserName());
        jsonObject.put(USER_LEVEL, user.getUserLevel());
        jsonObject.put(VALIDITY, System.currentTimeMillis() + 10000);

        return AppSecurity.encrypt(jsonObject.toString());
    }

    public static User extractToken(String token){
        try{
            JSONObject jsonObject = new JSONObject(AppSecurity.decrypt(token));
            User user = new User();
            user.setUserName(jsonObject.getString(USER_NAME));
            user.setUserLevel(jsonObject.getInt(USER_LEVEL));
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static TokenValidity validateToken(String token){
        String decryptedToken = AppSecurity.decrypt(token);

        if(decryptedToken == null){
            return TokenValidity.INVALID;
        }else{
            JSONObject jsonObject = new JSONObject(decryptedToken);
            if(jsonObject.getLong(VALIDITY) < System.currentTimeMillis()){
                return TokenValidity.EXPIRED;
            }else{
                return TokenValidity.VALID;
            }
        }
    }

}
