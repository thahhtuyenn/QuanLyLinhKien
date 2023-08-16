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

import entity.CategoryEntity;
import entity.EmployeeEntity;

public class CategoryProductDAO extends AbstractDAO {
	private CategoryEntity categoryEntity;

	public CategoryEntity findById(int id) {
		CategoryEntity categoryEntity = null;
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Category WHERE CategoryID = " + id;
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int categoryId = resultSet.getInt(1);
					String categoryName = resultSet.getString(2).trim();
					String description = resultSet.getString(3).trim();
					categoryEntity = new CategoryEntity(categoryId, categoryName, description);
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

		return categoryEntity;
	}

	public List<CategoryEntity> findAll() {
		List<CategoryEntity> list = new ArrayList<>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Category";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int categoryId = resultSet.getInt(1);
					String categoryName = resultSet.getString(2);
					String description = resultSet.getString(3);
					CategoryEntity categoryEntity = new CategoryEntity(categoryId, categoryName, description);
					list.add(categoryEntity);
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

	public CategoryEntity insertOne(CategoryEntity entity) {
		CategoryEntity entityResult = null;
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "INSERT INTO Category VALUES(?, ?)";

				preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, entity.getCategoryName());
				preparedStatement.setString(2, entity.getDescription());

				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					entityResult = new CategoryEntity();
					entityResult.setCategoryId(resultSet.getInt(1));
					;
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

	public int updateOne(CategoryEntity entity) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Category \r\n" + "SET CategoryName  = ?,\r\n" + " Description = ?\r\n"
						+ "WHERE CategoryID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, entity.getCategoryName());
				preparedStatement.setString(2, entity.getDescription());
				preparedStatement.setInt(3, entity.getCategoryId());

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

	public int removeOne(int categoryId) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "DELETE FROM Category WHERE CategoryID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, categoryId);

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

	public List<CategoryEntity> findByCategoryName(String CategoryNameSearch) {
		List<CategoryEntity> list = new ArrayList<CategoryEntity>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				StringBuilder sb = new StringBuilder("SELECT *FROM Category ");
				statement = conn.createStatement();

				if (!CategoryNameSearch.equals("")) {
					sb.append("WHERE CategoryName LIKE N'%" + CategoryNameSearch + "%' ");
				}

				resultSet = statement.executeQuery(sb.toString());
				while (resultSet.next()) {

					int categoryId = resultSet.getInt(1);
					String CategoryName = resultSet.getString(2).trim();
					String Description = resultSet.getString(3).trim();
					CategoryEntity CategoryEntity = new CategoryEntity(categoryId, CategoryName, Description);
					list.add(CategoryEntity);

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

}
