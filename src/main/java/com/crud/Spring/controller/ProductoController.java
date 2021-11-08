package com.crud.Spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crud.Spring.model.Producto;
import com.crud.Spring.service.ProductoService;
import com.crud.Spring.service.UploadFileService;

@Controller
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoService ProductoService;

	@Autowired
	private UploadFileService upload;

	@GetMapping("")
	public String home(Model elModelo) {
		List<Producto> lista_products = ProductoService.listar();
		elModelo.addAttribute("productos", lista_products);
		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";

	}

	@PostMapping("/save")
	public String save(Producto p, @RequestParam("img") MultipartFile file) throws IOException {

		if (p.getId() == null) { // cuando se crea prod
			String nombreImagen = upload.saveImage(file);
			p.setImagen(nombreImagen);
		} else {
			if (file.isEmpty()) {
				Producto pr = new Producto();
				pr = ProductoService.get(p.getId()).get();
				p.setImagen(p.getImagen());
			} else {

				String nombreImagen = upload.saveImage(file);
				p.setImagen(nombreImagen);

			}

		}

		ProductoService.save(p);

		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model elModelo) {
		Producto producto = new Producto();
		Optional<Producto> op = ProductoService.get(id);
		producto = op.get();
		elModelo.addAttribute("producto", producto);

		return "productos/edit";
	}

	@PostMapping("/update")
	public String update(Producto p) {
		ProductoService.update(p);
		return "redirect:/productos";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		ProductoService.delete(id);
		return "redirect:/productos";

	}
}
