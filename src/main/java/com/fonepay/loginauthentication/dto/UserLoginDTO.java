package com.fonepay.loginauthentication.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String userName;
    private String emailId;
    private String password;
}
