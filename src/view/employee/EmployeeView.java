package view.employee;

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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.EmployeeController;
import entity.EmployeeEntity;
import entity.RoleEntity;
import service.EmployeeService;
import service.RoleService;
import utils.ExcelUtils;
import utils.PasswordUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.ListSelectionModel;
import java.awt.Component;

public class EmployeeView extends JPanel {
	private JTextField textSearchName;
	private JTable table;
	private JTextField textAddress;
	private JTextField textPhone;
	private JTextField texUserName;
	private JTextField textPassword;
	public JButton btnAdd;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnFind;
	private JDateChooser textDateFrom;
	private JDateChooser textDateTo;
	private DefaultTableModel tableModel;
	public JButton btnClear;
	private List<EmployeeEntity> list;
	private EmployeeService service = new EmployeeService();
	private RoleService roleService = new RoleService();
	private JTextField textID;
	private JTextField textFullName;
	private JDateChooser textBirthDate;
	private DefaultComboBoxModel<RoleEntity> defaultComboBoxModel;
	private List<RoleEntity> roles;
	private JComboBox<RoleEntity> roleComboBox;
	private JComboBox<String> emComboBox;
	public JButton btnExport;
	private DefaultComboBoxModel<String> dfcbbEmployee;
	private EmployeeEntity mEmployeeEntity;

	public EmployeeView(EmployeeEntity employeeEntity) {
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

		btnFind = new JButton("Tìm");
		btnFind.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icon_search.png")));
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		panelControl.add(btnFind);
		
		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(78, 51, 303, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên nhân viên:");
		lblID.setBounds(10, 28, 93, 17);
		lblID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(lblID);

		textSearchName = new JTextField();
		textSearchName.setLocation(101, 19);
		textSearchName.setSize(new Dimension(171, 35));
		textSearchName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(textSearchName);
		textSearchName.setColumns(15);

		JPanel panelSearch_1 = new JPanel();
		panelSearch_1.setLayout(null);
		panelSearch_1.setPreferredSize(new Dimension(10, 50));
		panelSearch_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ng\u00E0y sinh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch_1.setBounds(391, 51, 357, 65);
		panelNorth.add(panelSearch_1);

		textDateFrom = new JDateChooser();
		textDateFrom.setBounds(43, 20, 125, 35);
		textDateFrom.setDateFormatString("yyyy/MM/dd");
		panelSearch_1.add(textDateFrom);

		JLabel lblNewLabel = new JLabel("Từ:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 30, 35, 13);
		panelSearch_1.add(lblNewLabel);

		textDateTo = new JDateChooser();
		textDateTo.setBounds(222, 20, 125, 35);
		textDateTo.setDateFormatString("yyyy/MM/dd");
		panelSearch_1.add(textDateTo);

		JLabel lbln = new JLabel("Đến:");
		lbln.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbln.setBounds(181, 30, 35, 13);
		panelSearch_1.add(lbln);

		btnClear = new JButton("Làm mới");
		btnClear.setBackground(Color.CYAN);
		btnClear.setIcon(new ImageIcon(EmployeeView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBounds(758, 68, 155, 38);
		panelNorth.add(btnClear);

		Panel panel = new Panel();
		panel.setBounds(10, 155, 939, 244);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel(new String[] { "STT", "M\u00E3 nh\u00E2n vi\u00EAn",
				"T\u00EAn nh\u00E2n vi\u00EAn", "Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9",
				"S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Tr\u1EA1ng th\u00E1i", "Tài khoản", "Mật khẩu", "Mã quyền" }, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã nhân viên: ");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên nhân viên: ");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFullName = new JTextField();
		textFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textFullName);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);
		panelInformationEmployee.add(Box.createVerticalStrut(15));
		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		JLabel labelBirthDate = new JLabel("Ngày sinh: ");
		labelBirthDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(labelBirthDate);

		textBirthDate = new JDateChooser();
		textBirthDate.setDate(new Date(System.currentTimeMillis()));
		textBirthDate.setDateFormatString("yyyy/MM/dd");
		boxID_1.add(textBirthDate);

		Box boxFullName_1 = Box.createHorizontalBox();
		box1_1.add(Box.createHorizontalStrut(25));
		box1_1.add(boxFullName_1);

		JLabel labelAddress = new JLabel("Địa chỉ: ");
		labelAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(labelAddress);

		textAddress = new JTextField();
		textAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(textAddress);

		Component verticalStrut = Box.createVerticalStrut(15);
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
		
		dfcbbEmployee = new DefaultComboBoxModel<>(new String[] {"Đang làm", "Đã nghỉ"});
		emComboBox = new JComboBox<String>(dfcbbEmployee);
		emComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1.add(emComboBox);

		labelBirthDate.setPreferredSize(labelID.getPreferredSize());
		labelPhone.setPreferredSize(labelID.getPreferredSize());
		labelAddress.setPreferredSize(labelFullName.getPreferredSize());
		labelStatus.setPreferredSize(labelFullName.getPreferredSize());

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		panelInformationEmployee.add(verticalStrut_1);

		Box box1_1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1_1);

		Box boxID_1_1_1 = Box.createHorizontalBox();
		box1_1_1_1.add(boxID_1_1_1);

		JLabel labelTaiKhoan = new JLabel("Tài khoản:");
		labelTaiKhoan.setPreferredSize(new Dimension(83, 17));
		labelTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1_1.add(labelTaiKhoan);

		texUserName = new JTextField();
		texUserName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1_1.add(texUserName);

		Component horizontalStrut_1 = Box.createHorizontalStrut(25);
		box1_1_1_1.add(horizontalStrut_1);

		Box boxFullName_1_1_1 = Box.createHorizontalBox();
		box1_1_1_1.add(boxFullName_1_1_1);

		JLabel labelMatKhau = new JLabel("Mật khẩu:");
		labelMatKhau.setPreferredSize(new Dimension(87, 17));
		labelMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1_1.add(labelMatKhau);

		textPassword = new JTextField();
		textPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1_1.add(textPassword);

		Component verticalStrut_1_1 = Box.createVerticalStrut(15);
		panelInformationEmployee.add(verticalStrut_1_1);

		Box box1_1_1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1_1_1);

		Box boxID_1_1_1_1 = Box.createHorizontalBox();
		box1_1_1_1_1.add(boxID_1_1_1_1);

		JLabel labelAc = new JLabel("Quyền");
		labelAc.setPreferredSize(new Dimension(83, 17));
		labelAc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1_1_1.add(labelAc);

		roles = roleService.findAll();

		defaultComboBoxModel = new DefaultComboBoxModel<RoleEntity>();
		defaultComboBoxModel.addAll(roles);
		roleComboBox = new JComboBox<RoleEntity>(defaultComboBoxModel);
		roleComboBox.setSelectedIndex(0);
		boxID_1_1_1_1.add(roleComboBox);

		Box boxFullName_1_1_1_1 = Box.createHorizontalBox();
		box1_1_1_1_1.add(boxFullName_1_1_1_1);

		EmployeeController employeeController = new EmployeeController(this);
		btnAdd.addActionListener(employeeController);
		btnClear.addActionListener(employeeController);
		btnFind.addActionListener(employeeController);
		btnRemove.addActionListener(employeeController);
		btnUpdate.addActionListener(employeeController);
		table.addMouseListener(employeeController);
		btnExport.addActionListener(employeeController);
		loadDatabase();
	}

