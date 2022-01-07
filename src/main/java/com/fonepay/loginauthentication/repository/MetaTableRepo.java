package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.MetaTable;
import com.fonepay.loginauthentication.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaTableRepo extends JpaRepository<MetaTable, Long> {
    @Query(value = "SELECT * FROM meta_table mt WHERE mt.NAME = :name AND mt.IS_ENABLED IS TRUE", nativeQuery = true) //native query
    MetaTable getMetaTableByValue(String name);
}
