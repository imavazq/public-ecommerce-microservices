package com.imavazq.ecommerce.service.impl;

import com.imavazq.ecommerce.domain.entity.Product;
import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;
import com.imavazq.ecommerce.domain.dto.ProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductResponseDTO;
import com.imavazq.ecommerce.exception.ProductNotFoundException;
import com.imavazq.ecommerce.exception.ProductPurchaseException;
import com.imavazq.ecommerce.mapper.ProductMapper;
import com.imavazq.ecommerce.repository.ProductRepository;
import com.imavazq.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequestDTO productRequestDTO) {
        var product = productRepository.save(productMapper.toProduct(productRequestDTO));//var permite inferir tipos (detecte automáticamente tipo)
        return product.getId();
    }

    @Override
    public List<PurchasedProductResponseDTO> purchaseProducts(List<PurchasedProductRequestDTO> requestedList) {
        //nos quedamos con los id's de los productos
        List<Integer> productIds = requestedList.stream()
                                                .map(PurchasedProductRequestDTO::productId)
                                                .toList();//lista inmutable List<Integer>

        //valido que los id's esten disponibles en la bd
        //uso query JPA para que checkee si estan IN entre los ids cargados en la bd
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(requestedList);

        //si tamaño listas no es el mismo -> exception
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exist in the system.");
        }

        //reordeno la lista de productos pedidos por id para poder comparar 1 a 1 la cantidad solicitada con la disponible en bd
        List<PurchasedProductRequestDTO> requestedPurchaseProducts = requestedList.stream()
                .sorted(Comparator.comparing(PurchasedProductRequestDTO::productId))
                .toList();

        //inicio lista de productos comprados a devolver como response
        var purchasedProducts = new ArrayList<PurchasedProductResponseDTO>();
        //validamos que cantidad pedida este disponible
        //comparo 1 a 1 que cantidad disponible sea mayor o igual a la pedida
        for(int i = 0; i < storedProducts.size(); i++){
            Product product = storedProducts.get(i);
            PurchasedProductRequestDTO requestedProduct = requestedPurchaseProducts.get(i);

            if(product.getAvailableQuantity() < requestedProduct.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id: " + requestedProduct.productId());
            }

            //actualizo cantidad disponible en bd del producto correspondiente
            var newAvailableQuantity = product.getAvailableQuantity() - requestedProduct.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product); //actualizo producto en bd

            //agrego producto comprado a lista de ProductPurchaseResponseDTO
            purchasedProducts.add(productMapper.toProductPurchaseResponseDTO(product, requestedProduct.quantity()));
        }

        return purchasedProducts;
    }

    @Override
    public ProductResponseDTO findById(Integer id) {
        return productRepository.findById(id)
                //.map((product) -> return productMapper.toProductResponseDTO(product));
                .map(productMapper::toProductResponseDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with the provided id: " + id));
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponseDTO)
                .collect(Collectors.toList());//lista mutable
    }
}
