package com.windsor.cyanraft.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.cyanraft.dto.BuyItem;
import com.windsor.cyanraft.dto.CreateOrderRequest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  private RequestBuilder post(CreateOrderRequest request, Integer userId) throws Exception {
    String json = objectMapper.writeValueAsString(request);

    return MockMvcRequestBuilders.post("/users/{userId}/orders", userId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);
  }

  // 創建訂單
  @Transactional
  @Test
  public void createOrder_success() throws Exception {
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    List<BuyItem> buyItemList = new ArrayList<>();

    BuyItem buyItem1 = new BuyItem();
    buyItem1.setProductId(1);
    buyItem1.setQuantity(5);
    buyItemList.add(buyItem1);

    BuyItem buyItem2 = new BuyItem();
    buyItem2.setProductId(2);
    buyItem2.setQuantity(2);
    buyItemList.add(buyItem2);

    createOrderRequest.setBuyItemList(buyItemList);

    RequestBuilder requestBuilder = post(createOrderRequest, 1);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().is(201))
        .andExpect(jsonPath("$.orderId", notNullValue()))
        .andExpect(jsonPath("$.userId", equalTo(1)))
        .andExpect(jsonPath("$.totalAmount", equalTo(750)))
        .andExpect(jsonPath("$.orderItemList", hasSize(2)))
        .andExpect(jsonPath("$.createdDate", notNullValue()))
        .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
  }

  @Transactional
  @Test
  public void createOrder_illegalArgument_emptyBuyItemList() throws Exception {
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    List<BuyItem> buyItemList = new ArrayList<>();
    createOrderRequest.setBuyItemList(buyItemList);

    RequestBuilder requestBuilder = post(createOrderRequest, 1);

    mockMvc.perform(requestBuilder).andExpect(status().is(400));
  }

  @Transactional
  @Test
  public void createOrder_userNotExist() throws Exception {
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    List<BuyItem> buyItemList = new ArrayList<>();

    BuyItem buyItem1 = new BuyItem();
    buyItem1.setProductId(1);
    buyItem1.setQuantity(1);
    buyItemList.add(buyItem1);

    createOrderRequest.setBuyItemList(buyItemList);

    RequestBuilder requestBuilder = post(createOrderRequest, 20000);

    mockMvc.perform(requestBuilder).andExpect(status().is(400));
  }

  @Transactional
  @Test
  public void createOrder_productNotExist() throws Exception {
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    List<BuyItem> buyItemList = new ArrayList<>();

    BuyItem buyItem1 = new BuyItem();
    buyItem1.setProductId(20000);
    buyItem1.setQuantity(1);
    buyItemList.add(buyItem1);

    createOrderRequest.setBuyItemList(buyItemList);

    RequestBuilder requestBuilder = post(createOrderRequest, 1);

    mockMvc.perform(requestBuilder).andExpect(status().is(400));
  }

  @Transactional
  @Test
  public void createOrder_stockNotEnough() throws Exception {
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    List<BuyItem> buyItemList = new ArrayList<>();

    BuyItem buyItem1 = new BuyItem();
    buyItem1.setProductId(1);
    buyItem1.setQuantity(20000);
    buyItemList.add(buyItem1);

    createOrderRequest.setBuyItemList(buyItemList);

    RequestBuilder requestBuilder = post(createOrderRequest, 1);

    mockMvc.perform(requestBuilder).andExpect(status().is(400));
  }

  // 查詢訂單列表
  @Test
  public void getOrders() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/orders", 1);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.limit", notNullValue()))
        .andExpect(jsonPath("$.offset", notNullValue()))
        .andExpect(jsonPath("$.total", notNullValue()))
        .andExpect(jsonPath("$.results", hasSize(2)))
        .andExpect(jsonPath("$.results[0].orderId", notNullValue()))
        .andExpect(jsonPath("$.results[0].userId", equalTo(1)))
        .andExpect(jsonPath("$.results[0].totalAmount", equalTo(100000)))
        .andExpect(jsonPath("$.results[0].orderItemList", hasSize(1)))
        .andExpect(jsonPath("$.results[0].createdDate", notNullValue()))
        .andExpect(jsonPath("$.results[0].lastModifiedDate", notNullValue()))
        .andExpect(jsonPath("$.results[1].orderId", notNullValue()))
        .andExpect(jsonPath("$.results[1].userId", equalTo(1)))
        .andExpect(jsonPath("$.results[1].totalAmount", equalTo(500690)))
        .andExpect(jsonPath("$.results[1].orderItemList", hasSize(3)))
        .andExpect(jsonPath("$.results[1].createdDate", notNullValue()))
        .andExpect(jsonPath("$.results[1].lastModifiedDate", notNullValue()));
  }

  @Test
  public void getOrders_pagination() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/users/{userId}/orders", 1)
            .param("limit", "2")
            .param("offset", "2");

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.limit", notNullValue()))
        .andExpect(jsonPath("$.offset", notNullValue()))
        .andExpect(jsonPath("$.total", notNullValue()))
        .andExpect(jsonPath("$.results", hasSize(0)));
  }

  @Test
  public void getOrders_userHasNoOrder() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/orders", 2);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.limit", notNullValue()))
        .andExpect(jsonPath("$.offset", notNullValue()))
        .andExpect(jsonPath("$.total", notNullValue()))
        .andExpect(jsonPath("$.results", hasSize(0)));
  }

  @Test
  public void getOrders_userNotExist() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/orders", 20000);

    mockMvc.perform(requestBuilder).andExpect(status().is(400));
  }
}
