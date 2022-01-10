package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.EditMetaTableDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface EditMetaTableService {
    ResponseEntity<ResponseDTO> editMeta(EditMetaTableDTO editMetaTableDTO);
}
