package com.imavazq.ecommerce.mapper;

import com.imavazq.ecommerce.domain.entity.Product;
import com.imavazq.ecommerce.domain.entity.Category;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;
import com.imavazq.ecommerce.domain.dto.ProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    //Mapeo ProductRequestDTO a Product
    public Product toProduct(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .id(productRequestDTO.id())
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .price(productRequestDTO.price())
                .availableQuantity(productRequestDTO.availableQuantity())
                .category(
                        Category.builder()
                        .id(productRequestDTO.categoryId())
                        .build()
                )
                .build();
    }

    //Mapeo Product a ProductResponseDTO
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public PurchasedProductResponseDTO toProductPurchaseResponseDTO(Product product, double quantity) {
        return new PurchasedProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity //cantidad pedida
        );
    }
}
