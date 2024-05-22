package com.stiven.app.service;

import java.util.List;

import com.stiven.app.entity.ResidenciaEntity;

public interface IResidenciaService {

	List<ResidenciaEntity> listAllDisponibles();
	void save(ResidenciaEntity residencia);
	List<ResidenciaEntity> listAll();
	void deleteById(Long id);
	ResidenciaEntity listById(Long id);
	void update(ResidenciaEntity residencia);
	
}
