package com.imavazq.ecommerce.repository;

import com.imavazq.ecommerce.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