	public void loadDatabase() {
		list = new ArrayList<EmployeeEntity>();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		int stt = 1;
		for (EmployeeEntity employeeEntity : list) {
			tableModel.addRow(new Object[] { stt++, employeeEntity.getEmployeeId(), employeeEntity.getFullName(),
					employeeEntity.getBirthDate(), employeeEntity.getAddress(), employeeEntity.getPhone(),
					(employeeEntity.getStatus() == 1 ? "Đang làm" : "Đã nghỉ"), employeeEntity.getUserName(), employeeEntity.getPassword(),
					employeeEntity.getRoleId() });

		}
	}

	public void handlerActionAdd() {
		if(mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String fullName = textFullName.getText().trim();
		if (fullName.equals("")) {
			showMessage("Vui lòng nhập họ và tên", textFullName);
			return;
		}

		Date birthDate = new Date(textBirthDate.getDate().getTime());

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

		int status = ((String) emComboBox.getSelectedItem()).equals("Đang làm") == true ? 1 : 0;

		String userName = texUserName.getText().trim();
		if (userName.equals("")) {
			showMessage("Vui lòng nhập tài khoản", texUserName);
			return;
		}

		String password = textPassword.getText().trim();
		if (password.equals("")) {
			showMessage("Vui lòng nhập mật khẩu", textPassword);
			return;
		}

		RoleEntity roleChoose = (RoleEntity) this.roleComboBox.getSelectedItem();
		int roleId = roleChoose.getRoleId();

		EmployeeEntity employeeEntity = new EmployeeEntity(fullName, birthDate, address, phone, status,
				userName, password, roleId);
		employeeEntity = service.insert(employeeEntity);
		if (employeeEntity != null) {
			showMessage("Thêm nhân viên mới thành công", texUserName);
		} else {
			showMessage("Thêm nhân viên mới thất bại", texUserName);
		}

		handlerActionClear();

	}

	public void showMessage(String messsage, JTextField componant) {
		JOptionPane.showMessageDialog(this, messsage);
		componant.requestFocus();
	}

	public void handlerActionClear() {
		textSearchName.setText("");
		textBirthDate.setDate(new Date(System.currentTimeMillis()));
		textAddress.setText("");
		textPhone.setText("");
		texUserName.setText("");
		textPassword.setText("");
		textID.setText("");
		textFullName.setText("");
		emComboBox.setSelectedItem("Đang làm");
		roleComboBox.setSelectedIndex(0);
		textDateFrom.setCalendar(null);
		textDateTo.setCalendar(null);
		loadDatabase();
	}

	public void handlerActionRemove() {
		if(mEmployeeEntity.getRoleId() != 4) {
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
				int status = service.remove(Integer.valueOf(roleId));
				if (status != 0) {
					showMessage("Xóa thành công", textID);
					handlerActionClear();
				} else {
					showMessage("Xóa không thành công", textID);
				}
			}
		} else {
			showMessage("Vui lòng chọn dòng để xóa", textID);
		}
	}

