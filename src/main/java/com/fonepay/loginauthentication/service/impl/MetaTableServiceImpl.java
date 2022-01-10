package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dto.MetaTableDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.dto.UserRegisterDTO;
import com.fonepay.loginauthentication.entity.MetaTable;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepo;
import com.fonepay.loginauthentication.repository.MetaTableRepo;
import com.fonepay.loginauthentication.service.MetaTableService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//@Log4j2
public class MetaTableServiceImpl implements MetaTableService {

    @Autowired
    private MetaTableRepo metaTableRepo;

    @Autowired
    private LoginRepo loginRepository;


    ResponseDTO responseDTO = new ResponseDTO();

    @Override
    public ResponseEntity<ResponseDTO> insertMeta(MetaTableDTO metaTableDTO) {
        try {
            if (metaTableDTO.getUserName() != null || !metaTableDTO.getUserName().isEmpty()) {
                UserLogin userLogin = loginRepository.findByUserName(metaTableDTO.getUserName());
                if (userLogin != null) {
                    MetaTable checkName = metaTableRepo.findByName(metaTableDTO.getName());
                    if (checkName == null) {
                        MetaTable metaTable = new MetaTable();

                        metaTable.setName(metaTableDTO.getName());
                        metaTable.setDescription(metaTableDTO.getDescription());
                        metaTable.setCreatedBy(metaTableDTO.getUserName());
                        metaTable.setCreatedDate(java.time.LocalDateTime.now().toString());
                        metaTable.setValue("false");
                        metaTable.setApprovedBy("empty");
                        metaTable.setApprovedDate("empty");

                        metaTableRepo.save(metaTable);

                        responseDTO.setResponseStatus(true);
                        responseDTO.setResponseMessage("Data inserted Successfully. Find approval to activate.");
                    } else {
                        responseDTO.setResponseStatus(false);
                        responseDTO.setResponseMessage("Meta Name already exists");
                    }
                } else {
                    responseDTO.setResponseStatus(false);
                    responseDTO.setResponseMessage("Username doesn't exist");
                }
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("Username is empty or null");
            }
        } catch (Exception e) {
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Unsuccessful Insertion " + e.getMessage());
        } finally {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }


//    private final MetaTableRepo metaTableRepo;
//
//    public MetaTableServiceImpl(MetaTableRepo metaTableRepo) {
//        this.metaTableRepo = metaTableRepo;
//    }

//    @Override
//    public String metaTableVale(String name) throws Exception {
//        try {
//            MetaTable metaTable = metaTableRepo.getMetaTableByValue(name);
//            log.info("Meta Table Response: " + metaTable.getValue());
//            if (metaTable == null) {
//                log.error("Meta table data null ayo hai");
//                throw new Exception("Meta Table Exception: " + "meta table with name cannot be found.");
//            } else {
//                String value = metaTable.getValue();
//                return metaTable.getValue();
//            }
//
//        } catch (Exception exception) {
//            log.error("Meta Table Exception: " + "null ayyo haiii");
//            throw new Exception("Meta Table Exception: " + exception.getMessage());
//        }
//    }
//
//    public static void main (String [] args) {
//
//    }
}
