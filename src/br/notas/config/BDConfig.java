package br.notas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.postgresql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost/notes_db", "postgres", "123");

	}
}
