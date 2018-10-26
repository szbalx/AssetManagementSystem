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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brian.assetmanagement.bean.Vendor;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.VendorService;
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
public class VendorController extends AbstractTemplateController {
    private static final Logger LOG = getLogger(VendorController.class);
    
    @Autowired
    private VendorService vendorService;
    
    @FXML
    private TableView<Vendor> vendorTable;
    
    @FXML
    private TableColumn<Vendor, Long> colVendorId;
    
    @FXML
    private TableColumn<Vendor, String> colName;
    
    @FXML
    private TableColumn<Vendor, String> colPhoneNum;
    
    @FXML
    private TableColumn<Vendor, String> colEmail;
    
    @FXML
    private TableColumn<Vendor, String> colVendorRep;
    
    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;
    
    private ObservableList<Vendor> vendorList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("Inside EmployeeController::initialize");
        vendorTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTableColumnProperties();
        populateVendors();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    
    private void setTableColumnProperties() {
        colVendorId.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colVendorRep.setCellValueFactory(new PropertyValueFactory<>("vendorRep"));
    }

    private void populateVendors() {
        vendorList.clear();
        vendorList.addAll(vendorService.getAll());
        vendorTable.setItems(vendorList);
    }

}
