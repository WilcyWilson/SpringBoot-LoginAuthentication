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

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "APPROVED_BY")
    private String approvedBy;

    @Column(name = "APPROVED_DATE")
    private String approvedDate;

    @Column(name = "EDITED_BY")
    private String editedBy;

    @Column(name = "EDITED_DATE")
    private String editedDate;
}
