package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
