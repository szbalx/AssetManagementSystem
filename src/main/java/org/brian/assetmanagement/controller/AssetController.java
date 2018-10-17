/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brian.assetmanagement.bean.Asset;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.AssetService;
import org.brian.assetmanagement.view.ViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class AssetController implements Initializable{
    
    @FXML
    private TableView<Asset> assetTable;
    
    @FXML
    private TableColumn<Asset, Long> colId;
    
    @FXML
    private TableColumn<Asset, String> colType;
    
    @FXML
    private TableColumn<Asset, String> colManufacturer;
    
    @FXML
    private TableColumn<Asset, String> colModel;
    
    @FXML
    private TableColumn<Asset, String> colSerial;
    
    @FXML
    private TableColumn<Asset, String> colAssignedTo;
    
    @Autowired
    private AssetService assetService;
    
    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;
    
    private ObservableList<Asset> assetList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assetTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTableColumnProperties();
        populateAssets();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }


    private void populateAssets() {
        assetList.clear();
        createDummyAssets();
        assetList.addAll(assetService.getAll());
        assetTable.setItems(assetList);
    }

    private void setTableColumnProperties() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
    }

    private void createDummyAssets() {
        Asset asset = new Asset();
        asset.setId(1L);
        asset.setType("Laptop");
        asset.setManufacturer("Lenovo");
        asset.setModel("00001");
        asset.setSerial("24647654725");
        asset.setAssignedTo("Mark Smith");
        assetService.save(asset);
        asset = new Asset();
        asset.setId(2L);
        asset.setType("Laptop");
        asset.setManufacturer("Lenovo");
        asset.setModel("asdf123");
        asset.setSerial("00253242345456");
        asset.setAssignedTo("Brian Stoiber");
        assetService.save(asset);
    }
    
        
    @FXML
    private void handleEmployeesClick() throws IOException{
        sceneManager.switchScene(ViewResolver.EMPLOYEES);
    }
}
