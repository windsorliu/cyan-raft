package com.windsor.cyanraft.dao;

import com.windsor.cyanraft.dto.ProductRequest;
import com.windsor.cyanraft.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
