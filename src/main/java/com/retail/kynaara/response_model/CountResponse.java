package com.retail.kynaara.response_model;

public class CountResponse {

    private Long count;
    private Object result;

    public CountResponse(Long count, Object result){
        this.count = count;
        this.result = result;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
