package com.qa.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

public class DBUtils {
	private final String dbUrl;
	
	private final String dbUser;
	
	private final String dbPassword;
	
	private static DBUtils instance;
	
	public DBUtils(String properties) {
		Properties dbProps = new Properties();
		try (InputStream fis = ClassLoader.getSystemResourceAsStream(properties)) {
			dbProps.load(fis);
		} catch (Exception e) {
			System.err.println(e);
		}
		this.dbUrl = dbProps.getProperty("db.url", "");
		this.dbUser = dbProps.getProperty("db.user", "");
		this.dbPassword = dbProps.getProperty("db.password", "");
	}
	
	public DBUtils() {
		this("db.properties");
	}
	
	public int init(String... paths) {
		int modified = 0;
		
		for (String path : paths) {
			modified += executeQuery(path);
		}
		
		return modified;
	}
	
	public int executeQuery(String file) {
		int modified = 0;
		try (Connection connection = this.getConnection();
		     BufferedReader br = new BufferedReader(new FileReader(file));) {
			String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					System.err.println(e);
					return 0;
				}
			}).reduce(Integer::sum).orElse(0);
		} catch (Exception e) {
			System.err.println(e);
		}
		return modified;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
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