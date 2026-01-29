package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.utils.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private ApplicationContext springContext;

    @FXML
    private void goToNewSale(ActionEvent event) {
        loadView("/views/NewSale.fxml");
    }

    @FXML
    private void goToStock(ActionEvent event) {
        loadView("/views/Stock.fxml");
    }

    @FXML
    private void goToGarageServices(ActionEvent event) {
        loadView("/views/GarageServices.fxml");
    }

    @FXML
    private void goToFinances(ActionEvent event) {
        loadView("/views/Finances.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            loader.setControllerFactory(springContext::getBean);

            Parent view = loader.load();
            NavigationManager.getInstance().navigateTo(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}