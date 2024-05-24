package com.stiven.app.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.service.EstadoService;
import com.stiven.app.service.HabitacionService;
import com.stiven.app.service.ResidenciaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;

	@Autowired
	private ResidenciaService residenciaService;

	@Autowired
	private EstadoService estadoService;
	
	// PERMITE VER LA INFORMACION DE HABITACIONES PERTENECIENTES A UN USUARIO CON SU
	// RESIDENCIA
	@GetMapping("/usuario/{usuarioId}")
	public String getHabitacionesPorUsuario(@PathVariable Long usuarioId, Model model) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(usuarioId);
		List<HabitacionEntity> habitaciones = habitacionService.getHabitacionesPorUsuario(usuario);

		// Obtener información de la residencia asociada a cada habitación
		for (HabitacionEntity habitacion : habitaciones) {
			ResidenciaEntity residencia = habitacion.getResidencia();
			// Asegúrate de cargar la información de residencia si no está cargada aún
			if (residencia != null && !Hibernate.isInitialized(residencia)) {
				Hibernate.initialize(residencia);
			}
		}

		model.addAttribute("habitaciones", habitaciones);
		return "/habitacion/habitaciones-por-usuario"; // Nombre de la vista
	}

	@GetMapping("/ver-habitaciones")
	public String verHabitaciones() {
		return "/habitacion/ver-habitaciones";
	}

	// PERMITE VER LA INFORMACION DE LAS HABITACIONES PERTENECIENTES A UNA
	// RESIDENCIA
	@GetMapping("/residencia/{residenciaId}")
	public String getHabitacionesPorResidencia(@PathVariable Long residenciaId, Model model) {
		ResidenciaEntity residencia = residenciaService.listById(residenciaId);
		List<HabitacionEntity> habitaciones = habitacionService.getHabitacionesPorResidencia(residencia);
		model.addAttribute("residencia", residencia);
		model.addAttribute("habitaciones", habitaciones);
		return "/habitacion/habitaciones-por-residencia"; // Nombre de la vista
	}

	@GetMapping("/crear-habitacion")
	public String mostrarFormularioNuevaHabitacion(Model model) {
		model.addAttribute("habitacion", new HabitacionEntity());
		model.addAttribute("estados", estadoService.listaEstados());
		return "/habitacion/formulario-nueva-habitacion"; // Nombre de la vista
	}

	@PostMapping("/guardar-habitacion")
	public String guardarHabitacionAdmin(@Valid @ModelAttribute HabitacionEntity habitacionEntity, BindingResult result,
			Model model, RedirectAttributes attribute) {

		if (result.hasErrors()) {
			model.addAttribute("habitacionEntity", habitacionEntity);
			return "/habitacion/formulario-nueva-habitacion";
		}

		// Verificar si es una habitación existente
		boolean esExistente = habitacionEntity.getId() != null;
		// Si es una habitación existente, actualiza sus datos; de lo contrario,
		// guárdala como nueva
		if (esExistente) {
			habitacionService.actualizarHabitacion(habitacionEntity);
		} else {
			habitacionService.crearHabitacion(habitacionEntity);
		}

		return "redirect:/habitacion/ver-habitaciones";
	}

	@GetMapping("/editar/{habitacionId}")
	public String editarHabitacion(@PathVariable("habitacionId") Long idHabitacionEntity, Model model,
			RedirectAttributes attribute) {

		HabitacionEntity habitacionEntity = null;

		if (idHabitacionEntity > 0) {
			habitacionEntity = habitacionService.obtenerHabitacionPorId(idHabitacionEntity);

			if (habitacionEntity == null) {
				return "redirect:/habitacion/ver-habitaciones";
			}
		} else {
			return "redirect:/habitacion/ver-habitaciones";
		}

		model.addAttribute("habitacion", habitacionEntity);
		model.addAttribute("estados", estadoService.listaEstados());

		return "/habitacion/formulario-nueva-habitacion";
	}

	@GetMapping("/eliminar/{habitacionId}")
	public String eliminarHabitacion(@PathVariable Long habitacionId, @RequestParam Long usuarioId) {
		habitacionService.eliminarHabitacion(habitacionId);
		return "redirect:/habitacion/ver-habitaciones";
	}
}
