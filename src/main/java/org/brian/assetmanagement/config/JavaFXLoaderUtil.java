package org.brian.assetmanagement.config;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Kavitha
 * Loads the fxml files on startup and allows JavaFX files to be used in Spring Boot App. 
 * TODO: replace the FXMLLoader with this class inside the AssetManagementApplication.start. 
 */
@Component
public class JavaFXLoaderUtil {
    private final ResourceBundle resourceBundle;
    private final ApplicationContext context;

    @Autowired
    public JavaFXLoaderUtil(ApplicationContext context, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.context = context;
    }

    public Parent load(String fxmlPath) throws IOException {      
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean); //Spring now FXML Controller Factory
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader.load();
    }
}
