package com.example.redis.service;

import com.example.redis.model.Product;
import com.example.redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CacheService cacheService;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        final String cacheKey = "product:" + id;
        Product product = (Product) cacheService.getObject(cacheKey);
        if (product != null) {
            return product;
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(
                p -> cacheService.cacheObject(cacheKey, p, 1, TimeUnit.MINUTES)
        );

        return optionalProduct.orElse(null);
    }
}
