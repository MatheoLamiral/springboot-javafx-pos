package com.cgelectrosound.gestion.persistence.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "services")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GarageService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date serviceDate;

    private String carName;
    private String carPatent;
    private String serviceDescription;

    private Double serviceCosts;
    private Double servicePrice;

    public GarageService(Date serviceDate, String carName, String carPatent, String serviceDescription, Double serviceCosts, Double servicePrice) {
        this.serviceDate = serviceDate;
        this.carName = carName;
        this.carPatent = carPatent;
        this.serviceDescription = serviceDescription;
        this.serviceCosts = serviceCosts;
        this.servicePrice = servicePrice;
    }

    public Double getProfit() {
        return this.servicePrice - serviceCosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPatent() {
        return carPatent;
    }

    public void setCarPatent(String carPatent) {
        this.carPatent = carPatent;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Double getServiceCosts() {
        return serviceCosts;
    }

    public void setServiceCosts(Double serviceCosts) {
        this.serviceCosts = serviceCosts;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
