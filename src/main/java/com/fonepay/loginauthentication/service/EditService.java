package com.fonepay.loginauthentication.service;

import com.fonepay.loginauthentication.dto.EditDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface EditService {
    ResponseEntity<ResponseDTO> editUser(EditDTO editDTO);
}
