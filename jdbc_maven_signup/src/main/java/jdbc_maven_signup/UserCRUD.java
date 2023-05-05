package jdbc_maven_signup;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserCRUD {

	public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		Class.forName(properties.getProperty("className"));
		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));
		return connection;
	}

	public void signUp(User user) throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO USER (ID, NAME, EMAIL, PASSWORD, ADDRESS) VALUES (?,?,?,?,?)");
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getAddress());

		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Signed up successfully");
		} else {
			System.out.println("Failed to Sign Up");
		}
		connection.close();
	}

	public boolean login(String email, String password) throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String dbPassword = resultSet.getString(1);

			if (dbPassword.equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void updateFacebookPassword(String password, String email)
			throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE USER SET FACEBOOKPASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Passwor added successfully");
		} else {
			System.out.println("Failed");
		}
		connection.close();
	}

	public void updateWhatsAppPassword(String password, String email)
			throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE USER SET WHATSAPPPASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Passwor added successfully");
		} else {
			System.out.println("Failed");
		}
		connection.close();
	}

	public void updateSnapchatPassword(String password, String email)
			throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE USER SET SNAPCHATPASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Passwor added successfully");
		} else {
			System.out.println("Failed");
		}
		connection.close();
	}

	public void updateTwiterPassword(String password, String email)
			throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE USER SET TWITERPASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Passwor added successfully");
		} else {
			System.out.println("Failed");
		}
		connection.close();
	}

	public void showAll(String email) throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT FACEBOOKPASSWORD, WHATSAPPPASSWORD, SNAPCHATPASSWORD, TWITERPASSWORD FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println("Facebook Password : " + resultSet.getString(1));
			System.out.println("WhatsApp Password : " + resultSet.getString(2));
			System.out.println("Snapchat Password : " + resultSet.getString(3));
			System.out.println("Twiter Password : " + resultSet.getString(4));
			System.out.println("**************");
		}
	}

	public boolean forgotPassword(String femail) throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT EMAIL FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, femail);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String dbemail = resultSet.getString(1);
			if (dbemail.equals(femail)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public void updatePassword(String password, String email) throws ClassNotFoundException, IOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USER SET PASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Passwor updeted successfully");
		} else {
			System.out.println("Failed");
		}
		connection.close();
	}
}
