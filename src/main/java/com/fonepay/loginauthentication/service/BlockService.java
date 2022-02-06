package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.ApprovalDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface BlockService {
    ResponseEntity<ResponseDTO> approveUser(ApprovalDTO approvalDTO);
}
