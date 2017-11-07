package com.proyecto.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.HorarioDAO;
import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class ReservaController {

	@RequestMapping(value = "reservar")
	public ModelAndView reserva(HttpServletRequest request, ModelAndView vista,
			@RequestParam(value = "id_menu") int id_menu, @RequestParam(value = "id_horario") int id_horario,
			@RequestParam(value = "cantRaciones") int cantRaciones) {
		HttpSession session = request.getSession(true);
		int id = (int) session.getAttribute("id");

		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		ReservaDAO reservaDAO = new ReservaDAO();
		HorarioDAO horarioDAO = new HorarioDAO();
		MenuDAO menuDAO = new MenuDAO();

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.getSaldo(id) < menuDAO.getPrecio(id_menu)) {
			vista.addObject("sinSaldo", "No tiene salod");
			vista.addObject("horarioDisponible", session.getAttribute("horarioDisponible"));
			vista.addObject("menu", session.getAttribute("menu"));
			vista.addObject("id_menu", id_menu);
			vista.setViewName("visualizarHorarioDisponible");
		} else {
			if (cantRaciones == 0) {

				vista.addObject("horarioDisponible", session.getAttribute("horarioDisponible"));
				vista.addObject("menu", session.getAttribute("menu"));
				vista.addObject("id_menu", id_menu);
				vista.addObject("NoHayAlmuerzosEnHorario", "No quedan almuerzos disponibles en el horario indicado");
				vista.setViewName("visualizarHorarioDisponible");
			} else {
				if (horarioDAO.hayRaciones(id_horario) == 1) {
					if (menuDAO.hayRaciones(id_menu) == 1) {
						if (reservaDAO.reservar(fecha, id_menu, id_horario, id)) {
							vista.addObject("reservado", "reservado");
							horarioDAO.actualizaRacionesHorario(id_horario);
							menuDAO.actualizaRacionesMenu(id_menu);
							usuarioDAO.updateSaldo(id, menuDAO.getPrecio(id_menu));
						} else {
							vista.addObject("noreservado", "Error al reservar");
						}
						vista.setViewName("indexUsuario");
					} else {
						vista.addObject("NoHayAlmuerzos", "No quedan almuerzos");
						vista.setViewName("verMenu");
					}
				} else {
					vista.addObject("NoHayAlmuerzosEnHorario",
							"No quedan almuerzos disponibles en el horario indicado");
					vista.setViewName("visualizarHorarioDisponible");
				}

			}

		}

		return vista;
	}

	@RequestMapping(value = "verReservas")
	public ModelAndView verReservas(ModelAndView vista, HttpSession sesion, HttpServletRequest request) {
		ReservaDAO reservaDAO = new ReservaDAO();
		sesion = request.getSession(true);
		int id = (int) sesion.getAttribute("id");
		if (reservaDAO.obtenerDatosReservaHorario(id).isEmpty()) {
			vista.setViewName("indexUsuario");
			vista.addObject("NoTieneReservas", "No tiene reservas");
		} else {
			vista.setViewName("verReservas");
			vista.addObject("datosMenuReservado", reservaDAO.obtenerDatosReservaMenu(id));
			vista.addObject("datosHorarioReservado", reservaDAO.obtenerDatosReservaHorario(id));
			vista.addObject("datosReserva", reservaDAO.obtenerDatosReserva(id));
		}

		return vista;
	}

	@RequestMapping(value = "seleccionarCambiarMenu")
	public ModelAndView cambiarMenu(ModelAndView vista, @RequestParam(value = "idMenuAnterior") int idMenuAnterior,
			@RequestParam(value = "idReserva") int idReserva, @RequestParam(value = "idMenuNuevo") int idMenuNuevo,
			@RequestParam(value="fecha") String fecha,
			HttpSession sesion, HttpServletRequest request) {
		sesion = request.getSession(true);
		int idUsuario = (int) sesion.getAttribute("id");
		ReservaDAO reservaDAO = new ReservaDAO();
		MenuDAO menuDAO = new MenuDAO();
		if (menuDAO.hayRaciones(idMenuNuevo) == 1) {
			// hay raciones disponibles para el menu elegido
			menuDAO.actualizaRacionesMenu(idMenuNuevo);
			menuDAO.actualizarRacionesMenuAnterior(idMenuAnterior);
			// actualizar reserva
			reservaDAO.updateReserva(idMenuNuevo, idUsuario, idReserva);
			vista.addObject("idMenu", idMenuNuevo);
			vista.addObject("MenuCambiado", "Menu Cambiado Exitosamente");
		} else {// No hay
			vista.addObject("idMenu", idMenuAnterior);
			vista.addObject("NoHayAlmuerzos", "No quedan Almuerzos");
		}
		vista.addObject("idReserva", idReserva);
		MenuTO menuTO=new MenuTO();
		
		//fecha Menu
		java.sql.Date date = java.sql.Date.valueOf(fecha);
		menuTO.setFecha(date);
		vista.addObject("fecha", date);
		vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
		vista.setViewName("verMenu2");
		return vista;
	}

}
