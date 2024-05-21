package com.stiven.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.service.IResidenciaService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IResidenciaService residenciaService;
    
    @GetMapping("/ver-residencias")
    public String verResidenciasAdmin(Model model) {
        List<ResidenciaEntity> residencias = residenciaService.listAll();
        model.addAttribute("residencias", residencias);
        return "/admin/ver-residencias";
    }

    @GetMapping("/crear-residencia")
    public String getCrearResidenciaAdmin(Model model) {
        ResidenciaEntity residenciaEntity = new ResidenciaEntity();
        model.addAttribute("residenciaEntity", residenciaEntity);
        return "/admin/crear-residencia";
    }

    @PostMapping("/guardar-residencia")
    public String guardarResidenciaAdmin(@Valid @ModelAttribute ResidenciaEntity residenciaEntity, BindingResult result,
            Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("residenciaEntity", residenciaEntity);
            return "/admin/crear-residencia";
        }

        // Verificar si es una residencia existente
        boolean esExistente = residenciaEntity.getId() != null;

        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "C://Residencia//recursos";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                residenciaEntity.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Si es una residencia existente, actualiza sus datos; de lo contrario, guárdala como nueva
        if (esExistente) {
            residenciaService.update(residenciaEntity); // Asume que existe un método `update` en `residenciaService`
        } else {
            residenciaService.save(residenciaEntity);
        }

        return "redirect:/admin/ver-residencias";
    }

    
    @GetMapping("/editar-residencia/{id}")
    public String editarResidenciaAdmin(@PathVariable("id") Long idResidenciaEntity, Model model, RedirectAttributes attribute) {
    	
    	ResidenciaEntity residenciaEntity=null;
    	
    	if(idResidenciaEntity > 0) {
    		residenciaEntity=residenciaService.listById(idResidenciaEntity);
    		
    		if(residenciaEntity ==null)
    		{
    			return "redirect:/admin/ver-residencias";
    		}
    	}else {
    		return "redirect:/admin/ver-residencias";
    	}
    	
    	model.addAttribute("residenciaEntity",residenciaEntity);
    	
    	return "/admin/crear-residencia";
    }
    
    @GetMapping("/eliminar-residencia/{id}")
    public String eliminarResidenciaAdmin(@PathVariable("id") Long idResidenciaEntity, RedirectAttributes attribute) {
        if (idResidenciaEntity > 0) {
            try {
                residenciaService.deleteById(idResidenciaEntity);
            } catch (Exception e) {
                attribute.addFlashAttribute("error", "Error al eliminar la residencia.");
            }
        } else {
            attribute.addFlashAttribute("error", "ID de residencia no válido.");
        }
        return "redirect:/admin/ver-residencias";
    }
   
    
    
}
