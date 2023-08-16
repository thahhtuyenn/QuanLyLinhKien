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
import entity.ProductEntity;

public class ProductDAO extends AbstractDAO {
	public List<ProductEntity> findAll() {
		List<ProductEntity> list = new ArrayList<>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Product ";
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int productId = resultSet.getInt(1);
					int categoryId = resultSet.getInt(2);
					String name = resultSet.getString(3).trim();
					double price = resultSet.getDouble(4);
					int quantity = resultSet.getInt(5);
					int status = resultSet.getInt(6);
					ProductEntity productEntity = new ProductEntity(productId, categoryId, name, price, quantity,
							status);
					list.add(productEntity);
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

	public ProductEntity findById(int id) {
		ProductEntity productEntity = null;
		Connection conn = getConnection();
		java.sql.Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM Product WHERE ProductID = " + id;
				statement = conn.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int productId = resultSet.getInt(1);
					int categoryId = resultSet.getInt(2);
					String name = resultSet.getString(3).trim();
					double price = resultSet.getDouble(4);
					int quantity = resultSet.getInt(5);
					int status = resultSet.getInt(6);
					productEntity = new ProductEntity(productId, categoryId, name, price, quantity, status);
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

		return productEntity;
	}

	public ProductEntity insertOne(ProductEntity product) {
		ProductEntity productResult = null;
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO Product VALUES (?,?,?,?,?)";
				statement = connect.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, product.getCategoryId());
				statement.setString(2, product.getName());
				statement.setDouble(3, product.getPrice());
				statement.setInt(4, product.getQuantity());
				statement.setInt(5, product.getStatus());

				statement.executeUpdate();
				
				resultSet = statement.getGeneratedKeys();

				while (resultSet.next()) {
					productResult = new ProductEntity();
					productResult.setProductId(resultSet.getInt(1));
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
		return productResult;
	}

	public int updateOne(ProductEntity product) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "UPDATE Product\r\n" + "SET Name = ?,\r\n" + "	Price = ?,\r\n" + "	Quantity = ?,\r\n"
						+ "CategoryID =?\r\n" + " WHERE ProductID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, product.getName());
				preparedStatement.setDouble(2, product.getPrice());
				preparedStatement.setInt(3, product.getQuantity());
				preparedStatement.setInt(4, product.getCategoryId());
				preparedStatement.setInt(5, product.getProductId());

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

	public int removeOne(int productId) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {
				String query = "DELETE FROM Product WHERE ProductID = ?";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, productId);

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

	public List<ProductEntity> findByNameAndTotalDue(String NameSearch, Double TotalDueFrom, Double TotalDueTo) {
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				StringBuilder sb = new StringBuilder("SELECT *FROM Product ");
				statement = conn.createStatement();

				if (!NameSearch.equals("") && TotalDueFrom != null && TotalDueTo != null) {
					sb.append(String.format("WHERE Name LIKE N'%%%s%%' AND Price >= %f AND Price <= %f", NameSearch,
							TotalDueFrom, TotalDueTo));

				} else if (!NameSearch.equals("") && (TotalDueFrom == null && TotalDueTo == null)) {
					sb.append(String.format("WHERE Name LIKE N'%%%s%%'", NameSearch));
				} else if (NameSearch.equals("") && (TotalDueFrom != null && TotalDueTo != null)) {
					sb.append(String.format("WHERE Price >= %f AND Price <= %f", TotalDueFrom, TotalDueTo));
				}
				System.out.println(sb.toString());
				resultSet = statement.executeQuery(sb.toString());
				while (resultSet.next()) {
					int productId = resultSet.getInt(1);
					int categoryId = resultSet.getInt(2);
					String name = resultSet.getString(3).trim();
					double totalDue = resultSet.getDouble(4);
					int quantity = resultSet.getInt(5);
					int status = resultSet.getInt(6);
					ProductEntity productEntity = new ProductEntity(productId, categoryId, name, totalDue, quantity,
							status);
					list.add(productEntity);
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

	public Integer count() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Integer result = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM Product";
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
