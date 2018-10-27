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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.brian.assetmanagement.bean.Employee;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.EmployeeService;
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
public class EmployeeController extends AbstractTemplateController {

    private static final Logger LOG = getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Long> colEmpId;

    @FXML
    private TableColumn<Employee, String> colEmpName;

    @FXML
    private TableColumn<Employee, String> colPhoneNum;

    @FXML
    private TableColumn<Employee, String> colEmpEmail;

    @FXML
    private TableColumn<Employee, String> colStartDate;

    @FXML
    private Button deleteBtn;

    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;

    private ObservableList<Employee> empList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("Inside EmployeeController::initialize");
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTableColumnProperties();
        populateEmployees();
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (employeeTable.getItems() == null || employeeTable.getItems().isEmpty()) {
            Alert alert = AlertFactory.getAlert(Alert.AlertType.WARNING, "NO_EMPLOYEE_TO_DELETE");
            alert.showAndWait();
        } else {
            List<Employee> selectedEmployees = employeeTable.getSelectionModel().getSelectedItems();
            if (selectedEmployees == null || selectedEmployees.isEmpty()) {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "SELECT_ONE_EMPLOYEE");
                alert.showAndWait();
            } else {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.CONFIRMATION, "CONFIRM_DELETE_EMPLOYEE");
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    employeeService.deleteInBatch(selectedEmployees);
                    populateEmployees();
                }
            }
        }

    }
    private void setTableColumnProperties() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    }

    private void populateEmployees() {
        empList.clear();
        empList.addAll(employeeService.getAll());
        employeeTable.setItems(empList);
    }

}
