package com.proyecto.controller;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.UsuarioDAO;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class LoginController {

	@RequestMapping(value = "inicioCasino")
	public ModelAndView inicioCasino() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("inicioCasino");

		// obtener Menu del dia
		MenuDAO menuDAO = new MenuDAO();

		// hay menu normal y extra
		if (!menuDAO.menuDelDiaNormal().isEmpty() && !menuDAO.menuDelDiaExtra().isEmpty()) {
			vista.addObject("MenuNormal", menuDAO.menuDelDiaNormal());
			vista.addObject("MenuExtra", menuDAO.menuDelDiaExtra());
		} else {
			
			System.out.println("Paso por aca");
			// solo hay menu normal
			if (!menuDAO.menuDelDiaNormal().isEmpty() && menuDAO.menuDelDiaExtra().isEmpty()) {
				vista.addObject("MenuNormal", menuDAO.menuDelDiaNormal());
			}

			// solo hay extra
			if (!menuDAO.menuDelDiaExtra().isEmpty() && menuDAO.menuDelDiaNormal().isEmpty()) {
				vista.addObject("MenuExtra", menuDAO.menuDelDiaExtra());
			}

			// no hay menu para el dia
			if (menuDAO.menuDelDiaExtra().isEmpty() && menuDAO.menuDelDiaNormal().isEmpty()) {
				vista.addObject("NoHayMenu", "No hay menu para hoy");
				System.out.println("No hay menu");
			}

		}

		return vista;
	}

	@RequestMapping(value = "login")
	public ModelAndView goToLogin() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("login");

		return vista;
	}

	@RequestMapping(value = "verificarLogin", method = RequestMethod.POST)
	public ModelAndView verificarLogin(@RequestParam(value = "rut", required = true) String rut,
			@RequestParam(value = "password", required = true) String pass, ModelAndView vista,
			HttpServletRequest request, HttpServletResponse response) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();

		usuarioTO.setRut(rut);
		usuarioTO.setClave(pass);

		if (usuarioDAO.verificar(usuarioTO) != null) {
			String nombre = usuarioDAO.verificar(usuarioTO).getNombre();
			String rol = usuarioDAO.verificar(usuarioTO).getRol();

			// Vista usuario
			if (rol.equalsIgnoreCase("usuario")) {

				vista.setViewName("indexUsuario");
			} else {

				// Vista adm
				if (rol.equalsIgnoreCase("Administrador")) {
					vista.setViewName("indexAdministrador");
				}
			}

			HttpSession session = request.getSession(true);
			session.setAttribute("nombre", nombre);
			session.setAttribute("id", usuarioDAO.getUser(rut, pass));

			return vista;
		} else {
			vista.addObject("errorUsuario", "Error");
			vista.setViewName("login");
			return vista;
		}
	}

	// Index Adm
	@RequestMapping(value = "indexAdministrador", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("indexAdministrador");
		return vista;
	}

	// Index User
	@RequestMapping(value = "indexUsuario", method = RequestMethod.GET)
	public ModelAndView indexUsuario(HttpSession sesion, HttpServletRequest request) {
		sesion = request.getSession(true);
		sesion.removeAttribute("listaMenu");
		sesion.removeAttribute("menu");
		sesion.removeAttribute("horarioDisponible");
		ModelAndView vista = new ModelAndView();
		vista.setViewName("indexUsuario");
		return vista;
	}

	@RequestMapping(value = "salir")
	public ModelAndView cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("login");
		HttpSession sesion = request.getSession(true);
		sesion.removeAttribute("id");
		sesion.removeAttribute("nombre");
		sesion.invalidate();
		return vista;

	}

}
