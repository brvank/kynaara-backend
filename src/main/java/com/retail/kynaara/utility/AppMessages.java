package com.retail.kynaara.utility;

import org.springframework.stereotype.Component;

@Component
public class AppMessages {

    @Component
    public class Success{
        public final String userAdded = "User added successfully!"
                , loggedIn = "Logged in successfully!";
    }

    @Component
    public class Error{
        public final String userNotAdded = "Error saving User. User not added!"
                , userNameExist = "User name already exists!"
                , invalidCredentials = "Invalid login credentials!"
                , provideAllFields = "Provide all the fields!"
                , notAuthorized = "Authentication failed. Please re-login again"
                , errorLogging = "Unknown error occurred while logging in!";
    }
}
