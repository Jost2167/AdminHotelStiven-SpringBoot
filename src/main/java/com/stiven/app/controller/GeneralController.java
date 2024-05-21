package com.stiven.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.service.IResidenciaService;
import com.stiven.app.service.UsuarioService;

@Controller
public class GeneralController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private IResidenciaService residenciaService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<ResidenciaEntity> residencias = residenciaService.listAll();
		model.addAttribute("residencias", residencias);
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registrarse")
	public String getRegistro(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "registrarse";
	}
	
	@PostMapping("/registrarse")
	public String postRegistro(@ModelAttribute UsuarioEntity usuario) {
		usuarioService.registrar(usuario);
		return "redirect:/login?success";
	}
	
	
}
