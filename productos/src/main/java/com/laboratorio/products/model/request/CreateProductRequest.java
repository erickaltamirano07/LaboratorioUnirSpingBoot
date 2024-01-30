package com.laboratorio.products.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

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
