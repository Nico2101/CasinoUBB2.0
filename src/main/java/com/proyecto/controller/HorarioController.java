package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.HorarioDAO;

@Controller 
public class HorarioController {
	@RequestMapping(value="liberarCupos")
	public  ModelAndView liberarCupos(ModelAndView vista) {
		
		HorarioDAO HorarioDAO=new HorarioDAO();
		if(HorarioDAO.actualizarRaciones()) {
			vista.addObject("RacionesActualizadas", "Raciones actualizadas correctamente! ");
			vista.setViewName("indexAdministrador");
		}  else {
			vista.addObject("NoActualizadasRaciones", "Error al actualizar las raciones");
			vista.setViewName("indexAdministrador");
		}
		
		return vista;
		
	}
	
	

}