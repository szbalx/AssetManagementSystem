package org.brian.assetmanagement;

import javafx.application.Application;
import javafx.stage.Stage;
import org.brian.assetmanagement.config.FXMLSceneManager;
import org.brian.assetmanagement.view.ViewResolver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AssetManagementApplication extends Application {
    
    protected ConfigurableApplicationContext springContext;
    protected FXMLSceneManager sceneManager;
    
    private static String[] savedArgs;
    
    
    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        sceneManager = springContext.getBean(FXMLSceneManager.class, stage);
        sceneManager.switchScene(ViewResolver.ASSETS);
    }
    
    @Override
    public void stop() throws Exception{
        springContext.close();
    }
    
    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AssetManagementApplication.class);
        return builder.run(savedArgs);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        savedArgs = args;
        Application.launch(AssetManagementApplication.class, args);
    }
    
}