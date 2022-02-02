package com.fonepay.loginauthentication.dto;

import com.fonepay.loginauthentication.entity.UserRegister;
import lombok.Data;

@Data
public class GetDataDTO {
    private Long id;
    private String email;
    private String userName;
    private String createdBy;
    private Boolean status;
}
