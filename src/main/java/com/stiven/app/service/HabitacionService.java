package com.stiven.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.repository.HabitacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HabitacionService {
	
	@Autowired
    private HabitacionRepository habitacionRepository;

	public List<HabitacionEntity> getHabitacionesDisponiblesPorResidencia(ResidenciaEntity residencia) {
        return habitacionRepository.findByResidenciaAndEstado(residencia, "Disponible");
    }
	
    public List<HabitacionEntity> getHabitacionesPorUsuario(UsuarioEntity usuario) {
        return habitacionRepository.findByUsuario(usuario);
    }

    public List<HabitacionEntity> getHabitacionesPorResidencia(ResidenciaEntity residencia) {
        return habitacionRepository.findByResidencia(residencia);
    }

    public HabitacionEntity crearHabitacion(HabitacionEntity habitacion) {
        return habitacionRepository.save(habitacion);
    }
    
    public HabitacionEntity obtenerHabitacionPorId(Long habitacionId) {
	    return habitacionRepository.findById(habitacionId).orElse(null);
	}

    public HabitacionEntity actualizarHabitacion(HabitacionEntity habitacion) {
        // Verificar si la habitación existe en la base de datos
        Optional<HabitacionEntity> optionalHabitacion = habitacionRepository.findById(habitacion.getId());
        if (optionalHabitacion.isPresent()) {
            // La habitación existe, actualizar sus atributos
            HabitacionEntity habitacionExistente = optionalHabitacion.get();
            habitacionExistente.setNombre(habitacion.getNombre());
            habitacionExistente.setPrecio(habitacion.getPrecio());
            habitacionExistente.setEstado(habitacion.getEstado());
            habitacionExistente.setResidencia(habitacion.getResidencia());
            habitacionExistente.setUsuario(habitacion.getUsuario());
            // Guardar los cambios en la base de datos
            return habitacionRepository.save(habitacionExistente);
        } else {
            // La habitación no existe en la base de datos, lanzar una excepción
            throw new EntityNotFoundException("La habitación con ID " + habitacion.getId() + " no existe en la base de datos");
        }
    }


	public void eliminarHabitacion(Long habitacionId) {
	    habitacionRepository.deleteById(habitacionId);
	}
    
}
