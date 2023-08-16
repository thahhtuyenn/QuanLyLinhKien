package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.CategoryEntity;
import entity.CustomerEntity;

public class CustomerDAO extends AbstractDAO {
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> list = new ArrayList<>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Customer";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int customerId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					String address = resultSet.getString(3).trim();
					String phone = resultSet.getString(4).trim();
					int status = resultSet.getInt(5);

					CustomerEntity customerEntity = new CustomerEntity(customerId, fullName, address, phone, status);
					list.add(customerEntity);
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

	public CustomerEntity insertOne(CustomerEntity entity) {
		CustomerEntity entityResult = null;
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "INSERT INTO Customer VALUES(?, ?, ?, ?)";

				preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, entity.getFullName());
				preparedStatement.setString(2, entity.getAddress());
				preparedStatement.setString(3, entity.getPhone());
				preparedStatement.setInt(4, entity.getStatus());

				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					entityResult = new CustomerEntity();
					entityResult.setCustomerId(resultSet.getInt(1));
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
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
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
		return entityResult;

	}

	public int updateOne(CustomerEntity entity) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Customer\r\n" + "SET FullName = ?,\r\n" + "	Address = ?,\r\n"
						+ "	Phone = ?,\r\n" + "	Status = ?\r\n" + " WHERE CustomerID = ?";
				preparedStatement = conn.prepareStatement(query);

				preparedStatement.setString(1, entity.getFullName());
				preparedStatement.setString(2, entity.getAddress());
				preparedStatement.setString(3, entity.getPhone());
				preparedStatement.setInt(4, entity.getStatus());
				preparedStatement.setInt(5, entity.getCustomerId());

				return preparedStatement.executeUpdate();

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
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
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
		return 0;

	}

	public int removeOne(int customerId) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "DELETE FROM Customer WHERE CustomerID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, customerId);

				return preparedStatement.executeUpdate();

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
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
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
		return 0;

	}

	public List<CustomerEntity> findByFullNameAndAddress(String customerName, String customerAddress) {
		List<CustomerEntity> list = new ArrayList<CustomerEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = getConnection();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT *FROM Customer ");
				if (!customerName.equals("") && !customerAddress.equals("")) {
					query.append(String.format("WHERE FullName LIKE N'%%%s%%' AND Address LIKE N'%%%s%%'", customerName,
							customerAddress));
				} else if (customerName.equals("") && !customerAddress.equals("")) {
					query.append(String.format("WHERE Address LIKE N'%%%s%%'", customerAddress));
				} else if (!customerName.equals("") && customerAddress.equals("")) {
					query.append(String.format("WHERE FullName LIKE N'%%%s%%'", customerName));
				}
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query.toString());
				while (resultSet.next()) {
					int customerId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					String address = resultSet.getString(3).trim();
					String phone = resultSet.getString(4).trim();
					int status = resultSet.getInt(5);

					CustomerEntity customerEntity = new CustomerEntity(customerId, fullName, address, phone, status);
					list.add(customerEntity);
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

	public CustomerEntity findById(int id) {
		CustomerEntity customerEntity = null;
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Customer WHERE CustomerID = " + id;
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int customerId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					String address = resultSet.getString(3).trim();
					String phone = resultSet.getString(4).trim();
					int status = resultSet.getInt(5);

					customerEntity = new CustomerEntity(customerId, fullName, address, phone, status);
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

		return customerEntity;
	}

	public Integer count() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Integer result = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM Customer";
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
