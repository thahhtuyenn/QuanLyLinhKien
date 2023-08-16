package view.orderDetails;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.PurchaseOrderDetailController;
import entity.ProductEntity;
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import service.ProductService;
import service.PurchaseOrderDetailService;
import utils.PriceFormatter;

public class PurchaseOrderDetailView extends JFrame{
	
	private PuschaseOrderEntity puschaseOrder;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	public JButton btnExit;
	private List<PuschaseOrderDetailEntity> list;
	private PurchaseOrderDetailService service = new PurchaseOrderDetailService();
	private ProductService serviceProduct = new ProductService();
	
	public PurchaseOrderDetailView(PuschaseOrderEntity puschaseOrder) {
		this.puschaseOrder = puschaseOrder;
		System.out.println(puschaseOrder);
		setTitle("Chi tiết hóa đơn");
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		String[] cols = {"Stt", "Mã chi tiết hóa đơn", "Mã hóa đơn", "Tên sản phẩm", "Số lượng", "Thành tiền"};
		tableModel = new DefaultTableModel(cols, 0);
		contentPane.setLayout(null);
		table = new JTable(tableModel);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 10, 652, 253);
		
		contentPane.add(pane);
		setVisible(true);
		setContentPane(contentPane);
		
		btnExit = new JButton("Thoát");
		btnExit.setBackground(new Color(0, 255, 255));
		btnExit.setIcon(new ImageIcon(SalesOrderDetailView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExit.setBounds(295, 273, 113, 39);
		contentPane.add(btnExit);
		
		PurchaseOrderDetailController purchaseOrderDetailController = new PurchaseOrderDetailController(this);
		btnExit.addActionListener(purchaseOrderDetailController);
		loadDatabase();
	}
	
	public void loadDatabase() {
		list = new ArrayList<PuschaseOrderDetailEntity>();
		table.removeAll();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findByOrderId(puschaseOrder.getPuschaseOrderId());
		ProductEntity productEntity = null;
		int numberOrder = 1;
		for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : list) {
			productEntity = serviceProduct.findById(puschaseOrderDetailEntity.getProductId());
			tableModel.addRow(new Object[] {numberOrder++, puschaseOrderDetailEntity.getPuschaseOrderDetailId(), 
					puschaseOrderDetailEntity.getPuschaseOrderId(), productEntity.getName(),
					puschaseOrderDetailEntity.getOrderQuantity(), PriceFormatter.format(puschaseOrderDetailEntity.getSubTotal())});
		}
	}
	
	public void handlerActionExit() {
		this.dispose();
	}
}
