package com.stiven.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@GetMapping("/ver-reserva")
	public String verReserva() {
		return "usuario/ver-reserva";
	}
	
}
