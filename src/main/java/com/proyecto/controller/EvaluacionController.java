package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EvaluacionController {

	@RequestMapping(value="listarMenusComprados")
	public ModelAndView listarMenusComprados(ModelAndView vista) {
		vista.setViewName("evaluacion");
		return vista;
	}
}
