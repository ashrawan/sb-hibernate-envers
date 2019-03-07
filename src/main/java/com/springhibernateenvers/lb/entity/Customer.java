package com.springhibernateenvers.lb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Audited
public class Customer {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "bal_amount")
    private Long balAmount;

    // @Audited(targetAuditMode = NOT_AUDITED)
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Asset> assets;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBalAmount() {
        return balAmount;
    }

    public void setBalAmount(Long balAmount) {
        this.balAmount = balAmount;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }
}
