package view.statistics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.map.HashedMap;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import com.toedter.calendar.JDateChooser;

import controller.SalesStatisticsController;
import entity.BaseEntity;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import entity.PromotionEntity;
import interfaces.ISendData;
import service.SalesOrderService;
import utils.PriceFormatter;
import view.sales.ChooseCustomerView;
import view.sales.ChoosePromotionView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;

public class SalesStatisticsView extends JPanel {
	private JTable table;
	private JTextField textCountOrder;
	private JTextField textTotal;
	private JTextField textEmployee;
	private JTextField textCustomer;
	private JTextField textProduct;
	private List<BaseEntity> list = new ArrayList<BaseEntity>();
	private SalesOrderService salesOrderService = new SalesOrderService();
	private JDateChooser textDateFrom;
	private JDateChooser textDateTo;
	private DefaultTableModel tableModel;
	private double totalDue = 0;
	private int countOrder = 0;
	public JButton btnClear;
	private CustomerEntity customerEntity = null;
	private EmployeeEntity employeeEntity = null;
	private ProductEntity productEntity = null;
	public JButton btnChooseEmployee;
	public JButton btnChooseCustomer;
	public JButton btnChooseProduct;
	public JButton btnFind;

	public SalesStatisticsView() {
		setLayout(null);

		JPanel panelNgayLap = new JPanel();
		panelNgayLap.setLayout(null);
		panelNgayLap.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Ng\u00E0y L\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNgayLap.setBounds(10, 10, 293, 79);
		add(panelNgayLap);

		textDateFrom = new JDateChooser();
		textDateFrom.setDateFormatString("yyyy/MM/dd");
		textDateFrom.setBounds(43, 23, 90, 35);
		panelNgayLap.add(textDateFrom);

		textDateTo = new JDateChooser();
		textDateTo.setDateFormatString("yyyy/MM/dd");
		textDateTo.setBounds(185, 23, 90, 35);
		panelNgayLap.add(textDateTo);

		JLabel lblDen = new JLabel("Từ :");
		lblDen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDen.setBounds(10, 36, 45, 13);
		panelNgayLap.add(lblDen);

		JLabel lblNewLabel_1_1 = new JLabel("Đến :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(143, 36, 45, 13);
		panelNgayLap.add(lblNewLabel_1_1);

		tableModel = new DefaultTableModel(new Object[] { "Hóa đơn", "Tên nhân viên", "Tên khách hàng", "Ngày lập",
				"Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền" }, 0);
		table = new JTable(tableModel);
		table.setBounds(10, 111, 939, 430);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 107);
		scrollPane.setSize(917, 328);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBounds(10, 490, 422, 51);
		add(panel);
		panel.setLayout(new GridLayout(1, 2, 20, 20));

		textCountOrder = new JTextField();
		textCountOrder.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textCountOrder.setEditable(false);
		panel.add(textCountOrder);
		textCountOrder.setColumns(10);

		textTotal = new JTextField();
		textTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textTotal.setColumns(10);
		panel.add(textTotal);

		JLabel textTotalDue = new JLabel("Tổng thành tiền");
		textTotalDue.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textTotalDue.setBounds(232, 456, 137, 24);
		add(textTotalDue);

		JLabel vvvv = new JLabel("Tổng số hóa đơn");
		vvvv.setFont(new Font("Times New Roman", Font.BOLD, 20));
		vvvv.setBounds(10, 456, 160, 24);
		add(vvvv);

		textEmployee = new JTextField();
		textEmployee.setEditable(false);
		textEmployee.setColumns(10);
		textEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textEmployee.setBounds(312, 30, 142, 43);
		add(textEmployee);

		btnChooseEmployee = new JButton("New button");
		btnChooseEmployee.setBackground(Color.CYAN);
		btnChooseEmployee.setBounds(464, 36, 41, 37);
		add(btnChooseEmployee);

		textCustomer = new JTextField();
		textCustomer.setEditable(false);
		textCustomer.setColumns(10);
		textCustomer.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textCustomer.setBounds(516, 30, 142, 43);
		add(textCustomer);

		btnChooseCustomer = new JButton("New button");
		btnChooseCustomer.setBackground(Color.CYAN);
		btnChooseCustomer.setBounds(667, 36, 41, 37);
		add(btnChooseCustomer);

		textProduct = new JTextField();
		textProduct.setEditable(false);
		textProduct.setColumns(10);
		textProduct.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"S\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textProduct.setBounds(719, 30, 142, 43);
		add(textProduct);

		btnChooseProduct = new JButton("New button");
		btnChooseProduct.setBackground(Color.CYAN);
		btnChooseProduct.setBounds(870, 36, 41, 37);
		add(btnChooseProduct);

		btnClear = new JButton("Làm Mới");
		btnClear.setIcon(new ImageIcon(SalesStatisticsView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBackground(Color.CYAN);
		btnClear.setBounds(445, 490, 152, 51);
		add(btnClear);

		SalesStatisticsController salesStatisticsController = new SalesStatisticsController(this);

		btnFind = new JButton("Tìm kiếm");
		btnFind.setIcon(new ImageIcon(SalesStatisticsView.class.getResource("/images/icon_search.png")));
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		btnFind.setBounds(607, 490, 152, 51);
		add(btnFind);
		btnClear.addActionListener(salesStatisticsController);
		btnChooseCustomer.addActionListener(salesStatisticsController);
		btnChooseEmployee.addActionListener(salesStatisticsController);
		btnChooseProduct.addActionListener(salesStatisticsController);
		btnFind.addActionListener(salesStatisticsController);
		handlerActionClear();

	}

	public void showChooseCustomerView() {
		new ChooseCustomerView(new ISendData<CustomerEntity>() {

			@Override
			public void send(CustomerEntity o) {
				customerEntity = o;
				textCustomer.setText(customerEntity.getFullName());

			}
		}).setVisible(true);
	}

	public void showChooseEmployeeView() {
		new ChooseEmployeeView(new ISendData<EmployeeEntity>() {
			@Override
			public void send(EmployeeEntity o) {
				employeeEntity = o;
				textEmployee.setText(employeeEntity.getFullName());
			}
		}, 2).setVisible(true);
	}

	public void showChooseProductView() {
		new ChooseProductView(new ISendData<ProductEntity>() {
			@Override
			public void send(ProductEntity o) {
				productEntity = o;
				textProduct.setText(productEntity.getName());
			}
		}).setVisible(true);

	}

	public void handlerActionClear() {
		countOrder = 0;
		totalDue = 0;
		loadDatabase();
		textDateFrom.setCalendar(null);
		textDateTo.setCalendar(null);
		employeeEntity = null;
		customerEntity = null;
		textCustomer.setText("");
		textEmployee.setText("");
		textProduct.setText("");
		textCountOrder.setText(countOrder + "");
		textTotal.setText(PriceFormatter.format(totalDue));
	}

	public void loadDatabase() {
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = salesOrderService.statisticalAll(null);
		int idOld = -1;
		int index = 0;

		for (BaseEntity baseEntity : list) {
			if (idOld != baseEntity.getSalesOrderID()) {
				idOld = baseEntity.getSalesOrderID();
				tableModel.addRow(new Object[] { baseEntity.getSalesOrderID(), baseEntity.getEmployeeName(),
						baseEntity.getCustomerNameOrVendorName(), baseEntity.getOrderDate(), null, null, null, null });
				tableModel.addRow(new Object[] { null, null, null, null, baseEntity.getProductName(),
						baseEntity.getQuantityProduct(), PriceFormatter.format(baseEntity.getPriceProduct()),
						PriceFormatter.format(baseEntity.getTotalPrice()) });
			} else if (idOld == baseEntity.getSalesOrderID()) {
				tableModel.addRow(new Object[] { null, null, null, null, baseEntity.getProductName(),
						baseEntity.getQuantityProduct(), PriceFormatter.format(baseEntity.getPriceProduct()),
						PriceFormatter.format(baseEntity.getTotalPrice()) });
			}
			if (index < list.size() - 1) {
				if ((list.get(index + 1).getSalesOrderID() != baseEntity.getSalesOrderID())) {
					tableModel.addRow(new Object[] { null, null, null, null, null, null, "Tổng tiền",
							PriceFormatter.format(baseEntity.getTotalDue()) });
					totalDue += baseEntity.getTotalDue();
					countOrder++;
				}
			} else {
				tableModel.addRow(new Object[] { null, null, null, null, null, null, "Tổng tiền",
						PriceFormatter.format(baseEntity.getTotalDue()) });
				totalDue += baseEntity.getTotalDue();
				countOrder++;
			}
			index++; // tới vị trí tiếp theo
		}

	}

	public void handlerActionFind() {
		Map<Object, Object> searchs = new HashedMap<>();
		if (textDateFrom.getDate() != null && textDateTo.getDate() != null) {
			searchs.put("OrderDateFrom", new Date(textDateFrom.getDate().getTime()));
			searchs.put("OrderDateTo", new Date(textDateTo.getDate().getTime()));
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày để tìm kiếm");
			return;
		}

		if (customerEntity != null) {
			searchs.put("C.CustomerID", customerEntity.getCustomerId());
		}

		if (employeeEntity != null) {
			searchs.put("E.EmployeeID", employeeEntity.getEmployeeId());
		}

		if (productEntity != null) {
			searchs.put("P.ProductID", productEntity.getProductId());
		}

		list = salesOrderService.statisticalAll(searchs);
		countOrder = 0;
		totalDue = 0;
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		int idOld = -1;
		int index = 0;

		for (BaseEntity baseEntity : list) {
			if (idOld != baseEntity.getSalesOrderID()) {
				idOld = baseEntity.getSalesOrderID();
				tableModel.addRow(new Object[] { baseEntity.getSalesOrderID(), baseEntity.getEmployeeName(),
						baseEntity.getCustomerNameOrVendorName(), baseEntity.getOrderDate(), null, null, null, null });
				tableModel.addRow(new Object[] { null, null, null, null, baseEntity.getProductName(),
						baseEntity.getQuantityProduct(), PriceFormatter.format(baseEntity.getPriceProduct()),
						PriceFormatter.format(baseEntity.getTotalPrice()) });
			} else if (idOld == baseEntity.getSalesOrderID()) {
				tableModel.addRow(new Object[] { null, null, null, null, baseEntity.getProductName(),
						baseEntity.getQuantityProduct(), PriceFormatter.format(baseEntity.getPriceProduct()),
						PriceFormatter.format(baseEntity.getTotalPrice()) });
			}
			if (index < list.size() - 1) {
				if ((list.get(index + 1).getSalesOrderID() != baseEntity.getSalesOrderID())) {
					tableModel.addRow(new Object[] { null, null, null, null, null, null, "Tổng tiền",
							PriceFormatter.format(baseEntity.getTotalDue()) });
					totalDue += baseEntity.getTotalDue();
					countOrder++;
				}
			} else {
				tableModel.addRow(new Object[] { null, null, null, null, null, null, "Tổng tiền",
						PriceFormatter.format(baseEntity.getTotalDue()) });
				totalDue += baseEntity.getTotalDue();
				countOrder++;
			}
			index++; // tới vị trí tiếp theo
		}
		
		textCountOrder.setText(countOrder + "");
		textTotal.setText(PriceFormatter.format(totalDue));
	}

}
