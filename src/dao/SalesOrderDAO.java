package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import entity.BaseEntity;
import entity.EmployeeEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;

public class SalesOrderDAO extends AbstractDAO {
	public List<SalesOrderEntity> findAll() {
		List<SalesOrderEntity> list = new ArrayList<SalesOrderEntity>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM SalesOrder";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int salesOrderId, employeeId, customerId, promotionId;
					Date orderDate;
					String orderTime;
					double totalDue;
					salesOrderId = resultSet.getInt(1);
					employeeId = resultSet.getInt(2);
					customerId = resultSet.getInt(3);
					promotionId = resultSet.getInt(4);
					orderDate = resultSet.getDate(5);
					orderTime = resultSet.getString(6);
					totalDue = resultSet.getDouble(7);
					SalesOrderEntity salesOrder = new SalesOrderEntity(salesOrderId, employeeId, customerId,
							promotionId, orderDate, orderTime, totalDue);
					list.add(salesOrder);
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

	public SalesOrderEntity findById(int id) {
		SalesOrderEntity salesOrder = null;
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM SalesOrder WHERE SalesOrderID = " + id;
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int salesOrderId, employeeId, customerId, promotionId;
					Date orderDate;
					String orderTime;
					double totalDue;
					salesOrderId = resultSet.getInt(1);
					employeeId = resultSet.getInt(2);
					customerId = resultSet.getInt(3);
					promotionId = resultSet.getInt(4);
					orderDate = resultSet.getDate(5);
					orderTime = resultSet.getString(6);
					totalDue = resultSet.getDouble(7);
					salesOrder = new SalesOrderEntity(salesOrderId, employeeId, customerId, promotionId, orderDate,
							orderTime, totalDue);
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

		return salesOrder;
	}

	public SalesOrderEntity insertOne(SalesOrderEntity salesOrder) {
		SalesOrderEntity salesOrderRedult = null;
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO SalesOrder VALUES (?,?,?,?,?,?,?)";
				statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, salesOrder.getSalesOrderID());
				statement.setInt(2, salesOrder.getEmployeeId());
				statement.setInt(3, salesOrder.getCustomerId());
				statement.setInt(4, salesOrder.getPromotionId());
				statement.setDate(5, salesOrder.getOrderDate());
				statement.setString(6, salesOrder.getOrderTime());
				statement.setDouble(7, salesOrder.getTotalDue());

				statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					salesOrderRedult = new SalesOrderEntity();
					salesOrderRedult.setSalesOrderID(resultSet.getInt(1));
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
		return salesOrderRedult;
	}

