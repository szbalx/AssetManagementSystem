package org.brian.assetmanagement.config;

import java.io.IOException;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Objects;

import org.slf4j.Logger;

import org.brian.assetmanagement.view.ViewResolver;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Kavitha
 * Manages switching Scenes on the Primary Stage. With the help of
 * JavaFXLoaderUtil, this loads the node hierarchy. Problem with
 * javafx.fxml.FXMLLoader is solved using the JavaFXLoaderUtil.load function.
 */
public class FXMLSceneManager {

    private static final Logger LOG = getLogger(FXMLSceneManager.class);
    private final Stage primaryStage;
    private final JavaFXLoaderUtil fxmlLoaderUtil;

    public FXMLSceneManager(JavaFXLoaderUtil fxmlLoaderUtil, Stage stage) {
        this.fxmlLoaderUtil = fxmlLoaderUtil;
        this.primaryStage = stage;
    }

    public void switchScene(final ViewResolver view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        Scene scene = prepareScene(viewRootNodeHierarchy);
        primaryStage.setTitle(view.getTitle());
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();

        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit("Unable to show scene for title" + view.getTitle(), exception);
        }
    }

    private Scene prepareScene(Parent rootnode) {
        Scene scene = primaryStage.getScene();

        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = fxmlLoaderUtil.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (IOException exception) {
            logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
        }
        return rootNode;
    }

    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

}
