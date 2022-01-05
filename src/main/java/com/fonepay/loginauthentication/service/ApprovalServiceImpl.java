package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.ApprovalDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Checker Maker Concept

@Service
public class ApprovalServiceImpl implements ApprovalService {
    @Autowired
    private LoginRepository loginRepository;

    ResponseDTO responseDTO = new ResponseDTO();

    @Override
    public ResponseEntity<ResponseDTO> approveUser(ApprovalDTO approvalDTO) {
        if (approvalDTO.getApprover() != null || !approvalDTO.getApprover().isEmpty()) {
            UserLogin approver = loginRepository.findByUserName(approvalDTO.getApprover());
            if (approver != null) {
                UserLogin destinationUser = loginRepository.findByUserName(approvalDTO.getDestinationUser());
                if (destinationUser != null) {
                    if (!destinationUser.getCreatedBy().equals(approver.getUserName())) {
                        if(!destinationUser.getStatus()){
                            destinationUser.setStatus(true);
                            loginRepository.save(destinationUser);
                            responseDTO.setResponseMessage("Destination User Successfully Approved");
                            responseDTO.setResponseStatus(true);
                        }else{
                            responseDTO.setResponseMessage("Destination User already aprroved");
                            responseDTO.setResponseStatus(false);
                        }
                    } else {
                        responseDTO.setResponseMessage("Approver and Destination User cannot be same");
                        responseDTO.setResponseStatus(false);
                    }
                } else {
                    responseDTO.setResponseMessage("Destination User doesn't exist");
                    responseDTO.setResponseStatus(false);
                }
            } else {
                responseDTO.setResponseMessage("No such user Exists");
                responseDTO.setResponseStatus(false);
            }
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
