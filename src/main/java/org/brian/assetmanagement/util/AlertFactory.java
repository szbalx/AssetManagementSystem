/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.util;

import javafx.scene.control.Alert;

/**
 *
 * @author Kavitha
 * Factory pattern to create new Alert based on type and alert message code
 */
public class AlertFactory {
    public static Alert getAlert(Alert.AlertType type, String code){
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(ApplicationHelper.getAlertMessage(code));
        return alert;
    }
}
