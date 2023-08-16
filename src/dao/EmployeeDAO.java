package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.EmployeeEntity;
import entity.RoleEntity;
import utils.PasswordUtils;

public class EmployeeDAO extends AbstractDAO {
	public List<EmployeeEntity> findAll() {
		List<EmployeeEntity> list = new ArrayList<>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Employee";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleId = resultSet.getInt(9);
					EmployeeEntity employeeEntity = new EmployeeEntity(employeeId, fullName, birthDate, address, phone,
							status, userName, password, roleId);
					list.add(employeeEntity);
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

	public EmployeeEntity insertOne(EmployeeEntity entity) {
		EmployeeEntity entityResult = null;
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

				preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, entity.getFullName());
				preparedStatement.setDate(2, entity.getBirthDate());
				preparedStatement.setString(3, entity.getAddress());
				preparedStatement.setString(4, entity.getPhone());
				preparedStatement.setInt(5, entity.getStatus());
				preparedStatement.setString(6, entity.getUserName());
				// Mã hóa mật khẩu SHA1
				preparedStatement.setString(7, entity.getPassword());
				preparedStatement.setInt(8, entity.getRoleId());

				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					entityResult = new EmployeeEntity();
					entityResult.setEmployeeId(resultSet.getInt(1));
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

	public int updateOne(EmployeeEntity entity) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Employee\r\n" + "SET FullName = ?,\r\n" + "    BirthDate = ?,\r\n"
						+ "    Address = ?,\r\n" + "    Phone = ?,\r\n" + "    Status = ?,\r\n"
						+ "    UserName = ?,\r\n" + "    Password = ?,\r\n" + "    RoleID = ?\r\n"
						+ "WHERE EmployeeID = ?";
				preparedStatement = conn.prepareStatement(query);

				preparedStatement.setString(1, entity.getFullName());
				preparedStatement.setDate(2, entity.getBirthDate());
				preparedStatement.setString(3, entity.getAddress());
				preparedStatement.setString(4, entity.getPhone());
				preparedStatement.setInt(5, entity.getStatus());
				preparedStatement.setString(6, entity.getUserName());
				preparedStatement.setInt(9, entity.getEmployeeId());

				preparedStatement.setString(7, entity.getPassword());
				preparedStatement.setInt(8, entity.getRoleId());

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

	public int removeOne(int employeeId) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "DELETE FROM Employee WHERE EmployeeID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, employeeId);

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

	public List<EmployeeEntity> findByFullNameAndStartDateAndEndDate(String fullNameSearch, Date startDate,
			Date endDate) {
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				StringBuilder sb = new StringBuilder("SELECT *FROM Employee ");
				statement = conn.createStatement();

				if (!fullNameSearch.equals("")) {
					sb.append("WHERE FullName LIKE N'%" + fullNameSearch + "%' ");
				}
				if (startDate != null && endDate != null) {
					System.out.println(fullNameSearch);
					if (fullNameSearch.trim().equals("")) {
						sb.append(String.format("WHERE (BirthDate BETWEEN '%s' AND '%s')", startDate.toString(),
								endDate.toString()));
					} else {
						sb.append(String.format("AND (BirthDate BETWEEN '%s' AND '%s')", startDate.toString(),
								endDate.toString()));
					}
				}
				resultSet = statement.executeQuery(sb.toString());
				while (resultSet.next()) {

					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleId = resultSet.getInt(9);
					EmployeeEntity employeeEntity = new EmployeeEntity(employeeId, fullName, birthDate, address, phone,
							status, userName, password, roleId);
					list.add(employeeEntity);

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

	public EmployeeEntity findById(int employeeId) {
		EmployeeEntity employeeEntity = null;
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				statement = connect.createStatement();
				String query = "SELECT *FROM Employee WHERE EmployeeID = " + employeeId;
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					employeeEntity = new EmployeeEntity(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6),
							resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
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
		return employeeEntity;
	}

	public static void main(String[] args) {
		System.out.println(new EmployeeDAO().findById(2));
	}

	public EmployeeEntity findByUserNameAndPassword(String userNameSearch, String passwordSearch) {
		EmployeeEntity result = null;
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Employee WHERE UserName = ? AND Password = ?";
				statement = conn.prepareStatement(query);
				statement.setString(1, userNameSearch);
				statement.setString(2, passwordSearch);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleId = resultSet.getInt(9);
					result = new EmployeeEntity(employeeId, fullName, birthDate, address, phone, status, userName,
							password, roleId);
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

	public int updatePasswordByUserName(String userName, String newPassword) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Employee SET Password = ? WHERE UserName = ?";
				preparedStatement = conn.prepareStatement(query);

				preparedStatement.setString(1, PasswordUtils.toSHA1(newPassword));
				preparedStatement.setString(2, userName);

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

	public Integer count() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Integer result = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM Employee";
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

	public List<EmployeeEntity> findByRoldId(int roleId) {
		List<EmployeeEntity> result = new ArrayList<EmployeeEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Employee WHERE RoleID = ?";
				statement = conn.prepareStatement(query);
				statement.setInt(1, roleId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleIds = resultSet.getInt(9);
					result.add(new EmployeeEntity(employeeId, fullName, birthDate, address, phone, status, userName,
							password, roleIds));
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

	public List<EmployeeEntity> findByFullNameAndRoleId(String name, int roleId) {
		List<EmployeeEntity> result = new ArrayList<EmployeeEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Employee WHERE RoleID = ? AND FullName LIKE N'%%?%%'";
				statement = conn.prepareStatement(query);
				statement.setInt(1, roleId);
				statement.setString(2, name);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleIds = resultSet.getInt(9);
					result.add(new EmployeeEntity(employeeId, fullName, birthDate, address, phone, status, userName,
							password, roleIds));
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

	public EmployeeEntity findByUserNameAndPasswordAndStatus(String userNameSearch, String passwordSearch, int statusSearch) {
		EmployeeEntity result = null;
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Employee WHERE UserName = ? AND Password = ? AND Status = ?";
				statement = conn.prepareStatement(query);
				statement.setString(1, userNameSearch);
				statement.setString(2, passwordSearch);
				statement.setInt(3, statusSearch);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int employeeId = resultSet.getInt(1);
					String fullName = resultSet.getString(2).trim();
					Date birthDate = resultSet.getDate(3);
					String address = resultSet.getString(4).trim();
					String phone = resultSet.getString(5).trim();
					int status = resultSet.getInt(6);
					String userName = resultSet.getString(7).trim();
					String password = resultSet.getString(8).trim();
					int roleId = resultSet.getInt(9);
					result = new EmployeeEntity(employeeId, fullName, birthDate, address, phone, status, userName,
							password, roleId);
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
