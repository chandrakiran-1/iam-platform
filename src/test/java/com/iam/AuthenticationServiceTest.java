package com.iam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AuthenticationServiceTest
{
    @Test
    void CorrectPasswordReturnTrue()
    {
        User user = new User("admin" , "1234");
        AuthenticationService service = new AuthenticationService();
        boolean result = service.authenticate(user , "1234");
        assertTrue(result);
    }
    @Test
    void WrongPasswordReturnFalse()
    {
        User user = new User("admin" ,"1234");
        AuthenticationService service = new AuthenticationService();
        boolean result = service.authenticate(user , "false");
        assertFalse(result);
    }
}