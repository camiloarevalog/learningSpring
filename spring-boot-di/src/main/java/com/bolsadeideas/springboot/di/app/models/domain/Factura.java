package com.bolsadeideas.springboot.di.app.models.domain;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class Factura {

	@Value("${factura.descipcion}")
	private String descipcion;

	@Autowired
	private Cliente cliente;

	@Autowired
	@Qualifier("itemsFacturaOficina")
	private List<ItemFactura> items;

	@PostConstruct
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat("").concat("Andres"));
		descipcion = descipcion.concat(" del cliente ").concat(cliente.getNombre());
	}
	
	@PreDestroy
	public void destruir() {
		System.out.print("Factura destruida".concat(descipcion));
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

}
