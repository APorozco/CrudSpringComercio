package com.crud.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.Spring.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
	

}
