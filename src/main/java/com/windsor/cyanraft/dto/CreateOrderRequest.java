package com.windsor.cyanraft.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateOrderRequest {

    @Valid
    @NotEmpty
    private List<BuyItem> buyItemList;
}
