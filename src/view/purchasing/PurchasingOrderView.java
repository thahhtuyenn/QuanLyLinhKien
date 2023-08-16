package view.purchasing;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.PurchasingOrderController;
import entity.EmployeeEntity;
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import entity.VendorEntity;
import service.EmployeeService;
import service.PurchasingOrderService;
import service.VendorService;
import utils.ExcelUtils;
import view.order.SalesOrderView;
import view.orderDetails.PurchaseOrderDetailView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PurchasingOrderView extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textPriceFrom;
	private JTextField textPriceTo;
	private JTextField textEmployee;
	private JTextField textOrderDate;
	private JTextField textTimeOrder;
	private JTextField textTotalDue;
	private JTextField textVendor;
	private JTextField textID;
	private JDateChooser textDateFrom;
	private JDateChooser textDateTo;
	private List<PuschaseOrderEntity> list;
	private PurchasingOrderService service = new PurchasingOrderService();
	private VendorService serviceVendor = new VendorService();
	private EmployeeService serviceEmployee = new EmployeeService();
	public JButton btnShowDetails;
	public JButton btnExport;
	public JButton btnClear;
	public JButton btnSearch;

	/**
	 * Create the panel.
	 */
	public PurchasingOrderView() {
		setBounds(new Rectangle(0, 0, 959, 619));
		setLayout(null);

		JPanel panelNgayLap = new JPanel();
		panelNgayLap.setLayout(null);
		panelNgayLap.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ng\u00E0y L\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNgayLap.setBounds(38, 27, 302, 79);
		add(panelNgayLap);

		textDateFrom = new JDateChooser();
		textDateFrom.setDateFormatString("yyyy/MM/dd");
		textDateFrom.setBounds(43, 23, 90, 35);
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
		panelTongTien.setBounds(369, 27, 226, 79);
		add(panelTongTien);

		JLabel lblTu = new JLabel("Từ :");
		lblTu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTu.setBounds(10, 36, 45, 13);
		panelTongTien.add(lblTu);

		JLabel lblNewLabel_1_1_1 = new JLabel("Đến :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(113, 36, 45, 13);
		panelTongTien.add(lblNewLabel_1_1_1);

		textPriceFrom = new JTextField();
		textPriceFrom.setBounds(41, 23, 63, 35);
		panelTongTien.add(textPriceFrom);
		textPriceFrom.setColumns(10);

		textPriceTo = new JTextField();
		textPriceTo.setColumns(10);
		textPriceTo.setBounds(147, 23, 63, 35);
		panelTongTien.add(textPriceTo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 936, 201);
		add(scrollPane);

		String[] cols = { "STT", "M\u00E3 Phi\u1EBFu Nh\u1EADp", "Tên Nh\u00E0 Cung C\u1EA5p",
				"Tên Nh\u00E2n Vi\u00EAn", "Ng\u00E0y Nh\u1EADp", "Gi\u1EDD Nh\u1EADp", "T\u1ED5ng Ti\u1EC1n" };
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

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
		JLabel labelID = new JLabel("Mã phiếu nhập:");

		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Nhà cung cấp:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textVendor = new JTextField();
		textVendor.setEditable(false);
		textVendor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textVendor);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(25);
		box1.add(horizontalStrut_1);

		Box boxFullName_2 = Box.createHorizontalBox();
		box1.add(boxFullName_2);

		JLabel labelFullName_1 = new JLabel("Nhân viên:");
		labelFullName_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2.add(labelFullName_1);

		textEmployee = new JTextField();
		textEmployee.setEditable(false);
		textEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_2.add(textEmployee);

		Component boxz = Box.createVerticalStrut(25);
		panelInformationEmployee.add(boxz);

		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

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

		PurchasingOrderController purchasingOrderController = new PurchasingOrderController(this);

		btnSearch = new JButton("Tìm");
		btnSearch.setIcon(new ImageIcon(PurchasingOrderView.class.getResource("/images/icon_search.png")));
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSearch.setBackground(Color.CYAN);
		btnSearch.setBounds(636, 18, 150, 40);
		add(btnSearch);

		btnClear = new JButton("Làm Mới");
		btnClear.setIcon(new ImageIcon(PurchasingOrderView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBackground(Color.CYAN);
		btnClear.setBounds(796, 66, 150, 40);
		add(btnClear);

		btnExport = new JButton("Xuất");
		btnExport.setIcon(new ImageIcon(PurchasingOrderView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExport.setBackground(Color.CYAN);
		btnExport.setBounds(636, 67, 150, 40);
		add(btnExport);

		btnShowDetails = new JButton("Xem Chi Tiết");
		btnShowDetails
				.setIcon(new ImageIcon(PurchasingOrderView.class.getResource("/images/icons8_show_property_30px.png")));
		btnShowDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnShowDetails.setBackground(Color.CYAN);
		btnShowDetails.setBounds(796, 18, 150, 40);
		add(btnShowDetails);

		table.addMouseListener(purchasingOrderController);
		btnSearch.addActionListener(purchasingOrderController);
		btnClear.addActionListener(purchasingOrderController);
		btnExport.addActionListener(purchasingOrderController);
		btnShowDetails.addActionListener(purchasingOrderController);
		loadDatabase();
	}

	public void loadDatabase() {
		table.removeAll();
		list = new ArrayList<PuschaseOrderEntity>();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		VendorEntity vendorEntity = null;
		EmployeeEntity employeeEntity = null;
		DecimalFormat df = new DecimalFormat("#,###");
		int numberOrder = 1;
		for (PuschaseOrderEntity puschaseOrderEntity : list) {
			vendorEntity = serviceVendor.findById(puschaseOrderEntity.getVendorId());
			employeeEntity = serviceEmployee.findById(puschaseOrderEntity.getEmployeeId());
			tableModel.addRow(new Object[] { numberOrder++, puschaseOrderEntity.getPuschaseOrderId(),
					vendorEntity.getVendorName(), employeeEntity.getFullName(), puschaseOrderEntity.getOrderDate(),
					puschaseOrderEntity.getOrderTime(), df.format(puschaseOrderEntity.getTotalDue()) });
		}
	}

	public void handlerActionShowDetail() {
		int select = table.getSelectedRow();
		if (select >= 0) {
			PuschaseOrderEntity entity = list.get(select);
			new PurchaseOrderDetailView(entity);
		} else {
			JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn nào!");
		}
	}

	public void handlerActionFind() {
		loadDatabase();
		if (textDateFrom.getDate() == null && textPriceFrom.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày bắt đầu hoặc tổng tiền tối thiếu");
			return;
		} else {
			// Tim theo ngay va don gia
			table.removeAll();
			list = new ArrayList<PuschaseOrderEntity>();
			tableModel.setRowCount(0);
			table.setRowSelectionAllowed(false);
			Date startDate = null;
			Date endDate = null;
			Double startPrice = null;
			Double endPrice = null;

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
					startPrice = Double.parseDouble(textPriceFrom.getText());
					if (!textPriceTo.getText().equals("")) {
						try {
							endPrice = Double.parseDouble(textPriceTo.getText());
							if (endPrice < startPrice) {
								JOptionPane.showMessageDialog(null,
										"Vui lòng nhập tổng tiền tối đa lớn hơn hoặc tổng tiền tối thiểu");
								return;
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối đa là số");
							return;
						}
					}

					if (startPrice < 0) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền là số nguyên dương");
						return;
					} else {
						list = service.findByDateAndPrice(startDate, endDate, startPrice, endPrice);
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
				list = service.findByOrderStartDateAndEndDate(startDate, endDate);
			} else if (textDateFrom.getDate() == null && !textPriceFrom.getText().equals("")) {
				try {
					startPrice = Double.parseDouble(textPriceFrom.getText());
					if (!textPriceTo.getText().equals("")) {
						try {
							endPrice = Double.parseDouble(textPriceTo.getText());
							if (endPrice < startPrice) {
								JOptionPane.showMessageDialog(null,
										"Vui lòng nhập tổng tiền tối đa lớn hơn hoặc tổng tiền tối thiểu");
								return;
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối đa là số");
							return;
						}
					}

					if (startPrice < 0) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền là số nguyên dương");
						return;
					} else {
						list = service.findByTotalDueStartAndTotalDueEnd(startPrice, endPrice);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tổng tiền tối thiểu là số");
					return;
				}
			}

			VendorEntity vendorEntity = null;
			EmployeeEntity employeeEntity = null;
			DecimalFormat df = new DecimalFormat("#,###");
			int numberOrder = 1;
			for (PuschaseOrderEntity puschaseOrderEntity : list) {
				vendorEntity = serviceVendor.findById(puschaseOrderEntity.getVendorId());
				employeeEntity = serviceEmployee.findById(puschaseOrderEntity.getEmployeeId());
				tableModel.addRow(new Object[] { numberOrder++, puschaseOrderEntity.getPuschaseOrderId(),
						vendorEntity.getVendorName(), employeeEntity.getFullName(),
						puschaseOrderEntity.getOrderDate(), puschaseOrderEntity.getOrderTime(),
						df.format(puschaseOrderEntity.getTotalDue()) });
			}
		}
	}

	public void handlerActionClean() {
		loadDatabase();
		textID.setText("");
		textVendor.setText("");
		textEmployee.setText("");
		textOrderDate.setText("");
		textTimeOrder.setText("");
		textTotalDue.setText("");
		textPriceFrom.setText("");
		textPriceTo.setText("");
		textDateFrom.setCalendar(null);
		textDateTo.setCalendar(null);
	}

	public void showInfor() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			PuschaseOrderEntity entity = list.get(row);
			VendorEntity vendorEntity = serviceVendor.findById(entity.getVendorId());
			EmployeeEntity employeeEntity = serviceEmployee.findById(entity.getEmployeeId());
			textID.setText(String.valueOf(entity.getPuschaseOrderId()));
			textVendor.setText(vendorEntity.getVendorName());
			textEmployee.setText(employeeEntity.getFullName());
			textOrderDate.setText(String.valueOf(entity.getOrderDate()));
			String time = (entity.getOrderTime()).substring(0, 8);
			textTimeOrder.setText(time);
			DecimalFormat df = new DecimalFormat("#,### VND");
			textTotalDue.setText(df.format(entity.getTotalDue()));
		}
	}

	public void handlerExport() {
		ExcelUtils.exportExcelPurchaseOrder(list);
	}
}
