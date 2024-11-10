package com.sinfolix.Sai_Samarth.service;

import com.sinfolix.Sai_Samarth.DTO.OrderDTO;
import com.sinfolix.Sai_Samarth.DTO.OrderProductDTO;
import com.sinfolix.Sai_Samarth.entities.Order;

import java.util.List;

public interface OrderService {

    //    create a new order
    OrderDTO createOrder(OrderDTO orderDTO, List<OrderProductDTO> orderProductDTOList);

    //    Get order List details by Customer email id
    List<Order> getOrderListByCustomerEmail(String customerEmail);

    //    Get Order Details by order ID
    OrderDTO getOrderById(int orderId);

    List<Order> getAllOrders();


//    List<OrderDTO> getAllPlacedOrders();
//


}
