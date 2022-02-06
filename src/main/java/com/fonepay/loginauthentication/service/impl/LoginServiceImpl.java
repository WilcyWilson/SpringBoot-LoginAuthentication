package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.dao.LoginTableDAO;
import com.fonepay.loginauthentication.dto.GetDataDTO;
import com.fonepay.loginauthentication.dto.ResponseDTO;
import com.fonepay.loginauthentication.dto.UserLoginDTO;
import com.fonepay.loginauthentication.entity.UserLogin;
import com.fonepay.loginauthentication.repository.LoginRepo;
import com.fonepay.loginauthentication.service.EncryptionService;
import com.fonepay.loginauthentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepo loginRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private LoginTableDAO loginTableDAO;

    private final ResponseDTO responseDTO = new ResponseDTO();

    public ResponseEntity<ResponseDTO> checkUser(UserLoginDTO userLoginDTO) {
        if(userLoginDTO.getUserName()!=null || !userLoginDTO.getUserName().isEmpty()){
            UserLogin userLogin=loginRepository.findByUserName(userLoginDTO.getUserName());
            if(userLogin != null) {
                if (encryptionService.decrypt(userLogin.getPassword(),userLogin.getUserName()).equals(userLoginDTO.getPassword())) {
                    responseDTO.setResponseStatus(true);
                    responseDTO.setResponseMessage("Username and password Correct. Login Successful. Welcome Kale Dai.");
                } else {
                    responseDTO.setResponseStatus(false);
                    responseDTO.setResponseMessage("Username or password Incorrect");
                }
            } else {
                responseDTO.setResponseStatus(false);
                responseDTO.setResponseMessage("Username not found");
            }
        }else{
            responseDTO.setResponseStatus(false);
            responseDTO.setResponseMessage("Username is empty or null");
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getData(Map<String, String> allRequestParams,int page, int size){
        if(!loginRepository.findAll().isEmpty()){
            Pageable paging = PageRequest.of(page, size);

            List<GetDataDTO> getDataDTOList = loginTableDAO.searchDetail(allRequestParams, paging);
            Long count = loginTableDAO.countDetail(allRequestParams);

            Map<String, Object> response = new HashMap<>();
            response.put("logins", getDataDTOList);
            response.put("totalItems", count);

            return new ResponseEntity<>(response,HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
