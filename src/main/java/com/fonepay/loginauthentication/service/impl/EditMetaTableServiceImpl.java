package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.EditMetaTableDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.entity.MetaTable;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepo;
import com.fonepay.loginauthentication.repository.MetaTableRepo;
import com.fonepay.loginauthentication.service.EditMetaTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EditMetaTableServiceImpl implements EditMetaTableService {
    @Autowired
    private MetaTableRepo metaTableRepo;

    @Autowired
    private LoginRepo loginRepository;

    ResponseDTO responseDTO = new ResponseDTO();

    @Override
    public ResponseEntity<ResponseDTO> editMeta(EditMetaTableDTO editMetaTableDTO) {
        try {
            if (editMetaTableDTO.getEditorName() != null || !editMetaTableDTO.getEditorName().isEmpty()) {
                UserLogin userLogin = loginRepository.findByUserName(editMetaTableDTO.getEditorName());
                if (userLogin != null) {
                    MetaTable checkName = metaTableRepo.findByName(editMetaTableDTO.getName());
                    if (checkName != null) {
                        checkName.setValue(editMetaTableDTO.getNewValue());
                        checkName.setEditedBy(editMetaTableDTO.getEditorName());
                        checkName.setEditedDate(java.time.LocalDateTime.now().toString());

                        metaTableRepo.save(checkName);

                        responseDTO.setResponseStatus(true);
                        responseDTO.setResponseMessage("Value successfully edited");
                    } else {
                        responseDTO.setResponseStatus(false);
                        responseDTO.setResponseMessage("Meta Name doesn't exist");
                    }
                } else {
                    responseDTO.setResponseStatus(false);
                    responseDTO.setResponseMessage("Editor Username doesn't exist");
                }
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("Editor is empty or null");
            }
        } catch (Exception e) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Unsuccessful Edit " + e.getMessage());
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
