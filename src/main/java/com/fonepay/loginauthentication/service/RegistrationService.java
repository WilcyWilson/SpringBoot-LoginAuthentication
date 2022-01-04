package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.dto.UserLoginDTO;
import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import org.springframework.http.ResponseEntity;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface RegistrationService {
    ResponseEntity<ResponseDTO> saveUser(UserRegisterDTO userDto) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
