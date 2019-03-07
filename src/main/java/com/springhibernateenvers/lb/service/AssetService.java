package com.springhibernateenvers.lb.service;

import com.springhibernateenvers.lb.dto.AssetDTO;

import java.util.List;

public interface AssetService {

    List<AssetDTO> getAllAssets();

    AssetDTO getAssetById(Long id);

    AssetDTO createAsset(AssetDTO assetDTO);

    AssetDTO updateAsset(AssetDTO assetDTO);

    boolean deleteAsset(Long id);
}
