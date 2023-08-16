package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public abstract class AbstractDAO {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost;database=QuanLyLinhKien;";
			String userName = "sa";
			String password = "010803";
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
