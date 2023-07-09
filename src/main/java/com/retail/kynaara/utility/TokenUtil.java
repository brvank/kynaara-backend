package com.retail.kynaara.utility;

import com.retail.kynaara.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Autowired
    AppSecurity appSecurity;

    private final String USER_NAME = "user_name";
    private final String USER_ID = "user_id";
    private final String USER_LEVEL = "user_level";
    private final String VALIDITY = "validity";
    private final long EXPIRY_TIME = 24 * 60 * 60 * 1000; //one day validity

    public enum TokenValidity{
        INVALID,
        VALID,
        EXPIRED
    }

    public String generateToken(User user){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(USER_ID, user.getUser_id());
            jsonObject.put(USER_NAME, user.getUser_user_name());
            jsonObject.put(USER_LEVEL, user.getUser_user_level());
            jsonObject.put(VALIDITY, System.currentTimeMillis() + EXPIRY_TIME);

            return appSecurity.encrypt(jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public User extractUserFromToken(String token){
        try{
            JSONObject jsonObject = new JSONObject(appSecurity.decrypt(token));
            User user = new User();
            user.setUser_id(jsonObject.getInt(USER_ID));
            user.setUser_user_name(jsonObject.getString(USER_NAME));
            user.setUser_user_level(jsonObject.getInt(USER_LEVEL));
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public TokenValidity validateToken(String token){
        try{
            String decryptedToken = appSecurity.decrypt(token);

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
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
