package com.retail.kynaara.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AppResponse {

    /*
    * success
    * statusCode
    * message
    * data
     */

    @Autowired
    AppUtil.Constants appUtilConstants;

    private ResponseEntity<Object> response(HashMap<String, Object> returnMap, HttpStatusCode httpStatusCode){
        return new ResponseEntity<>(returnMap, httpStatusCode);
    }

    public ResponseEntity<Object> successResponse(Object object, String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(appUtilConstants.SUCCESS, appUtilConstants.SUCCESS_TRUE);
        hashMap.put(appUtilConstants.STATUS_CODE, appUtilConstants.CODE_200);
        hashMap.put(appUtilConstants.MESSAGE, message);
        hashMap.put(appUtilConstants.DATA, object);
        return response(hashMap, HttpStatusCode.valueOf(appUtilConstants.CODE_200));
    }

    public ResponseEntity<Object> successResponse(String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(appUtilConstants.SUCCESS, appUtilConstants.SUCCESS_TRUE);
        hashMap.put(appUtilConstants.STATUS_CODE, appUtilConstants.CODE_200);
        hashMap.put(appUtilConstants.MESSAGE, message);
        hashMap.put(appUtilConstants.DATA, null);
        return response(hashMap, HttpStatusCode.valueOf(appUtilConstants.CODE_200));
    }

    public ResponseEntity<Object> failureResponse(Object object, String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(appUtilConstants.SUCCESS, appUtilConstants.SUCCESS_FALSE);
        hashMap.put(appUtilConstants.STATUS_CODE, appUtilConstants.CODE_200);
        hashMap.put(appUtilConstants.MESSAGE, message);
        hashMap.put(appUtilConstants.DATA, object);
        return response(hashMap, HttpStatusCode.valueOf(appUtilConstants.CODE_200));
    }

    public ResponseEntity<Object> failureResponse(String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(appUtilConstants.SUCCESS, appUtilConstants.SUCCESS_FALSE);
        hashMap.put(appUtilConstants.STATUS_CODE, appUtilConstants.CODE_200);
        hashMap.put(appUtilConstants.MESSAGE, message);
        hashMap.put(appUtilConstants.DATA, null);
        return response(hashMap, HttpStatusCode.valueOf(appUtilConstants.CODE_200));
    }

    public ResponseEntity<Object> authenticationErrorResponse(Object object, String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(appUtilConstants.SUCCESS, appUtilConstants.SUCCESS_FALSE);
        hashMap.put(appUtilConstants.STATUS_CODE, appUtilConstants.CODE_401);
        hashMap.put(appUtilConstants.MESSAGE, message);
        hashMap.put(appUtilConstants.DATA, object);
        return response(hashMap, HttpStatusCode.valueOf(appUtilConstants.CODE_401));
    }
}
