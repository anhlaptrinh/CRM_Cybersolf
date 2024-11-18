package crm_app07service;

import java.util.List;

import crm_app07entity.LoginEntity;
import crm_app07entity.RoleEntity;
import crm_app07entity.TaskEntity;
import crm_app07repository.LoginRepository;
import crm_app07repository.RoleRepository;
import crm_app07repository.UserRepository;
import utils.MD5;

public class UserService {

	private LoginRepository repository = new LoginRepository();
	private UserRepository userRepository = new UserRepository();
	private RoleRepository roleRepository = new RoleRepository();
	
	
	public List<LoginEntity> getAllUser(){
		return repository.findAll();
	}
	
	public boolean deleteUserById(int id) {
		int count = userRepository.deleteById(id);
		
		return count > 0;
	}
	
	public List<RoleEntity> getAllRole(){
		return roleRepository.findAll();
	}
	
	public boolean insertUser(String email, String password, String fullname, int roleId) {
		return userRepository.insert(email, MD5.getMd5(password), fullname, roleId) > 0;
	}
	public List<TaskEntity> getTask(int id){
		return userRepository.findTaskbyUserId(id);
	}
	public boolean updateRoleUserById(int id, int role) {
		return userRepository.updateRoleById(id, role)>0;
	}
	
}
