package com.proyecto.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.HorarioDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class ReservaController {

	@RequestMapping(value = "reservar")
	public ModelAndView reserva(HttpServletRequest request, ModelAndView vista,
			@RequestParam(value = "id_menu") int id_menu, @RequestParam(value = "id_horario") int id_horario) {
		HttpSession session=request.getSession(true);
		int id=(int) session.getAttribute("id");
		
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		ReservaDAO reservaDAO=new ReservaDAO();
		HorarioDAO horarioDAO=new HorarioDAO();
		if(reservaDAO.reservar(fecha, id_menu, id_horario, id)) {
			vista.addObject("reservado", "reservado");
			horarioDAO.actualizarHorario(id_horario);
		}else {
			vista.addObject("noreservado", "Error al reservar");
		}
		
		vista.setViewName("indexUsuario");
		return vista;
	}
}
