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
    @Column(name = "registration_id")
    private long id;

    @Column(name = "registration_emailid")
    private String emailId;

    @Column(name = "registration_username", unique=true)
    private String userName;

    @Column(name = "registration_password")
    private String password;

    @Column(name = "registration_firstname")
    private String firstName;

    @Column(name = "registration_phoneno")
    private String phoneNo;

    @Column(name = "registration_lastname")
    private String lastName;

    @Column(name = "registration_address")
    private String address;

    @Column(name = "created_date")
    private String createdDate;

    @OneToOne(mappedBy = "userRegister")
    private UserLogin userLogin;
}