	public void handlerActionFind() {
		
		String fullName = textSearchName.getText().trim();
		Date startDate = null;
		Date endDate = null;
		if (fullName.equals("")) {
			if (textDateFrom.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu");
				return;
			}
			if (textDateTo.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc");
				return;
			}
		}
		if (textDateFrom.getDate() != null) {
			startDate = new Date(textDateFrom.getDate().getTime());
		}
		if (textDateFrom.getDate() != null) {
			endDate = new Date(textDateTo.getDate().getTime());
		}

		list = service.findByFullNameAndStartDateAndEndDate(fullName, startDate, endDate);
		tableModel.setRowCount(0);
		int stt = 1;
		for (EmployeeEntity employeeEntity : list) {
			tableModel.addRow(new Object[] { stt++, employeeEntity.getEmployeeId(), employeeEntity.getFullName(),
					employeeEntity.getBirthDate(), employeeEntity.getAddress(), employeeEntity.getPhone(),
					employeeEntity.getStatus(), employeeEntity.getUserName(), employeeEntity.getPassword(),
					employeeEntity.getRoleId() });

		}

	}

	public void handlerActionUpdate() {
		if(mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int index = table.getSelectedRow();
		if (index >= 0) {
			int id = list.get(index).getEmployeeId();

			String fullName = textFullName.getText().trim();
			if (fullName.equals("")) {
				showMessage("Vui lòng nhập họ và tên", textFullName);
				return;
			}

			Date birthDate = new Date(textBirthDate.getDate().getTime());

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

			int status = ((String) emComboBox.getSelectedItem()).equals("Đang làm") == true ? 1 : 0;

			String userName = texUserName.getText().trim();
			if (userName.equals("")) {
				showMessage("Vui lòng nhập tài khoản", texUserName);
				return;
			}

			String password = textPassword.getText().trim();
			if (password.equals("")) {
				showMessage("Vui lòng nhập mật khẩu", textPassword);
				return;
			}

			RoleEntity roleChoose = (RoleEntity) this.roleComboBox.getSelectedItem();
			int roleId = roleChoose.getRoleId();

			EmployeeEntity employeeEntity = new EmployeeEntity(id, fullName, birthDate, address, phone,
					status, userName, password, roleId);
			System.out.println(employeeEntity);
			int status1 = service.updateOne(employeeEntity);
			if (status1 != 0) {
				showMessage("Sửa thành công", texUserName);
				handlerActionClear();
			} else {
				showMessage("Sửa không thành công", texUserName);
			}
		} else {
			showMessage("Vui lòng chọn dòng để sửa", texUserName);
		}

	}

	public void actionShowInfo() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			EmployeeEntity entity = list.get(index);
			textBirthDate.setDate(entity.getBirthDate());
			textAddress.setText(entity.getAddress());
			textPhone.setText(entity.getPhone());
			emComboBox.setSelectedItem(entity.getStatus() == 1 ? "Đang làm" : "Đã nghỉ");
			texUserName.setText(entity.getUserName());
			textPassword.setText(entity.getPassword());
			textID.setText(entity.getEmployeeId() + "");
			textFullName.setText(entity.getFullName());
			RoleEntity roleEntitySelected = roleService.findById(entity.getRoleId());
			roleComboBox.setSelectedItem(roleEntitySelected);
		}

	}
	public void handlerExport() {
		ExcelUtils.exportExcelEmployee(list);
	}
}
