package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.ReservaDAO;
import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class UsuarioController {

	@RequestMapping(value = "agregarSaldo", method = RequestMethod.GET)
	public @ResponseBody UsuarioTO verificarUsuario(@RequestParam String rutUsuario, @RequestParam int saldo) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		if (usuarioDAO.verificarUsuarioParaAgregarSaldo(rutUsuario)) {
			usuarioTO.setRut(rutUsuario);
			usuarioDAO.agregarSaldo(saldo, rutUsuario);
			return usuarioTO;
		} else {
			return usuarioTO;
		}

	}

	@RequestMapping(value = "listarUsuarios")
	public ModelAndView listarUsuarios(ModelAndView vista) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.obtenerListaUsuarios() != null) {
			vista.addObject("totalUsuarios", usuarioDAO.obtenerListaUsuarios());
			vista.setViewName("listaUsuarios");
		} else {
			vista.addObject("noHayUusuarios", " error no hay usuarios registrados");
			vista.setViewName("listaUsuarios");
		}
		return vista;
	}

	@RequestMapping(value = "listaPersonasMes")
	public ModelAndView listarPersonas() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("SeleccionListaUsuarios");

		return vista;
	}

	@RequestMapping(value = "listUsers")
	public ModelAndView lista(ModelAndView vista, @RequestParam(value = "mes") int mes,
			@RequestParam(value = "year") int year) {

		if (mes == -1 || year == -1) {
			vista.setViewName("SeleccionListaUsuarios");
			vista.addObject("seleccion", "Debe seleccionar los datos requeridos");
			return vista;
		}

		String fechaInicio = year + "-" + mes + "-01";
		String fechaFin = year + "-" + mes + "-" + dias(mes);

		ReservaDAO reservaDAO = new ReservaDAO();

		vista.setViewName("reservasPorMes");
		if (!reservaDAO.obtenerReservas(fechaInicio, fechaFin).isEmpty()) {
			System.out.println("Pase!");
			vista.addObject("totalReservas", reservaDAO.obtenerReservas(fechaInicio, fechaFin));

		}

		return vista;
	}

	// Función que retorna el numero de días que tiene el mes
	private int dias(int mes) {
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
			return 31;
		else if (mes == 2)
			return 28;
		else
			return 30;
	}

	// funcion que retorna el nombre del mes
	private String mes(int mes) {
		switch (mes) {
		case 1:
			return "Enero";

		case 2:
			return "Febrero";

		case 3:
			return "Marzo";

		case 4:
			return "Abril";

		case 5:
			return "Mayo";

		case 6:
			return "Junio";

		case 7:
			return "Julio";

		case 8:
			return "Agosto";

		case 9:
			return "Septiembre";

		case 10:
			return "Octubre";

		case 11:
			return "Noviembre";

		case 12:
			return "Diciembre";

		}
		return null;

	}

}
