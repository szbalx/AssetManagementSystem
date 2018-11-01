/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.controller;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
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
        employeeTable.setEditable(true);
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
    
    @FXML
    private void handleEditCommitEvent(TableColumn.CellEditEvent<Employee, String> event){
        String inputFxId = ((TableColumn)event.getSource()).getId();
        LOG.info("Event trigerred from : " + inputFxId );
        Long empId = event.getRowValue().getEmployeeID();
        Employee emp = event.getRowValue();
        switch(inputFxId){
            case "colEmpName":
                emp.setName(event.getNewValue());
                break;
            case "colPhoneNum":
                emp.setPhoneNumber(event.getNewValue());
                break;
            case "colEmpEmail":
                emp.setEmail(event.getNewValue());
                break;
            case "colStartDate":
                emp.setStartDate(event.getNewValue());
                break;
        }
        employeeService.save(emp);
        
    }
    
    private void setTableColumnProperties() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpName.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPhoneNum.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//        colStartDate.setCellFactory(datePickerCellFactory);
    }

    private void populateEmployees() {
        empList.clear();
        empList.addAll(employeeService.getAll());
        employeeTable.setItems(empList);
    }
    
    Callback<TableColumn<Employee, String>, TableCell<Employee, String>> datePickerCellFactory = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
        @Override
        public TableCell call(final TableColumn<Employee, String> param) {

            final TableCell<Employee, String> cell = new TableCell<Employee, String>() {

                final DatePicker datePicker = new DatePicker();

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        if(isEditing()){
                            setGraphic(datePicker);
//                            setText(null);
                        }
                    }
                }
            };
            return cell;
        }
    };

}
