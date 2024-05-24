package com.stiven.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.CategoriaEntity;
import com.stiven.app.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{

	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public List<CategoriaEntity> listaCategorias() {			
		return (List<CategoriaEntity>) categoriaRepository.findAll();
	}
	
	
}
