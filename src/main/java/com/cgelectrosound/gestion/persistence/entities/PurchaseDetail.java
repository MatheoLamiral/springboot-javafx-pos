package com.cgelectrosound.gestion.persistence.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double unitCost;
    private Double subtotalCost;

    public PurchaseDetail(Product product, Integer quantity, Double unitCost) {
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subtotalCost = unitCost * quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getSubtotalCost() {
        return subtotalCost;
    }

    public void setSubtotalCost(Double subtotalCost) {
        this.subtotalCost = subtotalCost;
    }
}