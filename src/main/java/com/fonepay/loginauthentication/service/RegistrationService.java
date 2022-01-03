package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.UserDTO;
import com.fonepay.loginauthentication.entity.User;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<Object> saveUser(UserDTO userDto);
}
