package com.imavazq.ecommerce.repository;

import com.imavazq.ecommerce.domain.entity.Product;
import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //consulta JPA devuelve productos ordenados por id que se encuentran en bd comparando ids pasados en la lista
    List<Product> findAllByIdInOrderById(List<PurchasedProductRequestDTO> requestedList);
}
