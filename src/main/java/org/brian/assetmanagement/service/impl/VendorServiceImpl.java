/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.service.impl;

import java.util.List;
import org.brian.assetmanagement.bean.Vendor;
import org.brian.assetmanagement.repository.VendorRepository;
import org.brian.assetmanagement.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kavitha
 */
@Service("vendorService")
public class VendorServiceImpl implements VendorService{
    
    @Autowired
    public VendorRepository vendorRepo;

    @Override
    public void save(Vendor vendor) {
        vendorRepo.save(vendor);
    }

    @Override
    public Vendor getVendor(Long vendorId) {
        return vendorRepo.findOne(vendorId);
    }

    @Override
    public void delete(Long vendorId) {
        vendorRepo.delete(vendorId);
    }

    @Override
    public void delete(Vendor vendor) {
        vendorRepo.delete(vendor);
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepo.findAll();
    }
    
}
