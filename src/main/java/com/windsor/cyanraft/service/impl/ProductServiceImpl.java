package com.windsor.cyanraft.service.impl;

import com.windsor.cyanraft.dao.ProductDao;
import com.windsor.cyanraft.dto.ProductRequest;
import com.windsor.cyanraft.model.Product;
import com.windsor.cyanraft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
