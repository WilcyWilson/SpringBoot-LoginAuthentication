package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "registration_table")
public class UserRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REGISTRATION_ID")
    private long id;

    @Column(name = "REGISTRATION_EMAILID")
    private String emailId;

    @Column(name = "REGISTRATION_USERNAME", unique=true)
    private String userName;

    @Column(name = "REGISTRATION_PASSWORD")
    private String password;

    @Column(name = "REGISTRATION_FIRSTNAME")
    private String firstName;

    @Column(name = "REGISTRATION_PHONENO")
    private String phoneNo;

    @Column(name = "REGISTRATION_LASTNAME")
    private String lastName;

    @Column(name = "REGISTRATION_ADDRESS")
    private String address;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @OneToOne(mappedBy = "userRegister")
    private UserLogin userLogin;
}
