package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.SalesOrderDetailEntity;

public class SalesOrderDetailDAO extends AbstractDAO{
	public List<SalesOrderDetailEntity> findByOrderId(int orderId) {
		List<SalesOrderDetailEntity> list = new ArrayList<SalesOrderDetailEntity>();
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if(connect != null) {
			try {
				String query = "SELECT * FROM SalesOrderDetail WHERE SalesOrderID = ?";
				statement = connect.prepareStatement(query);
				statement.setInt(1, orderId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					SalesOrderDetailEntity salesOrderDetail = new SalesOrderDetailEntity(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getDouble(5));
					
					list.add(salesOrderDetail);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.getStackTrace();
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
}
