/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.service;

import java.util.List;
import org.brian.assetmanagement.bean.Vendor;

/**
 *
 * @author Kavitha
 */
public interface VendorService {
    public void save(Vendor employee);
    public Vendor getVendor(Long employeeId);
    public void delete(Long employeeId);
    public void delete(Vendor employee);
    public List<Vendor> getAll();
}
