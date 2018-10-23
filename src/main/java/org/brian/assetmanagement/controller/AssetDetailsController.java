/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class AssetDetailsController extends AbstractTemplateController{
    private static final Logger LOG = getLogger(AssetDetailsController.class);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("inside AssetDetailsController:: initializer");
    }
    
}
