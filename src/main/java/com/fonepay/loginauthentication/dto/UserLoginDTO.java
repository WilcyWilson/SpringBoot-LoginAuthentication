package com.fonepay.loginauthentication.dto;

import com.fonepay.loginauthentication.service.EncryptionService;
import lombok.Data;

@Data
public class UserLoginDTO {
    private String userName;
    private String emailId;
    private String password;

}
