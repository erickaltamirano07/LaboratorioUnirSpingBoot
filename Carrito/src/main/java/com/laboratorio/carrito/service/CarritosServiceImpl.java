package com.laboratorio.carrito.service;

import com.laboratorio.carrito.data.CarritoJpaRepository;
import com.laboratorio.carrito.facade.ProductsFacade;
import com.laboratorio.carrito.model.Product;
import com.laboratorio.carrito.model.db.Carrito;
import com.laboratorio.carrito.model.request.CarritoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarritosServiceImpl implements CarritosService {

  @Autowired
  private ProductsFacade productsFacade;

  @Autowired
  private CarritoJpaRepository repository;

  @Override
  public Carrito createCarrito(CarritoRequest request) {

    List<Product> products = request.getProducts().stream().map(productsFacade::getProduct).filter(Objects::nonNull).toList();

    if(products.size() != request.getProducts().size()) {
      return null;
    } else {
      Carrito carrito = Carrito.builder().products(products.stream().map(Product::getId).collect(Collectors.toList())).build();
      repository.save(carrito);
      return carrito;
    }
  }

  @Override
  public Carrito getCarrito(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }

  @Override
  public List<Carrito> getCarritos() {
    List<Carrito> carrito = repository.findAll();
    return carrito.isEmpty() ? null : carrito;
  }

  @Override
  public Carrito putCarrito(String id, CarritoRequest request)
  {
    Carrito carrito = repository.getById(Long.valueOf(id));
    List<Product> products = request.getProducts().stream().map(productsFacade::getProduct).toList();
    if (products.size() != request.getProducts().size()) {
      return null;
    } else {
      carrito.setProducts(products.stream().map(Product::getId).collect(Collectors.toList()));
      repository.save(carrito);
      return carrito;
    }
  }

  @Override
  public Boolean eliminarCarrito(String id){
    Carrito carrito = repository.getById(Long.valueOf(id));
    if(carrito!= null){
      repository.delete(carrito);
      return Boolean.TRUE;
    }else {
      return Boolean.FALSE;
    }

  }


}
