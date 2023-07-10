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
                , USER_ID = "user_id"
                , CHANNEL_NAME = "channel_name"
                , CHANNEL_LINK = "link"
                , CHANNEL_LOGO_LINK = "logo_link"
                , CHANNEL_CREATION_DATE = "creation_date"
                , CHANNEL_CREATOR_ID = "creator_id"
                , CHANNEL_ID = "channel_id"
                , PRODUCT_LINK = "link"
                , PRODUCT_IMAGE_LINK = "image_link"
                , PRODUCT_CREATION_DATE = "creation_date"
                , PRODUCT_CREATOR_ID = "creator_id"
                , PRODUCT_ASSIGNED_DATE = "assigned_date"
                , PRODUCT_ASSIGNEE_ID = "assignee_id"
                , PRODUCT_CHANNEL_ID = "channel_id"
                , PRODUCT_ID = "product_id";

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
