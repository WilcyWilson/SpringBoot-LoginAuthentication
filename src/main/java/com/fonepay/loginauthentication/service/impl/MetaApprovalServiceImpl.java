package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.MetaApprovalDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.entity.MetaTable;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepo;
import com.fonepay.loginauthentication.repository.MetaTableRepo;
import com.fonepay.loginauthentication.service.MetaApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MetaApprovalServiceImpl implements MetaApprovalService {
    @Autowired
    private MetaTableRepo metaTableRepo;

    @Autowired
    private LoginRepo loginRepository;

    ResponseDTO responseDTO = new ResponseDTO();

    @Override
    public ResponseEntity<ResponseDTO> checkMeta(MetaApprovalDTO metaApprovalDTO) {
        try {
            if (metaApprovalDTO.getApprover() != null || !metaApprovalDTO.getApprover().isEmpty()) {
                UserLogin userLogin = loginRepository.findByUserName(metaApprovalDTO.getApprover());
                if (userLogin != null) {
                    MetaTable checkName = metaTableRepo.findByName(metaApprovalDTO.getName());
                    if (checkName != null) {
                        if (!checkName.getCreatedBy().equals(metaApprovalDTO.getApprover())) {
                            if(!checkName.getIsEnabled()){
                                checkName.setApprovedDate(java.time.LocalDateTime.now().toString());
                                checkName.setApprovedBy(metaApprovalDTO.getApprover());
                                checkName.setIsEnabled(true);

                                metaTableRepo.save(checkName);

                                responseDTO.setResponseStatus(true);
                                responseDTO.setResponseMessage("Meta Data with name " + metaApprovalDTO.getName() + " activated successfully");
                            }else{
                                responseDTO.setResponseStatus(true);
                                responseDTO.setResponseMessage(metaApprovalDTO.getName() + " is already active. You buffoon.");
                            }
                        } else {
                            responseDTO.setResponseStatus(false);
                            responseDTO.setResponseMessage("Creator and Approver cannot be same");
                        }
                    } else {
                        responseDTO.setResponseStatus(false);
                        responseDTO.setResponseMessage("Meta Name doesn't exist");
                    }
                } else {
                    responseDTO.setResponseStatus(false);
                    responseDTO.setResponseMessage("Approver Username doesn't exist");
                }
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("Aprrover is empty or null");
            }
        } catch (Exception e) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Unsuccessful Activation " + e.getMessage());
        }
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        
    }
}
