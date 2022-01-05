package com.fonepay.loginauthentication.controller;

import com.fonepay.loginauthentication.constants.PathConstants;
import com.fonepay.loginauthentication.dto.*;
import com.fonepay.loginauthentication.service.ApprovalService;
import com.fonepay.loginauthentication.service.EditService;
import com.fonepay.loginauthentication.service.LoginService;
import com.fonepay.loginauthentication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private EditService editService;

    @PostMapping(PathConstants.SAVE_USER)
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserRegisterDTO userDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

         return registrationService.saveUser(userDto);
    }

    @PostMapping(PathConstants.CHECK_USER)
    public ResponseEntity<ResponseDTO> checkUser(@RequestBody UserLoginDTO userLoginDTO) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return loginService.checkUser(userLoginDTO);
    }

    @PostMapping(PathConstants.CHECK_APPROVAL)
    public ResponseEntity<ResponseDTO> checkApproval(@RequestBody ApprovalDTO approvalDTO) {
        return approvalService.approveUser(approvalDTO);
    }

    @PostMapping(PathConstants.EDIT_USER)
    public ResponseEntity<ResponseDTO> editUser(@RequestBody EditDTO editDTO) {
        return editService.editUser(editDTO);
    }
}
