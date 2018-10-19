/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
 */
@Controller
public class DashboardController extends AbstractTemplateController {

    @FXML
    private Hyperlink viewAsset;

    @FXML
    private Hyperlink addNewAsset;

    @FXML
    private Hyperlink viewEmployee;

    @FXML
    private Hyperlink addNewEmployee;

    @FXML
    private Hyperlink viewVendor;

    @FXML
    private Hyperlink addNewVendor;

    @FXML
    private VBox dVBox;

    @FXML
    private VBox aVBox;

    @FXML
    private VBox eVBox;

    @FXML
    private VBox vVBox;


    private static final Logger LOG = getLogger(DashboardController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleHyperLinkForward(ActionEvent event) throws IOException {
        // Facade pattern implemented here. Dashboard is the facade or interface which gives you a way to interract with multiple controllers.
        // once scene is switched, the corresponding controller takes over the control from dashboard.
        Hyperlink currentLink = (Hyperlink) event.getSource();
        LOG.info("inside handleHyperLinkForward. Event trigerred from: " + currentLink.getText());
        delegateToFacade(currentLink.getText());
    }

    private void delegateToFacade(String sourceName) {
        switch (sourceName) {
            case "View Assets":
                sceneManager.switchScene(ViewResolver.ASSETS);
                break;
            case "Add New Asset":
                sceneManager.switchScene(ViewResolver.ASSET_DETAILS);
                break;
            case "View Employees":
                sceneManager.switchScene(ViewResolver.EMPLOYEES);
                break;
            case "Add New Employee":
                // will add corresponding file once created.
                break;
            case "View Vendors":
                sceneManager.switchScene(ViewResolver.VENDORS);
                break;
            case "Add New Vendor":
                // will add corresponding file once created.
                break;
        }
    }
}
