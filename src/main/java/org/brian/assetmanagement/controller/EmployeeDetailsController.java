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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.brian.assetmanagement.bean.Employee;
import org.brian.assetmanagement.service.EmployeeService;
import org.brian.assetmanagement.util.AlertFactory;
import static org.brian.assetmanagement.util.ApplicationHelper.validate;
import static org.brian.assetmanagement.util.ApplicationHelper.emptyValidation;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Kavitha
 */
@Controller
public class EmployeeDetailsController extends AbstractTemplateController {

    private static final Logger LOG = getLogger(EmployeeDetailsController.class);

    @Autowired
    private EmployeeService employeeService;

    @FXML
    private TextField empId;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField email;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("inside EmployeeDetailsController:: initializer");
    }

    @FXML
    private void reset(ActionEvent event) {
        refreshForm();
    }

    @FXML
    private void saveEmployee(ActionEvent event) {
        if (validateEmployeeDetails()) {
            LOG.info(" employee details verified... inside EmployeeDetailsController:: saveEmployee");
            if (empId.getText() != null && empId.getText().equals("")) {
                Employee oldEmp = employeeService.getEmployee(Long.parseLong(empId.getText()));
                oldEmp.setEmail(email.getText());
                oldEmp.setName(name.getText());
                oldEmp.setPhoneNumber(phoneNumber.getText());
                oldEmp.setStartDate(startDate.getValue());
                employeeService.save(oldEmp);
            } else {
                Employee emp = new Employee();
                emp.setEmployeeID(Long.parseLong(empId.getText()));
                emp.setEmail(email.getText());
                emp.setName(name.getText());
                emp.setPhoneNumber(phoneNumber.getText());
                emp.setStartDate(startDate.getValue());
                employeeService.save(emp);
            }
            refreshForm();
            showCreateAlert();
        }

    }

    private void refreshForm() {
        empId.clear();
        name.clear();
        phoneNumber.clear();
        startDate.getEditor().clear();
        email.clear();
    }

    private boolean validateEmployeeDetails() {
        return validate("empId", empId.getText(), "^[\\d\\s]+$")
                && validate("name", name.getText(), "^[A-Za-z\\s]+$")
                && validate("phoneNumber", phoneNumber.getText(), "^[\\d]+$")
                && validate("email", email.getText(), "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
                && emptyValidation("startDate", startDate.getEditor().getText().isEmpty());
    }

    private void showCreateAlert() {
        Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "CREATED_EMPLOYEE");
        alert.showAndWait();
    }
}
