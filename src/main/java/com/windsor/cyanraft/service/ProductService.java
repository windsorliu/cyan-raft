package com.windsor.cyanraft.service;

import com.windsor.cyanraft.dto.ProductRequest;
import com.windsor.cyanraft.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
