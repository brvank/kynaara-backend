package com.retail.kynaara.middleware;

public class Message {

    public String message;

    public boolean success;

    public Message(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
