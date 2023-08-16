package service;

import java.util.List;

import dao.RoleDAO;
import entity.RoleEntity;

public class RoleService {
	private RoleDAO roleDao;

	public RoleService() {
		roleDao = new RoleDAO();
	}

	public List<RoleEntity> findAll() {
		return roleDao.findAll();
	}

	public RoleEntity insert(RoleEntity entity) {
		if (entity != null) {
			return roleDao.insertOne(entity);
		}
		return null;
	}

	public int remove(int id) {
		return roleDao.removeOne(id);
	}

	public int updateOne(RoleEntity entityNew) {
		return roleDao.updateOne(entityNew);

	}

	public List<RoleEntity> findByRoleName(String roleName) {
		List<RoleEntity> list = roleDao.findByRoleName(roleName);
		return list;

	}

	public RoleEntity findById(int roleId) {
		return roleDao.findById(roleId);
	}
}
