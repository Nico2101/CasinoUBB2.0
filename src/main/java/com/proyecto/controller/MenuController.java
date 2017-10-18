package com.proyecto.controller;

import java.sql.Date;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.persistence.MenuDAO;
import com.proyecto.transferObject.MenuTO;

@Controller
public class MenuController {
	   @RequestMapping(value ="/agregar",method =RequestMethod.GET)
	   public String login(@RequestParam(value="nombreMenu",required=false,defaultValue="World")String nombreMenu, @RequestParam(value="tipoMenu",required=false,defaultValue="World")String tipoMenu,@RequestParam(value="precioMenu",required=false,defaultValue="World")String precioMenu,@RequestParam(value="dateMenu",required=false,defaultValue="World")Date dateMenu, Model model) throws SQLException {
		  // model.addAttribute("user",user);
		  // model.addAttribute("pass",pass);
        MenuDAO dao = new MenuDAO();
        MenuTO menuTO = dao.ingresaMenu(nombreMenu,tipoMenu,precioMenu,dateMenu);
        model.addAttribute("MenuTO",menuTO);

		return "index.jsp";
}   
	   
}
