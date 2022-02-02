package com.fonepay.loginauthentication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "meta_table")
@Data
public class MetaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private String value;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private String approvedDate;

    @Column(name = "edited_by")
    private String editedBy;

    @Column(name = "edited_date")
    private String editedDate;
}
