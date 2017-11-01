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

		if (cantRaciones == 0) {

			vista.addObject("horarioDisponible", session.getAttribute("horarioDisponible"));
			vista.addObject("menu", session.getAttribute("menu"));
			vista.addObject("id_menu",id_menu);
			vista.addObject("NoHayAlmuerzosEnHorario", "No quedan almuerzos disponibles en el horario indicado");
			vista.setViewName("recargarVisualizarHorarioDisponible");
		} else {
			if (horarioDAO.hayRaciones(id_horario) == 1) {
				if (menuDAO.hayRaciones(id_menu) == 1) {
					if (reservaDAO.reservar(fecha, id_menu, id_horario, id)) {
						vista.addObject("reservado", "reservado");
						horarioDAO.actualizaRacionesHorario(id_horario);
						menuDAO.actualizaRacionesMenu(id_menu);
					} else {
						vista.addObject("noreservado", "Error al reservar");
					}
					vista.setViewName("indexUsuario");
				} else {
					vista.addObject("NoHayAlmuerzos", "No quedan almuerzos");
					vista.setViewName("verMenu");
				}
			} else {
				vista.addObject("NoHayAlmuerzosEnHorario", "No quedan almuerzos disponibles en el horario indicado");
				vista.setViewName("visualizarHorarioDisponible");
			}

		}

		return vista;
	}
}
