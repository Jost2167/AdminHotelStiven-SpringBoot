package com.stiven.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.repository.HabitacionRepository;

@Service
public class HabitacionService {
	
	@Autowired
    private HabitacionRepository habitacionRepository;

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
	    return habitacionRepository.save(habitacion);
	}

	public void eliminarHabitacion(Long habitacionId) {
	    habitacionRepository.deleteById(habitacionId);
	}
    
}
