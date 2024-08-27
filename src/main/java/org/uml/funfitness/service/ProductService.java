package org.uml.funfitness.service;

import org.uml.funfitness.pojo.Product;

import java.util.List;

public interface ProductService {

    //查询所有商品信息
    List<Product> findAll();

    //添加商品信息
    Boolean addProduct(Product product);

    //下架商品
    Boolean deleteProduct(Integer product_id);

    //更新商品信息
    Boolean updateProduct(Product product);

    //搜寻商品
    Product  getProductById(Integer productId);
}
