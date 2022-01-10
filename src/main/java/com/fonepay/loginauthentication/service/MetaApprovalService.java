package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.MetaApprovalDTO;
import com.fonepay.loginauthentication.dto.MetaTableDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface MetaApprovalService {
    ResponseEntity<ResponseDTO> checkMeta(MetaApprovalDTO metaApprovalDTO);
}
