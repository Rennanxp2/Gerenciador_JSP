package br.MagicToys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	public static Connection getConnection() throws SQLException{
		String url ="jdbc:mysql://localhost:3306/dbMagicToys";
		String user="root";
		String pass="";
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		return DriverManager.getConnection(url, user, pass);
	}

}
