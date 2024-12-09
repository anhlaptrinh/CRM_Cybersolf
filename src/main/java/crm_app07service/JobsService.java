package crm_app07service;



import java.util.List;

import crm_app07entity.JobEntity;
import crm_app07repository.JobRepository;

/**
 * Servlet implementation class JobsService
 */

public class JobsService {
	private JobRepository jobRepository;
	
	public List<JobEntity> getListJob(){
		return jobRepository.findAll();
	}

}
