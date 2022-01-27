package com.fonepay.loginauthentication.dto;

import lombok.Data;

@Data
public class GetDataDTO {
    private long id;
    private String userName;
    private String emailId;
    private String createdBy;
    private Boolean status;
}
