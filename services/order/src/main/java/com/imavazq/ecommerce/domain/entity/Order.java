package com.imavazq.ecommerce.domain.entity;

import com.imavazq.ecommerce.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class) //habilitamos auditoria de la clase
@Table(name = "customer_order")//nombre tabla bd que crea Hibernate
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    private String reference;

    private BigDecimal totalAmount;

    @Enumerated(STRING) //para persistir correctamente
    private PaymentMethod paymentMethod; //ENUM PaymentMethod

    private String customerId; //id de Customer service es String (NOSql)
    //No declaramos constraint xq Customer pertenece a otro dominio

    @OneToMany(mappedBy = "order") //una order puede tener varias orderlines - "order" es nombre atr en orderLine
    private List<OrderLine> orderLines;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
