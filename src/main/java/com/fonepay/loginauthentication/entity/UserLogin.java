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
    private Long id;

    @OneToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "registration_id")
    private UserRegister userRegister;

    @Column(name = "login_emailid")
    private String emailId;

    @Column(name = "login_username" , unique=true)
    private String userName;

    @Column(name = "login_password")
    private String password;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "status")
    private Boolean status;
}
