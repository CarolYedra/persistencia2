package es.avalon.miproyecto.persistencia2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persona {

	private  String nombre;
	private String apellido;
	private int edad;

	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Persona(String nombre, String apellido, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public static List<Persona> buscarTodos() {
		List<Persona> personas = new ArrayList<Persona>();
		ResultSet rs = null;
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			String sql = "select * from Persona";
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int edad = rs.getInt("edad");
				Persona p = new Persona(nombre, apellido, edad);
				personas.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;

	}

//INSERTAR
	public void insertar() {
		String sql = "insert into persona (nombre, apellido, edad) values (?,?,?)";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellido);
			sentencia.setInt(3, edad);
			sentencia.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//BORRAR
	public static void borrar(String nombre) throws ClassNotFoundException {
		String sql = "DELETE FROM Persona where nombre = ?";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.borrarPreparedStatement(conexion, sql);) {
			sentencia.setString(1, nombre);
			sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//ACTUALIZAR
	public void update() throws ClassNotFoundException {
		String sql = "update Persona set nombre ='" + this.getNombre() 
				+ "', apellido=" + this.getApellido()
				+ " where edad ='" + this.getEdad() + "'";
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			sentencia.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ACTUALIZADO!");
		}
	}

//BUSCAR UNO
	public static Persona buscarUno(String nombre) throws ClassNotFoundException {
		Persona p = null;
		String sql = "select * FROM Persona  WHERE nombre ='" + nombre + "'";
		try (Connection conexion = DBHelper.crearConexion();
				Statement sentencia = DBHelper.crearStatement(conexion);
				ResultSet rs = sentencia.executeQuery(sql)) {
			rs.next();
			p = new Persona(rs.getString("nombre"), rs.getString("edad"), rs.getInt("edad"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
