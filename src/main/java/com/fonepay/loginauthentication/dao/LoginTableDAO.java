package com.fonepay.loginauthentication.dao;

import com.fonepay.loginauthentication.dto.GetDataDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Map;

public interface LoginTableDAO {
    List<GetDataDTO> searchDetail(Map<String,String> allRequestParams, Pageable pageable);
    Long getPaymentClientAppUserDetailsCount(Map<String, String> allRequestParams);
}
