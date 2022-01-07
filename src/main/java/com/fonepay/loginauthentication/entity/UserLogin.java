package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOGIN_TABLE")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOGIN_ID")
    private long id;

    @OneToOne
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
    private UserRegister userRegister;

    @Column(name = "LOGIN_EMAILID")
    private String emailId;

    @Column(name = "LOGIN_USERNAME" , unique=true)
    private String userName;

    @Column(name = "LOGIN_PASSWORD")
    private String password;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "STATUS")
    private Boolean status;
}
