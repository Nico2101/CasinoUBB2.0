/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.proyecto.transferObject.HorarioTO;
import com.proyecto.transferObject.ReservaTO;

/**
 *
 * @author Nicolas
 */
public class HorarioDAO {

	private static final String OBTENER_HORARIO_DISPONIBLE = "select * from horario";
	private static final String ACTUALIZAR_RACIONES_HORARIO = "update horario set cantMaxRaciones=cantMaxRaciones-1 where id=?";
	private static final String HAY_RACIONES = "select * from horario where id=? and cantMaxRaciones>0";
	private static final String ACTUALIZAR_RACIONES_HORARIO_ANTIGUO = "update horario set cantMaxRaciones=cantMaxRaciones+1 where id=?";
	private static final String HORARIO_RESERVADO = "SELECT h.horaInicio,h.horaFin, COUNT(*) as cant from reserva r JOIN horario h ON r.idhorario=h.id JOIN menu m on r.idmenu=m.id where m.fecha>=CURRENT_DATE and r.idusuario=? GROUP BY r.idhorario";

	private static final String VERIFICAR = "select * from horario where cantMaxRaciones>0";
	private static final String LIBERAR_CUPOS = "update horario set cantMaxRaciones=30";

	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public LinkedList<ReservaTO> getHorario(int idUsuario){
		return null;
	}

	public void actualizaRacionesHorarioAntiguo(int idHorario) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_RACIONES_HORARIO_ANTIGUO);
			ps.setInt(1, idHorario);
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public int hayRaciones(int idHorario) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(HAY_RACIONES);
			ps.setInt(1, idHorario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return 1;
			}

		} catch (SQLException e) {

		}
		return 0;
	}

	public boolean actualizarRaciones() {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(LIBERAR_CUPOS);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {

		}
		return false;
	}

	public void actualizaRacionesHorario(int idHorario) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_RACIONES_HORARIO);
			ps.setInt(1, idHorario);
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public boolean verificarhorario() {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(VERIFICAR);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}

	public LinkedList<HorarioTO> obtenerHorarioDisponible() {
		LinkedList<HorarioTO> list = new LinkedList<>();
		Connection conn = null;
		HorarioTO result = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(OBTENER_HORARIO_DISPONIBLE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new HorarioTO();
				result.setId(rs.getInt("id"));
				result.setHoraInicio(rs.getTime("horaInicio"));
				result.setHoraFin(rs.getTime("horaFin"));
				result.setCantMaxRaciones(rs.getInt("cantMaxRaciones"));
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Quedo la pata hermano!!!");
		}
		return list;
	}

	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			System.err.println("Quedo la pata hermano!!!");
		}
		return conn;
	}
}
