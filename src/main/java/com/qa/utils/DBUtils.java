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
		this.dbUrl = "jdbc:h2:~/testdb";
		this.dbUser = "sa";
		this.dbPassword = "";
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
	}
	
	public void executeQuery() {
		try (Connection conn = this.getConnection();
		     Statement stmnt = conn.createStatement()) {
			String sql =
					"DROP TABLE IF EXISTS transactions;" +
					"CREATE TABLE IF NOT EXISTS transactions (" +
					"`id` BIGINT AUTO_INCREMENT PRIMARY KEY," +
					"`transaction_date` DATE," +
					"`vendor` varchar(255) NOT NULL," +
					"`amount` DOUBLE NOT NULL," +
					"`category` varchar(255)" +
					"); " +
					"INSERT INTO transactions" +
					"(`transaction_date`, `vendor`, `amount`, `category`)" +
					"VALUES ('2022-03-15', 'WHSmiths', 10.50, 'Leisure');";
			stmnt.executeUpdate(sql);
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
