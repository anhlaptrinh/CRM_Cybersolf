package crm_app07repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app07config.MysqlConfig;
import crm_app07entity.LoginEntity;
import crm_app07entity.RoleEntity;

public class RoleRepository {

	public List<RoleEntity> findAll(){
		List<RoleEntity> listRoles = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				RoleEntity entity = new RoleEntity();
				entity.setId(result.getInt("id"));
				entity.setName(result.getString("name"));
				entity.setDesc(result.getString("description"));
				
				listRoles.add(entity);
			}
		} catch (Exception e) {
			 System.out.println("findByEmailAndPassword : " + e.getLocalizedMessage());
		}
		
		return listRoles;
	}
	
	public int insertRole(String roleName, String desc) {
		int rowInsert = 0;
		String query = "INSERT INTO roles(name,description)VALUES(?,?)";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, roleName);
			statement.setString(2, desc);
			
			
			rowInsert = statement.executeUpdate();
			
		} catch (Exception e) {
			 System.out.println("deleteById : " + e.getLocalizedMessage());
		}
		
		return rowInsert;
		
	}
	
	public int deleteRole(int id) {
		int rowDeleted = 0;
		String query = "DELETE FROM roles WHERE id = ?";
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
	
}
