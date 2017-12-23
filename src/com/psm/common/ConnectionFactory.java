package com.psm.common;

import java.sql.*;

public class ConnectionFactory {

	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=商场";
	private static String userName = "sa";
	private static String password = "1234";
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
