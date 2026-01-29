package com.cgelectrosound.gestion.utils;

import com.cgelectrosound.gestion.persistence.entities.Product;
import com.cgelectrosound.gestion.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        resetDB();

        if (productRepository.count() == 0) {
            System.out.println("--- Base de datos vacía. Cargando datos de prueba... ---");
            seedDB();
        }
    }

    public void seedDB() {
        List<Product> products = Arrays.asList(
                new Product("Aceite Shell Helix 10W40", 15000.0, "Aceite semi-sintético 4L", 20, 9500.0),
                new Product("Filtro de Aire Ford Fiesta", 4500.0, "Original Ford", 15, 2200.0),
                new Product("Bujía NGK", 2500.0, "Para motores 1.6 16v", 50, 1100.0),
                new Product("Lámpara H7 Philips", 3200.0, "Vision Plus +60%", 30, 1800.0),
                new Product("Batería Moura 12x75", 85000.0, "Reforzada", 5, 60000.0)
        );

        productRepository.saveAll(products);
        System.out.println("--> ¡5 Productos cargados exitosamente!");
    }

    public void resetDB() {
        System.out.println("!!! Borrando todos los productos !!!");
        productRepository.deleteAll();
    }
}
