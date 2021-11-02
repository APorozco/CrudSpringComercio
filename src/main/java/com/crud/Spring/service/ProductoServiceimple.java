package com.crud.Spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.Spring.model.Producto;
import com.crud.Spring.repository.IProductoRepository;


@Service
public class ProductoServiceimple implements ProductoService {

	@Autowired
	private IProductoRepository prod_repo;
	
	
	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return prod_repo.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		
		return prod_repo.findById(id);
	}

	@Override
	public void update(Producto producto) {
		 prod_repo.save(producto);
	}

	@Override
	public void delete(Integer id) {
		prod_repo.deleteById(id);
		
	}

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		return prod_repo.findAll();
	}

}
