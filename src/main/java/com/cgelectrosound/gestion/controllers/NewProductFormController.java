package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.dtos.product.ProductCreateDTO;
import com.cgelectrosound.gestion.services.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewProductFormController {

    @Autowired
    private ProductService productService;

    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecioLista;
    @FXML private TextField txtPrecioVenta;
    @FXML private TextField txtStock;

    @FXML
    void save(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String desc = txtDescripcion.getText();

            if (nombre.isEmpty() || desc.isEmpty()) {
                showAlert("Error", "Por favor completa el nombre y la descripción.");
                return;
            }

            Double precioLista = Double.parseDouble(txtPrecioLista.getText().replace(",", "."));
            Double precioVenta = Double.parseDouble(txtPrecioVenta.getText().replace(",", "."));
            Integer stock = Integer.parseInt(txtStock.getText());

            if (precioLista < 0 || precioVenta < 0 || stock < 0) {
                showAlert("Datos inválidos", "Los valores del precio, precio de lista y stock no pueden ser negativos.");
                return; // Detenemos el guardado aquí
            }

            ProductCreateDTO newProduct = new ProductCreateDTO(
                    nombre,
                    precioVenta,
                    desc,
                    stock,
                    precioLista
            );

            productService.createProduct(newProduct);

            close(event);

        } catch (NumberFormatException e) {
            showAlert("Error en números", "Por favor verifica que los precios y el stock sean números válidos.");
        } catch (Exception e) {
            showAlert("Error al guardar", "Ocurrió un problema: " + e.getMessage());
        }
    }

    @FXML
    void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}