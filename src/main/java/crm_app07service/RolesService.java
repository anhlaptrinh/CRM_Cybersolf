package crm_app07service;

import java.util.List;

import crm_app07entity.RoleEntity;
import crm_app07repository.RoleRepository;

public class RolesService {
	private static RoleRepository roleRepository = new RoleRepository();
	
	public List<RoleEntity> loadRoles(){
		return roleRepository.findAll();
	}
	
	public boolean addRole(String ten, String mota) {
		return roleRepository.insertRole(ten, mota)>0;
	}
}
