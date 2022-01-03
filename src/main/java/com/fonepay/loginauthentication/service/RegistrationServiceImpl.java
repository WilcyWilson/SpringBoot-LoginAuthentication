package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.UserDTO;
import com.fonepay.loginauthentication.entity.User;
import com.fonepay.loginauthentication.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService  {
    @Autowired
    private RegistrationRepository repo;

    // Saving User data from the frontend to backend
    @Override
    public ResponseEntity<Object> saveUser(User user){
        repo.save(user);

        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setEmailId(user.getEmailId());
        userDto.setUserName(user.getUserName());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
