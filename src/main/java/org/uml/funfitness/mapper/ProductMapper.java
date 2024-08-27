package org.uml.funfitness.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.uml.funfitness.pojo.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 查询所有商品信息
    List<Product> findAll();

    // 添加商品信息
    Boolean addProduct(Product product);

    // 下架商品
    Boolean deleteProduct(Integer productId);

    // 更新商品信息
    Boolean updateProduct(Product product);

    // 通过ID查询商品信息
    Product findByProductId(Integer productId);
}
