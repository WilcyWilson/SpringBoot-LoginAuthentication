package com.fonepay.loginauthentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "login_table")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "registration_id")
    private UserRegister userRegister;

    @Column(name = "login_emailid")
    private String emailId;

    @Column(name = "login_username")
    private String userName;

    @Column(name = "login_password")
    private String password;
}