	public List<SalesOrderEntity> findByStartDateAndEndDateAndPriceFromAndPriceTo(Date startDate, Date endDate,
			Double priceFrom, Double priceTo) {

		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(priceFrom);
		System.out.println(priceTo);

		List<SalesOrderEntity> list = new ArrayList<SalesOrderEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM SalesOrder WHERE OrderDate >= ? AND OrderDate <= ? AND TotalDue >= ? AND  TotalDue <= ?";

				statement = conn.prepareStatement(query);
				statement.setDate(1, startDate);
				statement.setDate(2, endDate);
				statement.setDouble(3, priceFrom);
				statement.setDouble(4, priceTo);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {

					int salesOrderId, employeeId, customerId, promotionId;
					Date orderDate;
					String orderTime;
					double totalDue;
					salesOrderId = resultSet.getInt(1);
					employeeId = resultSet.getInt(2);
					customerId = resultSet.getInt(3);
					promotionId = resultSet.getInt(4);
					orderDate = resultSet.getDate(5);
					orderTime = resultSet.getString(6);
					totalDue = resultSet.getDouble(7);
					SalesOrderEntity salesOrder = new SalesOrderEntity(salesOrderId, employeeId, customerId,
							promotionId, orderDate, orderTime, totalDue);
					list.add(salesOrder);

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

	public List<SalesOrderEntity> findByPriceFromAndPriceTo(Double priceFrom, Double priceTo) {
		List<SalesOrderEntity> list = new ArrayList<SalesOrderEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM SalesOrder WHERE TotalDue >= ? AND  TotalDue <= ?";

				statement = conn.prepareStatement(query);
				statement.setDouble(1, priceFrom);
				statement.setDouble(2, priceTo);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {

					int salesOrderId, employeeId, customerId, promotionId;
					Date orderDate;
					String orderTime;
					double totalDue;
					salesOrderId = resultSet.getInt(1);
					employeeId = resultSet.getInt(2);
					customerId = resultSet.getInt(3);
					promotionId = resultSet.getInt(4);
					orderDate = resultSet.getDate(5);
					orderTime = resultSet.getString(6);
					totalDue = resultSet.getDouble(7);
					SalesOrderEntity salesOrder = new SalesOrderEntity(salesOrderId, employeeId, customerId,
							promotionId, orderDate, orderTime, totalDue);
					list.add(salesOrder);

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

	public List<SalesOrderEntity> findByStartDateAndEndDate(Date startDate, Date endDate) {
		List<SalesOrderEntity> list = new ArrayList<SalesOrderEntity>();
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM SalesOrder WHERE OrderDate >= ? AND OrderDate <= ?";

				statement = conn.prepareStatement(query);
				statement.setDate(1, startDate);
				statement.setDate(2, endDate);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {

					int salesOrderId, employeeId, customerId, promotionId;
					Date orderDate;
					String orderTime;
					double totalDue;
					salesOrderId = resultSet.getInt(1);
					employeeId = resultSet.getInt(2);
					customerId = resultSet.getInt(3);
					promotionId = resultSet.getInt(4);
					orderDate = resultSet.getDate(5);
					orderTime = resultSet.getString(6);
					totalDue = resultSet.getDouble(7);
					SalesOrderEntity salesOrder = new SalesOrderEntity(salesOrderId, employeeId, customerId,
							promotionId, orderDate, orderTime, totalDue);
					list.add(salesOrder);

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

	public Integer insertOne(SalesOrderEntity salesOrderEntity, List<SalesOrderDetailEntity> listDetailEntities) {
		Integer id = null;
		Connection conn = getConnection();
		PreparedStatement statementSalesOrder = null;
		PreparedStatement statementDetails = null;
		ResultSet resultSet = null;

		if (conn != null) {
			try {

				conn.setAutoCommit(false);

				String queryOrder = "INSERT INTO [dbo].[SalesOrder]([EmployeeID] ,[CustomerID] ,[PromotionID] ,[TotalDue])\r\n"
						+ "VALUES(?, ?, ?, ?)\r\n";
				statementSalesOrder = conn.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);
				statementSalesOrder.setInt(1, salesOrderEntity.getEmployeeId());
				statementSalesOrder.setInt(2, salesOrderEntity.getCustomerId());
				statementSalesOrder.setInt(3, salesOrderEntity.getPromotionId());
				statementSalesOrder.setDouble(4, salesOrderEntity.getTotalDue());
				statementSalesOrder.executeUpdate();

				resultSet = statementSalesOrder.getGeneratedKeys();
				while (resultSet.next()) {
					id = resultSet.getInt(1);
				}

				StringBuilder queryDetails = new StringBuilder("");
				for (SalesOrderDetailEntity salesOrderDetailEntity : listDetailEntities) {
					queryDetails.append(String.format(
							"INSERT INTO [dbo].[SalesOrderDetail]([SalesOrderID],[ProductID],[OrderQuantity],[SubTotal])\r\n"
									+ "VALUES(%d, %d, %d, %f)\r\n",
							id, salesOrderDetailEntity.getProductId(), salesOrderDetailEntity.getOrderQuantity(),
							salesOrderDetailEntity.getSubTotal()));
				}
				statementDetails = conn.prepareStatement(queryDetails.toString());

				statementDetails.executeUpdate();

				conn.commit();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
					e1.printStackTrace();
				}
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statementSalesOrder != null) {
					try {
						statementSalesOrder.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statementDetails != null) {
					try {
						statementDetails.close();
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

		return id;
	}

	public List<BaseEntity> statisticalAll(Map<Object, Object> searchs) {
		List<BaseEntity> list = new ArrayList<BaseEntity>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		if (connect != null) {
			try {
				StringBuilder sb = new StringBuilder(" ");
				if (searchs != null) {
					sb.append(String.format(
							"WHERE SO.SalesOrderID \r\n" + "IN \r\n" + "(\r\n" + "	SELECT SO.SalesOrderID\r\n"
									+ "	FROM SalesOrder AS SO\r\n" + "	JOIN SalesOrderDetail AS SOD\r\n"
									+ "	ON SO.SalesOrderID = SOD.SalesOrderID\r\n" + "	JOIN Product AS P\r\n"
									+ "	ON P.ProductID = SOD.ProductID\r\n" + "	JOIN Customer AS C\r\n"
									+ "	ON C.CustomerID = SO.CustomerID\r\n" + "	JOIN Employee AS E\r\n"
									+ "	ON E.EmployeeID = SO.EmployeeID\r\n"
									+ "	WHERE SO.OrderDate >= '%s' AND SO.OrderDate <= '%s' " + "\r\n",
							searchs.get("OrderDateFrom"), searchs.get("OrderDateTo")));

					Set<Object> set = searchs.keySet();
					for (Object key : set) {
						if (!key.equals("OrderDateFrom") && !key.equals("OrderDateTo")) {
							sb.append(" AND " + key + " = " + searchs.get(key));
						}
					}
					sb.append(" ) ");
				}
				String query = "SELECT SO.SalesOrderID, E.FullName, C.FullName, P.Name, \r\n"
						+ "SO.OrderDate, SOD.OrderQuantity, P.Price, SOD.SubTotal, SO.TotalDue\r\n"
						+ "FROM SalesOrder AS SO\r\n" + "JOIN SalesOrderDetail AS SOD\r\n"
						+ "ON SO.SalesOrderID = SOD.SalesOrderID\r\n" + "JOIN Product AS P\r\n"
						+ "ON P.ProductID = SOD.ProductID\r\n" + "JOIN Customer AS C\r\n"
						+ "ON C.CustomerID = SO.CustomerID\r\n" + "JOIN Employee AS E\r\n"
						+ "ON E.EmployeeID = SO.EmployeeID\r\n" + sb.toString() + "ORDER BY SO.SalesOrderID ";
				System.out.println(query);
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int salesOrderID = resultSet.getInt(1);
					String employeeName = resultSet.getString(2);
					String customerName = resultSet.getString(3);
					String productName = resultSet.getString(4);
					int quantityProduct = resultSet.getInt(6);
					String orderDate = resultSet.getString(5);
					double priceProduct = resultSet.getDouble(7);
					double totalPrice = resultSet.getDouble(8);
					double totalDue = resultSet.getDouble(9);

					list.add(new BaseEntity(salesOrderID, employeeName, customerName, productName, quantityProduct,
							orderDate, priceProduct, totalPrice, totalDue));

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
