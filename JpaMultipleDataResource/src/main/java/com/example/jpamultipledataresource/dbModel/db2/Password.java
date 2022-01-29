package com.example.jpamultipledataresource.dbModel.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Password {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "PASS_VALUE")
    private String passValue;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "CREATED_ON")
    private OffsetDateTime createdOn;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "EXPIRES_ON")
    private OffsetDateTime expiresOn;


    @Column(name = "DOMAIN")
    private String domain;

    @Column(name = "DESCR")
    private String descr;

    @Column(name = "PASS_GROUP")
    private String passGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassValue() {
        return passValue;
    }

    public void setPassValue(String passValue) {
        this.passValue = passValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public OffsetDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(OffsetDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPassGroup() {
        return passGroup;
    }

    public void setPassGroup(String passGroup) {
        this.passGroup = passGroup;
    }
}
