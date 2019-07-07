package com.vendingmachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {

	static Connection connection = null;
	static PreparedStatement preparedStatement = null;

	public static void main(String[] args) {

		try {

			makeJDBCConnection();

			addDataToDB("Coca-Cola", 4.5);
			addDataToDB("Fanta", 4.2);
			addDataToDB("Sprite", 3.7);

			getDataFromDB();

			preparedStatement.close();
			connection.close(); // connection close

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// method to connect to DB
	private static void makeJDBCConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			log("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "abcdef");
			if (connection != null) {
				log("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				log("Failed to make connection!");
			}
		} catch (SQLException e) {
			log("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
	}

	private static void log(String string) {
		System.out.println(string);

	}

	// method to add products to DB
	private static void addDataToDB(String name, double price) {

		try {
			String insertQueryStatement = "INSERT INTO vending_machine.product (name, price) VALUES (?, ?)";

			preparedStatement = connection.prepareStatement(insertQueryStatement);

			preparedStatement.setString(1, name);
			preparedStatement.setDouble(2, price);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	// method to retrieve data from DB
	private static void getDataFromDB() {

		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM vending_machine.product";

			preparedStatement = connection.prepareStatement(getQueryStatement);

			// Execute the Query, and get a java ResultSet
			ResultSet rs = preparedStatement.executeQuery();

			// iterate through the java ResultSet
			while (rs.next()) {
				String name = rs.getString("Name");
				double price = rs.getDouble("Price");
				int id = rs.getInt("ID");

				// Simply Print the results
				System.out.print("ID: " + id + " ");
				System.out.print("Produs: " + name + " ");
				System.out.print("Pret: " + price);
				System.out.println();
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}
}
