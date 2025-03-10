package com.imavazq.ecommerce.repository;

import com.imavazq.ecommerce.domain.entity.Product;
import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //consulta JPA devuelve productos ordenados por id que se encuentran en bd comparando ids pasados en la lista
    List<Product> findAllByIdInOrderById(List<Integer> requestedList);
}
