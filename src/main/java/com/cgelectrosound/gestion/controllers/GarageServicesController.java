package com.cgelectrosound.gestion.controllers;

import com.cgelectrosound.gestion.persistence.entities.GarageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class GarageServicesController {

    @FXML private TextField txtBuscar;
    @FXML private TableView<GarageService> tblServicios;

    @FXML private TableColumn<GarageService, Long> colId;
    @FXML private TableColumn<GarageService, Date> colFecha;
    @FXML private TableColumn<GarageService, String> colAuto;
    @FXML private TableColumn<GarageService, String> colPatente;
    @FXML private TableColumn<GarageService, String> colDescripcion;
    @FXML private TableColumn<GarageService, Double> colCosto;
    @FXML private TableColumn<GarageService, Double> colPrecio;
    @FXML private TableColumn<GarageService, Double> colGanancia;

    @FXML
    public void initialize() {
        configurarColumnas();
    }

    private void configurarColumnas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        colAuto.setCellValueFactory(new PropertyValueFactory<>("carName"));
        colPatente.setCellValueFactory(new PropertyValueFactory<>("carPatent"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("serviceCosts"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        colGanancia.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    @FXML
    void nuevoServicio(ActionEvent event) {
        System.out.println("Nuevo Servicio");
    }

    @FXML
    void editarServicio(ActionEvent event) {
        GarageService seleccionado = tblServicios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            System.out.println("Editando servicio ID: " + seleccionado.getId());
        }
    }

    @FXML
    void eliminarServicio(ActionEvent event) {
        GarageService seleccionado = tblServicios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            System.out.println("Eliminando servicio ID: " + seleccionado.getId());
        }
    }
}
