package com.cgelectrosound.gestion.dtos.product;

import com.cgelectrosound.gestion.persistence.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductCreateDTO (
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        @NotBlank(message = "Description is required")
        @Size(max= 500, message = "Description can't exceed 500 characters")
        String description,

        @NotNull(message = "Actual stock is required")
        @Positive(message = "Actual stock must be positive")
        Integer actualStock,

        @NotNull(message = "List price is required")
        @Positive(message = "List price must be positive")
        Double listPrice
){

    public Product toEntityProduct() {
        return new Product(
                this.name,
                this.price,
                this.description,
                this.actualStock,
                this.listPrice
        );
    }
}
