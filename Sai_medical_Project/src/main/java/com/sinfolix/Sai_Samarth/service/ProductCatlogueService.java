package com.sinfolix.Sai_Samarth.service;

import com.sinfolix.Sai_Samarth.DTO.ProductCatlogueDTO;
import com.sinfolix.Sai_Samarth.entities.ProductCatlogue;

import java.util.List;

public interface ProductCatlogueService {

    ProductCatlogueDTO createProductCatlogue(ProductCatlogueDTO product);
    ProductCatlogueDTO updateProductCatlogue(ProductCatlogueDTO product,Integer ProductId);
    ProductCatlogueDTO getProductCatlogueById(Integer ProductId);
    List<ProductCatlogueDTO> getAllProductCatlogue();
    void deleteProductCatlogue(Integer ProductId);
    void disableProduct(Integer id);
    ProductCatlogue addComment(Integer id, String comment);
}
