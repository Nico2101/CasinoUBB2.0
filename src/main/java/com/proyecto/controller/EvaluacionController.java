package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EvaluacionController {

	@RequestMapping(value="listarMenusComprados")
	public ModelAndView listarMenusComprados(ModelAndView vista) {
		vista.setViewName("evaluacion");
		return vista;
	}
	
	@RequestMapping(value="agregarValoracion", method=RequestMethod.GET)
	public ModelAndView agregarValoracion(ModelAndView vista, @RequestParam(value="score") float score, @RequestParam(value="comentario")String comentario) {
		//enviar los datos a la bbdd
		return vista;
	}
}
