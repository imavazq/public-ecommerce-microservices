package com.imavazq.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price; //Usamos tipo BigDecimal xq trae muchos métodos optimizados que podemos llegar a usar con precios
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
