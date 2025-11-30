package com.mystore.store_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "inventories")
@Data // Gera Getters, Setters, toString, etc.
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE inventories SET deleted = true WHERE id = ?")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity = 0;

    @Column(nullable = false)
    private int reservedQuantity = 0;

    @Column(nullable = false)
    private boolean deleted = false;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
