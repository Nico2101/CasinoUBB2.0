package com.proyecto.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Map;

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
			vista.addObject("NoHayMenuParaEvaluar", "No tiene menús disponibles para evaluar");
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
				vista.addObject("noQuedanMenusParaEvaluar", "No quedan menús para evaluar");

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

	@RequestMapping(value = "verPromedioValoraciones")
	public ModelAndView verPromedioValoraciones(ModelAndView vista) {
		EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
		LinkedList<EvaluacionTO> lista = new LinkedList<>();
		MenuDAO menuDAO = new MenuDAO();
		MenuTO menuTO = new MenuTO();
		LinkedList<MenuTO> listaMenu = new LinkedList<>();
		if (!evaluacionDAO.promedioValoracion().isEmpty()) {
			lista = evaluacionDAO.promedioValoracion();
			// paso a la vista el promedio de las valoraciones de los menús
			vista.addObject("promedio", lista);

			// ahora buscar la info del menu para mostrarla
			for (int i = 0; i < lista.size(); i++) {
				menuTO.setId(lista.get(i).getIdMenu());
				listaMenu.add(menuDAO.buscarMenu(menuTO));

			}

			vista.addObject("listaMenu", listaMenu);
			vista.setViewName("verPromedioValoraciones");
			
		} else {
			vista.setViewName("indexAdministrador");
			vista.addObject("NoHayValoraciones", "No existen evaluaciones de usuarios");
		}

		return vista;
	}
	@RequestMapping(value = "verEvaluaciones")
	public ModelAndView verEvaluaciones(ModelAndView vista, HttpSession sesion, HttpServletRequest request) throws SQLException {
		EvaluacionDAO dao = new EvaluacionDAO();
		sesion = request.getSession(true);
		int id = (int) sesion.getAttribute("id");
		//int id= 2;
	    vista.setViewName("verEvaluaciones");
	    vista.addObject("lista", dao.obtenerEvaluaciones(id));
		
		return vista;
	}
	
	

	@RequestMapping(value = "editarEvaluacion", method = RequestMethod.GET)
	public ModelAndView buscaEva(ModelAndView vista,
			@RequestParam(value = "id", required = true) int id) throws ParseException {
		EvaluacionDAO dao = new EvaluacionDAO();
	    EvaluacionTO to = new EvaluacionTO();
	    to.setId(id);
	    
		vista.addObject("editEva", dao.obtieneE(to));
		vista.setViewName("editarEvaluacion2");
		return vista;
	}
	
	@RequestMapping(value = "actualizarEvaluacion", method = RequestMethod.GET)
	public ModelAndView actualiza(ModelAndView vista,HttpSession sesion,HttpServletRequest request,
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "score") float score,
			@RequestParam(value="comentario",required=true)String comentario) throws ParseException, SQLException {
		
		EvaluacionDAO dao = new EvaluacionDAO();
		EvaluacionTO to = new EvaluacionTO();
		to.setId(id);
		to.setComentario(comentario);
		to.setValoracion(score);
		
		dao.actualizaEva(to);
		
		sesion = request.getSession(true);
		int idU = (int) sesion.getAttribute("id");
	    vista.setViewName("verEvaluaciones");
	    vista.addObject("lista", dao.obtenerEvaluaciones(idU));
		return vista;
	}
	
	
	@RequestMapping(value = "eliminarEva", method = RequestMethod.GET)
	public ModelAndView elimina(ModelAndView vista,HttpSession sesion,HttpServletRequest request,
			@RequestParam(value = "id", required = true) int id) throws SQLException {
		EvaluacionDAO dao = new EvaluacionDAO();
		EvaluacionTO to = new EvaluacionTO();
		to.setId(id);
		dao.eliminaEvaluacion(to);
		
		sesion = request.getSession(true);
		int idU = (int) sesion.getAttribute("id");
	    vista.setViewName("verEvaluaciones");
	    vista.addObject("lista", dao.obtenerEvaluaciones(idU));
		return vista;
	}
}
