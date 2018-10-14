/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.service;

import java.util.List;
import org.brian.assetmanagement.bean.Asset;

/**
 *
 * @author Suresh
 */
public interface AssetService {
    public void save(Asset asset);
    public Asset getOneAsset(Long id);
    public void delete(Long id);
    public void delete(Asset asset);
    public List<Asset> getAll();         
}
