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
public abstract class AbstractTemplateController implements Initializable {

    @Autowired
    @Lazy
    protected FXMLSceneManager sceneManager;

    @FXML
    private void handleDashboardVBoxClick() throws IOException {
        sceneManager.switchScene(ViewResolver.DASHBOARD);
    }

    @FXML
    private void handleAssetVBoxClick() throws IOException {
        sceneManager.switchScene(ViewResolver.ASSETS);
    }

    @FXML
    private void handleEmployeeVBoxClick() throws IOException {
        sceneManager.switchScene(ViewResolver.EMPLOYEES);
    }

    @FXML
    private void handleVendorsVBoxClick() throws IOException {
        sceneManager.switchScene(ViewResolver.VENDORS);
    }
}
