package com.stiven.app.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stiven.app.entity.CategoriaEntity;
import com.stiven.app.entity.ERol;
import com.stiven.app.entity.EstadoEntity;
import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.entity.UsuarioEntity;
import com.stiven.app.repository.CategoriaRepository;
import com.stiven.app.repository.EstadoRepository;
import com.stiven.app.repository.HabitacionRepository;
import com.stiven.app.repository.ResidenciaRepository;
import com.stiven.app.repository.UsuarioRepository;

@Configuration
public class DataConfig {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, 
                                      ResidenciaRepository residenciaRepository, 
                                      HabitacionRepository habitacionRepository,
                                      EstadoRepository estadoRepository,
                                      CategoriaRepository categoriaRepository ) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            //CREAR ESTADOS
            EstadoEntity estado1= new EstadoEntity();
            estado1.setEstado("DISPONIBLE");
            estadoRepository.save(estado1);
            
            EstadoEntity estado2= new EstadoEntity();
            estado2.setEstado("OCUPADO");
            estadoRepository.save(estado2);
            
            //CREAR CATEGORIAS
            CategoriaEntity categoria1= new CategoriaEntity();
            categoria1.setCategoria("Hotel");
            categoriaRepository.save(categoria1);
            
            CategoriaEntity categoria2= new CategoriaEntity();
            categoria2.setCategoria("Motel");
            categoriaRepository.save(categoria2);
            
            CategoriaEntity categoria3= new CategoriaEntity();
            categoria3.setCategoria("Hogar de paso");
            categoriaRepository.save(categoria3);
            

            //CREAR USUARIO
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setUserName("stiven");
            usuario.setNombre("Stiven");
            usuario.setApellido("Sánchez");
            usuario.setPassword(passwordEncoder.encode("2167"));
            usuario.setCorreo("stiven@gmail.com");
            usuario.setRol(ERol.USER);
            usuarioRepository.save(usuario);

            //CREAR RESIDENCIAS
            ResidenciaEntity residencia1 = new ResidenciaEntity();
            residencia1.setNombre("Hotel Casa Pablo");
            residencia1.setCategoria("Hotel");
            residencia1.setUbicacion("Calle 5 N. 12-45, 410010 Neiva, Colombia");
            residencia1.setDescripcion("Este establecimiento se encuentra en Neiva, a solo 100 metros de la iglesia de San José. Ofrece habitaciones cómodas con conexión Wi-Fi gratuita, desayuno diario y aparcamiento privado gratuito.");
            residencia1.setTelefono("+57 311 2273662");
            residencia1.setEstado("DISPONIBLE");
            residencia1.setImagen("casapablo.webp");
            residenciaRepository.save(residencia1);

            ResidenciaEntity residencia2 = new ResidenciaEntity();
            residencia2.setNombre("GHL Hotel Neiva");
            residencia2.setCategoria("Hotel");
            residencia2.setUbicacion("Carrera 16 No 42-195, 410002 Neiva, Colombia");
            residencia2.setDescripcion("El establecimiento GHL Hotel Neiva está situado en Neiva, en el centro comercial San Pedro Plaza, y ofrece WiFi gratuita. Alberga un restaurante y un centro de negocios.");
            residencia2.setTelefono("+57 316 229 3484");
            residencia2.setEstado("DISPONIBLE");
            residencia2.setImagen("ghlhotelneiva.webp");
            residenciaRepository.save(residencia2);
            
            ResidenciaEntity residencia3 = new ResidenciaEntity();
            residencia3.setNombre("Hotel Neiva Plaza");
            residencia3.setCategoria("Hotel");
            residencia3.setUbicacion("Calle 7 No 4-62, 410002 Neiva, Colombia");
            residencia3.setDescripcion("El Hotel Neiva Plaza se encuentra en Neiva y alberga un restaurante y un centro de fitness. Ofrece un desayuno diario gratuito y conexión Wi-Fi gratuita.");
            residencia3.setTelefono("+57 318 251 3591");
            residencia3.setEstado("DISPONIBLE");
            residencia3.setImagen("hotelneivaplaza.webp");
            residenciaRepository.save(residencia3);
            
            ResidenciaEntity residencia4 = new ResidenciaEntity();
            residencia4.setNombre("Motel Casa Loma");
            residencia4.setCategoria("Motel");
            residencia4.setUbicacion("Carrera 7 B # 25 – 05 Zona industrial Palermo, Neiva, Colombia");
            residencia4.setDescripcion("El Motel Casa Loma ofrece habitaciones privadas con todas las comodidades necesarias para una estancia discreta y confortable. Ubicado en la zona industrial de Palermo, es ideal para viajeros de negocios y aquellos que buscan privacidad.");
            residencia4.setTelefono("+57 320 852 7890");
            residencia4.setEstado("DISPONIBLE");
            residencia4.setImagen("descargar.jpeg");
            residenciaRepository.save(residencia4);
            
            ResidenciaEntity residencia5 = new ResidenciaEntity();
            residencia5.setNombre("Hogar de Paso Esperanza");
            residencia5.setCategoria("Hogar de paso");
            residencia5.setUbicacion("Carrera 10 No 15-30, 410010 Neiva, Colombia");
            residencia5.setDescripcion("El Hogar de Paso Esperanza brinda refugio temporal a personas en situación de vulnerabilidad, ofreciendo un ambiente seguro y acogedor. Proporciona atención integral incluyendo alimentación, servicios médicos y apoyo psicológico.");
            residencia5.setTelefono("+57 322 455 1234");
            residencia5.setEstado("DISPONIBLE");
            residencia5.setImagen("hogarlaesperanza.jpeg");
            residenciaRepository.save(residencia5);
            

            //HABITACIONES PARA RESIDENCIA 1
            HabitacionEntity habitacion1r1 = new HabitacionEntity();
            habitacion1r1.setNombre("Habitación Doble");
            habitacion1r1.setPrecio("69000");
            habitacion1r1.setEstado("DISPONIBLE");
            habitacion1r1.setResidencia(residencia1);
            habitacion1r1.setUsuario(usuario);
            habitacionRepository.save(habitacion1r1);
            
            HabitacionEntity habitacion2r1 = new HabitacionEntity();
            habitacion2r1.setNombre("Habitación Sencilla");
            habitacion2r1.setPrecio("49999");
            habitacion2r1.setEstado("DISPONIBLE");
            habitacion2r1.setResidencia(residencia1);
            habitacion2r1.setUsuario(usuario);
            habitacionRepository.save(habitacion2r1);

            HabitacionEntity habitacion3r1 = new HabitacionEntity();
            habitacion3r1.setNombre("Habitación Triple");
            habitacion3r1.setPrecio("89999");
            habitacion3r1.setEstado("DISPONIBLE");
            habitacion3r1.setResidencia(residencia1);
            habitacion3r1.setUsuario(usuario);
            habitacionRepository.save(habitacion3r1);

            HabitacionEntity habitacion4r1 = new HabitacionEntity();
            habitacion4r1.setNombre("Suite Junior");
            habitacion4r1.setPrecio("119999");
            habitacion4r1.setEstado("DISPONIBLE");
            habitacion4r1.setResidencia(residencia1);
            habitacion4r1.setUsuario(usuario);
            habitacionRepository.save(habitacion4r1);

            HabitacionEntity habitacion5r1 = new HabitacionEntity();
            habitacion5r1.setNombre("Suite Presidencial");
            habitacion5r1.setPrecio("199999");
            habitacion5r1.setEstado("DISPONIBLE");
            habitacion5r1.setResidencia(residencia1);
            habitacion5r1.setUsuario(usuario);
            habitacionRepository.save(habitacion5r1);

            HabitacionEntity habitacion6r1 = new HabitacionEntity();
            habitacion6r1.setNombre("Habitación Familiar");
            habitacion6r1.setPrecio("139999");
            habitacion6r1.setEstado("DISPONIBLE");
            habitacion6r1.setResidencia(residencia1);
            habitacion6r1.setUsuario(usuario);
            habitacionRepository.save(habitacion6r1);
            
            HabitacionEntity habitacion7r1 = new HabitacionEntity();
            habitacion7r1.setNombre("Habitación Deluxe");
            habitacion7r1.setPrecio("149999");
            habitacion7r1.setEstado("DISPONIBLE");
            habitacion7r1.setResidencia(residencia1);
            habitacion7r1.setUsuario(usuario);
            habitacionRepository.save(habitacion7r1);

            HabitacionEntity habitacion8r1 = new HabitacionEntity();
            habitacion8r1.setNombre("Habitación Económica");
            habitacion8r1.setPrecio("39999");
            habitacion8r1.setEstado("DISPONIBLE");
            habitacion8r1.setResidencia(residencia1);
            habitacion8r1.setUsuario(usuario);
            habitacionRepository.save(habitacion8r1);

            HabitacionEntity habitacion9r1 = new HabitacionEntity();
            habitacion9r1.setNombre("Habitación Ejecutiva");
            habitacion9r1.setPrecio("109999");
            habitacion9r1.setEstado("DISPONIBLE");
            habitacion9r1.setResidencia(residencia1);
            habitacion9r1.setUsuario(usuario);
            habitacionRepository.save(habitacion9r1);
            
            HabitacionEntity habitacion10r1 = new HabitacionEntity();
            habitacion10r1.setNombre("Suite Presidencial");
            habitacion10r1.setPrecio("299999");
            habitacion10r1.setEstado("DISPONIBLE");
            habitacion10r1.setResidencia(residencia1);
            habitacion10r1.setUsuario(usuario);
            habitacionRepository.save(habitacion10r1);

            HabitacionEntity habitacion11r1 = new HabitacionEntity();
            habitacion11r1.setNombre("Habitación Familiar");
            habitacion11r1.setPrecio("129999");
            habitacion11r1.setEstado("DISPONIBLE");
            habitacion11r1.setResidencia(residencia1);
            habitacion11r1.setUsuario(usuario);
            habitacionRepository.save(habitacion11r1);

            HabitacionEntity habitacion12r1 = new HabitacionEntity();
            habitacion12r1.setNombre("Habitación Sencilla");
            habitacion12r1.setPrecio("49999");
            habitacion12r1.setEstado("DISPONIBLE");
            habitacion12r1.setResidencia(residencia1);
            habitacion12r1.setUsuario(usuario);
            habitacionRepository.save(habitacion12r1);

            //HABITACIONES PARA RESIDENCIA 2
            HabitacionEntity habitacion1r2 = new HabitacionEntity();
            habitacion1r2.setNombre("Habitación Doble");
            habitacion1r2.setPrecio("69000");
            habitacion1r2.setEstado("DISPONIBLE");
            habitacion1r2.setResidencia(residencia2);
            habitacion1r2.setUsuario(usuario);
            habitacionRepository.save(habitacion1r2);

            HabitacionEntity habitacion2r2 = new HabitacionEntity();
            habitacion2r2.setNombre("Habitación Sencilla");
            habitacion2r2.setPrecio("49999");
            habitacion2r2.setEstado("DISPONIBLE");
            habitacion2r2.setResidencia(residencia2);
            habitacion2r2.setUsuario(usuario);
            habitacionRepository.save(habitacion2r2);

            HabitacionEntity habitacion3r2 = new HabitacionEntity();
            habitacion3r2.setNombre("Habitación Triple");
            habitacion3r2.setPrecio("89999");
            habitacion3r2.setEstado("DISPONIBLE");
            habitacion3r2.setResidencia(residencia2);
            habitacion3r2.setUsuario(usuario);
            habitacionRepository.save(habitacion3r2);

            HabitacionEntity habitacion4r2 = new HabitacionEntity();
            habitacion4r2.setNombre("Suite Junior");
            habitacion4r2.setPrecio("119999");
            habitacion4r2.setEstado("DISPONIBLE");
            habitacion4r2.setResidencia(residencia2);
            habitacion4r2.setUsuario(usuario);
            habitacionRepository.save(habitacion4r2);

            HabitacionEntity habitacion5r2 = new HabitacionEntity();
            habitacion5r2.setNombre("Suite Presidencial");
            habitacion5r2.setPrecio("199999");
            habitacion5r2.setEstado("DISPONIBLE");
            habitacion5r2.setResidencia(residencia2);
            habitacion5r2.setUsuario(usuario);
            habitacionRepository.save(habitacion5r2);

            HabitacionEntity habitacion6r2 = new HabitacionEntity();
            habitacion6r2.setNombre("Habitación Familiar");
            habitacion6r2.setPrecio("139999");
            habitacion6r2.setEstado("DISPONIBLE");
            habitacion6r2.setResidencia(residencia2);
            habitacion6r2.setUsuario(usuario);
            habitacionRepository.save(habitacion6r2);

            HabitacionEntity habitacion7r2 = new HabitacionEntity();
            habitacion7r2.setNombre("Habitación Deluxe");
            habitacion7r2.setPrecio("149999");
            habitacion7r2.setEstado("DISPONIBLE");
            habitacion7r2.setResidencia(residencia2);
            habitacion7r2.setUsuario(usuario);
            habitacionRepository.save(habitacion7r2);

            HabitacionEntity habitacion8r2 = new HabitacionEntity();
            habitacion8r2.setNombre("Habitación Económica");
            habitacion8r2.setPrecio("39999");
            habitacion8r2.setEstado("DISPONIBLE");
            habitacion8r2.setResidencia(residencia2);
            habitacion8r2.setUsuario(usuario);
            habitacionRepository.save(habitacion8r2);

            HabitacionEntity habitacion9r2 = new HabitacionEntity();
            habitacion9r2.setNombre("Habitación Ejecutiva");
            habitacion9r2.setPrecio("109999");
            habitacion9r2.setEstado("DISPONIBLE");
            habitacion9r2.setResidencia(residencia2);
            habitacion9r2.setUsuario(usuario);
            habitacionRepository.save(habitacion9r2);

            HabitacionEntity habitacion10r2 = new HabitacionEntity();
            habitacion10r2.setNombre("Suite Presidencial");
            habitacion10r2.setPrecio("299999");
            habitacion10r2.setEstado("DISPONIBLE");
            habitacion10r2.setResidencia(residencia2);
            habitacion10r2.setUsuario(usuario);
            habitacionRepository.save(habitacion10r2);

            HabitacionEntity habitacion11r2 = new HabitacionEntity();
            habitacion11r2.setNombre("Habitación Familiar");
            habitacion11r2.setPrecio("129999");
            habitacion11r2.setEstado("DISPONIBLE");
            habitacion11r2.setResidencia(residencia2);
            habitacion11r2.setUsuario(usuario);
            habitacionRepository.save(habitacion11r2);

            HabitacionEntity habitacion12r2 = new HabitacionEntity();
            habitacion12r2.setNombre("Habitación Sencilla");
            habitacion12r2.setPrecio("49999");
            habitacion12r2.setEstado("DISPONIBLE");
            habitacion12r2.setResidencia(residencia2);
            habitacion12r2.setUsuario(usuario);
            habitacionRepository.save(habitacion12r2);

            //HABITACIONES PARA RESIDENCIA 3
            HabitacionEntity habitacion1r3 = new HabitacionEntity();
            habitacion1r3.setNombre("Habitación Doble");
            habitacion1r3.setPrecio("69000");
            habitacion1r3.setEstado("DISPONIBLE");
            habitacion1r3.setResidencia(residencia3);
            habitacion1r3.setUsuario(usuario);
            habitacionRepository.save(habitacion1r3);

            HabitacionEntity habitacion2r3 = new HabitacionEntity();
            habitacion2r3.setNombre("Habitación Sencilla");
            habitacion2r3.setPrecio("49999");
            habitacion2r3.setEstado("DISPONIBLE");
            habitacion2r3.setResidencia(residencia3);
            habitacion2r3.setUsuario(usuario);
            habitacionRepository.save(habitacion2r3);

            HabitacionEntity habitacion3r3 = new HabitacionEntity();
            habitacion3r3.setNombre("Habitación Triple");
            habitacion3r3.setPrecio("89999");
            habitacion3r3.setEstado("DISPONIBLE");
            habitacion3r3.setResidencia(residencia3);
            habitacion3r3.setUsuario(usuario);
            habitacionRepository.save(habitacion3r3);

            HabitacionEntity habitacion4r3 = new HabitacionEntity();
            habitacion4r3.setNombre("Suite Junior");
            habitacion4r3.setPrecio("119999");
            habitacion4r3.setEstado("DISPONIBLE");
            habitacion4r3.setResidencia(residencia3);
            habitacion4r3.setUsuario(usuario);
            habitacionRepository.save(habitacion4r3);

            HabitacionEntity habitacion5r3 = new HabitacionEntity();
            habitacion5r3.setNombre("Suite Presidencial");
            habitacion5r3.setPrecio("199999");
            habitacion5r3.setEstado("DISPONIBLE");
            habitacion5r3.setResidencia(residencia3);
            habitacion5r3.setUsuario(usuario);
            habitacionRepository.save(habitacion5r3);

            HabitacionEntity habitacion6r3 = new HabitacionEntity();
            habitacion6r3.setNombre("Habitación Familiar");
            habitacion6r3.setPrecio("139999");
            habitacion6r3.setEstado("DISPONIBLE");
            habitacion6r3.setResidencia(residencia3);
            habitacion6r3.setUsuario(usuario);
            habitacionRepository.save(habitacion6r3);

            HabitacionEntity habitacion7r3 = new HabitacionEntity();
            habitacion7r3.setNombre("Habitación Deluxe");
            habitacion7r3.setPrecio("149999");
            habitacion7r3.setEstado("DISPONIBLE");
            habitacion7r3.setResidencia(residencia3);
            habitacion7r3.setUsuario(usuario);
            habitacionRepository.save(habitacion7r3);

            HabitacionEntity habitacion8r3 = new HabitacionEntity();
            habitacion8r3.setNombre("Habitación Económica");
            habitacion8r3.setPrecio("39999");
            habitacion8r3.setEstado("DISPONIBLE");
            habitacion8r3.setResidencia(residencia3);
            habitacion8r3.setUsuario(usuario);
            habitacionRepository.save(habitacion8r3);

            HabitacionEntity habitacion9r3 = new HabitacionEntity();
            habitacion9r3.setNombre("Habitación Ejecutiva");
            habitacion9r3.setPrecio("109999");
            habitacion9r3.setEstado("DISPONIBLE");
            habitacion9r3.setResidencia(residencia3);
            habitacion9r3.setUsuario(usuario);
            habitacionRepository.save(habitacion9r3);

            HabitacionEntity habitacion10r3 = new HabitacionEntity();
            habitacion10r3.setNombre("Suite Presidencial");
            habitacion10r3.setPrecio("299999");
            habitacion10r3.setEstado("DISPONIBLE");
            habitacion10r3.setResidencia(residencia3);
            habitacion10r3.setUsuario(usuario);
            habitacionRepository.save(habitacion10r3);

            HabitacionEntity habitacion11r3 = new HabitacionEntity();
            habitacion11r3.setNombre("Habitación Familiar");
            habitacion11r3.setPrecio("129999");
            habitacion11r3.setEstado("DISPONIBLE");
            habitacion11r3.setResidencia(residencia3);
            habitacion11r3.setUsuario(usuario);
            habitacionRepository.save(habitacion11r3);

            HabitacionEntity habitacion12r3 = new HabitacionEntity();
            habitacion12r3.setNombre("Habitación Sencilla");
            habitacion12r3.setPrecio("49999");
            habitacion12r3.setEstado("DISPONIBLE");
            habitacion12r3.setResidencia(residencia3);
            habitacion12r3.setUsuario(usuario);
            habitacionRepository.save(habitacion12r3);

            //HABITACIONES PARA RESIDENCIA 4
            HabitacionEntity habitacion1r4 = new HabitacionEntity();
            habitacion1r4.setNombre("Habitación Doble");
            habitacion1r4.setPrecio("69000");
            habitacion1r4.setEstado("DISPONIBLE");
            habitacion1r4.setResidencia(residencia4);
            habitacion1r4.setUsuario(usuario);
            habitacionRepository.save(habitacion1r4);

            HabitacionEntity habitacion2r4 = new HabitacionEntity();
            habitacion2r4.setNombre("Habitación Sencilla");
            habitacion2r4.setPrecio("49999");
            habitacion2r4.setEstado("DISPONIBLE");
            habitacion2r4.setResidencia(residencia4);
            habitacion2r4.setUsuario(usuario);
            habitacionRepository.save(habitacion2r4);

            HabitacionEntity habitacion3r4 = new HabitacionEntity();
            habitacion3r4.setNombre("Habitación Triple");
            habitacion3r4.setPrecio("89999");
            habitacion3r4.setEstado("DISPONIBLE");
            habitacion3r4.setResidencia(residencia4);
            habitacion3r4.setUsuario(usuario);
            habitacionRepository.save(habitacion3r4);

            HabitacionEntity habitacion4r4 = new HabitacionEntity();
            habitacion4r4.setNombre("Suite Junior");
            habitacion4r4.setPrecio("119999");
            habitacion4r4.setEstado("DISPONIBLE");
            habitacion4r4.setResidencia(residencia4);
            habitacion4r4.setUsuario(usuario);
            habitacionRepository.save(habitacion4r4);

            HabitacionEntity habitacion5r4 = new HabitacionEntity();
            habitacion5r4.setNombre("Suite Presidencial");
            habitacion5r4.setPrecio("199999");
            habitacion5r4.setEstado("DISPONIBLE");
            habitacion5r4.setResidencia(residencia4);
            habitacion5r4.setUsuario(usuario);
            habitacionRepository.save(habitacion5r4);

            HabitacionEntity habitacion6r4 = new HabitacionEntity();
            habitacion6r4.setNombre("Habitación Familiar");
            habitacion6r4.setPrecio("139999");
            habitacion6r4.setEstado("DISPONIBLE");
            habitacion6r4.setResidencia(residencia4);
            habitacion6r4.setUsuario(usuario);
            habitacionRepository.save(habitacion6r4);

            HabitacionEntity habitacion7r4 = new HabitacionEntity();
            habitacion7r4.setNombre("Habitación Deluxe");
            habitacion7r4.setPrecio("149999");
            habitacion7r4.setEstado("DISPONIBLE");
            habitacion7r4.setResidencia(residencia4);
            habitacion7r4.setUsuario(usuario);
            habitacionRepository.save(habitacion7r4);

            HabitacionEntity habitacion8r4 = new HabitacionEntity();
            habitacion8r4.setNombre("Habitación Económica");
            habitacion8r4.setPrecio("39999");
            habitacion8r4.setEstado("DISPONIBLE");
            habitacion8r4.setResidencia(residencia4);
            habitacion8r4.setUsuario(usuario);
            habitacionRepository.save(habitacion8r4);

            HabitacionEntity habitacion9r4 = new HabitacionEntity();
            habitacion9r4.setNombre("Habitación Ejecutiva");
            habitacion9r4.setPrecio("109999");
            habitacion9r4.setEstado("DISPONIBLE");
            habitacion9r4.setResidencia(residencia4);
            habitacion9r4.setUsuario(usuario);
            habitacionRepository.save(habitacion9r4);

            HabitacionEntity habitacion10r4 = new HabitacionEntity();
            habitacion10r4.setNombre("Suite Presidencial");
            habitacion10r4.setPrecio("299999");
            habitacion10r4.setEstado("DISPONIBLE");
            habitacion10r4.setResidencia(residencia4);
            habitacion10r4.setUsuario(usuario);
            habitacionRepository.save(habitacion10r4);

            HabitacionEntity habitacion11r4 = new HabitacionEntity();
            habitacion11r4.setNombre("Habitación Familiar");
            habitacion11r4.setPrecio("129999");
            habitacion11r4.setEstado("DISPONIBLE");
            habitacion11r4.setResidencia(residencia4);
            habitacion11r4.setUsuario(usuario);
            habitacionRepository.save(habitacion11r4);

            HabitacionEntity habitacion12r4 = new HabitacionEntity();
            habitacion12r4.setNombre("Habitación Sencilla");
            habitacion12r4.setPrecio("49999");
            habitacion12r4.setEstado("DISPONIBLE");
            habitacion12r4.setResidencia(residencia4);
            habitacion12r4.setUsuario(usuario);
            habitacionRepository.save(habitacion12r4);

            //HABITACIONES PARA RESIDENCIA 5
            HabitacionEntity habitacion1r5 = new HabitacionEntity();
            habitacion1r5.setNombre("Habitación Doble");
            habitacion1r5.setPrecio("69000");
            habitacion1r5.setEstado("DISPONIBLE");
            habitacion1r5.setResidencia(residencia5);
            habitacion1r5.setUsuario(usuario);
            habitacionRepository.save(habitacion1r5);

            HabitacionEntity habitacion2r5 = new HabitacionEntity();
            habitacion2r5.setNombre("Habitación Sencilla");
            habitacion2r5.setPrecio("49999");
            habitacion2r5.setEstado("DISPONIBLE");
            habitacion2r5.setResidencia(residencia5);
            habitacion2r5.setUsuario(usuario);
            habitacionRepository.save(habitacion2r5);

            HabitacionEntity habitacion3r5 = new HabitacionEntity();
            habitacion3r5.setNombre("Habitación Triple");
            habitacion3r5.setPrecio("89999");
            habitacion3r5.setEstado("DISPONIBLE");
            habitacion3r5.setResidencia(residencia5);
            habitacion3r5.setUsuario(usuario);
            habitacionRepository.save(habitacion3r5);

            HabitacionEntity habitacion4r5 = new HabitacionEntity();
            habitacion4r5.setNombre("Suite Junior");
            habitacion4r5.setPrecio("119999");
            habitacion4r5.setEstado("DISPONIBLE");
            habitacion4r5.setResidencia(residencia5);
            habitacion4r5.setUsuario(usuario);
            habitacionRepository.save(habitacion4r5);

            HabitacionEntity habitacion5r5 = new HabitacionEntity();
            habitacion5r5.setNombre("Suite Presidencial");
            habitacion5r5.setPrecio("199999");
            habitacion5r5.setEstado("DISPONIBLE");
            habitacion5r5.setResidencia(residencia5);
            habitacion5r5.setUsuario(usuario);
            habitacionRepository.save(habitacion5r5);

            HabitacionEntity habitacion6r5 = new HabitacionEntity();
            habitacion6r5.setNombre("Habitación Familiar");
            habitacion6r5.setPrecio("139999");
            habitacion6r5.setEstado("DISPONIBLE");
            habitacion6r5.setResidencia(residencia5);
            habitacion6r5.setUsuario(usuario);
            habitacionRepository.save(habitacion6r5);

            HabitacionEntity habitacion7r5 = new HabitacionEntity();
            habitacion7r5.setNombre("Habitación Deluxe");
            habitacion7r5.setPrecio("149999");
            habitacion7r5.setEstado("DISPONIBLE");
            habitacion7r5.setResidencia(residencia5);
            habitacion7r5.setUsuario(usuario);
            habitacionRepository.save(habitacion7r5);

            HabitacionEntity habitacion8r5 = new HabitacionEntity();
            habitacion8r5.setNombre("Habitación Económica");
            habitacion8r5.setPrecio("39999");
            habitacion8r5.setEstado("DISPONIBLE");
            habitacion8r5.setResidencia(residencia5);
            habitacion8r5.setUsuario(usuario);
            habitacionRepository.save(habitacion8r5);

            HabitacionEntity habitacion9r5 = new HabitacionEntity();
            habitacion9r5.setNombre("Habitación Ejecutiva");
            habitacion9r5.setPrecio("109999");
            habitacion9r5.setEstado("DISPONIBLE");
            habitacion9r5.setResidencia(residencia5);
            habitacion9r5.setUsuario(usuario);
            habitacionRepository.save(habitacion9r5);

            HabitacionEntity habitacion10r5 = new HabitacionEntity();
            habitacion10r5.setNombre("Suite Presidencial");
            habitacion10r5.setPrecio("299999");
            habitacion10r5.setEstado("DISPONIBLE");
            habitacion10r5.setResidencia(residencia5);
            habitacion10r5.setUsuario(usuario);
            habitacionRepository.save(habitacion10r5);

            HabitacionEntity habitacion11r5 = new HabitacionEntity();
            habitacion11r5.setNombre("Habitación Familiar");
            habitacion11r5.setPrecio("129999");
            habitacion11r5.setEstado("DISPONIBLE");
            habitacion11r5.setResidencia(residencia5);
            habitacion11r5.setUsuario(usuario);
            habitacionRepository.save(habitacion11r5);

            HabitacionEntity habitacion12r5 = new HabitacionEntity();
            habitacion12r5.setNombre("Habitación Sencilla");
            habitacion12r5.setPrecio("49999");
            habitacion12r5.setEstado("DISPONIBLE");
            habitacion12r5.setResidencia(residencia5);
            habitacion12r5.setUsuario(usuario);
            habitacionRepository.save(habitacion12r5);

        };
    }
}
