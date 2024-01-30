package com.laboratorio.products.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.laboratorio.products.model.pojo.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.laboratorio.products.model.pojo.Product;
import com.laboratorio.products.model.request.CreateProductRequest;
import com.laboratorio.products.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en una base de datos en memoria.")
public class ProductsController {

    private final ProductsService service;

    @GetMapping("/products")
    @Operation(
            operationId = "Obtener productos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    public ResponseEntity<List<Product>> getProducts(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "name", description = "Nombre del producto. No tiene por que ser exacto", example = "iPhone", required = false)
            @RequestParam(required = false) String name,
            @Parameter(name = "category", description = "Categoría del producto. Debe ser exacto", example = "Electronico", required = false)
            @RequestParam(required = false) String category,
            @Parameter(name = "price", description = "Precio del producto. No tiene por que ser exacta", example = "18.00", required = false)
            @RequestParam(required = false) Float price,
            @Parameter(name = "color", description = "Color del producto. No tiene por que ser exacta", example = "blanco", required = false)
            @RequestParam(required = false) String color,
            @Parameter(name = "peso", description = "Peso del producto. No tiene por que ser exacta", example = "15Kg", required = false)
            @RequestParam(required = false) String peso,
            @Parameter(name = "tamanio", description = "Tamaño del producto. No tiene por que ser exacta", example = "15cmx40cm", required = false)
            @RequestParam(required = false) String tamanio,
            @Parameter(name = "empresa", description = "Marca del producto. No tiene por que ser exacta", example = "Microsoft", required = false)
            @RequestParam(required = false) String empresa,
            @Parameter(name = "url", description = "url de la imagen del producto. Tiene que ser exacta", example = "https://images.pexels.com/photos/12339129", required = false)
            @RequestParam(required = false) String url,
            @Parameter(name = "disponilble", description = "Disponibilidad del producto. true o false", example = "true", required = false)
            @RequestParam(required = false) Boolean disponilble)

    {

        log.info("headers: {}", headers);
        List<Product> products = service.getProducts(name, category, price, color,peso, tamanio, empresa, url, disponilble);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/products/{productId}")
    @Operation(
            operationId = "Obtener un producto",
            description = "Operacion de lectura",
            summary = "Se devuelve un producto a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {

        log.info("Request received for product {}", productId);
        Product product = service.getProduct(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/products/{productId}")
    @Operation(
            operationId = "Eliminar un producto",
            description = "Operacion de escritura",
            summary = "Se elimina un producto a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {

        Boolean removed = service.removeProduct(productId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/products")
    @Operation(
            operationId = "Insertar un producto",
            description = "Operacion de escritura",
            summary = "Se crea un producto a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del producto a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateProductRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductRequest request) {

        Product createdProduct = service.createProduct(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PatchMapping("/products/{productId}")
    @Operation(
            operationId = "Modificar parcialmente un producto",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente un producto.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del producto a crear.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Producto inválido o datos incorrectos introducidos.")
    public ResponseEntity<Product> patchProduct(@PathVariable String productId, @RequestBody String patchBody) {

        Product patched = service.updateProduct(productId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/products/{productId}")
    @Operation(
            operationId = "Modificar totalmente un producto",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente un producto.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del producto a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = ProductDto.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Producto no encontrado.")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody ProductDto body) {

        Product updated = service.updateProduct(productId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
