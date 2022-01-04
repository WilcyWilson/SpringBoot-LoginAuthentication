package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.UserLogin;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Long> {
    @Query("select ul from UserLogin ul where ul.userName= :userName")
    UserLogin findByUserName(String userName);
}
