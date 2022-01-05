package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.EditDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.entity.UserAuditLog;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.entity.UserRegister;
import com.fonepay.loginauthentication.repository.AuditLogRepository;
import com.fonepay.loginauthentication.repository.LoginRepository;
import com.fonepay.loginauthentication.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Edit Register User, Don't change Login Data
@Service
public class EditServiceImpl implements EditService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    ResponseDTO responseDTO = new ResponseDTO();

    @Override
    public ResponseEntity<ResponseDTO> editUser(EditDTO editDTO) {
        if (editDTO.getUserName() != null && !editDTO.getUserName().isEmpty()) {
            UserLogin userLogin = loginRepository.findByUserName(editDTO.getUserName());
            if (userLogin != null) {
                UserRegister userRegister = userLogin.getUserRegister();
                UserAuditLog userAuditLog = new UserAuditLog();

                userAuditLog.setFirstName(userRegister.getFirstName());
                userAuditLog.setLastName(userRegister.getLastName());
                userAuditLog.setChangedBy(editDTO.getUserName());
                userAuditLog.setAddress(userRegister.getAddress());
                userAuditLog.setPhoneNo(userRegister.getPhoneNo());
                userAuditLog.setChangedDate(java.time.LocalDateTime.now().toString());

                userRegister.setCreatedDate(java.time.LocalDateTime.now().toString());
                userRegister.setFirstName(editDTO.getFirstName());
                userRegister.setLastName(editDTO.getLastName());
                userRegister.setAddress(editDTO.getAddress());
                userRegister.setPhoneNo(editDTO.getPhoneNo());

                registrationRepository.save(userRegister);
                auditLogRepository.save(userAuditLog);

                responseDTO.setResponseStatus(true);
                responseDTO.setResponseMessage("Register and Audit Log Table Successfully Updated");
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("No such User Exists");
            }
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
