package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.dtos.product.ProductResponseDTO;
import com.cgelectrosound.gestion.persistence.entities.Product;
import com.cgelectrosound.gestion.persistence.filters.ProductFilter;
import com.cgelectrosound.gestion.services.ProductService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class StockController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ApplicationContext context;

    @FXML private TextField txtBuscar;
    @FXML private TableView<ProductResponseDTO> tblProductos;

    @FXML private TableColumn<ProductResponseDTO, String> colNombre;
    @FXML private TableColumn<ProductResponseDTO, String> colDescripcion;
    @FXML private TableColumn<ProductResponseDTO, Integer> colStock;
    @FXML private TableColumn<ProductResponseDTO, Double> colPrecioLista;
    @FXML private TableColumn<ProductResponseDTO, Double> colPrecioVenta; // Asumo que es 'price' en tu entidad

    @FXML
    public void initialize() {
        System.out.println("Vista de Stock cargada.");
        configureColumns();
        loadProducts();

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            loadProducts();
        });
    }

    private void configureColumns() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().name()));
        colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().description()));
        colStock.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().actualStock()).asObject());
        colPrecioLista.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().listPrice()).asObject());
        colPrecioVenta.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().price()).asObject());

        colPrecioLista.setCellFactory(column -> new TableCell<ProductResponseDTO, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatCoin(item));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        colPrecioVenta.setCellFactory(column -> new TableCell<ProductResponseDTO, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatCoin(item));
                    setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
                }
            }
        });
    }

    private void loadProducts() {
        String searchText = txtBuscar.getText();

        ProductFilter filter = new ProductFilter(
                searchText,
                null,
                null, null, 0, 0
        );

        List<ProductResponseDTO> products = productService.findAllProducts(filter, Pageable.unpaged());

        tblProductos.getItems().clear();
        tblProductos.getItems().addAll(products);
    }

    @FXML
    void newProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/NewProductForm.fxml"));

            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuevo Producto");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

            loadProducts();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarProducto(ActionEvent event) {
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
    }

    private String formatCoin(Double valor) {
        if (valor == null) return "";

        NumberFormat formato = NumberFormat.getInstance(new Locale("es", "AR"));
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);

        return "$ " + formato.format(valor);
    }
}