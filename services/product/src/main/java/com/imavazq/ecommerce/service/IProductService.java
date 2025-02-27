package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;
import com.imavazq.ecommerce.domain.dto.ProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    public Integer createProduct(ProductRequestDTO productRequestDTO);

    public List<PurchasedProductResponseDTO> purchaseProducts(List<PurchasedProductRequestDTO> requestedList);

    public ProductResponseDTO findById(Integer id);

    public List<ProductResponseDTO> findAllProducts();
}
