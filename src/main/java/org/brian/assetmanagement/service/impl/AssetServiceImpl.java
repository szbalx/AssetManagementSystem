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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kavitha
 */
@Service
public class AssetServiceImpl implements AssetService{
    
    @Autowired
    private AssetRepository assetRepository;
    
    @Override
    public void save(Asset asset){
        System.out.println("Save AssetService called....");
        assetRepository.save(asset);
    }
    
    @Override
    public Asset getOneAsset(Long id){
        return assetRepository.findOne(id);
    }
    
    @Override
    public void delete(Long id){
        assetRepository.delete(id);
    }
    
    @Override
    public void delete(Asset asset){
        assetRepository.delete(asset);
    }
    
    @Override
    public List<Asset> getAll(){
        return assetRepository.findAll();
    }
    
}
