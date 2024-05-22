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
	    List<ResidenciaEntity> residenciasDisponibles = residenciaService.listAllDisponibles();
	    model.addAttribute("residencias", residenciasDisponibles);
	    return "index";
	}
	
	

//	@GetMapping("/")
//	public String index(Model model) {
//	    // Obtener el usuario existente por su nombre de usuario
//	    String nombreUsuario = "juan"; // Cambia esto por el nombre de usuario que deseas utilizar
//	    UsuarioEntity usuario = usuarioService.findByLogin(nombreUsuario);
//	    
//	    // Verificar si el usuario existe
//	    if (usuario != null) {
//	        // Crear una lista de autoridades con el rol de USER
//	        List<GrantedAuthority> authorities = new ArrayList<>();
//	        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//	        
//	        // Crear una autenticación para el usuario con el rol USER
//	        Authentication auth = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
//	        SecurityContextHolder.getContext().setAuthentication(auth);
//	        
//	        // Aquí puedes realizar otras operaciones si el usuario existe
//	        List<ResidenciaEntity> residenciasDisponibles = residenciaService.listAllDisponibles();
//	        model.addAttribute("residencias", residenciasDisponibles);
//	    } else {
//	        // Manejar el caso en el que el usuario no existe
//	        model.addAttribute("mensaje", "Usuario no encontrado");
//	    }
//	    
//	    return "index";
//	}
	
	
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
