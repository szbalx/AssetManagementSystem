package org.brian.assetmanagement;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration

@EnableJpaRepositories("org.brian.assetmanagement.repository")
//@EntityScan("com.brian.assetmanagement.bean")

public class AssetManagementApplication extends Application {
    
    protected ConfigurableApplicationContext springContext;
    
    private static String[] savedArgs;
    
    
    @Override
    public void init() throws Exception {
//        springContext = springBootApplicationContext();

        // set Thread name
        Thread.currentThread().setName("main");

        springContext = SpringApplication.run(getClass(), savedArgs);
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Assets.fxml")); // need to replace this with JavaFXLoaderUtil. 
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() throws Exception{
        springContext.close();
    }
    
    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AssetManagementApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        savedArgs = args;
//        launch(args);
        Application.launch(AssetManagementApplication.class, args);
    }
    
}