package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.utils.NavigationManager; // Importa tu clase nueva
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class NavigationController {

    @FXML
    void goBack(ActionEvent event) {
        NavigationManager.getInstance().goBack();
    }

    @FXML
    void goForward(ActionEvent event) {
        NavigationManager.getInstance().goForward();
    }
}