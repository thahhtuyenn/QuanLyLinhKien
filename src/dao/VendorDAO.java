package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.CustomerEntity;
import entity.VendorEntity;

public class VendorDAO extends AbstractDAO {
	public VendorDAO() {

	}

	public List<VendorEntity> findAll() {
		List<VendorEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM Vendor";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int vendorID;
					String vendorName, address, phone, fax;
					vendorID = resultSet.getInt(1);
					vendorName = resultSet.getString(2).trim();
					address = resultSet.getString(3).trim();
					phone = resultSet.getString(4).trim();
					fax = resultSet.getString(5).trim();
					VendorEntity vendorEntity = new VendorEntity(vendorID, vendorName, address, phone, fax);
					list.add(vendorEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return list;
	}

	public VendorEntity insertOne(VendorEntity vendorEntity) {
		VendorEntity entityResult = null;
		Connection connect = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO Vendor VALUES(?, ?, ?, ?)";
				preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, vendorEntity.getVendorName());
				preparedStatement.setString(2, vendorEntity.getAddress());
				preparedStatement.setString(3, vendorEntity.getPhone());
				preparedStatement.setString(4, vendorEntity.getFax());
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					entityResult = new VendorEntity();
					entityResult.setVendorId(resultSet.getInt(1));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return entityResult;
	}

	public int updateOne(VendorEntity vendorEntity) {
		Connection connect = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "UPDATE Vendor\r\n" + "SET VendorName = ?,\r\n" + "    Address = ?,\r\n"
						+ "    Phone = ?,\r\n" + "    Fax = ?\r\n" + "WHERE VendorID = ?";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, vendorEntity.getVendorName());
				preparedStatement.setString(2, vendorEntity.getAddress());
				preparedStatement.setString(3, vendorEntity.getPhone());
				preparedStatement.setString(4, vendorEntity.getFax());
				preparedStatement.setString(5, String.valueOf(vendorEntity.getVendorId()));

				return preparedStatement.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return 0;
	}

	public int removeOne(int vendorID) {
		Connection connect = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM Vendor WHERE VendorID = ?";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, vendorID);
				return preparedStatement.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return 0;
	}

	public List<VendorEntity> findByNameAndAddress(String name, String address) {
		List<VendorEntity> list = new ArrayList<VendorEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = getConnection();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT *FROM Vendor ");
				if (!name.equals("") && !address.equals("")) {
					query.append(
							String.format("WHERE VendorName LIKE N'%%%s%%' AND Address LIKE N'%%%s%%'", name, address));
				} else if (name.equals("") && !address.equals("")) {
					query.append(String.format("WHERE Address LIKE N'%%%s%%'", address));
				} else if (!name.equals("") && address.equals("")) {
					query.append(String.format("WHERE VendorName LIKE N'%%%s%%'", name));
				}
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query.toString());
				while (resultSet.next()) {
					int VendorId = resultSet.getInt(1);
					String VendorName = resultSet.getString(2).trim();
					String Address = resultSet.getString(3).trim();
					String Phone = resultSet.getString(4).trim();
					String Fax = resultSet.getString(5).trim();

					VendorEntity vendorEntity = new VendorEntity(VendorId, VendorName, Address, Phone, Fax);
					list.add(vendorEntity);
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;

	}

	public VendorEntity findById(int id) {
		VendorEntity vendorEntity = null;
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM Vendor WHERE VendorID = " + id;
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int vendorID;
					String vendorName, address, phone, fax;
					vendorID = resultSet.getInt(1);
					vendorName = resultSet.getString(2).trim();
					address = resultSet.getString(3).trim();
					phone = resultSet.getString(4).trim();
					fax = resultSet.getString(5).trim();
					vendorEntity = new VendorEntity(vendorID, vendorName, address, phone, fax);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return vendorEntity;
	}

	public Integer count() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Integer result = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM Vendor";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return result;
	}
}
