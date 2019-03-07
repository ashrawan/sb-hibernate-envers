package com.springhibernateenvers.lb.entity;

import com.springhibernateenvers.lb.config.CustomRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@Entity
@RevisionEntity(CustomRevisionListener.class)
// Modifying Default "REVINFO" table provided by Hibernate enver
public class AuditEnversInfo extends DefaultRevisionEntity {

    private String source;

    private long userId;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
