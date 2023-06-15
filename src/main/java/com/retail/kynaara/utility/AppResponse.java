package com.retail.kynaara.utility;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class AppResponse {

    /*
    * success
    * statusCode
    * message
    * data
     */
    private static final String SUCCESS = "success";
    private static final String STATUS_CODE = "statusCode";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    private static final int CODE_200 = 200;
    private static final int CODE_401 = 401;

    private static final boolean SUCCESS_TRUE = true;
    private static final boolean SUCCESS_FALSE = false;

    private static final HashMap<String, Object> RESPONSE_TEMPLATE = new HashMap<>(){{
        put(SUCCESS, SUCCESS_TRUE);
        put(STATUS_CODE, CODE_200);
        put(MESSAGE, null);
        put(DATA, null);
    }};

    private static ResponseEntity<Object> response(HashMap<String, Object> returnMap, HttpStatusCode httpStatusCode){
        return new ResponseEntity<>(returnMap, httpStatusCode);
    }

    //status 200
    public static ResponseEntity<Object> successResponse(JSONObject jsonObject, String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_TRUE);
        hashMap.replace(STATUS_CODE, CODE_200);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, jsonObject);
        return response(hashMap, HttpStatusCode.valueOf(CODE_200));
    }

    public static ResponseEntity<Object> successResponse(JSONArray jsonArray, String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_TRUE);
        hashMap.replace(STATUS_CODE, CODE_200);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, jsonArray);
        return response(hashMap, HttpStatusCode.valueOf(CODE_200));
    }

    public static ResponseEntity<Object> successResponse(String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_TRUE);
        hashMap.replace(STATUS_CODE, CODE_200);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, null);
        return response(hashMap, HttpStatusCode.valueOf(CODE_200));
    }

    //status 200
    public static ResponseEntity<Object> failureResponse(JSONObject jsonObject, String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_FALSE);
        hashMap.replace(SUCCESS, CODE_200);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, jsonObject);
        return response(hashMap, HttpStatusCode.valueOf(CODE_200));
    }

    public static ResponseEntity<Object> failureResponse(String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_FALSE);
        hashMap.replace(SUCCESS, CODE_200);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, null);
        return response(hashMap, HttpStatusCode.valueOf(CODE_200));
    }

    //status 401
    public static ResponseEntity<Object> authenticationErrorResponse(JSONObject jsonObject, String message){
        HashMap<String, Object> hashMap = RESPONSE_TEMPLATE;
        hashMap.replace(SUCCESS, SUCCESS_FALSE);
        hashMap.replace(SUCCESS, CODE_401);
        hashMap.replace(MESSAGE, message);
        hashMap.replace(DATA, jsonObject);
        return response(hashMap, HttpStatusCode.valueOf(CODE_401));
    }
}
