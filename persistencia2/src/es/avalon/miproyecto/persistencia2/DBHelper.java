package es.avalon.miproyecto.persistencia2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost/avalon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String usuario = "root";
	static final String clave = "";

	public static Connection crearConexion() throws ClassNotFoundException, SQLException {

		Connection conexion = null;
		Class.forName(DRIVER);
		conexion = DriverManager.getConnection(URL, usuario, clave);
		return conexion;
	}

	public static Statement crearStatement(Connection conexion) throws SQLException {

		return conexion.createStatement();

	}
	//INSERTAR
	public static PreparedStatement crearPreparedStatement(Connection conexion, String sql) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(sql);
		return ps;}
	
	//BORRAR
	public static PreparedStatement borrarPreparedStatement(Connection conexion, String sql) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(sql);
		return ps;}
	
}