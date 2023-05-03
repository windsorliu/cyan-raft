package com.windsor.cyanraft.dao;

import com.windsor.cyanraft.constant.ProductCategory;
import com.windsor.cyanraft.dto.ProductRequest;
import com.windsor.cyanraft.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}
