/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.brian.assetmanagement.bean.Vendor;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.VendorService;
import org.brian.assetmanagement.util.AlertFactory;
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

    @FXML
    private Button deleteBtn;

    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;

    private ObservableList<Vendor> vendorList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("Inside VendorController::initialize");
        vendorTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vendorTable.setEditable(true);
        setTableColumnProperties();
        populateVendors();
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void delete(ActionEvent event) {
        if (vendorTable.getItems() == null || vendorTable.getItems().isEmpty()) {
            Alert alert = AlertFactory.getAlert(Alert.AlertType.WARNING, "NO_VENDOR_TO_DELETE");
            alert.showAndWait();
        } else {
            List<Vendor> selectedVendors = vendorTable.getSelectionModel().getSelectedItems();
            if (selectedVendors == null || selectedVendors.isEmpty()) {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "SELECT_ONE_VENDOR");
                alert.showAndWait();
            } else {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.CONFIRMATION, "CONFIRM_DELETE_VENDOR");
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    vendorService.deleteInBatch(selectedVendors);
                    populateVendors();
                }
            }
        }

    }
    
    @FXML
    private void handleEditCommitEvent(TableColumn.CellEditEvent<Vendor, String> event){
        String inputFxId = ((TableColumn)event.getSource()).getId();
        LOG.info("Event trigerred from : " + inputFxId);
        Vendor vendor = event.getRowValue();
        switch(inputFxId){
            case "colName":
                vendor.setName(event.getNewValue());
                break;
            case "colPhoneNum":
                vendor.setPhoneNumber(event.getNewValue());
                break;
            case "colEmail":
                vendor.setEmail(event.getNewValue());
                break;
            case "colVendorRep":
                vendor.setVendorRep(event.getNewValue());
        }
        vendorService.save(vendor);
        
    }

    private void setTableColumnProperties() {
        colVendorId.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPhoneNum.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colVendorRep.setCellValueFactory(new PropertyValueFactory<>("vendorRep"));
        colVendorRep.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void populateVendors() {
        vendorList.clear();
        vendorList.addAll(vendorService.getAll());
        vendorTable.setItems(vendorList);
    }

}
