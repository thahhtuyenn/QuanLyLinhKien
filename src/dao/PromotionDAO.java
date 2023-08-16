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

import entity.PromotionEntity;

public class PromotionDAO extends AbstractDAO {

	public int updateOne(PromotionEntity promotion) {
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "UPDATE Promotion \r\n" + "SET PromotionName = ?, \r\n" + "PromotionPercent = ?,\r\n"
						+ "StartDate = ?,\r\n" + "EndDate = ?\r\n" + "WHERE PromotionID = ?";
				statement = connect.prepareStatement(query);

				statement.setString(1, promotion.getPromotionName());
				statement.setDouble(2, promotion.getPromotionPercent());
				statement.setDate(3, promotion.getStartDate());
				statement.setDate(4, promotion.getEndDate());
				statement.setInt(5, promotion.getPromotionId());

				return statement.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.getSQLState();
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

		return 0;
	}

	public int removeOne(int promotionId) {
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM Promotion WHERE PromotionID = ?";
				statement = connect.prepareStatement(query);

				statement.setInt(1, promotionId);

				return statement.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.getSQLState();
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
		return 0;
	}

	public PromotionEntity insertOne(PromotionEntity promotion) {
		PromotionEntity entity = null;
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "INSERT INTO Promotion VALUES (?,?,?,?)";
				statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, promotion.getPromotionName());
				statement.setDouble(2, promotion.getPromotionPercent());
				statement.setDate(3, promotion.getStartDate());
				statement.setDate(4, promotion.getEndDate());

				statement.executeUpdate();

				resultSet = statement.getGeneratedKeys();

				while (resultSet.next()) {
					entity = new PromotionEntity();
					entity.setPromotionId(resultSet.getInt(1));
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.getSQLState();
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

		return entity;
	}

	public PromotionEntity findById(int promotionId) {
		PromotionEntity promotion = null;
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM Promotion WHERE PromotionID = ?");
				statement = connect.prepareStatement(query.toString());
				statement.setInt(1, promotionId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					promotion = new PromotionEntity(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
							resultSet.getDate(4), resultSet.getDate(5));
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
		return promotion;
	}

	public List<PromotionEntity> findByName(String promotionName) {
		List<PromotionEntity> list = new ArrayList<PromotionEntity>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM Promotion WHERE PromotionName LIKE N'%" + promotionName + "%'";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					PromotionEntity promotion = new PromotionEntity(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getDouble(3), resultSet.getDate(4), resultSet.getDate(5));
					list.add(promotion);
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
		return list;
	}
	
	public List<PromotionEntity> findAllPromotion(){
		List<PromotionEntity> list = new ArrayList<PromotionEntity>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if(connect != null) {
			try {
				String query = "SELECT * FROM Promotion WHERE EndDate is null OR (EndDate >= GETDATE() AND StartDate <= GETDATE())";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while(resultSet.next()) {
					PromotionEntity promotion = new PromotionEntity(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getDouble(3), resultSet.getDate(4), resultSet.getDate(5));
					list.add(promotion);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			}finally {
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
		return list;
	}

	public List<PromotionEntity> findAll() {
		List<PromotionEntity> list = new ArrayList<PromotionEntity>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM Promotion";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					PromotionEntity promotion = new PromotionEntity(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getDouble(3), resultSet.getDate(4), resultSet.getDate(5));
					list.add(promotion);
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
		return list;
	}
}
