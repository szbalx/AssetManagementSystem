/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.repository;

import java.util.List;
import org.brian.assetmanagement.bean.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kavitha
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long>{
    Asset findBySerial(String serial);
    List<Asset> findByAssigned(String assignedTo);
}
