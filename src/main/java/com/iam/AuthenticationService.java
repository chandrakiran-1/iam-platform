package com.iam;

public class AuthenticationService {

    public boolean authenticate(User user , String password)
    {
        return user.getPassword().equals(password);
    }
}