package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// Table name always capital

@Data
@NoArgsConstructor
@Entity
@Table(name = "AUDITLOG_TABLE")
public class UserAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUDITLOG_ID")
    private long id;

    @Column(name = "AUDITLOG_FIRSTNAME")
    private String firstName;

    @Column(name = "AUDITLOG_PHONENO")
    private String phoneNo;

    @Column(name = "AUDITLOG_LASTNAME")
    private String lastName;

    @Column(name = "AUDITLOG_ADDRESS")
    private String address;

    @Column(name = "CHANGED_BY")
    private String changedBy;

    @Column(name = "CHANGED_DATA")
    private String changedDate;

    @Column(name = "REGISTRATION_AUDIT_ID")
    private Long registrationAuditId;
}
