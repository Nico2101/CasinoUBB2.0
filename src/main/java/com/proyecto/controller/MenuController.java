package com.proyecto.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.persistence.MenuDAO;
import com.proyecto.transferObject.MenuTO;

@Controller
public class MenuController {
	   @RequestMapping(value ="/ingresarmenu",method =RequestMethod.GET)
	   public String login(@RequestParam(value="nombreMenu",required=false,defaultValue="World")String nombreMenu, @RequestParam(value="tipoMenu",required=false,defaultValue="World")String tipoMenu,@RequestParam(value="precioMenu",required=false,defaultValue="World")int precioMenu,@RequestParam(value="dateMenu",required=false,defaultValue="World")String dateMenu, Model model) throws SQLException {
		  // model.addAttribute("user",user);
		  // model.addAttribute("pass",pass);
        
		MenuDAO dao = new MenuDAO();
        MenuTO to = new MenuTO();
        java.sql.Date fecha = java.sql.Date.valueOf(dateMenu);
        to.setFecha(fecha);
        to.setNombre(nombreMenu);
        to.setPrecio(precioMenu);
        to.setTipo(tipoMenu);
        //to.setFecha(dateMenu);
        
        dao.ingresaMenu(to);
        
        //model.addAttribute("MenuTO",menuTO);

		return "index";
}   
	   
}
