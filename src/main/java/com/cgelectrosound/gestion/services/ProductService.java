package com.cgelectrosound.gestion.services;

import com.cgelectrosound.gestion.dtos.product.ProductCreateDTO;
import com.cgelectrosound.gestion.dtos.product.ProductResponseDTO;
import com.cgelectrosound.gestion.dtos.product.ProductUpdateDTO;
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

    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado para editar"));

        if (!product.getName().equalsIgnoreCase(dto.name()) && productRepository.existsByNameIgnoreCase(dto.name())){
            throw new RuntimeException("¡Ya existe otro producto con el nombre " + dto.name() + "!");
        }

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setListPrice(dto.listPrice());
        product.setActualStock(dto.actualStock());

        return ProductResponseDTO.fromProduct(productRepository.save(product));
    }

    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("El producto que se desea eliminar no existe");
        }
        productRepository.deleteById(id);
    }

}
