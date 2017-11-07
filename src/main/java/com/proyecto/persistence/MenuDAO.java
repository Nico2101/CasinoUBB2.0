/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import com.proyecto.transferObject.MenuTO;

/**
 *
 * @author Nicolas
 */
public class MenuDAO {

	private static final String BUSCA_MENU = "select * from menu where fecha=?";
	private static final String BUSCA_MENUS = "select * from menu where id=?";
	private static final String OBTENER_MENU = "select * from menu where fecha=?";
	private static final String INSERT_QUERY = "insert into menu(nombre,precio,tipo,fecha, cantidadRaciones) values(?,?,?,?,?)";
	private static final String UPDATE_MENU = "update menu set nombre=?, tipo=?,fecha=?,precio=?, cantidadRaciones=? where id=?";
	private static final String LISTA_MENUS_COMPRADOS = "select * from menu where id=?";
	private static final String ACTUALIZAR_RACIONES_MENU = "update menu set cantidadRaciones=cantidadRaciones-1 where id=?";
	private static final String HAY_RACIONES = "select * from menu where id=? and cantidadRaciones>0";
	private static final String VALOR_MENU="select precio from menu where id=?";
	private static final String ACTUALIZAR_RACIONES_MENU_ANTIGUO="update menu set cantidadRaciones=cantidadRaciones+1 where id=?";

	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public void actualizarRacionesMenuAnterior(int idMenu) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_RACIONES_MENU_ANTIGUO);
			ps.setInt(1, idMenu);
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}
	
	public int getPrecio(int idMenu) {
		Connection conn=null;
		try {
			conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(VALOR_MENU);
			ps.setInt(1, idMenu);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("precio");
			}
		}catch(SQLException e) {
			
		}
		return 0;
	}

	public int hayRaciones(int idMenu) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(HAY_RACIONES);
			ps.setInt(1, idMenu);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return 1;
			}

		} catch (SQLException e) {

		}
		return 0;
	}

	public void actualizaRacionesMenu(int idMenu) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(ACTUALIZAR_RACIONES_MENU);
			ps.setInt(1, idMenu);
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public MenuTO listaMenuCompradoUsuario(int idMenu) {
		Connection conn = null;
		MenuTO result = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(LISTA_MENUS_COMPRADOS);
			ps.setInt(1, idMenu);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new MenuTO();
				result.setNombre(rs.getString("nombre"));
				result.setPrecio(rs.getInt("precio"));
				result.setTipo(rs.getString("tipo"));
				result.setFecha(rs.getDate("fecha"));
				result.setId(rs.getInt("id"));

			}
		} catch (SQLException e) {

		}
		return result;
	}

	public boolean updateMenu(MenuTO menu) {
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE_MENU);
			ps.setString(1, menu.getNombre());
			ps.setString(2, menu.getTipo());
			ps.setDate(3, menu.getFecha());
			ps.setInt(4, menu.getPrecio());
			ps.setInt(5, menu.getCantRaciones());
			ps.setInt(6, menu.getId());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

		}
		return false;
	}

	public int buscaMenu(MenuTO menu) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(BUSCA_MENU);
			ps.setDate(1, menu.getFecha());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return 1;
			}

		} catch (SQLException e) {

		}
		return 0;
	}

	public MenuTO buscarMenu(MenuTO menuTo) {
		Connection conn = null;
		MenuTO result = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(BUSCA_MENUS);
			ps.setInt(1, menuTo.getId());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = new MenuTO();
				result.setId(rs.getInt("id"));
				result.setNombre(rs.getString("nombre"));
				result.setPrecio(rs.getInt("precio"));
				result.setTipo(rs.getString("tipo"));
				result.setFecha(rs.getDate("fecha"));
				result.setCantRaciones(rs.getInt("cantidadRaciones"));

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.err.println("Quedo la pata hermano!!!");
		}
		return result;
	}

	public LinkedList<MenuTO> obtieneMenu(MenuTO menu) {
		Connection conn = null;
		LinkedList<MenuTO> lista = new LinkedList<>();
		MenuTO result = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(OBTENER_MENU);
			ps.setDate(1, menu.getFecha());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new MenuTO();
				result.setId(rs.getInt("id"));
				result.setNombre(rs.getString("nombre"));
				result.setPrecio(rs.getInt("precio"));
				result.setTipo(rs.getString("tipo"));
				result.setFecha(rs.getDate("fecha"));
				result.setCantRaciones(rs.getInt("cantidadRaciones"));
				lista.add(result);
			}

		} catch (SQLException e) {

		}
		return lista;
	}

	public int ingresaMenu(MenuTO to) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
			ps.setString(1, to.getNombre());
			ps.setInt(2, to.getPrecio());
			ps.setString(3, to.getTipo());
			ps.setDate(4, to.getFecha());
			ps.setInt(5, to.getCantRaciones());
			ps.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			System.out.println("Error aquiiii");
			System.out.println(e);
		} finally {
			if (conn != null)
				conn.close();
		}
		return result;
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
