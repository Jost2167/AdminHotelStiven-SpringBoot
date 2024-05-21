package com.stiven.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stiven.app.entity.ERol;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @PostConstruct
    public void postConstruct() {
    	UsuarioEntity usuario = new UsuarioEntity();
    	usuario.setRol(ERol.ADMIN);
    	usuario.setUserName("admin");
    	usuario.setPassword(passwordEncoder.encode("2167"));
    	usuarioRepository.save(usuario);
    }
    
    
    public void registrar(UsuarioEntity usuario) {
    	usuario.setRol(ERol.USER);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    	usuarioRepository.save(usuario);
    }

    public UsuarioEntity findByLogin(String login) {
        return usuarioRepository.findByUserName(login);
    }
}
