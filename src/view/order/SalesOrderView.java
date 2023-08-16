package view.order;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.SalesOrderController;
import dao.EmployeeDAO;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.PromotionEntity;
import entity.SalesOrderEntity;
import service.CustomerService;
import service.EmployeeService;
import service.PromotionService;
import service.SalesOrderService;
import utils.ExcelUtils;
import view.employee.EmployeeView;
import view.orderDetails.SalesOrderDetailView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

public class SalesOrderView extends JPanel {
	public JTable tableOrder;
	private JTextField textPriceFrom;
	private JTextField textPriceTo;
	private JTextField textPromotion;
	private JTextField textCustomer;
	private JTextField textOrderDate;
	private JTextField textTimeOrder;
	private JTextField textTotalDue;
	private List<SalesOrderEntity> list = new ArrayList<SalesOrderEntity>();
	private SalesOrderService salOrderService = new SalesOrderService();
	private DefaultTableModel tableOrderModel;
	public JButton btnShowDetails;
	public JButton btnClear;
	public JButton btnSearch;
	private JTextField textID;
	private JTextField textEmployee;
	private JDateChooser textDateFrom;
	private JDateChooser textDateTo;
	private SalesOrderController salOrderController = new SalesOrderController(this);
	public JButton btnExport;

	/**
	 * Create the panel.
	 */
	public SalesOrderView() {
		setBounds(new Rectangle(0, 0, 959, 619));
		setLayout(null);

		JPanel panelNgayLap = new JPanel();
		panelNgayLap.setLayout(null);
		panelNgayLap.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ng\u00E0y L\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNgayLap.setBounds(49, 27, 285, 79);
		add(panelNgayLap);

		textDateFrom = new JDateChooser();
		textDateFrom.setBounds(43, 23, 90, 35);
		textDateFrom.setDateFormatString("yyyy/MM/dd");
		panelNgayLap.add(textDateFrom);

		textDateTo = new JDateChooser();
		textDateTo.setBounds(185, 23, 90, 35);
		textDateTo.setDateFormatString("yyyy/MM/dd");
		panelNgayLap.add(textDateTo);

		JLabel lblDen = new JLabel("Từ :");
		lblDen.setBounds(10, 36, 45, 13);
		panelNgayLap.add(lblDen);
		lblDen.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1 = new JLabel("Đến :");
		lblNewLabel_1_1.setBounds(143, 36, 45, 13);
		panelNgayLap.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JPanel panelTongTien = new JPanel();
		panelTongTien.setLayout(null);
		panelTongTien.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u1ED5ng Ti\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTongTien.setBounds(344, 27, 206, 79);
		add(panelTongTien);

		JLabel lblTu = new JLabel("Từ :");
		lblTu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTu.setBounds(10, 36, 45, 13);
		panelTongTien.add(lblTu);

		JLabel lblNewLabel_1_1_1 = new JLabel("Đến :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(104, 36, 45, 13);
		panelTongTien.add(lblNewLabel_1_1_1);

		textPriceFrom = new JTextField();
		textPriceFrom.setBounds(41, 23, 57, 35);
		panelTongTien.add(textPriceFrom);
		textPriceFrom.setColumns(10);

		textPriceTo = new JTextField();
		textPriceTo.setColumns(10);
		textPriceTo.setBounds(142, 23, 57, 35);
		panelTongTien.add(textPriceTo);

		btnShowDetails = new JButton("Xem Chi Tiết");
		btnShowDetails.setBackground(Color.CYAN);
		btnShowDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnShowDetails.setIcon(new ImageIcon(SalesOrderView.class.getResource("/images/icons8_show_property_30px.png")));
		btnShowDetails.setBounds(765, 27, 150, 40);
		add(btnShowDetails);

		btnClear = new JButton("Làm Mới");
		btnClear.setBackground(Color.CYAN);
		btnClear.setIcon(new ImageIcon(SalesOrderView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBounds(765, 74, 150, 40);
		add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 123, 936, 201);
		add(scrollPane);

		tableOrder = new JTable();
		tableOrderModel = new DefaultTableModel(new Object[] { "STT", "M\u00E3 H\u00F3a \u0110\u01A1n",
				"Tên nhân viên", "Tên khách hàng", "Tên khuyễn mãi",
				"Ng\u00E0y L\u1EADp", "Gi\u1EDD L\u1EADp", "T\u1ED5ng Ti\u1EC1n" }, 0);
		tableOrder.setModel(tableOrderModel);
		tableOrder.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(tableOrder);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(7, 344, 939, 225);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã hóa đơn:");

		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên nhân viên:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textEmployee = new JTextField();
		textEmployee.setEditable(false);
		textEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textEmployee);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(25);
		box1.add(horizontalStrut_1);

		Box boxFullName_2 = Box.createHorizontalBox();
		box1.add(boxFullName_2);

		JLabel labelFullName_1 = new JLabel("Khuyến mãi:");
		labelFullName_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2.add(labelFullName_1);

		textPromotion = new JTextField();
		textPromotion.setEditable(false);
		textPromotion.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2.add(textPromotion);

		Component boxz = Box.createVerticalStrut(25);
		panelInformationEmployee.add(boxz);

		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng: ");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(lblTnKhchHng);

		textCustomer = new JTextField();
		textCustomer.setEditable(false);
		textCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(textCustomer);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1.add(horizontalStrut);

		Box box = Box.createHorizontalBox();
		box1_1.add(box);

		JLabel lblNgyLp = new JLabel("Ngày lập:");
		lblNgyLp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		box.add(lblNgyLp);

		textOrderDate = new JTextField();
		textOrderDate.setEditable(false);
		textOrderDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		box.add(textOrderDate);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(25);
		box1_1.add(horizontalStrut_1_1);

		Box boxFullName_2_1 = Box.createHorizontalBox();
		box1_1.add(boxFullName_2_1);

		JLabel labelFullName_1_1 = new JLabel("Giờ lập:");
		labelFullName_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2_1.add(labelFullName_1_1);

		textTimeOrder = new JTextField();
		textTimeOrder.setEditable(false);
		textTimeOrder.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2_1.add(textTimeOrder);

		Component boxz_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(boxz_1);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		JLabel lblTngTin = new JLabel("Tổng tiền:");
		lblTngTin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(lblTngTin);

		textTotalDue = new JTextField();
		textTotalDue.setEditable(false);
		textTotalDue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(textTotalDue);

		labelID.setPreferredSize(lblTnKhchHng.getPreferredSize());
		lblTngTin.setPreferredSize(lblTnKhchHng.getPreferredSize());
		lblNgyLp.setPreferredSize(lblTnKhchHng.getPreferredSize());
		labelFullName.setPreferredSize(lblTnKhchHng.getPreferredSize());

		labelFullName_1.setPreferredSize(lblTnKhchHng.getPreferredSize());
		labelFullName_1_1.setPreferredSize(lblTnKhchHng.getPreferredSize());

		btnSearch = new JButton("Tìm");
		btnSearch.setIcon(new ImageIcon(SalesOrderView.class.getResource("/images/icon_search.png")));
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSearch.setBackground(Color.CYAN);
		btnSearch.setBounds(603, 25, 150, 40);
		add(btnSearch);
		
		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(SalesOrderView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExport.setBounds(603, 74, 150, 40);
		add(btnExport);
		loadDatabase();
		btnShowDetails.addActionListener(salOrderController);
		btnClear.addActionListener(salOrderController);
		tableOrder.addMouseListener(salOrderController);
		btnSearch.addActionListener(salOrderController);
		btnExport.addActionListener(salOrderController);
	}

	public void handlerActionClear() {
		textTimeOrder.setText("");
		textOrderDate.setText("");
		textCustomer.setText("");
		textPriceFrom.setText("");
		textPriceTo.setText("");
		textPromotion.setText("");
		textTotalDue.setText("");
		textID.setText("");
		textEmployee.setText("");
		textDateFrom.setCalendar(null);
		textDateTo.setCalendar(null);
		tableOrderModel.setRowCount(0);
		loadDatabase();
	}

	public void mouseClick() {
		int select = tableOrder.getSelectedRow();
		if (select >= 0) {
			SalesOrderEntity salesOrder = list.get(select);
			EmployeeService emplService = new EmployeeService();
			CustomerService cusService = new CustomerService();
			PromotionService promoService = new PromotionService();
			
			EmployeeEntity empl = emplService.findById(salesOrder.getEmployeeId());
			CustomerEntity cus = cusService.findById(salesOrder.getCustomerId());
			PromotionEntity promotion = promoService.findById(salesOrder.getPromotionId());
			
			textID.setText(salesOrder.getSalesOrderID() + "");
			textEmployee.setText(empl.getFullName());
			textPromotion.setText(promotion.getPromotionName());
			textCustomer.setText(cus.getFullName());
			textOrderDate.setText(salesOrder.getOrderDate().toString());
			String time = (salesOrder.getOrderTime()).substring(0, 8);
			textTimeOrder.setText(time);
			DecimalFormat df = new DecimalFormat("#,### VND");
			textTotalDue.setText(df.format(salesOrder.getTotalDue()));
		}
	}

	public void handlerActionShowInfo() {
		int select = tableOrder.getSelectedRow();
		if (select >= 0) {
			SalesOrderEntity salesOrder = list.get(select);
			new SalesOrderDetailView(salesOrder);
		} else {
			JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn nào!");
		}
	}

	public void loadDatabase() {
		tableOrderModel.setRowCount(0);
		tableOrder.setRowSelectionAllowed(false);
		list = salOrderService.findAll();
		DecimalFormat df = new DecimalFormat("#,###Đ");
		EmployeeService emplService = new EmployeeService();
		CustomerService cusService = new CustomerService();
		PromotionService promoService = new PromotionService();
		int stt = 1;
		for (SalesOrderEntity salesOrder : list) {
			EmployeeEntity empl = emplService.findById(salesOrder.getEmployeeId());
			CustomerEntity cus = cusService.findById(salesOrder.getCustomerId());
			PromotionEntity promotion = promoService.findById(salesOrder.getPromotionId());
			tableOrderModel.addRow(new Object[] { stt++, salesOrder.getSalesOrderID(), empl.getFullName(),
					cus.getFullName(), promotion.getPromotionName(), salesOrder.getOrderDate(),
					salesOrder.getOrderTime(), df.format(salesOrder.getTotalDue()) });
		}
	}

	public void handlerActionFind() {
		Date startDate = null;
		Date endDate = null;
		Double priceFrom = null;
		Double priceTo = null;
		
		if (textDateFrom.getDate() == null && textPriceFrom.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày bắt đầu hoặc tổng tiền tối thiếu");
			return;
		}
		
		if (textDateFrom.getDate() != null && !textPriceFrom.getText().equals("")) {
			startDate = new Date(textDateFrom.getDate().getTime());
			if (textDateTo.getDate() != null) {
				if (textDateTo.getDate().getTime() < textDateFrom.getDate().getTime()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc sau ngày bắt đầu");
					return;
				} else {
					endDate = new Date(textDateTo.getDate().getTime());
				}
			} else {
				endDate = Date.valueOf(LocalDate.now());
			}

			try {
				priceFrom = Double.parseDouble(textPriceFrom.getText());
				if (!textPriceTo.getText().equals("")) {
					try {
						priceTo = Double.parseDouble(textPriceTo.getText());
						if (priceTo < priceFrom) {
							JOptionPane.showMessageDialog(null,
									"Vui lòng nhập tổng tiền tối đa lớn hơn hoặc tổng tiền tối thiểu");
							return;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối đa là số");
						return;
					}
				}

				if (priceFrom < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền là số nguyên dương");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối thiểu là số");
				return;
			}
		} else if (textDateFrom.getDate() != null && textPriceFrom.getText().equals("")) {
			startDate = new Date(textDateFrom.getDate().getTime());
			if (textDateTo.getDate() != null) {
				if (textDateTo.getDate().getTime() < textDateFrom.getDate().getTime()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc sau ngày bắt đầu");
					return;
				} else {
					endDate = new Date(textDateTo.getDate().getTime());
				}
			} else {
				endDate = Date.valueOf(LocalDate.now());
			}
		} else if (textDateFrom.getDate() == null && !textPriceFrom.getText().equals("")) {
			try {
				priceFrom = Double.parseDouble(textPriceFrom.getText());
				if (!textPriceTo.getText().equals("")) {
					try {
						priceTo = Double.parseDouble(textPriceTo.getText());
						if (priceTo < priceFrom) {
							JOptionPane.showMessageDialog(null,
									"Vui lòng nhập tổng tiền tối đa lớn hơn hoặc tổng tiền tối thiểu");
							return;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối đa là số");
						return;
					}
				}

				if (priceTo < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền là số nguyên dương");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối thiểu là số");
				return;
			}
		}
		
		list = salOrderService.findByStartDateAndEndDateAndPriceFromAndPriceTo(startDate, endDate, priceFrom, priceTo);
		tableOrderModel.setRowCount(0);
		
		EmployeeService emplService = new EmployeeService();
		CustomerService cusService = new CustomerService();
		PromotionService promoService = new PromotionService();
		DecimalFormat df = new DecimalFormat("#,###Đ");
		int stt = 1;
		for (SalesOrderEntity salesOrder : list) {
			EmployeeEntity empl = emplService.findById(salesOrder.getEmployeeId());
			CustomerEntity cus = cusService.findById(salesOrder.getCustomerId());
			PromotionEntity promotion = promoService.findById(salesOrder.getPromotionId());
			tableOrderModel.addRow(new Object[] { stt++, salesOrder.getSalesOrderID(), empl.getFullName(),
					cus.getFullName(), promotion.getPromotionName(), salesOrder.getOrderDate(),
					salesOrder.getOrderTime(), df.format(salesOrder.getTotalDue()) });
		}
		

	}
	public void handlerExport() {
		ExcelUtils.exportExcelSalesOrder(list);
		
	}
}
