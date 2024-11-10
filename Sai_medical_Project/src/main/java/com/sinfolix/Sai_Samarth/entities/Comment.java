//package com.sinfolix.Sai_Samarth.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String comment;
//
//    @Column(name = "comment_date")
//    private LocalDateTime timestamp;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//}
