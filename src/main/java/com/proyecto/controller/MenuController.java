package com.proyecto.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.HorarioDAO;
import com.proyecto.persistence.MenuDAO;
import com.proyecto.transferObject.MenuTO;

@Controller
public class MenuController {

	// adm view
	@RequestMapping(value = "ingresarMenu")
	public ModelAndView ingresarMenu() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("ingresarMenu");
		return vista;
	}

	@RequestMapping(value = "/ingresarmenu", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value = "nombreMenu", required = false, defaultValue = "World") String nombreMenu,
			@RequestParam(value = "tipoMenu", required = false, defaultValue = "World") String tipoMenu,
			@RequestParam(value = "precioMenu", required = false, defaultValue = "World") int precioMenu,
			@RequestParam(value = "dateMenu", required = false, defaultValue = "World") String dateMenu,
			@RequestParam(value = "cantidadRaciones") int cantRaciones, ModelAndView vista)
			throws SQLException, ParseException {
		// model.addAttribute("user",user);
		// model.addAttribute("pass",pass);

		// Validar fechas
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = parser.parse(dateMenu);
		Date fechaSistema = new Date();

		if (date1.getDate() < fechaSistema.getDate() || date1.getMonth() < fechaSistema.getMonth()
				|| date1.getYear() < fechaSistema.getYear()) {
			vista.addObject("fechaAnterior", "fecha ingresada anterior a la fecha actual");
			vista.addObject("nombreMenu", nombreMenu);
			vista.addObject("tipoMenu", tipoMenu);
			vista.addObject("precioMenu", precioMenu);
			vista.addObject("cantRaciones", cantRaciones);
			vista.setViewName("recargarIngresarMenu");
			return vista;
			// hasta aqui se valida fecha

		} else {
			MenuDAO dao = new MenuDAO();
			MenuTO to = new MenuTO();
			java.sql.Date fecha = java.sql.Date.valueOf(dateMenu);
			to.setFecha(fecha);
			to.setNombre(nombreMenu);
			to.setPrecio(precioMenu);
			to.setTipo(tipoMenu);
			to.setCantRaciones(cantRaciones);
			// to.setFecha(dateMenu);

			dao.ingresaMenu(to);

			// model.addAttribute("MenuTO",menuTO);
			vista.setViewName("ingresarMenu");
			vista.addObject("menuIngresado", "menuIngresado");
			return vista;
		}

	}

	// user view
	@RequestMapping(value = "buscarMenu", method = RequestMethod.GET)
	public ModelAndView buscarMenu(ModelAndView vista) {
		vista.setViewName("buscarMenu");
		return vista;
	}

	@RequestMapping(value = "buscarMenu2")
	public ModelAndView buscarMenu2() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("buscarMenu2");
		return vista;
	}

	@RequestMapping(value = "verificarMenu", method = RequestMethod.GET)
	public ModelAndView verificaMenu(ModelAndView vista,
			@RequestParam(value = "dateSelected", required = true) String date, HttpSession sesion,
			HttpServletRequest request) throws ParseException {

		// Validar fechas
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = parser.parse(date);
		Date fechaSistema = new Date();

		sesion.removeAttribute("listaMenu");

		if (date1.getDate() < fechaSistema.getDate() || date1.getMonth() < fechaSistema.getMonth()
				|| date1.getYear() < fechaSistema.getYear()) {
			vista.addObject("fechaAnterior", "fecha ingresada anterior a la fecha actual");
			vista.setViewName("buscarMenu");
			return vista;
			// hasta aqui se valida fecha

		} else {
			java.sql.Date fecha = java.sql.Date.valueOf(date);

			MenuDAO menuDAO = new MenuDAO();
			MenuTO menuTO = new MenuTO();

			menuTO.setFecha(fecha);
			if (menuDAO.buscaMenu(menuTO) == 1) {
				vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
				sesion = request.getSession(true);
				sesion.setAttribute("listaMenu", menuDAO.obtieneMenu(menuTO));
				vista.setViewName("verMenu");
				return vista;
			} else {
				vista.addObject("NoHayMenu", "No Hay Menu");
				vista.setViewName("buscarMenu");
				return vista;
			}

		}

	}

	@RequestMapping(value = "actualizarMenu", method = RequestMethod.GET)
	public ModelAndView actualizarMenu(ModelAndView vista, @RequestParam(value = "id", required = true) int id) {
		MenuDAO menuDAO = new MenuDAO();
		MenuTO menuTO = new MenuTO();
		menuTO.setId(id);

		vista.addObject("editMenu", menuDAO.buscarMenu(menuTO));
		vista.setViewName("actualizarMenu");
		return vista;
	}

	@RequestMapping(value = "editarMenu", method = RequestMethod.POST)
	public ModelAndView buscaMenu(ModelAndView vista,
			@RequestParam(value = "dateSelected", required = true) String date) throws ParseException {

		// Validar fechas
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = parser.parse(date);
		Date fechaSistema = new Date();

		if (date1.getDate() < fechaSistema.getDate() || date1.getMonth() < fechaSistema.getMonth()
				|| date1.getYear() < fechaSistema.getYear()) {
			vista.addObject("fechaAnterior", "fecha ingresada anterior a la fecha actual");
			vista.setViewName("buscarMenu2");
			return vista;
			// hasta aqui se valida fecha

		} else {
			java.sql.Date fecha = java.sql.Date.valueOf(date);

			MenuDAO menuDAO = new MenuDAO();
			MenuTO menuTO = new MenuTO();

			menuTO.setFecha(fecha);

			if (menuDAO.buscaMenu(menuTO) == 1) {

				vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
				vista.setViewName("editarMenu");
				return vista;

			} else {
				vista.addObject("NoHayMenu", "No Hay Menu");
				vista.setViewName("buscarMenu2");
				return vista;

			}
		}

	}

	@RequestMapping(value = "verHorarioDisponible")
	public ModelAndView verHorarioDisponible(ModelAndView vista, @RequestParam(value = "id") int id,
			@RequestParam(value = "menu") String menu, @RequestParam(value = "cantRaciones") int cantRaciones,
			HttpSession sesion) {

		if (cantRaciones == 0) {
			vista.setViewName("recargarVerMenu");
			vista.addObject("listaMenu", sesion.getAttribute("listaMenu"));
			vista.addObject("NoHayAlmuerzos", "No quedan almuerzos");
		} else {
			HorarioDAO horarioDAO = new HorarioDAO();

			if (horarioDAO.verificarhorario()) {
				if (horarioDAO.obtenerHorarioDisponible() != null) {
					vista.addObject("horarioDisponible", horarioDAO.obtenerHorarioDisponible());
					sesion.setAttribute("horarioDisponible", horarioDAO.obtenerHorarioDisponible());
					sesion.setAttribute("menu", menu);
					vista.addObject("id_menu", id);
					vista.addObject("menu", menu);
				}
			} else {
				vista.addObject("nohorario", "No hay horario disponible");
			}
			vista.setViewName("visualizarHorarioDisponible");
		}

		return vista;

	}

	@RequestMapping(value = "formActualizarMenu", method = RequestMethod.GET)
	public ModelAndView menuActualizado(ModelAndView vista,
			@RequestParam(value = "nombreMenu", required = false, defaultValue = "World") String nombreMenu,
			@RequestParam(value = "tipoMenu", required = false, defaultValue = "World") String tipoMenu,
			@RequestParam(value = "precioMenu", required = false, defaultValue = "World") int precioMenu,
			@RequestParam(value = "dateSelected", required = false, defaultValue = "World") String dateMenu,
			@RequestParam(value = "cantidadRaciones") int cantRaciones,
			@RequestParam(value = "id", required = false, defaultValue = "World") int id) throws ParseException {

		// Validar fechas
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = parser.parse(dateMenu);
		Date fechaSistema = new Date();

		if (date1.getDate() < fechaSistema.getDate() || date1.getMonth() < fechaSistema.getMonth()
				|| date1.getYear() < fechaSistema.getYear()) {
			MenuTO menuTO2 = new MenuTO();
			MenuDAO menuDAO = new MenuDAO();
			menuTO2.setId(id);

			vista.addObject("editMenu", menuDAO.buscarMenu(menuTO2));
			vista.addObject("fechaAnterior", "fecha ingresada anterior a la fecha actual");
			vista.setViewName("actualizarMenu");
			return vista;
			// hasta aqui se valida fecha

		} else {
			MenuDAO menuDAO = new MenuDAO();
			MenuTO menuTO = new MenuTO();

			java.sql.Date fecha = java.sql.Date.valueOf(dateMenu);

			menuTO.setId(id);
			menuTO.setFecha(fecha);
			menuTO.setNombre(nombreMenu);
			menuTO.setPrecio(precioMenu);
			menuTO.setTipo(tipoMenu);
			menuTO.setCantRaciones(cantRaciones);

			if (menuDAO.updateMenu(menuTO)) {
				vista.addObject("actualizado", "actualizado");
				vista.setViewName("indexAdministrador");
			} else {
				vista.addObject("noUpdated", "Erro al actualizar");
				MenuTO menuTO2 = new MenuTO();
				menuTO2.setId(id);
				vista.addObject("editMenu", menuDAO.buscarMenu(menuTO2));
				vista.setViewName("actualizarMenu");
			}
		}

		return vista;

	}

}
