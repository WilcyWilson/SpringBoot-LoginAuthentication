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
    public ResponseEntity<Object> saveUser(UserDTO userDTO){
          User user = new User();
          user.setUserName(userDTO.getUserName());
          user.setFirstName(userDTO.getFirstName());
          user.setLastName(userDTO.getLastName());
          user.setAddress(userDTO.getAddress());
          user.setPassword(userDTO.getPassword());
          user.setPhoneNo(userDTO.getPhoneNo());
          user.setEmailId(userDTO.getEmailId());

          repo.save(user);
          return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
