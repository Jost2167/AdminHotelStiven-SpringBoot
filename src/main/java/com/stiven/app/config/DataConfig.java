package com.stiven.app.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stiven.app.entity.ERol;
import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.repository.HabitacionRepository;
import com.stiven.app.repository.ResidenciaRepository;
import com.stiven.app.repository.UsuarioRepository;

@Configuration
public class DataConfig {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, 
                                      ResidenciaRepository residenciaRepository, 
                                      HabitacionRepository habitacionRepository) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Crear usuario
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setUserName("juan");
            usuario.setNombre("Juan");
            usuario.setApellido("Pérez");
            usuario.setPassword(passwordEncoder.encode("password"));
            usuario.setCorreo("juan@example.com");
            usuario.setRol(ERol.USER);
            usuarioRepository.save(usuario);

            // Crear residencias
            ResidenciaEntity residenciaA = new ResidenciaEntity();
            residenciaA.setNombre("Residencia A");
            residenciaA.setCategoria("Hotel");
            residenciaA.setUbicacion("Ciudad X");
            residenciaA.setDescripcion("Descripción de la Residencia A");
            residenciaA.setPrecio("100");
            residenciaA.setEstado("Disponible");
            residenciaA.setImagen("neiva1.jpg");
            residenciaRepository.save(residenciaA);

            ResidenciaEntity residenciaB = new ResidenciaEntity();
            residenciaB.setNombre("Residencia B");
            residenciaB.setCategoria("Hostal");
            residenciaB.setUbicacion("Ciudad Y");
            residenciaB.setDescripcion("Descripción de la Residencia B");
            residenciaB.setPrecio("50");
            residenciaB.setEstado("Disponible");
            residenciaB.setImagen("neiva2.jpg");
            residenciaRepository.save(residenciaB);

            // Crear habitaciones para residencia A
            HabitacionEntity habitacion1 = new HabitacionEntity();
            habitacion1.setNombre("Habitación Doble");
            habitacion1.setPrecio("80");
            habitacion1.setEstado("Disponible");
            habitacion1.setResidencia(residenciaA);
            habitacion1.setUsuario(usuario);
            habitacionRepository.save(habitacion1);

            HabitacionEntity habitacion2 = new HabitacionEntity();
            habitacion2.setNombre("Habitación Individual");
            habitacion2.setPrecio("60");
            habitacion2.setEstado("Disponible");
            habitacion2.setResidencia(residenciaA);
            habitacion2.setUsuario(usuario);
            habitacionRepository.save(habitacion2);

            // Crear habitaciones para residencia B
            HabitacionEntity habitacion3 = new HabitacionEntity();
            habitacion3.setNombre("Habitación Compartida");
            habitacion3.setPrecio("30");
            habitacion3.setEstado("Ocupado");
            habitacion3.setResidencia(residenciaB);
            habitacion3.setUsuario(usuario);
            habitacionRepository.save(habitacion3);
        };
    }
}
