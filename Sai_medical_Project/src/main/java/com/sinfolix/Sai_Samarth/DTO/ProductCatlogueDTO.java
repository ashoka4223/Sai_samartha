package com.sinfolix.Sai_Samarth.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatlogueDTO {

    private int id;
    private String image;
    private int quantity;
    private int price;
    private int discount;
    private String companyName;
    private String medicineName;
    private int minAge;
    private int maxAge;
    private int realMrp;
    private int discountMrp;
    private String prodDescription;
    private boolean disabled;
    private String comments;
}
