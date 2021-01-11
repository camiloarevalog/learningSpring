package com.bolsadeideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.services.IServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicioComplejo;

@Configuration
public class AppConfig {

	@Bean("miServicioSimple")
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}

	@Bean("miServicioComplejo")
	@Primary
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}

	@Bean("itemsFactura")
	public List<ItemFactura> regisrarItems() {
		Producto producto1 = new Producto("Celular Sony", 100);
		Producto producto2 = new Producto("Tv LG", 500);
		Producto producto3 = new Producto("Lavadora", 250);
		Producto producto4 = new Producto("Equipo de sonido", 140);
		Producto producto5 = new Producto("Ventilador", 90);

		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 7);
		ItemFactura linea3 = new ItemFactura(producto3, 12);
		ItemFactura linea4 = new ItemFactura(producto4, 2);
		ItemFactura linea5 = new ItemFactura(producto5, 9);

		return Arrays.asList(linea1, linea2, linea3, linea4, linea5);

	}

	@Bean("itemsFacturaOficina")
	public List<ItemFactura> regisrarItemsOficina() {
		Producto producto1 = new Producto("Tv LCD", 400);
		Producto producto2 = new Producto("Asus", 800);
		Producto producto3 = new Producto("Impresora", 450);

		ItemFactura linea1 = new ItemFactura(producto1, 1);
		ItemFactura linea2 = new ItemFactura(producto2, 3);
		ItemFactura linea3 = new ItemFactura(producto3, 5);

		return Arrays.asList(linea1, linea2, linea3);

	}

}
