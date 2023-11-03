package com.windsor.cyanraft.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyItem {

  @NotNull private Integer productId;

  @Min(1)
  @NotNull
  private Integer quantity;
}
