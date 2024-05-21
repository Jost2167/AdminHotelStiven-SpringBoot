package com.stiven.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	private String nombre;
	private String apellido;
	private String password;
	private String correo;
	private ERol rol;
	
	public UsuarioEntity() {}

	public UsuarioEntity(String userName, String nombre, String apellido, String password, String correo,
			ERol rol) {
		super();
		this.userName = userName;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.correo = correo;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public ERol getRol() {
		return rol;
	}

	public void setRol(ERol user) {
		this.rol = user;
	}
	
}



