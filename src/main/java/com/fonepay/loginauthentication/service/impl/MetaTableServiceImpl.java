package com.fonepay.loginauthentication.service.impl;

import com.fonepay.loginauthentication.entity.MetaTable;
import com.fonepay.loginauthentication.repository.MetaTableRepo;
import com.fonepay.loginauthentication.service.MetaTableService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MetaTableServiceImpl implements MetaTableService {


    private final MetaTableRepo metaTableRepo;

    public MetaTableServiceImpl(MetaTableRepo metaTableRepo) {
        this.metaTableRepo = metaTableRepo;
    }

    @Override
    public String metaTableVale(String name) throws Exception {
        try {
            MetaTable metaTable = metaTableRepo.getMetaTableByValue(name);
            log.info("Meta Table Response: " + metaTable.getValue());
            if (metaTable == null) {
                throw new Exception("Meta Table Exception: " + "meta table with name cannot be found.");
            } else {
                String value = metaTable.getValue();
                return metaTable.getValue();
            }

        } catch (Exception exception) {
            log.error("Meta Table Exception: " + "null ayyo haiii");
            throw new Exception("Meta Table Exception: " + exception.getMessage());
        }
    }

    public static void main (String [] args) {

    }
}
