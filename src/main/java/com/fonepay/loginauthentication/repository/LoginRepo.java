package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<UserLogin, Long> {
    @Query("select ul from UserLogin ul where ul.userName= :userName") //JPQL
    UserLogin findByUserName(String userName);
//    UserLogin findUserLoginByUserName(String username); // Spring Data
}
