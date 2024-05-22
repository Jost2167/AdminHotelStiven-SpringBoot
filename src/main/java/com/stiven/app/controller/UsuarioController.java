package com.stiven.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.service.IResidenciaService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IResidenciaService residenciaService;
	
	
	
	
	@GetMapping("/ver-reserva")
	public String verReserva(Model model) {
		List<ResidenciaEntity> residencias = residenciaService.listAll();
		model.addAttribute("residencias", residencias);
		return "index";
	}
	
}
