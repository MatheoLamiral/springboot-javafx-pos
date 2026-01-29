package com.cgelectrosound.gestion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

@Controller
public class NewSaleController {
    @FXML
    public void initialize() {
        System.out.println("Vista de Ventas cargada correctamente.");
    }

    @FXML
    void agregarProducto(ActionEvent event) {
        System.out.println("Click en Agregar Producto");
    }

    @FXML
    void finalizarVenta(ActionEvent event) {
        System.out.println("Click en Finalizar Venta");
    }
}
