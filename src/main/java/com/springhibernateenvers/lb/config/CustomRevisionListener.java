package com.springhibernateenvers.lb.config;

import com.springhibernateenvers.lb.entity.AuditEnversInfo;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    // Set auditEnversInfo here, Source; like ip, user who is editing
    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setSource("In application");
        auditEnversInfo.setUserId(1L); // current user id here, getCurrentUser()
    }

}
