package com.stiven.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.repository.ResidenciaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResidenciaService implements IResidenciaService{

	@Autowired
	ResidenciaRepository residenciaRepository;

	@Override
	public void save(ResidenciaEntity residencia) {
		residenciaRepository.save(residencia);
		
	}

	@Override
	public List<ResidenciaEntity> listAll() {
		return residenciaRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		residenciaRepository.deleteById(id);
		
	}

	@Override
	public ResidenciaEntity listById(Long id) {
		return residenciaRepository.findById(id).get();
	}
	
	@Override
	public void update(ResidenciaEntity residencia) {
	    // Verificar si la residencia existe en la base de datos
	    Optional<ResidenciaEntity> optionalResidencia = residenciaRepository.findById(residencia.getId());
	    if (optionalResidencia.isPresent()) {
	        // La residencia existe, actualizar sus atributos
	        ResidenciaEntity residenciaExistente = optionalResidencia.get();
	        residenciaExistente.setNombre(residencia.getNombre());
	        residenciaExistente.setUbicacion(residencia.getUbicacion());
	        residenciaExistente.setCategoria(residencia.getCategoria());
	        residenciaExistente.setEstado(residencia.getEstado());
	        residenciaExistente.setPrecio(residencia.getPrecio());
	        residenciaExistente.setDescripcion(residencia.getDescripcion());
	        residenciaExistente.setImagen(residencia.getImagen());
	        // Guardar los cambios en la base de datos
	        residenciaRepository.save(residenciaExistente);
	    } else {
	        // La residencia no existe en la base de datos
	        throw new EntityNotFoundException("La residencia con ID " + residencia.getId() + " no existe en la base de datos");
	    }
	}
	
	
	
	
}
