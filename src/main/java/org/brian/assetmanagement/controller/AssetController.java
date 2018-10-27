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
import org.brian.assetmanagement.bean.Asset;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.service.AssetService;
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
public class AssetController extends AbstractTemplateController{
    private static final Logger LOG = getLogger(AssetController.class);
        
    @Autowired
    private AssetService assetService;
    
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
    
    @FXML
    private Button deleteBtn;

    
    @Autowired
    @Lazy
    private FXMLSceneManager sceneManager;
    
    private ObservableList<Asset> assetList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.info("Inside AssetController::initialize");
        assetTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTableColumnProperties();
        populateAssets();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    private void delete(ActionEvent event) {
        if (assetTable.getItems() == null || assetTable.getItems().isEmpty()) {
            Alert alert = AlertFactory.getAlert(Alert.AlertType.WARNING, "NO_ASSET_TO_DELETE");
            alert.showAndWait();
        } else {
            List<Asset> selectedAssets = assetTable.getSelectionModel().getSelectedItems();
            if (selectedAssets == null || selectedAssets.isEmpty()) {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.INFORMATION, "SELECT_ONE_ASSET");
                alert.showAndWait();
            } else {
                Alert alert = AlertFactory.getAlert(Alert.AlertType.CONFIRMATION, "CONFIRM_DELETE_ASSET");
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    assetService.deleteInBatch(selectedAssets);
                    populateAssets();
                }
            }
        }

    }
    

    private void populateAssets() {
        assetList.clear();
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
    
}
