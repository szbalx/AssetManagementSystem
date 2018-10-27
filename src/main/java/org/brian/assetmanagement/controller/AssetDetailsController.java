/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.brian.assetmanagement.bean.Asset;
import org.brian.assetmanagement.service.AssetService;
import org.brian.assetmanagement.service.EmployeeService;
import org.brian.assetmanagement.util.AlertFactory;
import static org.brian.assetmanagement.util.ApplicationHelper.emptyValidation;
import static org.brian.assetmanagement.util.ApplicationHelper.validate;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class AssetDetailsController extends AbstractTemplateController {

    private static final Logger LOG = getLogger(AssetDetailsController.class);

    @Autowired
    private AssetService assetService;
    
    @Autowired
    private EmployeeService employeeService;

    @FXML
    private TextField id;

    @FXML
    private TextField type;

    @FXML
    private TextField manufacturer;

    @FXML
    private TextField model;

    @FXML
    private TextField serialNumber;


    @FXML
    private ComboBox assignedTo;

    @FXML
    private DatePicker purchaseDate;

    @FXML
    private TextField warranty;

    @FXML
    private TextField os;

    @FXML
    private TextField hdSize;

    @FXML
    private TextField ram;

    @FXML
    private Button saveBtn;

    @FXML
    private Button resetBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("inside AssetDetailsController:: initializer");
        List<String> names = employeeService.getEmployeeNamesOnly();
        LOG.info("Populating combobox..");
        assignedTo.getItems().clear();
        assignedTo.getItems().addAll(names);
        
    }

    @FXML
    public void saveAsset(ActionEvent event) {
        if (validateAssetValues()) {
            LOG.info("Validation successful inside saveAsset method.......");

            if (id.getText() != null && id.getText().equals("")) {
                // user gave id explicitly . check in the service whether the asset is already in place and update it. else create new asset.
                Asset oldAsset = assetService.getOneAsset(Long.parseLong(id.getText()));
                oldAsset.setType(type.getText());
                oldAsset.setManufacturer(manufacturer.getText());
                oldAsset.setModel(model.getText());
                oldAsset.setSerial(serialNumber.getText());
                oldAsset.setAssignedTo((String) assignedTo.getValue());
                oldAsset.setPurchaseDate(purchaseDate.getValue());
                oldAsset.setWarranty(warranty.getText());
                oldAsset.setOs(os.getText());
                oldAsset.setHdSize(hdSize.getText());
                oldAsset.setRam(ram.getText());
                assetService.save(oldAsset);
            } else {
                Asset asset = new Asset();
                asset.setId(Long.parseLong(id.getText()));
                asset.setType(type.getText());
                asset.setManufacturer(manufacturer.getText());
                asset.setModel(model.getText());
                asset.setSerial(serialNumber.getText());
                asset.setAssignedTo((String) assignedTo.getValue());
                asset.setPurchaseDate(purchaseDate.getValue());
                asset.setWarranty(warranty.getText());
                asset.setOs(os.getText());
                asset.setHdSize(hdSize.getText());
                asset.setRam(ram.getText());
                assetService.save(asset);
            }
            // After saving the asset details, clear all fields in UI
            refreshForm();
            showCreateAlert();
        }
    }

    private boolean validateAssetValues() {

        return validate("id", id.getText(), "^[\\d\\s]+$") &&
                validate("type", type.getText(), "^[\\w]+$")
                && validate("manufacturer", manufacturer.getText(), "^[\\w\\s]+$")
                && validate("model", model.getText(), "^[\\w-\\s]+$")
                && validate("serialNumber", serialNumber.getText(), "^[\\w]+$")
                // no need to validate values from assignedTo combo box as it is populated from database and not inputted by user.
                && emptyValidation("purchaseDate", purchaseDate.getEditor().getText().isEmpty())
                && validate("warranty", warranty.getText(), "^[\\w\\s]+$")
                && validate("os", os.getText(), "^[\\w\\s]+$")
                && validate("hdSize", hdSize.getText(), "^[\\w\\s]+$")
                && validate("ram", ram.getText(), "^[\\w\\s]+$");
    }

    @FXML
    private void reset(ActionEvent event) {
        refreshForm();
    }

    private void refreshForm() {
        id.clear();
        type.clear();
        manufacturer.clear();
        model.clear();
        serialNumber.clear();
        assignedTo.getSelectionModel().clearSelection();
        purchaseDate.getEditor().clear();
        warranty.clear();
        os.clear();
        hdSize.clear();
        ram.clear();
    }

    private void showCreateAlert() {
        Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "CREATED_ASSET");
        alert.showAndWait();
    }
    
}
