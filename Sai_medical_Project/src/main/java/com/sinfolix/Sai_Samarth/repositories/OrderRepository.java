package com.sinfolix.Sai_Samarth.repositories;

import com.sinfolix.Sai_Samarth.DTO.OrderDTO;
import com.sinfolix.Sai_Samarth.ENUM.StatusEnum;
import com.sinfolix.Sai_Samarth.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {
    List<Order> findByCustomerEmail(String customerEmail);

//    List<OrderDTO> findByOrderStatus();
//
//    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
//    List<Order> findByReviewed(boolean reviewed);
//    List<Order> findByClientName(String clientName);
}
