package com.windsor.cyanraft.model;

import com.windsor.cyanraft.constant.ProductCategory;
import java.util.Date;
import lombok.Data;

@Data
public class Product {
  private Integer productId;
  private String productName;
  private ProductCategory category;
  private String imageUrl;
  private Integer price;
  private Integer stock;
  private String description;
  private Date createdDate;
  private Date lastModifiedDate;
}
