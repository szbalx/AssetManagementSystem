/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brian.assetmanagement.bean.Asset;
import org.brian.assetmanagement.service.AssetService;
//import org.brian.assetmanagement.service.impl.AssetServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    private ObservableList<Asset> assetList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        assetTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTableColumnProperties();
        populateAssets();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }


    private void populateAssets() {
        assetList.clear();
        Asset asset = new Asset();
        asset.setType("Laptop");
        asset.setManufacturer("Lenovo");
        asset.setModel("00001");
        asset.setSerial("24647654725");
        asset.setAssignedTo("Mark Smith");
        assetList.add(asset);
        assetTable.setItems(assetList);
//        The following code is throwing null pointer exception although it would work in normal Spring Boot project
//        TODO: identify why the repository is null in the spring context and resolve.
//        if(assetService.getAll() != null ){
//            assetList.addAll(assetService.getAll());
//            assetTable.setItems(assetList);
//        }
    }

    private void setTableColumnProperties() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
    }
    
}
