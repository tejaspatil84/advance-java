package com.jspiders.jdbcmusicplayer.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SongOperations {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static String query;
	private final static String driverpath = "com.mysql.cj.jdbc.Driver";
	private final static String dburl = "jdbc:mysql://localhost:3306/musicplayer";
	private final static String user = "root";
	private final static String password = "root";
	private static ResultSet resultSet;
	private static int result;
	private static Scanner scanner = new Scanner(System.in);

	public static Connection openConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driverpath);
		connection = DriverManager.getConnection(dburl, user, password);
		return connection;
	}

	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

	public void addSong() throws ClassNotFoundException, SQLException {
		System.out.println("Enter song id");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter name of song");
		String name = scanner.nextLine();
		System.out.println("Enter name of singer");
		String sigerName = scanner.nextLine();
		System.out.println("Enter duration of song");
		int duration = scanner.nextInt();

		query = "INSERT INTO PLAYLIST VALUES(?,?,?,?)";
		Connection connection = openConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, sigerName);
		preparedStatement.setDouble(4, duration);
		result = preparedStatement.executeUpdate();

		System.out.println(result + " songs added successfully...");
		result = 0;
		closeConnection();
	}

	public void removeSong(int sid) throws ClassNotFoundException, SQLException {
		query = "DELETE FROM PLAYLIST WHERE ID=?";
		Connection connection = openConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, sid);
		result = preparedStatement.executeUpdate();

		System.out.println(result + " songs deleted successfully...");
		result = 0;
		closeConnection();
	}

	public void playAllSong() throws ClassNotFoundException, SQLException {
		query = "SELECT * FROM PLAYLIST";
		Connection connection = openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3)
					+ " | " + resultSet.getInt(4));
		}
		closeConnection();
	}

	public void playRandomeSong() throws ClassNotFoundException, SQLException {
		query = "SELECT ID FROM PLAYLIST ORDER BY ID DESC LIMIT 1";
		Connection connection = openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			result = resultSet.getInt(1);
		}
		for (int i = 1; i <= result; i++) {
			int id = (int) (Math.random() * result);
			query = "SELECT * FROM PLAYLIST WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3)
						+ " | " + resultSet.getInt(4));
			}
		}
		result = 0;
		closeConnection();
	}

	public void chooseToPlay(int id) throws ClassNotFoundException, SQLException {
		query = "SELECT * FROM PLAYLIST WHERE ID=?";
		Connection connection = openConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3)
					+ " | " + resultSet.getInt(4));
		}
	}

	public void editSong(int id) throws ClassNotFoundException, SQLException {
		query = "UPDATE PLAYLIST SET SONG_NAME=?, SINGER_NAME=?, DURATION=? WHERE ID=?";
		Connection connection = openConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		System.out.println("Enter song name");
		preparedStatement.setString(1, scanner.nextLine());
		System.out.println("Enter singer name");
		preparedStatement.setString(2, scanner.nextLine());
		System.out.println("Enter song duration");
		preparedStatement.setInt(3, scanner.nextInt());
		scanner.nextLine();
		preparedStatement.setInt(4, id);
		result = preparedStatement.executeUpdate();
		System.out.println(result + " songs updated successfully...");
		result = 0;
		closeConnection();
	}
}
