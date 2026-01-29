package com.cgelectrosound.gestion.persistence.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {
    private String name;
    private String description;
    private Double minPrice;
    private Double maxPrice;
    private int minStock;
    private int maxStock;
}
