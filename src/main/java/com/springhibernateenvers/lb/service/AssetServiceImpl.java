package com.springhibernateenvers.lb.service;

import com.springhibernateenvers.lb.dto.AssetDTO;
import com.springhibernateenvers.lb.entity.Asset;
import com.springhibernateenvers.lb.repository.AssetRepository;
import com.springhibernateenvers.lb.utils.exceptions.DataNotFoundException;
import com.springhibernateenvers.lb.utils.mapper.AssetMapper;
import com.springhibernateenvers.lb.utils.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<AssetDTO> getAllAssets() {
        return assetRepository.findAll()
                .stream()
                .map(asset -> AssetMapper.mapToDTO(asset))
                .collect(Collectors.toList());
    }

    @Override
    public AssetDTO getAssetById(Long id) {
        Asset asset = assetRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Asset Not Found"));
        return AssetMapper.mapToDTO(asset);
    }

    @Override
    public AssetDTO createAsset(AssetDTO assetDTO) {
        Asset asset = assetRepository.save(AssetMapper.mapToEntity(assetDTO));
        return AssetMapper.mapToDTO(asset);
    }

    @Override
    public AssetDTO updateAsset(AssetDTO assetDTO) {
        Asset returnedAsset = assetRepository.findById(assetDTO.getAssetId())
                .orElseThrow(() -> new DataNotFoundException("Asset Not Found"));
        returnedAsset.setAssetName(assetDTO.getAssetName());
        returnedAsset.setAssetType(assetDTO.getAssetType());
        if (assetDTO.getCustomerDTO() != null) {
            returnedAsset.setCustomer(CustomerMapper.mapToEntity(assetDTO.getCustomerDTO()));
        }
        return AssetMapper.mapToDTO(assetRepository.save(returnedAsset));
    }

    @Override
    public boolean deleteAsset(Long id) {
        Asset returnedAsset = assetRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Asset Not Found"));
        assetRepository.delete(returnedAsset);
        return true;
    }
}
