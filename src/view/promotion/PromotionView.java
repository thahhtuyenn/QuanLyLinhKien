package view.promotion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.PromotionController;
import entity.EmployeeEntity;
import entity.PromotionEntity;
import service.PromotionService;
import utils.ExcelUtils;
import view.categoryProduct.CategoryProductView;
import view.employee.EmployeeView;

public class PromotionView extends JPanel {

	private JTextField textSearch;
	private JTable table;
	private JTextField textPercent;
	public JButton btnAdd;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnClear;
	private DefaultTableModel tableModel;
	private PromotionService promotionService = new PromotionService();
	private PromotionController promotionController = new PromotionController(this);
	private List<PromotionEntity> list = new ArrayList<PromotionEntity>();
	private JTextField textPromotionName;
	private JTextField textID;
	private JDateChooser dcEndDate;
	private JDateChooser dcStartDate;
	public JButton btnExport;
	public JButton btnSearch;
	private EmployeeEntity mEmployeeEntity;

	/**
	 * Create the panel.
	 */
	public PromotionView(EmployeeEntity employeeEntity) {
		this.mEmployeeEntity = employeeEntity;
		setSize(959, 619);
		setLayout(null);

		JPanel panelNorth = new JPanel();
		panelNorth.setBounds(0, 0, 959, 116);
		add(panelNorth);
		panelNorth.setLayout(null);

		JPanel panelControl = new JPanel();
		panelControl.setBounds(0, 0, 959, 49);
		panelNorth.add(panelControl);

		btnAdd = new JButton("Thêm");
		btnAdd.setBackground(Color.CYAN);
		btnAdd.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_add_30px.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setBackground(Color.CYAN);
		btnRemove.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_delete_forever_30px_1.png")));
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_support_30px.png")));
		panelControl.add(btnUpdate);

		btnSearch = new JButton("Tìm");
		btnSearch.setIcon(new ImageIcon(PromotionView.class.getResource("/images/icon_search.png")));
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSearch.setBackground(Color.CYAN);
		panelControl.add(btnSearch);

		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(PromotionView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(78, 51, 562, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên khuyến mãi:");
		lblID.setBounds(10, 28, 93, 17);
		lblID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(lblID);

		textSearch = new JTextField();
		textSearch.setLocation(104, 19);
		textSearch.setSize(new Dimension(448, 35));
		textSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(textSearch);
		textSearch.setColumns(15);

		btnClear = new JButton("Làm mới");
		btnClear.setBackground(Color.CYAN);
		btnClear.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBounds(707, 68, 155, 38);
		panelNorth.add(btnClear);

		Panel panel = new Panel();
		panel.setBounds(10, 155, 939, 244);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel(new String[] { "STT", "Mã khuyến mãi", "Tên khuyến mãi",
				"Phần trăm khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc" }, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut_1);

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã khuyến mãi:");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên khuyến mãi:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPromotionName = new JTextField();
		textPromotionName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textPromotionName);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);

		Component verticalStrut_1_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut_1_1);

		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		Box boxFullName_1 = Box.createHorizontalBox();
		box1_1.add(boxFullName_1);

		JLabel lblPhnTrm = new JLabel("Phần trăm: ");
		lblPhnTrm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(lblPhnTrm);

		lblPhnTrm.setPreferredSize(labelFullName.getPreferredSize());

