package com.cgelectrosound.gestion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class FinancesController {

    @FXML private DatePicker dpDesde;
    @FXML private DatePicker dpHasta;
    @FXML private Label lblIngresos;
    @FXML private Label lblGastos;
    @FXML private Label lblGanancia;

    @FXML
    public void initialize() {
        dpDesde.setValue(LocalDate.now().withDayOfMonth(1));
        dpHasta.setValue(LocalDate.now());
        calcularFinanzas();
    }

    @FXML
    void filtrarPorFechas(ActionEvent event) {
        calcularFinanzas();
    }

    @FXML
    void verUltimoMes(ActionEvent event) {
        dpHasta.setValue(LocalDate.now());
        dpDesde.setValue(LocalDate.now().minusMonths(1));
        calcularFinanzas();
    }

    @FXML
    void verUltimaSemana(ActionEvent event) {
        dpHasta.setValue(LocalDate.now());
        dpDesde.setValue(LocalDate.now().minusWeeks(1));
        calcularFinanzas();
    }

    private void calcularFinanzas() {
        LocalDate inicio = dpDesde.getValue();
        LocalDate fin = dpHasta.getValue();

        System.out.println("Rango: " + inicio + " a " + fin);

        double ingresosMock = 0.00;
        double gastosMock = 0.00;
        double gananciaMock = ingresosMock - gastosMock;

        lblIngresos.setText(String.format("$ %.2f", ingresosMock));
        lblGastos.setText(String.format("$ %.2f", gastosMock));
        lblGanancia.setText(String.format("$ %.2f", gananciaMock));
    }
}