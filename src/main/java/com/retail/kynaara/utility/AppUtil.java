package com.retail.kynaara.utility;

import org.springframework.stereotype.Component;

@Component
public class AppUtil {

    @Component
    public class Constants{
        public final String NAME = "name"
                , USER_NAME = "user_name"
                , EMAIL = "email"
                , PASSWORD = "password"
                , USER_LEVEL = "user_level";

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
