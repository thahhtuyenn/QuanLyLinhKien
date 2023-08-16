package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.PuschaseOrderDetailEntity;

public class PurchaseOrderDetailDAO extends AbstractDAO{
	public List<PuschaseOrderDetailEntity> findByOrderId(int id){
		List<PuschaseOrderDetailEntity> list = new ArrayList<>();
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if(connect != null) {
			try {
				String query = "SELECT * FROM PuschaseOrderDetail WHERE PuschaseOrderID = ?";
				statement = connect.prepareStatement(query);
				statement.setInt(1, id);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int puschaseOrderDetailId, puschaseOrderId, productId, orderQuantity;
					double subTotal;
					puschaseOrderDetailId = resultSet.getInt(1);
					puschaseOrderId = resultSet.getInt(2);
					productId = resultSet.getInt(3);
					orderQuantity = resultSet.getInt(4);
					subTotal = resultSet.getDouble(5);
					PuschaseOrderDetailEntity entity = new PuschaseOrderDetailEntity(puschaseOrderDetailId, puschaseOrderId, productId, orderQuantity, subTotal);
					list.add(entity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
				e.printStackTrace();
			} finally {
				if(connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(resultSet != null) {
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
}
