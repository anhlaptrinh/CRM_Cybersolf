package crm_app07repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app07config.MysqlConfig;
import crm_app07entity.JobEntity;
import crm_app07entity.RoleEntity;

public class JobRepository {
	public List<JobEntity> findAll() {
		List<JobEntity> listJobs = new ArrayList<JobEntity>();
		String query = "SELECT * FROM jobs";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				JobEntity entity = new JobEntity();
				entity.setId(result.getInt("id"));
				entity.setName(result.getString("name"));
				entity.setStartDate(result.getDate("start_date"));
				entity.setEndDate(result.getDate("end_date"));
				listJobs.add(entity);
			}
		} catch (Exception e) {
			 System.out.println("findByEmailAndPassword : " + e.getLocalizedMessage());
		}
		
		return listJobs;
	}
}
