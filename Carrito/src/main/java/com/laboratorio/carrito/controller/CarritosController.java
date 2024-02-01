package com.laboratorio.carrito.controller;

import com.laboratorio.carrito.model.db.Carrito;
import com.laboratorio.carrito.model.request.CarritoRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laboratorio.carrito.service.CarritosService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CarritosController {

    private final CarritosService service;

    @PostMapping("/carritos")
    public ResponseEntity<Carrito> createCarrito(@RequestBody @Valid CarritoRequest request) {

        log.info("Creating carrito...");
        Carrito created = service.createCarrito(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/carritos")
    public ResponseEntity<List<Carrito>> getCarritos() {

        List<Carrito> carritos = service.getCarritos();
        if (carritos != null) {
            return ResponseEntity.ok(carritos);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/carritos/{id}")
    public ResponseEntity<Carrito> getCarrito(@PathVariable String id) {

        Carrito carrito = service.getCarrito(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/carritos/{id}")
    public ResponseEntity<Carrito> putCarrito(@PathVariable String id, @RequestBody @Valid CarritoRequest request) {

        Carrito carrito = service.putCarrito(id, request);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/carritos/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable String id) {

        Boolean removed = service.eliminarCarrito(id);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }



}
