package com.proyecto.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class LoginController {
	
	@RequestMapping (value="login")
	public ModelAndView goToLogin() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("login");
		
		return vista;
	}

	@RequestMapping(value = "verificarLogin", method = RequestMethod.POST)
	public ModelAndView verificarLogin(@RequestParam(value = "rut", required = true) String rut,
			@RequestParam(value = "password", required = true) String pass, ModelAndView vista) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();

		usuarioTO.setRut(rut);
		usuarioTO.setClave(pass);

		if (usuarioDAO.verificar(usuarioTO) != null) {
			String nombre = usuarioDAO.verificar(usuarioTO).getNombre();
			String rol=usuarioDAO.verificar(usuarioTO).getRol();
			
			//Vista usuario
			if(rol.equalsIgnoreCase("usuario")) {
				vista.addObject("nombre", nombre);
				vista.setViewName("indexUsuario");
			}else {
				
				//Vista adm
				if(rol.equalsIgnoreCase("Administrador")) {
					vista.addObject("nombre", nombre);
					vista.setViewName("index");
				}
			}
			
			return vista;
		} else {
			vista.addObject("notuser", "No es usuario");
			vista.setViewName("login");
			return vista;
		}
	}

	
}
