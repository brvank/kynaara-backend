package com.retail.kynaara.utility;

import com.retail.kynaara.model.UserPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPermissionsManager {

    @Autowired
    private UserPermissions userPermissions;

    public UserPermissions getUserPermissions(int userLevel){
        userPermissions.setUserPermissions(userLevel);

        return userPermissions;
    }
}
