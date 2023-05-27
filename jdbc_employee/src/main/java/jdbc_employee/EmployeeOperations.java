package jdbc_employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeOperations {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static String query;
	private final static String dburl = "jdbc:mysql://localhost:3306/empdb";
	private final static String driverPath = "com.mysql.cj.jdbc.Driver";
	private final static String user = "root";
	private final static String passsword = "root";
	private static ResultSet resultSet;
	private static int result;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Class.forName(driverPath);
			connection = DriverManager.getConnection(dburl, user, passsword);
			statement = connection.createStatement();

			// 1. create emp table
			query = "create table emp1 (empno int(4) primary key, ename varchar(20), job varchar(20), sal int(10))";
			result = statement.executeUpdate(query);
			System.out.println("Table created successfully...");
			result = 0;

			// 2. insert 5 records

			query = "insert into emp1 values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			for (int i = 1; i <= 5; i++) {
				System.out.println("Enter id for record " + i);
				preparedStatement.setInt(1, scanner.nextInt());
				System.out.println("Enter ename for record " + i);
				preparedStatement.setString(2, scanner.next());
				System.out.println("Enter job for record " + i);
				preparedStatement.setString(3, scanner.next());
				System.out.println("Enter salary for record " + i);
				preparedStatement.setInt(4, scanner.nextInt());
				result += preparedStatement.executeUpdate();
			}
			System.out.println(result + " records inserted successfully...");
			result = 0;

			// 3. display all records

			query = "select * from emp1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + " | "
						+ resultSet.getString(3) + " | " + resultSet.getString(4));
			}

			// 4. update 1 record
			query = "update emp1 set job=? where empno=?";
			preparedStatement = connection.prepareStatement(query);
			System.out.println("Enter new value for job");
			preparedStatement.setString(1, scanner.next());
			System.out.println("Enter the empno");
			preparedStatement.setInt(2, scanner.nextInt());
			result = preparedStatement.executeUpdate();
			System.out.println(result + " record updated successfully...");
			result = 0;

			// 5. Display all records

			query = "select * from emp1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + " | "
						+ resultSet.getString(3) + " | " + resultSet.getString(4));
			}

			// 6. delete 1 record
			query = "delete from emp1 where empno=?";
			preparedStatement = connection.prepareStatement(query);
			System.out.println("Enetr empno to delete");
			preparedStatement.setInt(1, scanner.nextInt());
			result = preparedStatement.executeUpdate();
			System.out.println(result + " records deleted successfully...");
			result = 0;

			// 7. display all records

			query = "select * from emp1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3)
						+ " | " + resultSet.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) {
					connection.close();
				}
				if(statement!=null) {
					statement.close();
				}
				if(preparedStatement!=null) {
					preparedStatement.close();
				}
				if(resultSet!=null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
	}
}
