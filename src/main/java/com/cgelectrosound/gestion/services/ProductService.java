package com.cgelectrosound.gestion.services;

import com.cgelectrosound.gestion.dtos.product.ProductCreateDTO;
import com.cgelectrosound.gestion.dtos.product.ProductResponseDTO;
import com.cgelectrosound.gestion.persistence.entities.Product;
import com.cgelectrosound.gestion.persistence.filters.ProductFilter;
import com.cgelectrosound.gestion.persistence.repositories.ProductRepository;
import com.cgelectrosound.gestion.persistence.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<ProductResponseDTO> findAllProducts(ProductFilter filter, Pageable pageable) {
        Specification<Product> specification = Specifications.getProductSpecification(filter);

        return productRepository.findAll(specification, pageable)
                .stream()
                .map(ProductResponseDTO::fromProduct)
                .toList();
    }

    public ProductResponseDTO createProduct(ProductCreateDTO dto) {
        if (productRepository.existsByNameIgnoreCase(dto.name())) {
            throw new RuntimeException("¡Ya existe un producto con el nombre '" + dto.name() + "'!");
        }
        Product newProduct = dto.toEntityProduct();
        return ProductResponseDTO.fromProduct(productRepository.save(newProduct));
    }

}
