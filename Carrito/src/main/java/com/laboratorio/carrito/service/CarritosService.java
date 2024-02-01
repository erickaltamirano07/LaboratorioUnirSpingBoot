package com.laboratorio.carrito.service;

import com.laboratorio.carrito.model.db.Carrito;
import com.laboratorio.carrito.model.request.CarritoRequest;


import java.util.List;

public interface CarritosService {
	
	Carrito createCarrito(CarritoRequest request);

	Carrito getCarrito(String id);

	List<Carrito> getCarritos();

	Carrito putCarrito( String id, CarritoRequest request);

	Boolean eliminarCarrito(String id);




}
