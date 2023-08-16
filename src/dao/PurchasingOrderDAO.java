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
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;

public class PurchasingOrderDAO extends AbstractDAO {
	public List<PuschaseOrderEntity> findAll() {
		List<PuschaseOrderEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM PuschaseOrder";
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int puschaseOrderID, vendorID, employeeID;
					Date orderDate;
					String orderTime;
					double totalDue;
					puschaseOrderID = resultSet.getInt(1);
					vendorID = resultSet.getInt(2);
					employeeID = resultSet.getInt(3);
					orderDate = resultSet.getDate(4);
					orderTime = resultSet.getString(5).trim();
					totalDue = resultSet.getDouble(6);
					PuschaseOrderEntity puschaseOrderEntity = new PuschaseOrderEntity(puschaseOrderID, vendorID,
							employeeID, orderDate, orderTime, totalDue);
					list.add(puschaseOrderEntity);
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

	public List<PuschaseOrderEntity> findByOrderStartDateAndEndDate(Date startDate, Date endDate) {
		List<PuschaseOrderEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELEct * FROM PuschaseOrder WHERE OrderDate BETWEEN ? AND ?";

				statement = connect.prepareStatement(query);
				statement.setDate(1, startDate);
				statement.setDate(2, endDate);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int puschaseOrderId = resultSet.getInt(1);
					int vendorId = resultSet.getInt(2);
					int employeeId = resultSet.getInt(3);
					Date orderDate = resultSet.getDate(4);
					String orderTime = resultSet.getString(5);
					double totalDue = resultSet.getDouble(6);
					PuschaseOrderEntity entity = new PuschaseOrderEntity(puschaseOrderId, vendorId, employeeId,
							orderDate, orderTime, totalDue);
					list.add(entity);
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

	public List<PuschaseOrderEntity> findByTotalDueStartAndTotalDueEnd(Double startPrice, Double endPrice) {
		List<PuschaseOrderEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM PuschaseOrder WHERE TotalDue >= ? ");
				if (endPrice != null && endPrice >= startPrice) {
					query.append("AND TotalDue <= ?");
				}
				statement = connect.prepareStatement(query.toString());
				statement.setDouble(1, startPrice);

				if (endPrice != null && endPrice >= startPrice) {
					statement.setDouble(2, endPrice);
				}

				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int puschaseOrderId = resultSet.getInt(1);
					int vendorId = resultSet.getInt(2);
					int employeeId = resultSet.getInt(3);
					Date orderDate = resultSet.getDate(4);
					String orderTime = resultSet.getString(5);
					double totalDue = resultSet.getDouble(6);
					PuschaseOrderEntity entity = new PuschaseOrderEntity(puschaseOrderId, vendorId, employeeId,
							orderDate, orderTime, totalDue);
					list.add(entity);
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

	public List<PuschaseOrderEntity> findByDateAndPrice(Date startDate, Date endDate, Double startPrice,
			Double endPrice) {
		List<PuschaseOrderEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM PuschaseOrder WHERE " + "OrderDate BETWEEN ? AND ? AND TotalDue >= ? ";
				if (endPrice != null && endPrice >= startPrice) {
					query += "AND TotalDue <= ?";
				}
				statement = connect.prepareStatement(query);
				statement.setDate(1, startDate);
				statement.setDate(2, endDate);
				statement.setDouble(3, startPrice);
				if (endPrice != null && endPrice >= startPrice) {
					statement.setDouble(4, endPrice);
				}
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int puschaseOrderId = resultSet.getInt(1);
					int vendorId = resultSet.getInt(2);
					int employeeId = resultSet.getInt(3);
					Date orderDate = resultSet.getDate(4);
					String orderTime = resultSet.getString(5);
					double totalDue = resultSet.getDouble(6);
					PuschaseOrderEntity entity = new PuschaseOrderEntity(puschaseOrderId, vendorId, employeeId,
							orderDate, orderTime, totalDue);
					list.add(entity);
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
							" WHERE PO.PuschaseOrderID IN\r\n" + "(\r\n"
									+ "	SELECT PO.PuschaseOrderID  FROM PuschaseOrder AS PO\r\n"
									+ "	JOIN PuschaseOrderDetail AS POD\r\n"
									+ "	ON PO.PuschaseOrderID = POD.PuschaseOrderID\r\n" + "	JOIN Employee AS E\r\n"
									+ "	ON E.EmployeeID = PO.EmployeeID\r\n" + "	JOIN Vendor AS V\r\n"
									+ "	ON V.VendorID = PO.VendorID\r\n" + "	JOIN Product AS P\r\n"
									+ "	ON P.ProductID = POD.ProductID\r\n"
									+ "	WHERE PO.OrderDate >= '%s' AND PO.OrderDate <= '%s' ",
							searchs.get("OrderDateFrom"), searchs.get("OrderDateTo")));

					Set<Object> set = searchs.keySet();
					for (Object key : set) {
						if (!key.equals("OrderDateFrom") && !key.equals("OrderDateTo")) {
							sb.append(" AND " + key + " = " + searchs.get(key));
						}
					}
					sb.append(" ) ");
				}
				String query = "SELECT PO.PuschaseOrderID, E.FullName, V.VendorName, PO.OrderDate, \r\n"
						+ "P.Name, POD.OrderQuantity, P.Price, POD.SubTotal,\r\n" + "PO.TotalDue "
						+ "FROM PuschaseOrder AS PO\r\n" + "JOIN PuschaseOrderDetail AS POD\r\n"
						+ "ON PO.PuschaseOrderID = POD.PuschaseOrderID\r\n" + "JOIN Employee AS E\r\n"
						+ "ON E.EmployeeID = PO.EmployeeID\r\n" + "JOIN Vendor AS V\r\n"
						+ "ON V.VendorID = PO.VendorID\r\n" + "JOIN Product AS P\r\n"
						+ "ON P.ProductID = POD.ProductID\r\n" + sb.toString() + "ORDER BY PO.PuschaseOrderID";
				System.out.println(query);
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int salesOrderID = resultSet.getInt(1);
					String employeeName = resultSet.getString(2);
					String vendorName = resultSet.getString(3);
					String orderDate = resultSet.getString(4);
					String productName = resultSet.getString(5);
					int quantityProduct = resultSet.getInt(6);
					double priceProduct = resultSet.getDouble(7);
					double totalPrice = resultSet.getDouble(8);
					double totalDue = resultSet.getDouble(9);

					list.add(new BaseEntity(salesOrderID, employeeName, vendorName, productName, quantityProduct,
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

	public Integer insertOne(PuschaseOrderEntity puschaseOrderEntity,
			List<PuschaseOrderDetailEntity> listDetailEntity) {
		Integer id = null;
		Connection connect = getConnection();
		PreparedStatement statementPurchaseOrder = null;
		PreparedStatement statementDetails = null;
		ResultSet resultSetOrder = null;
		ResultSet resultSetDetail = null;
		if (connect != null) {
			try {
				connect.setAutoCommit(false);
				String queryOrder = "INSERT INTO PuschaseOrder (VendorID, EmployeeID ,TotalDue)\r\n"
						+ "VALUES(?, ?, ?)";
				statementPurchaseOrder = connect.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);
				statementPurchaseOrder.setInt(1, puschaseOrderEntity.getVendorId());
				statementPurchaseOrder.setInt(2, puschaseOrderEntity.getEmployeeId());
				statementPurchaseOrder.setDouble(3, puschaseOrderEntity.getTotalDue());
				statementPurchaseOrder.executeUpdate();

				resultSetOrder = statementPurchaseOrder.getGeneratedKeys();
				while (resultSetOrder.next()) {
					id = resultSetOrder.getInt(1);
				}

				String queryDetail = "";

				for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : listDetailEntity) {
					queryDetail = "INSERT INTO PuschaseOrderDetail (PuschaseOrderID, ProductID, OrderQuantity, SubTotal)\r\n"
							+ "VALUES (?, ?, ?, ?)\r\n";
					statementDetails = connect.prepareStatement(queryDetail, Statement.RETURN_GENERATED_KEYS);
					statementDetails.setInt(1, id);
					statementDetails.setInt(2, puschaseOrderDetailEntity.getProductId());
					statementDetails.setInt(3, puschaseOrderDetailEntity.getOrderQuantity());
					statementDetails.setDouble(4, puschaseOrderDetailEntity.getSubTotal());
					statementDetails.executeUpdate();

					resultSetDetail = statementPurchaseOrder.getGeneratedKeys();
					while (resultSetDetail.next()) {
						puschaseOrderDetailEntity.setPuschaseOrderDetailId(resultSetDetail.getInt(1));
					}
				}
				connect.commit();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
				try {
					connect.rollback();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
					e1.printStackTrace();
				}
			} finally {
				try {
					connect.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (statementPurchaseOrder != null) {
					try {
						statementPurchaseOrder.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (statementDetails != null) {
					try {
						statementDetails.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSetOrder != null) {
					try {
						resultSetOrder.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (resultSetDetail != null) {
					try {
						resultSetDetail.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return id;
	}

	public PuschaseOrderEntity findById(int id) {
		PuschaseOrderEntity puschaseOrderEntity = null;
		Connection connect = getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM PuschaseOrder WHERE PuschaseOrderId = " + id;
				statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					int puschaseOrderID, vendorID, employeeID;
					Date orderDate;
					String orderTime;
					double totalDue;
					puschaseOrderID = resultSet.getInt(1);
					vendorID = resultSet.getInt(2);
					employeeID = resultSet.getInt(3);
					orderDate = resultSet.getDate(4);
					orderTime = resultSet.getString(5).trim();
					totalDue = resultSet.getDouble(6);
					puschaseOrderEntity = new PuschaseOrderEntity(puschaseOrderID, vendorID,
							employeeID, orderDate, orderTime, totalDue);
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
		return puschaseOrderEntity;
	}
}
