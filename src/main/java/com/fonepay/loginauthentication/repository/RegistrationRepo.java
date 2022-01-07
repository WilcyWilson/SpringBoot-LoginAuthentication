package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends JpaRepository<UserRegister, Long> {

}
