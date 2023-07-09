package com.retail.kynaara.utility;

import org.springframework.stereotype.Component;

@Component
public class AppMessages {

    @Component
    public class Success{
        public final String userAdded = "User added successfully!"
                , userUpdated = "User updated Successfully!"
                , userDeleted = "User deleted Successfully!"
                , loggedIn = "Logged in successfully!";
    }

    @Component
    public class Error{
        public final String userNotAdded = "Error saving User. User not added!"
                , userNotUpdated = "Error saving User. User not updated!"
                , userNotDeleted = "Error deleting User. User not deleted!"
                , userNameExist = "User name already exists!"
                , invalidCredentials = "Invalid login credentials!"
                , provideAllFields = "Provide all the fields!"
                , notAuthorized = "Authentication failed. Please re-login again"
                , errorLogging = "Unknown error occurred while logging in!"
                , permissionDenied = "Permission denied. You are not allowed to perform this task!"
                , userDoesNotExist = "User doesn't exist!"
                , unknownErrorOccurred = "Unknown error occurred at our end. Please try later!";
    }
}
