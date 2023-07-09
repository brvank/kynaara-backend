package com.retail.kynaara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;

@Deprecated(since = "Not in use presently")
@Entity
public class Session {

    @Id
    private int id;
    private int userId;

    private String token;

    public Session() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
