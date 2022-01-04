package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.UserLoginDTO;
import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.entity.UserRegister;
import com.fonepay.loginauthentication.repository.LoginRepository;
import com.fonepay.loginauthentication.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService  {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private LoginRepository loginRepository;

    // Saving User data from the frontend to backend
    @Override
    public ResponseEntity<Object> saveUser(UserRegisterDTO userRegisterDTO){
          UserRegister userRegister = new UserRegister();
          UserLogin userLogin = new UserLogin();
          UserLoginDTO userLoginDTO = new UserLoginDTO();

          userRegister.setUserName(userRegisterDTO.getUserName());
          userRegister.setFirstName(userRegisterDTO.getFirstName());
          userRegister.setLastName(userRegisterDTO.getLastName());
          userRegister.setAddress(userRegisterDTO.getAddress());
          userRegister.setPassword(userRegisterDTO.getPassword());
          userRegister.setPhoneNo(userRegisterDTO.getPhoneNo());
          userRegister.setEmailId(userRegisterDTO.getEmailId());

          userLoginDTO.setUserName(userRegisterDTO.getUserName());
          userLoginDTO.setEmailId(userRegisterDTO.getEmailId());
          userLoginDTO.setPassword(userRegisterDTO.getPassword());

          userLogin.setUserName(userLoginDTO.getUserName());
          userLogin.setEmailId(userLoginDTO.getEmailId());
          userLogin.setPassword(userLoginDTO.getPassword());
          userLogin.setUserRegister(userRegister);

          registrationRepository.save(userRegister);
          loginRepository.save(userLogin);

          return new ResponseEntity<>(userRegisterDTO, HttpStatus.OK);
    }
}
