package com.cgelectrosound.gestion.dtos.product;

import com.cgelectrosound.gestion.persistence.entities.Product;

public record ProductResponseDTO (
    Long id,
    String name,
    Double price,
    String description,
    Integer actualStock,
    Double listPrice
){
    public static ProductResponseDTO fromProduct(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getActualStock(),
                product.getListPrice()
        );
    }
}
