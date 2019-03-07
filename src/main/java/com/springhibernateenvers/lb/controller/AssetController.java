package com.springhibernateenvers.lb.controller;

import com.springhibernateenvers.lb.dto.AssetDTO;
import com.springhibernateenvers.lb.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<?> getAllAssets() {

        return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable long id) {

        return new ResponseEntity<>(assetService.getAssetById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAsset(@RequestBody AssetDTO assetDTO) {

        return new ResponseEntity<>(assetService.createAsset(assetDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateAsset(@RequestBody AssetDTO assetDTO) {

        return new ResponseEntity<>(assetService.updateAsset(assetDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable long id) {

        return new ResponseEntity<>(assetService.deleteAsset(id), HttpStatus.OK);
    }
}
