package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.ProductPurchaseRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductPurchaseResponseDTO;
import com.imavazq.ecommerce.domain.dto.ProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    public Integer createProduct(ProductRequestDTO productRequestDTO);

    public List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> requestedList);

    public ProductResponseDTO findById(Integer id);

    public List<ProductResponseDTO> findAllProducts();
}
