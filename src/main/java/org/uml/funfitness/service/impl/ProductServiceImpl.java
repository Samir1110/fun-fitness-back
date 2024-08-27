package org.uml.funfitness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uml.funfitness.mapper.ProductMapper;
import org.uml.funfitness.pojo.Product;
import org.uml.funfitness.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Boolean addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Boolean updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public Boolean deleteProduct(Integer productId) {
        return productMapper.deleteProduct(productId);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productMapper.findByProductId(productId);
    }
}
