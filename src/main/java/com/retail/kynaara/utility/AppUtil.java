package com.retail.kynaara.utility;

import org.springframework.stereotype.Component;

@Component
public class AppUtil {

    @Component
    public class Constants{
        public final String USER_FULL_NAME = "full_name"
                , USER_NAME = "user_name"
                , USER_EMAIL = "email"
                , USER_PASSWORD = "password"
                , USER_LEVEL = "user_level"
                , USER_ID = "user_id";

        public final String SUCCESS = "success"
                , STATUS_CODE = "statusCode"
                , MESSAGE = "message"
                , DATA = "data"
                , APPLICATION_JSON = "application/json";

        public final int CODE_200 = 200
                , CODE_401 = 401;

        public final boolean SUCCESS_TRUE = true
                , SUCCESS_FALSE = false;
    }

    @Component
    public class Functions{

    }
}
