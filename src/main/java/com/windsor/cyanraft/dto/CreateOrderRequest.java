package com.windsor.cyanraft.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateOrderRequest {

  @Valid @NotEmpty private List<BuyItem> buyItemList;
}
