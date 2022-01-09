package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.UserLoginDTO;
import com.fonepay.loginauthentication.service.PropertiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Value("${user.login.name}")
    String username;

    @Value("${user.login.password}")
    String password;

    @Override
    public ResponseEntity<Object> propertiesFileDemo() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserName(username);
        userLoginDTO.setPassword(password);
        return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
    }
}
