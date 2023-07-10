package com.retail.kynaara.model;

import jakarta.persistence.*;

@Entity
@Table(name = "table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_full_name;
    @Column(unique = true)
    protected String user_user_name;
    private String user_email;
    private String user_password;
    private int user_user_level;

    public User(String name, String user_user_name, String email, String password, int userLevel){
        this.user_full_name = name;
        this.user_user_name = user_user_name;
        this.user_email = email;
        this.user_password = password;
        this.user_user_level = userLevel;
    }

    public User(){
        this.user_full_name = "";
        this.user_user_name = "";
        this.user_email = "";
        this.user_password = "";
        this.user_user_level = 0;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_user_level() {
        return user_user_level;
    }

    public void setUser_user_level(int user_user_level) {
        this.user_user_level = user_user_level;
    }
}
