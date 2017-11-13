package com.proyecto.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.HorarioDAO;
import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.HorarioTO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.ReservaTO;
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
			vista.addObject("sinSaldo", "No tiene saldo");
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
			@RequestParam(value = "fecha") String fecha, @RequestParam(value = "precioNuevoMenu") int precioNuevoMenu,
			HttpSession sesion, HttpServletRequest request) {
		sesion = request.getSession(true);
		int idUsuario = (int) sesion.getAttribute("id");
		ReservaDAO reservaDAO = new ReservaDAO();
		MenuDAO menuDAO = new MenuDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// obtener precio menu reservado
		MenuTO menuTO = new MenuTO();
		menuTO.setId(idMenuAnterior);
		int precioMenuAnterior = menuDAO.buscarMenu(menuTO).getPrecio();

		// verificar que el usuario tiene saldo en caso de que cambie el almuerzo por
		// uno de mayor valor
		if (usuarioDAO.getSaldo(idUsuario) + precioMenuAnterior >= precioNuevoMenu) {

			if (menuDAO.hayRaciones(idMenuNuevo) == 1) {
				// hay raciones disponibles para el menu elegido
				menuDAO.actualizaRacionesMenu(idMenuNuevo);
				menuDAO.actualizarRacionesMenuAnterior(idMenuAnterior);
				// actualizar reserva
				reservaDAO.updateReserva(idMenuNuevo, idUsuario, idReserva);
				vista.addObject("idMenu", idMenuNuevo);
				vista.addObject("MenuCambiado", "Menu Cambiado Exitosamente");

				// Es necesario actualizar saldo
				// Le sumamos el precio Menu anterior y le restamos el nuevo precio del menu
				// elegido
				// puesto que no sabemos si eligirá un menu con un precio mayor o menor
				usuarioDAO.updateSaldoCambiarMenu(idUsuario, precioMenuAnterior);
				// actualizar nuevo saldo
				usuarioDAO.updateSaldo(idUsuario, precioNuevoMenu);

			} else {// No hay
				vista.addObject("idMenu", idMenuAnterior);
				vista.addObject("NoHayAlmuerzos", "No quedan Almuerzos");
			}
			vista.addObject("idReserva", idReserva);
			menuTO = null;
			menuTO = new MenuTO();

			// fecha Menu
			java.sql.Date date = java.sql.Date.valueOf(fecha);
			menuTO.setFecha(date);
			vista.addObject("fecha", date);
			vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
		} else {
			// No tiene saldo
			vista.addObject("idReserva", idReserva);
			menuTO = null;
			menuTO = new MenuTO();

			// fecha Menu
			java.sql.Date date = java.sql.Date.valueOf(fecha);
			menuTO.setFecha(date);
			vista.addObject("fecha", date);
			vista.addObject("idMenu", idMenuAnterior);
			vista.addObject("listaMenu", menuDAO.obtieneMenu(menuTO));
			vista.addObject("sinSaldo", "Usuario no tiene saldo suficiente");

		}

		vista.setViewName("verMenu2");
		return vista;
	}

	// Cambiar horario reserva
	@RequestMapping(value = "cambiarHorario")
	public ModelAndView cambiarHorarioReservado(@RequestParam(value = "idMenu") int idMenu,
			@RequestParam(value = "idHorario") int idHorario, @RequestParam(value = "idReserva") int idReserva,
			ModelAndView vista) {
		HorarioDAO horarioDAO = new HorarioDAO();
		MenuDAO menuDAO = new MenuDAO();
		MenuTO menuTO = new MenuTO();
		menuTO.setId(idMenu);
		vista.addObject("idReserva", idReserva);
		vista.addObject("idHorario", idHorario);
		vista.addObject("menu", menuDAO.buscarMenu(menuTO));
		vista.addObject("horarioDisponible", horarioDAO.obtenerHorarioDisponible());
		vista.setViewName("visualizarHorarioDisponible2");
		return vista;
	}

	@RequestMapping(value = "actualizarHorarioMenu")
	public ModelAndView actualizarHorarioMenu(ModelAndView vista, @RequestParam(value = "idReserva") int idReserva,
			@RequestParam(value = "id_menu") int idMenu,
			@RequestParam(value = "idHorarioSeleccionado") int idHorarioSeleccionado,
			@RequestParam(value = "idHorarioNuevo") int idHorarioNuevo,
			@RequestParam(value = "cantRaciones") int cantRaciones) {

		MenuDAO menuDAO = new MenuDAO();
		MenuTO menuTO = new MenuTO();
		HorarioDAO horarioDAO = new HorarioDAO();
		ReservaDAO reservaDAO = new ReservaDAO();

		if (cantRaciones == 0) {// No quedan raciones para ese horario por lo tanto no se puede seleccionar
			menuTO.setId(idMenu);

			vista.setViewName("visualizarHorarioDisponible2");
			vista.addObject("NoHayAlmuerzosEnHorario", "No quedan almuerzos en el horario indicado");

			// Se recargan los datos
			vista.addObject("idReserva", idReserva);
			vista.addObject("idHorario", idHorarioSeleccionado);
			vista.addObject("menu", menuDAO.buscarMenu(menuTO));
			vista.addObject("horarioDisponible", horarioDAO.obtenerHorarioDisponible());
		} else {
			// Quedan almuerzos en el horario seleccionado

			// Se necesita el horario anterior y el nuevo horario
			// Actualizar horario: aumentar al horario anterior y disminuir raciones al
			// nuevo horario
			menuTO.setId(idMenu);
			horarioDAO.actualizaRacionesHorario(idHorarioNuevo);
			horarioDAO.actualizaRacionesHorarioAntiguo(idHorarioSeleccionado);
			vista.addObject("idReserva", idReserva);
			vista.addObject("idHorario", idHorarioNuevo);
			vista.addObject("menu", menuDAO.buscarMenu(menuTO));
			vista.addObject("horarioDisponible", horarioDAO.obtenerHorarioDisponible());
			vista.setViewName("visualizarHorarioDisponible2");
			vista.addObject("HorarioCambiado", "Horario cambiado correctamente");

			// actualizar el id del horario en la tabla reserva

			reservaDAO.updateIdHorario(idReserva, idHorarioNuevo);

		}

		return vista;

	}

	@RequestMapping(value = "eliminarReserva")
	public ModelAndView eliminarReserva(HttpServletRequest request, ModelAndView vista,
			@RequestParam(value = "idMenu") int idMenu, @RequestParam(value = "idReserva") int idReserva,
			@RequestParam(value = "idHorario") int idHorario, HttpSession sesion) {

		ReservaTO reservaTO = new ReservaTO();
		HorarioDAO horarioDAO = new HorarioDAO();
		MenuDAO menuDAO = new MenuDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		sesion = request.getSession(true);
		int id = (int) sesion.getAttribute("id");

		reservaTO.setId(idReserva);
		horarioDAO.actualizaRacionesHorarioAntiguo(idHorario);
		menuDAO.actualizarRacionesMenuAnterior(idMenu);
		reservaDAO.eliminaReserva(idMenu, idHorario, idReserva);

		// obtener el valor del precio del amuerzo
		int precio = menuDAO.getPrecio(idMenu);

		// sumar el precio del almuerzo al saldo del usuario
		usuarioDAO.updateSaldoEliminarReserva(id, precio);

		if (reservaDAO.obtenerDatosReservaHorario(id).isEmpty()) {
			vista.setViewName("indexUsuario");
			vista.addObject("NoTieneMasReservas", "No tiene reservas");
			vista.addObject("ReservaEliminada", "Reserva eliminada correctamente");
		} else {
			vista.setViewName("verReservas");
			vista.addObject("datosMenuReservado", reservaDAO.obtenerDatosReservaMenu(id));
			vista.addObject("datosHorarioReservado", reservaDAO.obtenerDatosReservaHorario(id));
			vista.addObject("datosReserva", reservaDAO.obtenerDatosReserva(id));
			vista.addObject("ReservaEliminada", "Reserva eliminada correctamente");
		}

		return vista;
	}

}
