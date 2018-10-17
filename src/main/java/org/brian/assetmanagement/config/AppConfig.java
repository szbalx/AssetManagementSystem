/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.config;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import org.brian.assetmanagement.service.AssetService;
import org.brian.assetmanagement.service.EmployeeService;
import org.brian.assetmanagement.service.impl.AssetServiceImpl;
import org.brian.assetmanagement.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Kavitha
 *
 * Used to define the application configuration.Spring boot loads important
 * repositoriesm services, controller and beans based on definition here.
 */
@Configuration
@ComponentScan(basePackages = {"org.brian.assetmanagement.repository", "org.brian.assetmanagement.service"})
@EnableJpaRepositories("org.brian.assetmanagement.repository")
public class AppConfig {

    @Autowired
    JavaFXLoaderUtil springFXMLLoader;

    /**
     * Added to ensure that the service is available before controller invokes
     * fxml stage setup
     */
    @Bean
    public AssetService assetService() {
        return new AssetServiceImpl();
    }
    
    @Bean
    public EmployeeService employeeService(){
        return new EmployeeServiceImpl();
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    /**
     * Deal with FXML Loads only after services, repositories, controllers and
     * beans are loaded during Spring boot startup. Otherwise one or more Spring
     * components will remain missing.
     */
    @Bean
    @Lazy(value = true)
    public FXMLSceneManager stageManager(Stage stage) throws IOException {
        return new FXMLSceneManager(springFXMLLoader, stage);
    }

}
