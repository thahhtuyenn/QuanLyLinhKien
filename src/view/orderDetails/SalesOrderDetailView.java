package view.orderDetails;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.SalesOrderDetailController;
import entity.ProductEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;
import service.ProductService;
import service.SalesOrderDetailService;
import view.order.SalesOrderView;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class SalesOrderDetailView extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableOrderDetailModel;
	public JTable tableOrderDetail;
	public JButton btnExists;
	private SalesOrderDetailController slOrderDetailController = new SalesOrderDetailController(this);
	private SalesOrderDetailService saOrderDetailService = new SalesOrderDetailService();
	private List<SalesOrderDetailEntity> list = new ArrayList<SalesOrderDetailEntity>();
	private SalesOrderEntity salesOrder;

	/**
	 * Create the frame.
	 */
	public SalesOrderDetailView(SalesOrderEntity salesOrder) {
		this.salesOrder = salesOrder;
		setTitle("Chi tiết hóa đơn");
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		tableOrderDetailModel = new DefaultTableModel(new String[] {"Stt", "Mã chi tiết hóa đơn", "Mã hóa đơn", "Tên sản phẩm", "Số lượng", "Thành tiền"}, 0);
		contentPane.setLayout(null);
		tableOrderDetail = new JTable(tableOrderDetailModel);  
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tableOrderDetail.getColumnModel().getColumn(5).setCellRenderer(right);
		JScrollPane scTableOrderDetail = new JScrollPane(tableOrderDetail);
		scTableOrderDetail.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scTableOrderDetail.setBounds(10, 11, 661, 237);
		contentPane.add(scTableOrderDetail);

		setVisible(true);
		setContentPane(contentPane);
		
		btnExists = new JButton("Thoát");
		btnExists.setBackground(new Color(0, 255, 255));
		btnExists.setIcon(new ImageIcon(SalesOrderDetailView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExists.setBounds(276, 259, 126, 51);
		contentPane.add(btnExists);
		btnExists.addActionListener(slOrderDetailController);
		loadDatabase();
	}
	
	private void loadDatabase() {
		tableOrderDetailModel.setRowCount(0);
		list = saOrderDetailService.findByOrderId(salesOrder.getSalesOrderID());
		DecimalFormat df = new DecimalFormat("#, ###");
		ProductService productService = new ProductService();
		int stt =1;
		for (SalesOrderDetailEntity salesOrderDetailEntity : list) {
			ProductEntity product = productService.findById(salesOrderDetailEntity.getProductId());
			tableOrderDetailModel.addRow(new Object[] {stt++, salesOrderDetailEntity.getSalesOrderDetailId(), salesOrderDetailEntity.getSalesOrderId(), product.getName(), salesOrderDetailEntity.getOrderQuantity(), df.format(salesOrderDetailEntity.getSubTotal())});
			
		}
	}
	
	public void handlerActionExists() {
		this.dispose();
	}
}
