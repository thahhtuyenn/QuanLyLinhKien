package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.RoleEntity;

public class RoleDAO extends AbstractDAO {
	public RoleDAO() {
		// TODO Auto-generated constructor stub
	}

	public RoleEntity findById(int roleIdSearch) {
		RoleEntity result = null;
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Role WHERE RoleID = ?";
				statement = conn.prepareStatement(query);
				statement.setInt(1, roleIdSearch);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {

					int roleId = resultSet.getInt(1);
					String roleName = resultSet.getString(2).trim();
					String desciption = resultSet.getString(3).trim();

					result = new RoleEntity(roleId, roleName, desciption);

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

	public List<RoleEntity> findByRoleName(String name) {
		List<RoleEntity> list = new ArrayList<RoleEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Role WHERE RoleName LIKE ?";
				statement = conn.prepareStatement(query);
				statement.setString(1, "%" + name + "%");
				resultSet = statement.executeQuery();
				while (resultSet.next()) {

					int roleId = resultSet.getInt(1);
					String roleName = resultSet.getString(2).trim();
					String desciption = resultSet.getString(3).trim();

					list.add(new RoleEntity(roleId, roleName, desciption));

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

	public List<RoleEntity> findAll() {
		List<RoleEntity> list = new ArrayList<>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Role";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int roleId = resultSet.getInt(1);
					String roleName = resultSet.getString(2).trim();
					String desciption = resultSet.getString(3).trim();
					list.add(new RoleEntity(roleId, roleName, desciption));
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

	public RoleEntity insertOne(RoleEntity entity) {
		RoleEntity entityResult = null;
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "INSERT INTO Role VALUES(?, ?)";
				preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, entity.getRoleName());
				preparedStatement.setString(2, entity.getDescription());

				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					entityResult = new RoleEntity();
					entityResult.setRoleId(resultSet.getInt(1));
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

	public int updateOne(RoleEntity entity) {
		boolean status = false;
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Role SET RoleName = ?, Description = ? WHERE RoleID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, entity.getRoleName());
				preparedStatement.setString(2, entity.getDescription());
				preparedStatement.setInt(3, entity.getRoleId());

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

	public int removeOne(int roleId) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "DELETE FROM Role WHERE RoleID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, roleId);

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

	public static void main(String[] args) {
		System.out.println(new RoleDAO().removeOne(10));
	}
}
