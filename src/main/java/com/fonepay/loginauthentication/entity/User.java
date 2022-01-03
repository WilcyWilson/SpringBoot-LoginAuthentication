package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_emailid")
    private String emailId;

    @Column(name = "user_username")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_firstname")
    private String firstName;

    @Column(name = "user_phoneno")
    private String phoneNo;

    @Column(name = "user_lastname")
    private String lastName;

    @Column(name = "user_address")
    private String address;
}
