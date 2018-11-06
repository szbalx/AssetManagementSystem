/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import static org.brian.assetmanagement.util.ApplicationHelper.validate;
import javafx.scene.control.TextField;
import org.brian.assetmanagement.bean.Vendor;
import org.brian.assetmanagement.service.VendorService;
import org.brian.assetmanagement.util.AlertFactory;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class VendorDetailsController extends AbstractTemplateController {

    private static final Logger LOG = getLogger(VendorDetailsController.class);

    @Autowired
    private VendorService vendorService;

    @FXML
    private TextField vendorId;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField vendorRep;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("inside VendorDetailsController:: initializer");
    }

    @FXML
    private void reset(ActionEvent event) {
        refreshForm();
    }

    @FXML
    private void saveVendor(ActionEvent event) {
        if (validateVendorDetails()) {
            LOG.info("vendor details validated....inside VendorDetailsController:: saveVendor");
            if (vendorId.getText() != null && vendorId.getText().equals("")) {
                Vendor oldVendor = vendorService.getVendor(Long.parseLong(vendorId.getText()));
                oldVendor.setName(name.getText());
                oldVendor.setPhoneNumber(phoneNumber.getText());
                oldVendor.setEmail(email.getText());
                oldVendor.setVendorRep(vendorRep.getText());
                vendorService.save(oldVendor);
            } else {
                Vendor vendor = new Vendor();
                vendor.setVendorID(Long.parseLong(vendorId.getText()));
                vendor.setName(name.getText());
                vendor.setPhoneNumber(phoneNumber.getText());
                vendor.setEmail(email.getText());
                vendor.setVendorRep(vendorRep.getText());
                vendorService.save(vendor);
            }
            refreshForm();
            showCreateAlert();
        }

    }

    private void refreshForm() {
        vendorId.clear();
        name.clear();
        phoneNumber.clear();
        email.clear();
        vendorRep.clear();
    }

    private boolean validateVendorDetails() {
        return validate("vendorId", vendorId.getText(), "^[\\d\\s]+$")
                && validate("name", name.getText(), "^[A-Za-z\\s]+$")
                && validate("phoneNumber", phoneNumber.getText(), "^[\\d-.]+$")
                && validate("email", email.getText(), "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
                && validate("vendorRep", vendorRep.getText(), "^[\\w\\s]+$");
    }

    private void showCreateAlert() {
        Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "CREATED_VENDOR");
        alert.showAndWait();
    }
}
