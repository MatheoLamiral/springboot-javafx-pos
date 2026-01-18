package com.cgelectrosound.gestion.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class HomeController {

    @FXML
    private void goToNewSale(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/NewSale.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            // si prefieres reemplazar la raíz de la escena existente:
            if (stage.getScene() != null) {
                stage.getScene().setRoot(root);
            } else {
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
