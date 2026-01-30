package com.cgelectrosound.gestion.utils;

import javafx.scene.control.Alert;

public class Alerts {

    public static void showAlert(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public static Alert showDeleteConfirmationAlert(String item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Producto");
        alert.setHeaderText("¿Estás seguro de eliminar '" + item + "'?");
        alert.setContentText("Esta acción no se puede deshacer.");

        return alert;
    }

}
