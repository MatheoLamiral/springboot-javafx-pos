package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.dtos.product.ProductCreateDTO;
import com.cgelectrosound.gestion.dtos.product.ProductResponseDTO;
import com.cgelectrosound.gestion.dtos.product.ProductUpdateDTO;
import com.cgelectrosound.gestion.services.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import static com.cgelectrosound.gestion.utils.Alerts.showAlert;

@Controller
@Scope("prototype")
public class NewProductFormController {

    @Autowired
    private ProductService productService;

    @FXML private Label lblTitulo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecioLista;
    @FXML private TextField txtPrecioVenta;
    @FXML private TextField txtStock;

    private Long idUpdateProduct = null;

    public void setProductData(ProductResponseDTO product) {
        this.idUpdateProduct = product.id();

        txtNombre.setText(product.name());
        txtDescripcion.setText(product.description());
        txtStock.setText(String.valueOf(product.actualStock()));

        txtPrecioLista.setText(String.valueOf(product.listPrice()));
        txtPrecioVenta.setText(String.valueOf(product.price()));
    }

    @FXML
    void save(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String desc = txtDescripcion.getText();
            if (nombre.isEmpty() || desc.isEmpty()) {
                showAlert("Error", "Completa nombre y descripción.");
                return;
            }

            Double precioLista = Double.parseDouble(txtPrecioLista.getText().replace(",", "."));
            Double precioVenta = Double.parseDouble(txtPrecioVenta.getText().replace(",", "."));
            Integer stock = Integer.parseInt(txtStock.getText());

            if (precioLista < 0 || precioVenta < 0 || stock < 0) {
                showAlert("Datos inválidos", "No uses números negativos.");
                return;
            }

            if (idUpdateProduct == null) {
                ProductCreateDTO newProduct = new ProductCreateDTO(nombre, precioVenta, desc, stock, precioLista);
                productService.createProduct(newProduct);
            } else {
                ProductUpdateDTO updateProduct = new ProductUpdateDTO(nombre, precioVenta, desc, stock, precioLista);
                productService.updateProduct(idUpdateProduct, updateProduct);
            }

            close(event);

        } catch (NumberFormatException e) {
            showAlert("Error numérico", "Revisa que los precios y stock sean números válidos.");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}