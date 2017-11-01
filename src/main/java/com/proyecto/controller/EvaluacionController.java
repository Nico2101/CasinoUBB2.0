package com.proyecto.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.ReservaTO;

@Controller
public class EvaluacionController {
	// probar si es que funciona xD
	@RequestMapping(value = "listarMenusComprados")
	public ModelAndView listarMenusComprados(ModelAndView vista, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("id");
		ReservaDAO reservaDAO = new ReservaDAO();
		LinkedList<ReservaTO> reserva = new LinkedList<>();
		if (reservaDAO.existenMenus(idUsuario) != null) {
			reserva = reservaDAO.existenMenus(idUsuario);
		}

		LinkedList<MenuTO> menu = new LinkedList<>();
		MenuDAO menuDAO = new MenuDAO();
		for (int i = 0; i < reserva.size(); i++) {
			menu.add(i, menuDAO.listaMenuCompradoUsuario(reserva.get(i).getIdMenu()));
		}
		vista.addObject("menu", menu);
		vista.setViewName("menusComprados");
		return vista;
	}

	@RequestMapping(value = "evaluar")
	public ModelAndView agregarValoracion(ModelAndView vista, @RequestParam(value = "id") int idMenu) {
		vista.setViewName("evaluacion");
		//guardar el id del menu en una sesion
		return vista;
	}

	@RequestMapping(value = "agregarValoracion", method = RequestMethod.GET)
	public ModelAndView agregarValoracion(ModelAndView vista, @RequestParam(value = "score") float score,
			@RequestParam(value = "comentario") String comentario) {
		// enviar los datos a la bbdd
		return vista;
	}
}
