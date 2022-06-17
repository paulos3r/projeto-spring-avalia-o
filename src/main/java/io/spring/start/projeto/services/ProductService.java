package io.spring.start.projeto.services;

import io.spring.start.projeto.model.ProductModel;
import io.spring.start.projeto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Object save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    public void delete(ProductModel productModel) {
        productRepository.delete(productModel);
    }

    public Object update(ProductModel productModel) {
        return productRepository.save(productModel);
    }
}
