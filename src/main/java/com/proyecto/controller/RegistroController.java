package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class RegistroController {


	@RequestMapping(value = "registroUsuario", method = RequestMethod.POST)
	public ModelAndView registro(ModelAndView vista, @RequestParam(value = "rut", required = true) String rut,
			@RequestParam(value = "nombre", required = true) String nombre,
			@RequestParam(value = "appat", required = true) String apellidoPaterno,
			@RequestParam(value = "apmat", required = true) String apellidoMaterno,
			@RequestParam(value = "clave", required = true) String clave,
			@RequestParam(value = "clave2", required = true) String clave2) {

		UsuarioTO userTO = new UsuarioTO();
		UsuarioDAO userDAO = new UsuarioDAO();

		userTO.setNombre(nombre);
		userTO.setRut(rut);
		userTO.setApellidoPaterno(apellidoPaterno);
		userTO.setApellidoMaterno(apellidoMaterno);
		userTO.setClave(clave);

		if (userDAO.agregarUsuario(userTO)) {
			vista.addObject("registrado", "registrado");
			vista.setViewName("login");
			
		} else {
			vista.addObject("sinregistro", "No se pudo registrar");
			vista.setViewName("login");
			
		}

		return vista;
	}
}
