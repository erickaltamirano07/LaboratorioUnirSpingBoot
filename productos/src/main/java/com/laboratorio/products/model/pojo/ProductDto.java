package com.laboratorio.products.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {

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
