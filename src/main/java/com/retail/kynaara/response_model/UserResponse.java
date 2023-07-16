package com.retail.kynaara.response_model;

import com.retail.kynaara.model.User;
import com.retail.kynaara.model.UserPermissions;

public class UserResponse {
    private int user_id;
    private String user_full_name;
    protected String user_user_name;
    private String user_email;
    private int user_user_level;

    private UserPermissions user_permissions;

    public UserResponse(User user) {
        this.user_id = user.getUser_id();
        this.user_full_name = user.getUser_full_name();
        this.user_user_name = user.getUser_user_name();
        this.user_email = user.getUser_email();
        this.user_user_level = user.getUser_user_level();
        this.user_permissions = new UserPermissions();
        this.user_permissions.setUserPermissions(user.getUser_user_level());
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_user_name() {
        return user_user_name;
    }

    public void setUser_user_name(String user_user_name) {
        this.user_user_name = user_user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_user_level() {
        return user_user_level;
    }

    public void setUser_user_level(int user_user_level) {
        this.user_user_level = user_user_level;
    }

    public UserPermissions getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(UserPermissions user_permissions) {
        this.user_permissions = user_permissions;
    }
}
