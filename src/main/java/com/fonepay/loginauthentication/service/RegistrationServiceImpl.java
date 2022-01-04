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
public class RegistrationServiceImpl implements RegistrationService  {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private LoginRepository loginRepository;

    // Saving User data from the frontend to backend
    @Override
    public ResponseEntity<Object> saveUser(UserRegisterDTO userRegisterDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
          UserRegister userRegister = new UserRegister();
          UserLogin userLogin = new UserLogin();
          UserLoginDTO userLoginDTO = new UserLoginDTO();

          String input = userRegisterDTO.getPassword();
          SecretKey key = EncryptionServiceImpl.generateKey(userRegisterDTO.getFirstName(),userRegisterDTO.getLastName());
          IvParameterSpec ivParameterSpec = EncryptionServiceImpl.generateIv();
          String algorithm = "AES/CBC/PKCS5Padding";
          String cipherText = EncryptionServiceImpl.encrypt(algorithm, input, key, ivParameterSpec);
          String plainText = EncryptionServiceImpl.decrypt(algorithm, cipherText, key, ivParameterSpec);

          userRegister.setUserName(userRegisterDTO.getUserName());
          userRegister.setFirstName(userRegisterDTO.getFirstName());
          userRegister.setLastName(userRegisterDTO.getLastName());
          userRegister.setAddress(userRegisterDTO.getAddress());
          userRegister.setPassword(cipherText);
          userRegister.setPhoneNo(userRegisterDTO.getPhoneNo());
          userRegister.setEmailId(userRegisterDTO.getEmailId());

          userLoginDTO.setUserName(userRegisterDTO.getUserName());
          userLoginDTO.setEmailId(userRegisterDTO.getEmailId());
          userLoginDTO.setPassword(cipherText);

          userLogin.setUserName(userLoginDTO.getUserName());
          userLogin.setEmailId(userLoginDTO.getEmailId());
          userLogin.setPassword(cipherText);
          userLogin.setUserRegister(userRegister);

          registrationRepository.save(userRegister);
          loginRepository.save(userLogin);

          return new ResponseEntity<>(userRegisterDTO, HttpStatus.OK);
    }
}
