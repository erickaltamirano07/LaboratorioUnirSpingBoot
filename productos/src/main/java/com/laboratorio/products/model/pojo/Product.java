package com.laboratorio.products.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "category")
	private String category;
	@Column(name = "price")
	private Float price;
	@Column(name = "color")
	private String color;
	@Column(name = "peso")
	private String peso;
	@Column(name = "tamanio")
	private String tamanio;
	@Column(name = "empresa")
	private String empresa;
	@Column(name = "url")
	private String url;
	@Column(name = "disponible")
 	private Boolean disponilble;
	


	public void update(ProductDto productDto) {
		this.name = productDto.getName();
		this.category = productDto.getCategory();
		this.price = productDto.getPrice();
		this.color = productDto.getColor();
		this.peso=productDto.getPeso();
		this.tamanio=productDto.getTamanio();
		this.empresa= productDto.getEmpresa();
		this.url=productDto.getUrl();
		this.disponilble=productDto.getDisponilble();

	}

}
