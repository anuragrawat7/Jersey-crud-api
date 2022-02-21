package com.example.JerseyCRUD.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.JerseyCRUD.Model.User;

public class UserDao {
	
	private static Connection connection;
	
	private static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseycrud","root","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static boolean save(User user) {
		try {
			System.out.println("444");
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into jersey_table values(?,?,?,?);");
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getCity());
			preparedStatement.setInt(4, user.getAge());
			System.out.println("555");
			if (preparedStatement.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from jersey_table");
			while (resultSet.next())
				users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4)));
			resultSet.close();
			
			return users;
		} catch (Exception e) {
			System.out.println(e.toString());
			return users;
		}
	}
	
	public static boolean remove(int userId) {
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("Delete from jersey_table where User_Id = ?;");
			preparedStatement.setInt(1, userId);
			if (preparedStatement.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean update(User user) {
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("update jersey_table set Name=?,City=?,Age=? where User_Id=?;");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getCity());
			preparedStatement.setInt(3, user.getAge());
			preparedStatement.setInt(4, user.getUserId());
			if (preparedStatement.executeUpdate() > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

}
