package com.laboratorio.carrito.data;

import com.laboratorio.carrito.model.db.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoJpaRepository extends JpaRepository<Carrito, Long> {
}
