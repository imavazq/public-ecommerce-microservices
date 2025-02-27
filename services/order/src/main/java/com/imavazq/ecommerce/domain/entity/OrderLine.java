package com.imavazq.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine { //cantidad pedida de cada producto en una orden en específico
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne //muchas orderline pueden pertenecer a una order
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productId; //No declaramos constraint xq Product pertenece a otro dominio

    private double quantity;
    //en un futuro se puede agregar unitCost, totalCost
}
