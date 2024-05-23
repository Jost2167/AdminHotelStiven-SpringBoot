package com.stiven.app.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.service.EstadoService;
import com.stiven.app.service.HabitacionService;
import com.stiven.app.service.ResidenciaService;

@Controller
@RequestMapping("/habitacion")
public class HabitacionController {
	
	 @Autowired
	    private HabitacionService habitacionService;

	 @Autowired
	    private ResidenciaService residenciaService;
	 
	 @Autowired
	 	private EstadoService estadoService; 
	 
	 

	 	

	 //PERMITE VER LA INFORMACION DE HABITACIONES PERTENECIENTES A UN USUARIO CON SU RESIDENCIA
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
	 public String verHabitaciones(){
		 return "/habitacion/ver-habitaciones";
	 }
	 
	 //PERMITE VER LA INFORMACION DE LAS HABITACIONES PERTENECIENTES A UNA RESIDENCIA
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

	    @PostMapping
	    public String crearHabitacion(@ModelAttribute HabitacionEntity habitacion) {
	        habitacionService.crearHabitacion(habitacion);
	        return "redirect:/habitacion/residencia/" + habitacion.getResidencia().getId();
	    }
	    
	    @GetMapping("/editar/{habitacionId}")
		public String mostrarFormularioEditarHabitacion(@PathVariable Long habitacionId, Model model) {
		    HabitacionEntity habitacion = habitacionService.obtenerHabitacionPorId(habitacionId);
		    model.addAttribute("habitacion", habitacion);
		    return "/habitacion/formulario-editar-habitacion"; // Nombre de la vista
		}

		@PostMapping("/actualizar")
		public String actualizarHabitacion(@ModelAttribute HabitacionEntity habitacion, Model model) {
		    HabitacionEntity habitacionActualizada = habitacionService.actualizarHabitacion(habitacion);
		    model.addAttribute("habitacion", habitacionActualizada);
		    return "redirect:/habitacion/usuario/" + habitacion.getUsuario().getId(); // Ajusta según sea necesario
		}

		@GetMapping("/eliminar/{habitacionId}")
		public String eliminarHabitacion(@PathVariable Long habitacionId, @RequestParam Long usuarioId) {
		    habitacionService.eliminarHabitacion(habitacionId);
		    return "redirect:/habitacion/usuario/" + usuarioId; // Redirige a la lista de habitaciones por usuario
		}
}
