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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.view.ViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class DashboardController implements Initializable {

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

    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Facade pattern implemented here. Dashboard is the facade or interface which gives you a way to interract with multiple controllers.
        // once scene is switched, the corresponding controller takes over the control from dashboard.
        viewAsset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.ASSETS);
            }
        });
        addNewAsset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.ASSET_DETAILS);
            }
        });
        viewEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.EMPLOYEES);
            }
        });
        dVBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sceneManager.switchScene(ViewResolver.DASHBOARD);
            }
        });
        aVBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sceneManager.switchScene(ViewResolver.ASSETS);
            }
        });
        eVBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sceneManager.switchScene(ViewResolver.EMPLOYEES);
            }
        });
        // the following pages will be shown once they are implemented.
        // for the time being, redirection is done to the same page.
        addNewEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.DASHBOARD);
            }
        });
        viewVendor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.DASHBOARD);
            }
        });
        addNewVendor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sceneManager.switchScene(ViewResolver.DASHBOARD);
            }
        });

        vVBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sceneManager.switchScene(ViewResolver.DASHBOARD);
            }
        });
    }

}
