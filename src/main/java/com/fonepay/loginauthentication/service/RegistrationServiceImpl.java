package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.ResponseDTO;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
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

            String cipherText = getCipherText(userRegisterDTO);

            userRegister.setUserName(userRegisterDTO.getUserName());
            userRegister.setFirstName(userRegisterDTO.getFirstName());
            userRegister.setLastName(userRegisterDTO.getLastName());
            userRegister.setAddress(userRegisterDTO.getAddress());
            userRegister.setPassword(cipherText);
            userRegister.setPhoneNo(userRegisterDTO.getPhoneNo());
            userRegister.setEmailId(userRegisterDTO.getEmailId());

            userLogin.setUserName(userRegister.getUserName());
            userLogin.setEmailId(userRegister.getEmailId());
            userLogin.setPassword(cipherText);
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

    @Override
    public String getCipherText(UserRegisterDTO userRegisterDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String input = userRegisterDTO.getPassword();
        SecretKey key = EncryptionServiceImpl.generateKey(userRegisterDTO.getEmailId(), userRegisterDTO.getUserName());
        IvParameterSpec ivParameterSpec = EncryptionServiceImpl.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = EncryptionServiceImpl.encrypt(algorithm, input, key, ivParameterSpec);
        return cipherText;
    }

    @Override
    public String getPlainText(UserLoginDTO userLoginDTO, String cipherText) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = EncryptionServiceImpl.generateKey(userLoginDTO.getEmailId(), userLoginDTO.getUserName());
        IvParameterSpec ivParameterSpec = EncryptionServiceImpl.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = EncryptionServiceImpl.decrypt(algorithm, cipherText, key, ivParameterSpec);
        return plainText;
    }

}
