package com.proyecto.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.MenuDAO;
import com.proyecto.transferObject.MenuTO;

@Controller
public class MenuController {

	
	@RequestMapping(value="buscarMenu", method=RequestMethod.GET)
	public ModelAndView buscarMenu(ModelAndView vista) {
		vista.setViewName("buscarMenu");
		return vista;
	}

	@RequestMapping(value = "verificarMenu", method=RequestMethod.GET)
	public ModelAndView verificaMenu(ModelAndView vista,
			@RequestParam(value = "dateSelected", required = true) String date) {

		java.sql.Date fecha = java.sql.Date.valueOf(date);

		MenuDAO menuDAO = new MenuDAO();
		MenuTO menuTO = new MenuTO();

		menuTO.setFecha(fecha);

		if (menuDAO.buscaMenu(menuTO) == 1) {

			vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
			vista.setViewName("verMenu");
			return vista;

		} else {
			vista.addObject("NoHayMenu", "No Hay Menu");
			vista.setViewName("buscarMenu");
			return vista;

		}

	}

	@RequestMapping(value = "/ingresarmenu", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "nombreMenu", required = false, defaultValue = "World") String nombreMenu,
			@RequestParam(value = "tipoMenu", required = false, defaultValue = "World") String tipoMenu,
			@RequestParam(value = "precioMenu", required = false, defaultValue = "World") int precioMenu,
			@RequestParam(value = "dateMenu", required = false, defaultValue = "World") String dateMenu, ModelAndView vista)
			throws SQLException {
		// model.addAttribute("user",user);
		// model.addAttribute("pass",pass);

		MenuDAO dao = new MenuDAO();
		MenuTO to = new MenuTO();
		java.sql.Date fecha = java.sql.Date.valueOf(dateMenu);
		to.setFecha(fecha);
		to.setNombre(nombreMenu);
		to.setPrecio(precioMenu);
		to.setTipo(tipoMenu);
		// to.setFecha(dateMenu);

		dao.ingresaMenu(to);

		// model.addAttribute("MenuTO",menuTO);
		vista.setViewName("ingresarMenu");
		vista.addObject("menuIngresado", "menuIngresado");
		return vista;
	}


}
