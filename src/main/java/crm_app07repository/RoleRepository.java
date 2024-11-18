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
	
}
