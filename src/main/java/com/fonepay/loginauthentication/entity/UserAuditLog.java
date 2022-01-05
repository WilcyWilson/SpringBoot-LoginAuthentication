package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// Table name always capital

@Data
@NoArgsConstructor
@Entity
@Table(name = "auditlog_table")
public class UserAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auditlog_id")
    private long id;

    @Column(name = "auditlog_firstname")
    private String firstName;

    @Column(name = "auditlog_phoneno")
    private String phoneNo;

    @Column(name = "auditlog_lastname")
    private String lastName;

    @Column(name = "auditlog_address")
    private String address;

    @Column(name = "changed_by")
    private String changedBy;

    @Column(name = "changed_date")
    private String changedDate;
}
