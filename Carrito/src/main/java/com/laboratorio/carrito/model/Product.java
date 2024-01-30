package com.laboratorio.carrito.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	private Long id;
	private String name;
	private String category;
	private Float price;
	private String color;
	private String peso;
	private String tamanio;
	private String empresa;
	private String url;
	private Boolean disponilble;
}
