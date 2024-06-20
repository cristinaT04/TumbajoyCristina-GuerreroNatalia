package com.clinicaodontologica.proyectointegradorfinal;

import com.clinicaodontologica.proyectointegradorfinal.dbconnection.H2Connection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoIntegradorFinalApplication {

	public static void main(String[] args) {
//		H2Connection.crearTablas();
		SpringApplication.run(ProyectoIntegradorFinalApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
