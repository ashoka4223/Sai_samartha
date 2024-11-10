package com.sinfolix.Sai_Samarth.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "modified_time")
    private LocalDate modified_time;

    @Column(name = "prodStatus")
    private int status;

    @Column(name = "prescription")
    private boolean isPrescriptionBased;

    @Column(name = "reviewed")
    private boolean reviewed;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();
}
