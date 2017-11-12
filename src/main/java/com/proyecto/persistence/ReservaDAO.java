/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.persistence;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import org.springframework.util.SystemPropertyUtils;

import com.mysql.jdbc.Connection;
import com.proyecto.transferObject.HorarioTO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.ReservaTO;

/**
 *
 * @author Nicolas
 */
public class ReservaDAO {

	private static final String RESERVAR = "insert into reserva (fecha,idusuario,idmenu,idhorario) values(?,?,?,?)";
	private static final String BUSCA_MENUS = "select *,count(*) as cont from reserva r WHERE r.idusuario=? and NOT EXISTS (select * from evaluacion e where r.idmenu = e.idmenu and e.idusuario=r.idusuario) group by r.idmenu";
	private static final String DATOS_RESERVA_MENU = "SELECT m.id,m.nombre, m.precio,m.tipo, m.fecha from reserva r JOIN menu m on r.idmenu=m.id WHERE r.idusuario=? and m.fecha>=?";
	private static final String DATOS_RESERVA_HORARIO = "SELECT h.id,h.horaInicio,h.horaFin from reserva r JOIN horario h on r.idhorario=h.id JOIN menu m on r.idmenu=m.id where r.idusuario=? and m.fecha>=?";
	private static final String UPDATE_RESERVA="update reserva set idmenu=? where idusuario=? and id=?";
	private static final String DATOS_RESERVA="select * from reserva where idusuario=? and fecha>=?";
	private static final String UPDATE_ID_HORARIO="update reserva set idhorario=? where id=?";
	
	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public void updateIdHorario(int idReserva, int idHorario) {
		Connection conn=null;
		try {
			conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_ID_HORARIO);
			ps.setInt(1, idHorario);
			ps.setInt(2, idReserva);
			ps.executeUpdate();
		}catch(SQLException e) {
			
		}
	}
	
	public LinkedList<ReservaTO> obtenerDatosReserva(int idUsuario) {
		Connection conn = null;
		LinkedList<ReservaTO> lista = new LinkedList<>();
		ReservaTO result = null;
		// fecha actual
		Date fechaSistema = new Date(System.currentTimeMillis());
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = parser.format(fechaSistema);

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(DATOS_RESERVA);
			ps.setInt(1, idUsuario);
			ps.setString(2, date1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result=new ReservaTO();
				result.setId(rs.getInt("id"));
				lista.add(result);
			}
		} catch (SQLException e) {
				System.out.println("Error");
		}
		return lista;
	}
	
	public void updateReserva(int idMenuNuevo, int idUsuario, int idReserva) {
		Connection conn=null;
		try {
			conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_RESERVA);
			ps.setInt(1, idMenuNuevo);
			ps.setInt(2, idUsuario);
			ps.setInt(3, idReserva);
			ps.executeUpdate();
		}catch(SQLException e) {
			
		}
	}

	public LinkedList<HorarioTO> obtenerDatosReservaHorario(int idUsuario) {
		Connection conn = null;
		LinkedList<HorarioTO> lista = new LinkedList<>();
		HorarioTO result = null;
		// fecha actual
		Date fechaSistema = new Date(System.currentTimeMillis());
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = parser.format(fechaSistema);

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(DATOS_RESERVA_HORARIO);
			ps.setInt(1, idUsuario);
			ps.setString(2, date1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new HorarioTO();
				result.setId(rs.getInt("id"));
				result.setHoraInicio(rs.getTime("horaInicio"));
				result.setHoraFin(rs.getTime("horaFin"));
				lista.add(result);
			}
		} catch (SQLException e) {

		}
		return lista;
	}

	public LinkedList<MenuTO> obtenerDatosReservaMenu(int idUsuario) {
		Connection conn = null;
		LinkedList<MenuTO> lista = new LinkedList<>();
		MenuTO result = null;
		// fecha actual
		Date fechaSistema = new Date(System.currentTimeMillis());
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = parser.format(fechaSistema);
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(DATOS_RESERVA_MENU);
			ps.setInt(1, idUsuario);
			ps.setString(2, date1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new MenuTO();
				result.setId(rs.getInt("id"));
				result.setNombre(rs.getString("nombre"));
				result.setPrecio(rs.getInt("precio"));
				result.setTipo(rs.getString("tipo"));
				result.setFecha(rs.getDate("fecha"));
				lista.add(result);

			}
		} catch (SQLException e) {

		}
		return lista;
	}

	public LinkedList<ReservaTO> existenMenus(int idUsuario) {
		LinkedList<ReservaTO> lista = new LinkedList<>();
		Connection conn = null;
		ReservaTO result = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(BUSCA_MENUS);
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new ReservaTO();
				result.setIdMenu(rs.getInt("idmenu"));
				lista.add(result);
			}
		} catch (SQLException e) {

		}
		return lista;
	}

	public boolean reservar(Timestamp fecha, int idMenu, int idHorario, int idUsuario) {
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(RESERVAR);

			ps.setTimestamp(1, fecha);
			ps.setInt(2, idUsuario);
			ps.setInt(3, idMenu);
			ps.setInt(4, idHorario);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {

		}

		return false;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException el) {
			el.printStackTrace();
			System.err.println("Quedo la parte hermano!!!");
		}
		return conn;
	}
}
