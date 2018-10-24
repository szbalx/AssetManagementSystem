/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author Kavitha
 */
public class ApplicationHelper {

    /**
     * Helper method that validates if a given field is empty or not.
     *
     * @param field
     * @param empty
     * @return
     */
    public static boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    /**
     * Validates a given field's value to the pattern specified. If it is not
     * complying to said pattern, an alert is shown at user interface where a
     * meaningful error message is shown to the user.
     *
     * @param field
     * @param value
     * @param pattern
     * @return
     */
    public static boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    /**
     * Helper method that creates a proper validation alert using the field name
     * @param field
     * @param empty 
     */
    public static void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (empty) {
            alert.setContentText("Please Enter " + field);
        } else {
            alert.setContentText("Please Enter Valid " + field);
        }

        alert.showAndWait();
    }
}
