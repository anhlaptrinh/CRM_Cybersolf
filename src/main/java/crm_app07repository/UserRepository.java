package crm_app07repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app07config.MysqlConfig;
import crm_app07entity.TaskEntity;

public class UserRepository {
	
	public int deleteById(int id) {
		int rowDeleted = 0;
		String query = "DELETE FROM users WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			rowDeleted = statement.executeUpdate();
			
		} catch (Exception e) {
			 System.out.println("deleteById : " + e.getLocalizedMessage());
		}
		
		return rowDeleted;
	}
	
	public int insert(String email, String password, String fullname, int roleId) {
		int rowInsert = 0;
		String query = "INSERT INTO users(email,password,fullname,role_id)VALUES(?,?,?,?)";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullname);
			statement.setInt(4, roleId);
			
			rowInsert = statement.executeUpdate();
			
		} catch (Exception e) {
			 System.out.println("deleteById : " + e.getLocalizedMessage());
		}
		
		return rowInsert;
	}
	public List<TaskEntity> findTaskbyUserId(int userid){
		List<TaskEntity> taskList = new ArrayList<TaskEntity>();
		String query = "SELECT t.id, t.name, t.start_date, t.end_date, s.name as status \n"
				+ "FROM tasks t \n"
				+ "JOIN users u ON t.user_id = u.id \n"
				+ "JOIN status s ON t.status_id = s.id\n"
				+ "WHERE u.id = ?";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userid);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(result.getInt("id"));
				entity.setName(result.getString("name"));
				entity.setStart_date(result.getDate("start_date"));
				entity.setEnd_date(result.getDate("end_date"));
				entity.setStatus(result.getString("status"));
				taskList.add(entity);
			}
		} catch (Exception e) {
			 System.out.println("findTaskByUserId : " + e.getLocalizedMessage());
		}
		return taskList;
	}
	public int updateRoleById(int id, int role) {
		int rowUpdate = 0;
		String query = "UPDATE users\n"
				+ "SET role_id = ?\n"
				+ "WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, role);
			statement.setInt(2, id);
			
			rowUpdate = statement.executeUpdate();
			
		} catch (Exception e) {
			 System.out.println("updateRoleById : " + e.getLocalizedMessage());
		}
		
		return rowUpdate;
	}
	
}
