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
import org.brian.assetmanagement.bean.Employee;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.EmployeeService;
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

    
    private void setTableColumnProperties() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    }

    private void populateEmployees() {
        empList.clear();
        createDummyEmployees();
        empList.addAll(employeeService.getAll());
        employeeTable.setItems(empList);
    }

    private void createDummyEmployees() {
        Employee emp = new Employee();
        emp.setEmployeeID(1L);
        emp.setName("Brian Stoiber");
        emp.setPhoneNumber("555-555-5555");
        emp.setEmail("brian@email.com");
        emp.setStartDate("1/1/2000");
        employeeService.save(emp);
        emp = new Employee();
        emp.setEmployeeID(2L);
        emp.setName("Dan Louis");
        emp.setPhoneNumber("555-555-5554");
        emp.setEmail("dan@email.com");
        emp.setStartDate("1/1/2000");
        employeeService.save(emp);
        
    }

}
