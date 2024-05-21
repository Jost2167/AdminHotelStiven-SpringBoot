package com.stiven.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.stiven.app.entity.UsuarioEntity;

@Service
public class UsuarioDetalleService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity byLogin= usuarioService.findByLogin(username);
		if(byLogin==null) {
			return null;
		}
		
		return  User.builder()
				.username(byLogin.getUserName())
				.password(byLogin.getPassword())
				.roles(byLogin.getRol().name())
				.build();
				
				
	}

	
	
}
