package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class UsuarioController {

	@RequestMapping(value="agregarSaldo", method=RequestMethod.GET)
	public @ResponseBody UsuarioTO verificarUsuario(@RequestParam String rutUsuario, @RequestParam int saldo) {
		
		UsuarioDAO usuarioDAO=new UsuarioDAO();
		UsuarioTO usuarioTO=new UsuarioTO();
		if(usuarioDAO.verificarUsuarioParaAgregarSaldo(rutUsuario)) {
			usuarioTO.setRut(rutUsuario);
			usuarioDAO.agregarSaldo(saldo, rutUsuario);
			return usuarioTO;
		}else {
			return usuarioTO;
		}
		
	}
	
	@RequestMapping(value="listarUsuarios")
	public ModelAndView listarUsuarios(ModelAndView vista) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.obtenerListaUsuarios() != null) {
	      vista.addObject("totalUsuarios", usuarioDAO.obtenerListaUsuarios());
			vista.setViewName("listaUsuarios");
		}else {
			vista.addObject("noHayUusuarios", " error no hay usuarios registrados");
			vista.setViewName("indexAdministrador");
		}
		return vista;
	}
	
	
	
	
	
}