		textPercent = new JTextField();
		textPercent.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(textPercent);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut_1_1_1);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		JLabel lblNgyBtu = new JLabel("Ngày bắt đầu:");
		lblNgyBtu.setPreferredSize(new Dimension(90, 17));
		lblNgyBtu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(lblNgyBtu);

		dcStartDate = new JDateChooser();
		boxID_1_1.add(dcStartDate);
		dcStartDate.setDateFormatString("yyyy/MM/dd");

		Component horizontalStrut_1 = Box.createHorizontalStrut(25);
		box1_1_1.add(horizontalStrut_1);

		Box boxFullName_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxFullName_1_1);

		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc:");
		lblNgyKtThc.setPreferredSize(new Dimension(94, 17));
		lblNgyKtThc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1.add(lblNgyKtThc);

		dcEndDate = new JDateChooser();
		boxFullName_1_1.add(dcEndDate);
		dcEndDate.setDateFormatString("yyyy/MM/dd");
		loadDatabase();

		table.addMouseListener(promotionController);
		btnAdd.addActionListener(promotionController);
		btnClear.addActionListener(promotionController);
		btnRemove.addActionListener(promotionController);
		btnUpdate.addActionListener(promotionController);
		btnSearch.addActionListener(promotionController);
		btnExport.addActionListener(promotionController);
	}

	public void handlerActionSearch() {
		String txSearch = textSearch.getText().trim();
		if (!txSearch.equals("")) {
			tableModel.setRowCount(0);
			list = promotionService.findByName(txSearch);
			int stt = 1;
			for (PromotionEntity promotionEntity : list) {
				tableModel.addRow(new Object[] { stt++, promotionEntity.getPromotionId(),
						promotionEntity.getPromotionName(), promotionEntity.getPromotionPercent(),
						promotionEntity.getStartDate(), promotionEntity.getEndDate() });
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên khuyến mãi cần tìm!");
		}
	}

	public void loadDatabase() {
		tableModel.setRowCount(0);
		list = promotionService.findAll();
		int stt = 1;
		for (PromotionEntity promotionEntity : list) {
			tableModel.addRow(new Object[] { stt++, promotionEntity.getPromotionId(),
					promotionEntity.getPromotionName(), promotionEntity.getPromotionPercent(),
					promotionEntity.getStartDate(), promotionEntity.getEndDate() });
		}
	}

	public void handlerActionClear() {
		textID.setText("");
		textPercent.setText("");
		textPromotionName.setText("");
		textSearch.setText("");
		dcStartDate.setCalendar(null);
		dcEndDate.setCalendar(null);
		loadDatabase();
	}

	public void handlerActionUpdate() {
		if(mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int select = table.getSelectedRow();
		if (select >= 0) {
			PromotionEntity promotion = list.get(select);
			int idNew = Integer.parseInt(textID.getText().trim());
			if (idNew == promotion.getPromotionId()) {
				String name = textPromotionName.getText().trim();
				double percent = Double.parseDouble(textPercent.getText().trim());
				Date startDate = new Date(dcStartDate.getDate().getTime());
				Date endDate = new Date(dcEndDate.getDate().getTime());

				PromotionEntity promotionNew = new PromotionEntity(idNew, name, percent, startDate, endDate);
				int status = promotionService.updateOne(promotionNew);
				if (status > 0) {
					handlerActionClear();
					JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Không thể sửa mã khuyến mãi!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi cần sửa!");
		}
	}

	public void handlerActionRemove() {
		if(mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int select = table.getSelectedRow();
		if (select >= 0) {
			PromotionEntity promotion = list.get(select);
			System.out.println(promotion);
			int status = promotionService.removeOne(promotion.getPromotionId());
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thành công!");
				handlerActionClear();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi cần xóa!");
		}
	}

	public void handlerActionAdd() {
		if(mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String name, percent;

		name = textPromotionName.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên khuyến mãi không được rỗng!");
			return;
		}

		percent = textPercent.getText().trim();
		if (percent.equals("")) {
			JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi không được rỗng!");
			return;
		}

		double pt = 0;
		try {
			pt = Double.parseDouble(percent);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi là số dương!");
			return;
		}

		if (pt < 0) {
			JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi phải >= 0!");
			return;
		}

		Date startDate = null, endDate = null;
		if (dcStartDate.getDate() != null) {
			startDate = new Date(dcStartDate.getDate().getTime());
		}

		if (dcEndDate.getDate() != null) {
			endDate = new Date(dcEndDate.getDate().getTime());
		}
		
		Date curentDate = new Date(System.currentTimeMillis());
		
		if(startDate.before(curentDate)) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu khuyến mãi phải sau ngày hiện tại!");
			return;
		}
		
		if(endDate.before(startDate)) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc khuyến mãi phải sau ngày bắt đầu!");
			return;
		}

		PromotionEntity proEntity = new PromotionEntity(name, pt, startDate, endDate);

		if (promotionService.insertOne(proEntity) != null) {
			handlerActionClear();
			JOptionPane.showMessageDialog(this, "Thêm thành công 1 khuyễn mãi!");
		} else {
			JOptionPane.showMessageDialog(this, "Thêm khuyến mãi không thành công!");
		}
	}

	public void mouseClick() {
		int select = table.getSelectedRow();
		if (select >= 0) {
			PromotionEntity promotion = list.get(select);
			textID.setText(promotion.getPromotionId() + "");
			textPromotionName.setText(promotion.getPromotionName());
			textPercent.setText(promotion.getPromotionPercent() + "");
			dcStartDate.setDate(promotion.getStartDate());
			dcEndDate.setDate(promotion.getEndDate());
		}
	}

	public void handlerExport() {
		ExcelUtils.exportExcelPromotion(list);
	}
}
