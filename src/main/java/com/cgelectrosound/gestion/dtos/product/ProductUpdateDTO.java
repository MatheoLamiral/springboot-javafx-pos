package com.cgelectrosound.gestion.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductUpdateDTO(
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @NotNull
    String name,

    @Positive(message = "Price must be positive")
    @NotNull
    Double price,

    @Size(max = 500, message = "Description can't exceed 500 characters")
    String description,

    @Positive(message = "Actual stock must be positive")
    @NotNull
    Integer actualStock,

    @Positive(message = "List price must be positive")
    @NotNull
    Double listPrice
) {

}
