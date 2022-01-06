package com.fonepay.loginauthentication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "META_TABLE")
@Data
public class MetaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME" , unique=true)
    private String name;

    @Column(name = "VALUE" , unique=true)
    private String value;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;


}
