package com.laboratorio.carrito.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoRequest {

	@NotNull(message = "`products` cannot be null")
	@NotEmpty(message = "`products` cannot be empty")
	private List<String> products;
}
