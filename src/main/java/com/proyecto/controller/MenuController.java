package com.proyecto.controller;

import org.springframework.stereotype.Controller;
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

}
