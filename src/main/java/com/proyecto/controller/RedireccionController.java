package com.proyecto.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedireccionController {

	//Index Adm
	@RequestMapping (value="indexAdministrador",  method = RequestMethod.GET )
	public ModelAndView index() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("indexAdministrador");
		return vista;
	}
	
	//Index User
	@RequestMapping (value="indexUsuario",  method = RequestMethod.GET )
	public ModelAndView indexUsuario() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("indexUsuario");
		return vista;
	}
	
	@RequestMapping (value="salir")
	public ModelAndView cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException{
    	ModelAndView vista = new ModelAndView();
    	vista.setViewName("login");
    	HttpSession sesion = request.getSession(true);
    	sesion.removeAttribute("id");
    	return vista;

    }
	
	@RequestMapping (value="buscarMenu2")
	public ModelAndView buscarMenu2() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("buscarMenu2");
		return vista;
	}

	
}
