package com.proyecto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.proyecto.transferObject.EvaluacionTO;

public class EvaluacionDAO {

	private static final String AGREGAR_EVALUACION = "insert into evaluacion(valoracion, comentario, idusuario, idmenu) values(?,?,?,?) ";

	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public boolean agregarEvaluacion(EvaluacionTO evaluacionTO) {
		Connection conn = null;
		try {
			conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(AGREGAR_EVALUACION);
			ps.setFloat(1, evaluacionTO.getValoracion());
			ps.setString(2, evaluacionTO.getComentario());
			ps.setInt(3, evaluacionTO.getIdUsuario());
			ps.setInt(4, evaluacionTO.getIdMenu());
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			
		}
		return false;
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
