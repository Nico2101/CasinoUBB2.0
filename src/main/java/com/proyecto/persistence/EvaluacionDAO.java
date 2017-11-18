package com.proyecto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.proyecto.transferObject.EvaluacionTO;
import com.proyecto.transferObject.HorarioTO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.ReservaTO;

public class EvaluacionDAO {

	private static final String AGREGAR_EVALUACION = "insert into evaluacion(valoracion, comentario, idusuario, idmenu) values(?,?,?,?) ";
	private static final String OBTENER_EVALUACIONES = "SELECT idmenu, AVG(valoracion) as promedio from evaluacion GROUP BY idmenu";
    private static final String VER_EVALUACIONES ="SELECT evaluacion.id,valoracion,comentario,nombre FROM `evaluacion` JOIN `menu` ON(evaluacion.idmenu=MENU.id) WHERE idusuario=?";
	private static final String BUSCA_EV="select id,valoracion,comentario from evaluacion where id=?";
    private static final String EDIT_EV="update evaluacion set valoracion=?, comentario=? where id=?";
	private static final String ELIMINA_EVA="DELETE from evaluacion where id=?";
    
	private static final String DB_NAME = "mydb";
	private static final String PORT = "3306";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public LinkedList<EvaluacionTO> promedioValoracion() {
		LinkedList<EvaluacionTO> lista = new LinkedList<>();
		EvaluacionTO result = null;
		Connection conn = null;
		try {
			conn=getConnection();
			PreparedStatement ps= conn.prepareStatement(OBTENER_EVALUACIONES);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				result=new EvaluacionTO();
				result.setIdMenu(rs.getInt("idmenu"));
				result.setValoracion(rs.getFloat("promedio"));
				lista.add(result);
			}
		}catch(SQLException e) {
			
		}

		return lista;
	}

	public boolean agregarEvaluacion(EvaluacionTO evaluacionTO) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(AGREGAR_EVALUACION);
			ps.setFloat(1, evaluacionTO.getValoracion());
			ps.setString(2, evaluacionTO.getComentario());
			ps.setInt(3, evaluacionTO.getIdUsuario());
			ps.setInt(4, evaluacionTO.getIdMenu());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

		}
		return false;
	}
	
	public LinkedList<EvaluacionTO> obtenerEvaluaciones(int idUsuario) throws SQLException {
		Connection conn = null;
		LinkedList<EvaluacionTO> lista = new LinkedList<>();
		EvaluacionTO result = null;
		conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(VER_EVALUACIONES);
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result=new EvaluacionTO();
			result.setValoracion(rs.getFloat("valoracion"));
			result.setComentario(rs.getString("comentario"));
			result.setMenu(rs.getString("nombre"));
			result.setId(rs.getInt("id"));
			lista.add(result);
		}
		return lista;
	}
	public EvaluacionTO obtieneE(EvaluacionTO evaluacion) {
		Connection conn = null;
		EvaluacionTO result = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(BUSCA_EV);
			ps.setInt(1, evaluacion.getId());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = new EvaluacionTO();
				result.setId(rs.getInt("id"));
				result.setValoracion(rs.getInt("valoracion"));
				result.setComentario(rs.getString("comentario"));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.err.println("Quedo la pata hermano!!!");
		}
		return result;
	}
	
	public void actualizaEva(EvaluacionTO to) {
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(EDIT_EV);
			ps.setFloat(1, to.getValoracion());
			ps.setString(2, to.getComentario());
			ps.setInt(3, to.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {

		}
	}
	
	public void eliminaEvaluacion(EvaluacionTO evaluacion) {
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(ELIMINA_EVA);
			ps.setInt(1, evaluacion.getId());
			ps.executeUpdate();
		} catch (SQLException e) {

		}
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
