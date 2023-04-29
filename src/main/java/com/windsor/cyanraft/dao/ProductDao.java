package com.windsor.cyanraft.dao;

import com.windsor.cyanraft.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
