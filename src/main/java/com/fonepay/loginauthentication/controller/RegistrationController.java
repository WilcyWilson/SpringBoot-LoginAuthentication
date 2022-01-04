package com.fonepay.loginauthentication.controller;

import com.fonepay.loginauthentication.constants.PathConstants;
import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import com.fonepay.loginauthentication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(PathConstants.SAVE_USER)
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterDTO userDto){

         return registrationService.saveUser(userDto);
    }
}
