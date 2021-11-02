package com.crud.Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.Spring.model.Producto;
import com.crud.Spring.service.ProductoService;

@Controller
@RequestMapping("productos")
public class ProductoController {

	
	@Autowired
	private ProductoService pro;
	
	@GetMapping("")
	public String home() {
		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";

	}
	@PostMapping("/save")
	public String save(Producto p)
	{
		 pro.save(p);
		return "redirect:/productos";
	}
}
