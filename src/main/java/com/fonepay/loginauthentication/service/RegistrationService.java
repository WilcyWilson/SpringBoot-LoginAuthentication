package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.entity.User;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<Object> saveUser(User user);
}
