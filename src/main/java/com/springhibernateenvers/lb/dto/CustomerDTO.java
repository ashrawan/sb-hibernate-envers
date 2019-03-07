package com.springhibernateenvers.lb.dto;

import java.util.Set;

public class CustomerDTO {

    private Long customerId;

    private String fullName;

    private Long balAmount;

    private Set<AssetDTO> assetDTOSet;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Set<AssetDTO> getAssetDTOSet() {
        return assetDTOSet;
    }

    public void setAssetDTOSet(Set<AssetDTO> assetDTOSet) {
        this.assetDTOSet = assetDTOSet;
    }
}
