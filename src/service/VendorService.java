package service;

import java.util.List;

import dao.VendorDAO;
import entity.VendorEntity;

public class VendorService {
	private VendorDAO vendorDao;

	public VendorService() {
		vendorDao = new VendorDAO();
	}

	public List<VendorEntity> findAll() {
		return vendorDao.findAll();
	}

	public VendorEntity insert(VendorEntity vendorEntity) {
		return vendorDao.insertOne(vendorEntity);
	}

	public int update(VendorEntity vendorEntity) {
		return vendorDao.updateOne(vendorEntity);
	}

	public int remove(int id) {
		return vendorDao.removeOne(id);
	}

	public VendorEntity findById(int id) {
		return vendorDao.findById(id);
	}

	public List<VendorEntity> findByNameAndAddress(String name, String address) {
		return vendorDao.findByNameAndAddress(name, address);
	}

	public Integer count() {
		return vendorDao.count();
	}
}
