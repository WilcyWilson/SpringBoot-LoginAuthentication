package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<Object> saveUser(UserRegisterDTO userDto);
}
