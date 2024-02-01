package com.laboratorio.carrito.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carritos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ElementCollection
    @Column(name = "products")
    private List<Long> products;


}
