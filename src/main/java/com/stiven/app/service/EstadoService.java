package com.stiven.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.EstadoEntity;
import com.stiven.app.repository.EstadoRepository;

@Service
public class EstadoService implements IEstadoService{

	@Autowired
	EstadoRepository estadoRepository;
	
	@Override
	public List<EstadoEntity> listaEstados() {
		return (List<EstadoEntity>) estadoRepository.findAll();
	}
	
}
