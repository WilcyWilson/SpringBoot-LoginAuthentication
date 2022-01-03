package com.fonepay.loginauthentication.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String emailId;
    private String password;
    private String lastName;
    private String phoneNo;
    private String address;
    private String firstName;
}
