package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedireccionController {

	@RequestMapping (value="index",  method = RequestMethod.GET )
	public ModelAndView index() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("index");
		return vista;
	}
	
	@RequestMapping (value="buscarMenu")
	public ModelAndView buscarMenu() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("buscarMenu");
		return vista;
	}
	
	@RequestMapping (value="salir")
	public ModelAndView salir() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("login");
		return vista;
	}
	
	@RequestMapping (value="ingresarMenu")
	public ModelAndView ingresarMenu() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("ingresarMenu");
		return vista;
	}
	
	@RequestMapping (value="buscarMenu2")
	public ModelAndView buscarMenu2() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("buscarMenu2");
		return vista;
	}
	
	@RequestMapping (value="editarMenu")
	public ModelAndView editarrMenu() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("editarMenu");
		return vista;
	}
	
	@RequestMapping (value="actualizarMenu")
	public ModelAndView actualizarMenu() {
		ModelAndView vista=new ModelAndView();
		vista.setViewName("actualizarMenu");
		return vista;
	}
}
