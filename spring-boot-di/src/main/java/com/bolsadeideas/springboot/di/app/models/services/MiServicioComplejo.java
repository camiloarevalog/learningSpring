package com.bolsadeideas.springboot.di.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
//@Primary	
public class MiServicioComplejo implements IServicio {

	@Override
	public String operacion() {
		return "ejecutando un proceso complicado";
	}

}
