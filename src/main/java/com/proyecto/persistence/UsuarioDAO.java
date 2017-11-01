/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.persistence;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.proyecto.transferObject.UsuarioTO;

/**
 *
 * @author Nicolas
 */
public class UsuarioDAO {

	private static final String VERIFICAR_USUARIO = "select * from usuario where rut=? and clave=?";
	private static final String AGREGAR_USUARIO = "insert into usuario(rut,nombre,apellidoPaterno,apellidoMaterno,clave,rol,saldo) values(?,?,?,?,?,?,?)";
	private static final String GET_USER = "select * from usuario where rut=? and clave=?";

	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public int getUser(String rut, String clave) {
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(GET_USER);
			ps.setString(1, rut);
			ps.setString(2, clave);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");

			}
		} catch (SQLException e) {

		}
		return 0;
	}

	public UsuarioTO verificar(UsuarioTO usuario) {
		Connection conn = null;
		UsuarioTO result = null;
		try {
			conn = getConnection();

			PreparedStatement ps = conn.prepareStatement(VERIFICAR_USUARIO);
			ps.setString(1, usuario.getRut());
			ps.setString(2, usuario.getClave());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new UsuarioTO();
				result.setNombre(rs.getString("nombre"));
				result.setRol(rs.getString("rol"));

			}
		} catch (SQLException e) {

		}
		return result;
	}

	public boolean agregarUsuario(UsuarioTO usuario) {
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.clientPrepareStatement(AGREGAR_USUARIO);
			ps.setString(1, usuario.getRut());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidoPaterno());
			ps.setString(4, usuario.getApellidoMaterno());
			ps.setString(5, usuario.getClave());
			ps.setString(6, "usuario");
			ps.setInt(7, 0);

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
