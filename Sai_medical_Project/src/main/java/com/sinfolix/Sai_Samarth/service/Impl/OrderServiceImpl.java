package com.sinfolix.Sai_Samarth.service.Impl;

import com.sinfolix.Sai_Samarth.DTO.OrderDTO;
import com.sinfolix.Sai_Samarth.DTO.OrderProductDTO;
import com.sinfolix.Sai_Samarth.entities.Order;
import com.sinfolix.Sai_Samarth.entities.OrderProduct;
import com.sinfolix.Sai_Samarth.exceptions.ResourceNotFoundException;
import com.sinfolix.Sai_Samarth.repositories.OrderProductRepository;
import com.sinfolix.Sai_Samarth.repositories.OrderRepository;
import com.sinfolix.Sai_Samarth.repositories.ProductCatlogueRepo;
import com.sinfolix.Sai_Samarth.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductCatlogueRepo productCatlogueRepo;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO, List<OrderProductDTO> orderProductDTOList) {
        Order order = new Order();
//        Set order Properties
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerEmail(orderDTO.getCustomerEmail());
        order.setCustomerAddress(orderDTO.getCustomerAddress());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setModified_time(orderDTO.getModified_time());
        order.setStatus(orderDTO.getStatus());

        Order savedOrder = orderRepository.save(order);

        for (OrderProductDTO orderProductDTO : orderProductDTOList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(savedOrder);
            orderProduct.setProductCatlogue(productCatlogueRepo.findById(orderProductDTO.getProductCatlogue().getId()).orElseThrow(() -> new ResourceNotFoundException("Product Catlogue", "ID ", orderProductDTO.getProductCatlogue().getId())));
            orderProduct.setQuantity(orderProductDTO.getQuantity());
            orderProductRepository.save(orderProduct);
        }
        return OrderToOrderDTO(savedOrder);
    }


    // Return all the product ordered by specific customer
    @Override
    public List<Order> getOrderListByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    // Return order by ID
    @Override
    public OrderDTO getOrderById(int orderId) {
        return null;
    }

    //  Return all the placed orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private OrderDTO OrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setCustomerEmail(order.getCustomerEmail());
        orderDTO.setCustomerAddress(order.getCustomerAddress());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setModified_time(order.getModified_time());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;

    }

//    public List<Order> getOrders(LocalDate startDate, LocalDate endDate, Boolean reviewed, String clientName) {
//        if (startDate != null && endDate != null) {
//            return orderRepository.findByOrderDateBetween(startDate, endDate);
//        } else if (reviewed != null) {
//            return orderRepository.findByReviewed(reviewed);
//        } else if (clientName != null) {
//            return orderRepository.findByClientName(clientName);
//        }
//        return orderRepository.findAll();
//    }
}

