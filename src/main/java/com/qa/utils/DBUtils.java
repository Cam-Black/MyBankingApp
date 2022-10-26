package com.qa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private final String dbUrl;
	
	private final String dbUser;
	
	private final String dbPassword;
	
	private static DBUtils instance;
	
	public DBUtils() {
		this.dbUrl = "jdbc:h2:~/test";
		this.dbUser = "sa";
		this.dbPassword = "";
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
	}
	
	public void executeQuery() {
		try (Connection conn = this.getConnection();
		     Statement stmnt = conn.createStatement()) {
			stmnt.executeUpdate("CREATE SCHEMA test;\n" +
					"USE test;\n" +
					"CREATE TABLE transactions (\n" +
					"    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
					"    `transaction_date` DATE,\n" +
					"    `vendor` varchar(255) NOT NULL,\n" +
					"    `amount` DECIMAL NOT NULL,\n" +
					"    `category` varchar(255)\n" +
					");");
			stmnt.executeUpdate("INSERT INTO `test`.`transactions` (`transaction_date`, `vendor`, `amount`, " +
					"`category`)\n" +
					"    VALUES (2022-03-15, 'WHSmiths', 10.50, 'Leisure');");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void connect() {
		instance = new DBUtils();
	}
	
	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}
}
