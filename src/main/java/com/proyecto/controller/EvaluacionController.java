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

import com.proyecto.persistence.EvaluacionDAO;
import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.transferObject.EvaluacionTO;
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
		if (!reservaDAO.existenMenus(idUsuario).isEmpty()) {
			reserva = reservaDAO.existenMenus(idUsuario);

			LinkedList<MenuTO> menu = new LinkedList<>();
			MenuDAO menuDAO = new MenuDAO();
			for (int i = 0; i < reserva.size(); i++) {
				menu.add(i, menuDAO.listaMenuCompradoUsuario(reserva.get(i).getIdMenu()));
			}
			vista.addObject("menu", menu);
			vista.setViewName("menusComprados");
		} else {
			vista.addObject("NoHayMenuParaEvaluar", "No tiene men�s disponibles para evaluar");
			vista.setViewName("indexUsuario");
		}

		return vista;
	}

	@RequestMapping(value = "evaluar")
	public ModelAndView agregarValoracion(ModelAndView vista, @RequestParam(value = "id") int idMenu,
			HttpSession sesion, HttpServletRequest request) {
		sesion = request.getSession(true);

		// guardar el id del menu en una sesion
		sesion.setAttribute("idMenuEvaluado", idMenu);
		vista.setViewName("evaluacion");

		return vista;
	}

	@RequestMapping(value = "agregarValoracion", method = RequestMethod.GET)
	public ModelAndView agregarValoracion(ModelAndView vista, @RequestParam(value = "score") float score,
			@RequestParam(value = "comentario") String comentario, HttpSession sesion, HttpServletRequest request) {
		// enviar los datos a la bbdd
		sesion = request.getSession(true);
		int idUsuario = (int) sesion.getAttribute("id");
		int idMenu = (int) sesion.getAttribute("idMenuEvaluado");
		EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
		EvaluacionTO evaluacionTO = new EvaluacionTO();

		evaluacionTO.setValoracion(score);
		evaluacionTO.setComentario(comentario);
		evaluacionTO.setIdUsuario(idUsuario);
		evaluacionTO.setIdMenu(idMenu);
		ReservaDAO reservaDAO = new ReservaDAO();
		LinkedList<ReservaTO> reserva = new LinkedList<>();

		if (evaluacionDAO.agregarEvaluacion(evaluacionTO)) {

			if (reservaDAO.existenMenus(idUsuario).isEmpty()) {
				vista.addObject("evaluacionAgregada", "Evaluacion agregada correctamente");
				vista.addObject("noQuedanMenusParaEvaluar", "No quedan men�s para evaluar");

				vista.setViewName("indexUsuario");
			} else {
				reserva = reservaDAO.existenMenus(idUsuario);

				LinkedList<MenuTO> menu = new LinkedList<>();
				MenuDAO menuDAO = new MenuDAO();
				for (int i = 0; i < reserva.size(); i++) {
					menu.add(i, menuDAO.listaMenuCompradoUsuario(reserva.get(i).getIdMenu()));
				}
				vista.addObject("menu", menu);
				vista.setViewName("menusComprados");

				vista.addObject("evaluacionAgregada", "Evaluacion agregada correctamente");
			}

		} else {
			vista.setViewName("menusComprados");
			vista.addObject("errorAlValorar", "Se ha producido un error al valorar el menu");
		}
		
		sesion.removeAttribute("idMenuEvaluado");
		return vista;
	}
}
