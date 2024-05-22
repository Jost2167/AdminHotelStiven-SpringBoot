package com.stiven.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;

public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Long>{
	List<HabitacionEntity> findByUsuario(UsuarioEntity usuario);
    List<HabitacionEntity> findByResidencia(ResidenciaEntity residencia);
}
