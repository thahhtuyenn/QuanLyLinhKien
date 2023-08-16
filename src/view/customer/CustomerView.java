package view.customer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.CustomerController;
import controller.EmployeeController;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.RoleEntity;
import service.CustomerService;
import utils.ExcelUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Component;

public class CustomerView extends JPanel {
	private JTextField textSearchFullName;
	private JTable table;
	private JTextField textAddress;
	private JTextField textPhone;
	private JTextField textStatus;
	private JTextField textSearchAddress;
	private List<CustomerEntity> list;
	private DefaultTableModel tableModel;
	public JButton btnAdd;
	private DefaultComboBoxModel<CustomerEntity> defaultComboBoxModel;
	private CustomerService customerService = new CustomerService();
	public JButton btnClear;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnFind;
	private JTextField textID;
	private JTextField textFullName;
	public JButton btnExport;
	private EmployeeEntity mEmployeeEntity;

	public CustomerView(EmployeeEntity employeeEntity) {
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
		btnAdd.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons8_add_30px.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setBackground(Color.CYAN);
		btnRemove.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons8_delete_forever_30px_1.png")));
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons8_support_30px.png")));
		panelControl.add(btnUpdate);

		btnFind = new JButton("Tìm");
		btnFind.setBackground(Color.CYAN);
		btnFind.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icon_search.png")));
		panelControl.add(btnFind);
		
		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(68, 51, 638, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên khách hàng");
		lblID.setBounds(10, 28, 106, 17);
		lblID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(lblID);

		textSearchFullName = new JTextField();
		textSearchFullName.setLocation(110, 19);
		textSearchFullName.setSize(new Dimension(213, 35));
		textSearchFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(textSearchFullName);
		textSearchFullName.setColumns(15);

		JLabel lblTn = new JLabel("Địa chỉ:");
		lblTn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTn.setBounds(333, 28, 60, 17);
		panelSearch.add(lblTn);

		textSearchAddress = new JTextField();
		textSearchAddress.setSize(new Dimension(171, 35));
		textSearchAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textSearchAddress.setColumns(15);
		textSearchAddress.setBounds(396, 19, 213, 35);
		panelSearch.add(textSearchAddress);

		btnClear = new JButton("Làm mới");
		btnClear.setBackground(Color.CYAN);
		btnClear.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBounds(740, 68, 155, 38);
		panelNorth.add(btnClear);

		Panel panel = new Panel();
		panel.setBounds(10, 155, 939, 244);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel(new String[] { "STT", "M\u00E3 Kh\u00E1ch h\u00E0ng", "Tên khách hàng",
				"\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Tr\u1EA1ng th\u00E1i" }, 7);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã khách hàng: ");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên khách hàng: ");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFullName = new JTextField();
		textFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textFullName);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);
		panelInformationEmployee.add(Box.createVerticalStrut(25));
		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxFullName_1 = Box.createHorizontalBox();
		box1_1.add(boxFullName_1);

		JLabel labelAddress = new JLabel("Địa chỉ: ");
		labelAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(labelAddress);

		textAddress = new JTextField();
		textAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(textAddress);

		Component verticalStrut = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		JLabel labelPhone = new JLabel("Số điện thoại: ");
		labelPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(labelPhone);

		textPhone = new JTextField();
		textPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(textPhone);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1_1.add(horizontalStrut);

		Box boxFullName_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxFullName_1_1);

		JLabel labelStatus = new JLabel("Trạng thái:");
		labelStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1.add(labelStatus);

		textStatus = new JTextField();
		textStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1.add(textStatus);
		labelPhone.setPreferredSize(labelID.getPreferredSize());
		labelAddress.setPreferredSize(labelFullName.getPreferredSize());
		labelStatus.setPreferredSize(labelFullName.getPreferredSize());

		CustomerController customerController = new CustomerController(this);
		btnAdd.addActionListener(customerController);
		btnClear.addActionListener(customerController);
		btnRemove.addActionListener(customerController);
		btnUpdate.addActionListener(customerController);
		btnFind.addActionListener(customerController);
		table.addMouseListener(customerController);
		btnExport.addActionListener(customerController);
		loadDatabase();
	}

	public void handlerActionClear() {
		textSearchFullName.setText("");
		textSearchAddress.setText("");
		textAddress.setText("");
		textPhone.setText("");
		textStatus.setText("");
		textID.setText("");
		textFullName.setText("");
		table.setRowSelectionAllowed(false);
		loadDatabase();
	}

	private void loadDatabase() {
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = customerService.findAll();

		int stt = 1;
		for (CustomerEntity customerEntity : list) {
			tableModel.addRow(new Object[] { stt++, customerEntity.getCustomerId(), customerEntity.getFullName(),
					customerEntity.getAddress(), customerEntity.getPhone(), customerEntity.getStatus() });

		}
	}

	public void handlerActionRemove() {
		if(mEmployeeEntity.getRoleId() != 2 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int index = table.getSelectedRow();
		if (index >= 0) {
			int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
			if (option == JOptionPane.CANCEL_OPTION && option == JOptionPane.NO_OPTION) {
				return;
			}
			String roleId = textID.getText();
			if (!roleId.equals("")) {
				int status = customerService.remove(Integer.valueOf(roleId));
				if (status != 0) {
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					handlerActionClear();
				} else {
					JOptionPane.showMessageDialog(this, "Xóa không thành công");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa");
		}
	}

	public void handlerActionUpdate() {
		if(mEmployeeEntity.getRoleId() != 2 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int index = table.getSelectedRow();
		if (index >= 0) {
			int id = list.get(index).getCustomerId();

			String fullName = textFullName.getText().trim();
			if (fullName.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên");
				return;
			}

			String address = textAddress.getText().trim();
			if (address.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
				return;
			}

			String phone = textPhone.getText().trim();
			if (phone.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
				return;
			}

			String status = textStatus.getText().trim();
			if (status.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái(0, 1)");
				return;
			}

			CustomerEntity customerEntity = new CustomerEntity(id, fullName, address, phone, Integer.valueOf(status));
			int status1 = customerService.updateOne(customerEntity);
			if (status1 != 0) {
				JOptionPane.showMessageDialog(this, "Sửa thành công");
				handlerActionClear();
			} else {
				JOptionPane.showMessageDialog(this, "Sửa không thành công");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
		}

	}

	public void actionShowInfo() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			CustomerEntity entity = list.get(index);
			textAddress.setText(entity.getAddress());
			textPhone.setText(entity.getPhone());
			textStatus.setText(entity.getStatus() + "");
			textID.setText(entity.getCustomerId() + "");
			textFullName.setText(entity.getFullName());
		}

	}

	public void showMessage(String messsage, JTextField componant) {
		JOptionPane.showMessageDialog(this, messsage);
		componant.requestFocus();
	}

	public void handlerActionAdd() {
		if(mEmployeeEntity.getRoleId() != 2 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String fullName = textFullName.getText().trim();
		if (fullName.equals("")) {
			showMessage("Vui lòng nhập họ và tên", textFullName);
			return;
		}

		String address = textAddress.getText().trim();
		if (address.equals("")) {
			showMessage("Vui lòng nhập địa chỉ", textAddress);
			return;
		}

		String phone = textPhone.getText().trim();
		if (phone.equals("")) {
			showMessage("Vui lòng nhập số điện thoại", textPhone);
			return;
		}

		String status = textStatus.getText().trim();
		if (status.equals("")) {
			showMessage("Vui lòng nhập trạng thái(0, 1)", textStatus);
			return;
		}

		CustomerEntity customerEntity = new CustomerEntity(fullName, address, phone, Integer.valueOf(status));
		customerEntity = customerService.insert(customerEntity);
		if (customerService != null) {
			showMessage("Thêm khách hàng mới thành công", textFullName);
		} else {
			showMessage("Thêm nhân viên mới thất bại", textFullName);
		}

		handlerActionClear();

	}

	public void handlerActionFind() {
		String fullName = textSearchFullName.getText().trim();
		String address = textSearchAddress.getText().trim();

		list = customerService.findByFullNameAndAddress(fullName, address);
		tableModel.setRowCount(0);
		int stt = 1;
		for (CustomerEntity customerEntity : list) {
			tableModel.addRow(new Object[] { stt++, customerEntity.getCustomerId(), customerEntity.getFullName(),
					customerEntity.getAddress(), customerEntity.getPhone(), customerEntity.getStatus() });

		}

	}

	public void handlerExport() {
		ExcelUtils.exportExcelCustomer(list);
	}

}
