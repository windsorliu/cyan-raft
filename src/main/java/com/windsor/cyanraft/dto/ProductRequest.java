package com.windsor.cyanraft.dto;

import com.windsor.cyanraft.constant.ProductCategory;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

  @NotNull private String productName;

  @NotNull private ProductCategory category;

  @NotNull private String imageUrl;

  @NotNull private Integer price;

  @NotNull private Integer stock;

  private String description;
}
