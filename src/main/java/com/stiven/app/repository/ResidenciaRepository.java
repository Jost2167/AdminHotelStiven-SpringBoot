package com.stiven.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stiven.app.entity.ResidenciaEntity;

public interface ResidenciaRepository extends JpaRepository<ResidenciaEntity, Long>{
	List<ResidenciaEntity> findByEstado(String estado);
}
