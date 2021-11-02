package com.crud.Spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String home(Model elModelo) {
		List<Producto> lista_products = pro.listar();
		elModelo.addAttribute("productos", lista_products);
		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";

	}

	@PostMapping("/save")
	public String save(Producto p) {
		pro.save(p);
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model elModelo) {
		Producto producto = new Producto();
		Optional<Producto> op = pro.get(id);
		producto = op.get();
		elModelo.addAttribute("producto", producto);

		return "productos/edit";
	}

	@PostMapping("/update")
	public String update(Producto p) {
		pro.update(p);
		return "redirect:/productos";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		pro.delete(id);
		return "redirect:/productos";

	}
}
