package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.entity.UserRegister;
import com.fonepay.loginauthentication.repository.LoginRepository;
import com.fonepay.loginauthentication.repository.RegistrationRepository;
import com.fonepay.loginauthentication.service.EncryptionService;
import com.fonepay.loginauthentication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private LoginRepository loginRepository;

    // Saving User data from the frontend to backend
    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserRegisterDTO userRegisterDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try {
            UserRegister userRegister = new UserRegister();
            UserLogin userLogin = new UserLogin();
            ResponseDTO responseDTO = new ResponseDTO();

            userRegister.setUserName(userRegisterDTO.getUserName());
            userRegister.setFirstName(userRegisterDTO.getFirstName());
            userRegister.setLastName(userRegisterDTO.getLastName());
            userRegister.setAddress(userRegisterDTO.getAddress());
            userRegister.setPassword(EncryptionService.encrypt(userRegisterDTO.getPassword(),userRegisterDTO.getUserName()));
            userRegister.setPhoneNo(userRegisterDTO.getPhoneNo());
            userRegister.setEmailId(userRegisterDTO.getEmailId());
            userRegister.setCreatedDate(java.time.LocalDateTime.now().toString());

            userLogin.setUserName(userRegister.getUserName());
            userLogin.setEmailId(userRegister.getEmailId());
            userLogin.setPassword(userRegister.getPassword());
            userLogin.setCreatedBy(userRegister.getUserName());
            userLogin.setStatus(false);
            userLogin.setUserRegister(userRegister);

            registrationRepository.save(userRegister);
            loginRepository.save(userLogin);

            responseDTO.setResponseStatus(true);
            responseDTO.setResponseMessage("Successful Registration");

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Unsuccessful Registration  " + e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

}
