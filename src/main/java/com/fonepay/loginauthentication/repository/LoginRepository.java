package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Long> {
}
