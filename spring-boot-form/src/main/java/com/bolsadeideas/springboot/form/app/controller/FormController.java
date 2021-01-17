package com.bolsadeideas.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NombreEditor;
import com.bolsadeideas.springboot.form.app.models.domains.Pais;
import com.bolsadeideas.springboot.form.app.models.domains.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, "nombre", new NombreEditor());
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {

		return Arrays.asList(
				new Pais(1, "COL", "Colombia"), 
				new Pais(2, "AR", "Argentina"),
				new Pais(3, "CL", "Chile"),
				new Pais(4, "PE", "Peru"));
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("COL", "Colombia");
		paises.put("AR", "Argentina");
		paises.put("PE", "Peru");

		return paises;

	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Camilo");
		usuario.setApellido("Arevalo");
		usuario.setIdentificador("12.456.789-K");
		model.addAttribute("titulo", "Guardar Formulario");
		model.addAttribute("usuario", usuario);

		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		// validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado Formulario");

		if (result.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> { errores.put(err.getField(),
			 * "El campo: ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage(
			 * ))); }); model.addAttribute("error", errores);
			 */
			return "form";
		}

		model.addAttribute("titulo", "Resultado Formulario");
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}

}
