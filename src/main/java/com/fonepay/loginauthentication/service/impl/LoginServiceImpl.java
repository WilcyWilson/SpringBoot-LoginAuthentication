package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.dto.UserLoginDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepository;
import com.fonepay.loginauthentication.service.EncryptionService;
import com.fonepay.loginauthentication.service.LoginService;
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
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    private ResponseDTO responseDTO = new ResponseDTO();

    public ResponseEntity<ResponseDTO> checkUser(UserLoginDTO userLoginDTO) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        if(userLoginDTO.getUserName()!=null || !userLoginDTO.getUserName().isEmpty()){
            UserLogin userLogin=loginRepository.findByUserName(userLoginDTO.getUserName());
            if(userLogin != null) {
                if (EncryptionService.decrypt(userLogin.getPassword(),userLogin.getUserName()).equals(userLoginDTO.getPassword())) {
                    responseDTO.setResponseStatus(true);
                    responseDTO.setResponseMessage("Username and password Correct. Login Successful. Welcome Kale Dai.");
                } else {
                    responseDTO.setResponseStatus(false);
                    responseDTO.setResponseMessage("Username or password Incorrect");
                }
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("Username not found");
            }
        }else{
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Username is empty or null");
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
