package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.ResponseDTO;

public interface PasswordService {
    ResponseDTO passwordMatch(String password);
}
