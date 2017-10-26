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
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.proyecto.transferObject.ReservaTO;

/**
 *
 * @author Nicolas
 */
public class ReservaDAO {

	private static final String RESERVAR = "insert into reserva (fecha,idusuario,idmenu,idhorario) values(?,?,?,?)";
	private static final String BUSCA_MENUS="select * from reserva where idusuario=?";
	
	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public LinkedList<ReservaTO> existenMenus(int idUsuario) {
		LinkedList<ReservaTO> lista=new LinkedList<>();
		Connection conn=null;
		ReservaTO result=null;
		try {
			conn=getConnection();
			PreparedStatement ps= conn.prepareStatement(BUSCA_MENUS);
			ps.setInt(1, idUsuario);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				result=new ReservaTO();
				result.setIdMenu(rs.getInt("idmenu"));
				lista.add(result);
			}
		}catch(SQLException e) {
			
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
