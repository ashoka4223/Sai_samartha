package com.sinfolix.Sai_Samarth.controller;

import com.sinfolix.Sai_Samarth.DTO.OrderDTO;
import com.sinfolix.Sai_Samarth.DTO.OrderProductDTO;
import com.sinfolix.Sai_Samarth.DTO.OrderRequestDTO;
import com.sinfolix.Sai_Samarth.ENUM.StatusEnum;
import com.sinfolix.Sai_Samarth.entities.Order;
import com.sinfolix.Sai_Samarth.service.Impl.OrderServiceImpl;
import com.sinfolix.Sai_Samarth.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "/**")
// API Endpoint for Orders
public class OrderController {
    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        System.out.println("Product controller called ");
        OrderDTO order = new OrderDTO();
        // map orderRequestDTO to Order
        order.setCustomerAddress(orderRequestDTO.getCustomerAddress());
        order.setCustomerEmail(orderRequestDTO.getCustomerEmail());
        order.setCustomerName(orderRequestDTO.getCustomerName());
        order.setOrderDate(LocalDate.now());
        order.setStatus(StatusEnum.ORDER_PLACED.getStatus());
        order.setModified_time(LocalDate.now());

        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderRequestDTO.getOrderProductDTOList()) {
            OrderProductDTO newOrderProductDTO = new OrderProductDTO();
            newOrderProductDTO.setProductCatlogue(orderProductDTO.getProductCatlogue());
            newOrderProductDTO.setQuantity(orderProductDTO.getQuantity());
            orderProductDTOList.add(newOrderProductDTO);
        }
        OrderDTO savedOrder = orderServiceImpl.createOrder(order, orderProductDTOList);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);


    }

    @GetMapping("/{email}")
    public List<Order> getOrderListByCustomerEmail(@PathVariable String email){
        return orderServiceImpl.getOrderListByCustomerEmail(email);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllPlacedOrders(){
        return orderService.getAllOrders();
    }
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getOrders(
//            @RequestParam(required = false) LocalDate startDate,
//            @RequestParam(required = false) LocalDate endDate,
//            @RequestParam(required = false) Boolean reviewed,
//            @RequestParam(required = false) String clientName
//    ) {
//        List<Order> orders = orderServiceImpl.getOrders(startDate, endDate, reviewed, clientName);
//        return ResponseEntity.ok(orders);
//    }
}

