package com.fonepay.loginauthentication.controller;

import com.fonepay.loginauthentication.constants.PathConstants;
import com.fonepay.loginauthentication.dto.*;
import com.fonepay.loginauthentication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MetaTableService metaTableService;

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private MetaApprovalService metaApprovalService;

    @Autowired
    private EditMetaTableService editMetaTableService;

    @PostMapping(PathConstants.SAVE_USER)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserRegisterDTO userDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return registrationService.saveUser(userDto);
    }

    @PostMapping(PathConstants.CHECK_USER)
    @CrossOrigin(origins = "http://localhost:4200")
    // Another way by using server side proxy in frontend
    public ResponseEntity<ResponseDTO> checkUser(@RequestBody UserLoginDTO userLoginDTO) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return loginService.checkUser(userLoginDTO);
    }

    @PostMapping(PathConstants.CHECK_APPROVAL)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> checkApproval(@RequestBody ApprovalDTO approvalDTO) {
        return approvalService.approveUser(approvalDTO);
    }

    @PostMapping(PathConstants.EDIT_USER)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> editUser(@RequestBody EditDTO editDTO) {
        return editService.editUser(editDTO);
    }

    @PostMapping(PathConstants.META_TABLE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> metaTable(@RequestBody MetaTableDTO metaTableDTO) {
        return metaTableService.insertMeta(metaTableDTO);
    }

    @PostMapping(PathConstants.CHECK_META)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> checkMeta(@RequestBody MetaApprovalDTO metaApprovalDTO) {
        return metaApprovalService.checkMeta(metaApprovalDTO);
    }

    @PostMapping(PathConstants.EDIT_META)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResponseDTO> editMeta(@RequestBody EditMetaTableDTO editMetaTableDTO) {
        return editMetaTableService.editMeta(editMetaTableDTO);
    }

    @GetMapping(PathConstants.GET_DATA)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getData() {
        return loginService.getData();
    }

    //    @PostConstruct
    @GetMapping(PathConstants.PROPERTIES_DEMO)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> appProperties() {
        return propertiesService.propertiesFileDemo();
    }
}
