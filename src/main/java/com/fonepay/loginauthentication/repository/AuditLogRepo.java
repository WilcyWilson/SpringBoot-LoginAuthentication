package com.fonepay.loginauthentication.repository;

import com.fonepay.loginauthentication.entity.UserAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepo extends JpaRepository<UserAuditLog, Long> {
}
