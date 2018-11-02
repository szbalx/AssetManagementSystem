/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.service.impl;

import java.util.List;
import org.brian.assetmanagement.bean.Asset;
import org.brian.assetmanagement.repository.AssetRepository;
import org.brian.assetmanagement.service.AssetService;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kavitha
 * Service implementation for Asset Entity. Corresponding Repository is autowired here so that
 * database is queried according to what is called from controller.
 */
@Service("assetService")
public class AssetServiceImpl implements AssetService{
    private static final Logger LOG = getLogger(AssetServiceImpl.class);
    
    @Autowired
    protected AssetRepository assetRepository;

    
    @Override
    public void save(Asset asset){
        LOG.debug("AssetServiceImpl.save() called");
        assetRepository.save(asset);
    }
    
    @Override
    public void save(List<Asset> assetList){
        LOG.debug("AssetServiceImpl.save(list) called...");
        assetRepository.save(assetList);
    }
    
    @Override
    public Asset getOneAsset(Long id){
        LOG.debug("AssetServiceImpl.getOneAsset() called");
        return assetRepository.findOne(id);
    }
    
    @Override
    public void delete(Long id){
        LOG.debug("AssetServiceImpl.delete(id) called");
        assetRepository.delete(id);
    }
    
    @Override
    public void delete(Asset asset){
        LOG.debug("AssetServiceImpl.delete(Asset) called");
        assetRepository.delete(asset);
    }
    
    @Override
    public List<Asset> getAll(){
        LOG.debug("AssetServiceImpl.getAll() called");
        return assetRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Asset> selectedAssets) {
        assetRepository.deleteInBatch(selectedAssets);
    }

    @Override
    public List<Asset> getAllAssetsAssignedTo(String assignedTo) {
        LOG.info("AssetServiceImpl.getAllAssetsAssignedTo() called");
        return assetRepository.findByAssigned(assignedTo);
    }

}
