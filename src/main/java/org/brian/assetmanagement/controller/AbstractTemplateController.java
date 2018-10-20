/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.view.ViewResolver;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 *
 * Using template pattern to handle clicking on the masthead.
 * Reduces redundant code across controllers.
 */
@Controller
public class AbstractTemplateController implements Initializable {

    private static final Logger LOG = getLogger(AbstractTemplateController.class);
    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;

    @FXML
    public void handleDashboardVBoxClick() throws IOException {
        LOG.info("Inside AbstractTemplateController::handleDashboardVBoxClick");
        sceneManager.switchScene(ViewResolver.DASHBOARD);
    }

    @FXML
    public void handleAssetVBoxClick() throws IOException {
        LOG.info("Inside AbstractTemplateController::handleAssetVBoxClick");
        sceneManager.switchScene(ViewResolver.ASSETS);
    }

    @FXML
    public void handleEmployeeVBoxClick() throws IOException {
        LOG.info("Inside AbstractTemplateController::handleEmployeeVBoxClick");
        sceneManager.switchScene(ViewResolver.EMPLOYEES);
    }

    @FXML
    public void handleVendorsVBoxClick() throws IOException {
        LOG.info("Inside AbstractTemplateController::handleVendorsVBoxClick");
        sceneManager.switchScene(ViewResolver.VENDORS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  nothing to add here
        LOG.info("Inside AbstractTemplateController::initialize");
    }
}
