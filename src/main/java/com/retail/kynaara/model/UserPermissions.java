package com.retail.kynaara.model;

import org.springframework.stereotype.Component;

@Component
public class UserPermissions {
    private boolean alterSuperAdmin, alterAdmin, alterSalesPerson, alterChannel, alterProduct;

    public UserPermissions(){
        setUserPermissions(0);
    }

    public UserPermissions(int userLevel){
        setUserPermissions(userLevel);
    }

    public void setUserPermissions(int userLevel){
        if(userLevel == 1){
            alterSuperAdmin = true;
            alterAdmin = true;
            alterSalesPerson = false;
            alterChannel = true;
            alterProduct = false;
        }else if(userLevel == 2){
            alterSuperAdmin = false;
            alterAdmin = false;
            alterSalesPerson = true;
            alterChannel = false;
            alterProduct = true;
        }else if(userLevel == 3){
            alterSuperAdmin = false;
            alterAdmin = false;
            alterSalesPerson = false;
            alterChannel = false;
            alterProduct = false;
        }else{
            alterSuperAdmin = false;
            alterAdmin = false;
            alterSalesPerson = false;
            alterChannel = false;
            alterProduct = false;
        }
    }

    public boolean isAlterSuperAdmin() {
        return alterSuperAdmin;
    }

    public boolean isAlterAdmin() {
        return alterAdmin;
    }

    public boolean isAlterSalesPerson() {
        return alterSalesPerson;
    }

    public boolean isAlterChannel() {
        return alterChannel;
    }

    public boolean isAlterProduct() {
        return alterProduct;
    }
}
