package com.springhibernateenvers.lb.utils.mapper;

import com.springhibernateenvers.lb.dto.AssetDTO;
import com.springhibernateenvers.lb.entity.Asset;

public class AssetMapper {

    public static AssetDTO mapToDTO(Asset asset) {
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setAssetId(asset.getId());
        assetDTO.setAssetName(asset.getAssetName());
        assetDTO.setAssetType(asset.getAssetType());
        assetDTO.setCustomerDTO(CustomerMapper.mapToDTO(asset.getCustomer()));
        return assetDTO;
    }

    public static Asset mapToEntity(AssetDTO assetDTO) {
        Asset asset = new Asset();
        asset.setId(assetDTO.getAssetId());
        asset.setAssetName(assetDTO.getAssetName());
        asset.setAssetType(assetDTO.getAssetType());
        asset.setCustomer(CustomerMapper.mapToEntity(assetDTO.getCustomerDTO()));
        return asset;
    }
}
