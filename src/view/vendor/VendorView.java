package view.vendor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.VendorController;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.VendorEntity;
import service.VendorService;
import utils.ExcelUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Component;

public class VendorView extends JPanel {
	private JTextField textSearchName;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textAddress;
	private JTextField textPhone;
	private JTextField textFax;
	private JTextField textSearchAddress;
	private JTextField textID;
	private JTextField textFullName;
	public JButton btnAdd;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnFind;
	public JButton btnClear;
	private List<VendorEntity> list;
	private VendorService service = new VendorService();
	public JButton btnExport;
	private EmployeeEntity mEmployeeEntity;
	public VendorView(EmployeeEntity employeeEntity) {
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
		btnAdd.setIcon(new ImageIcon(VendorView.class.getResource("/images/icons8_add_30px.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setBackground(Color.CYAN);
		btnRemove.setIcon(new ImageIcon(VendorView.class.getResource("/images/icons8_delete_forever_30px_1.png")));
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(VendorView.class.getResource("/images/icons8_support_30px.png")));
		panelControl.add(btnUpdate);

		btnFind = new JButton("Tìm");
		btnFind.setIcon(new ImageIcon(VendorView.class.getResource("/images/icon_search.png")));
		btnFind.setBackground(Color.CYAN);
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnFind);

		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(VendorView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(65, 51, 637, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên nhà cung cấp:");
		lblID.setBounds(10, 28, 133, 17);
		lblID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(lblID);

		textSearchName = new JTextField();
		textSearchName.setLocation(153, 19);
		textSearchName.setSize(new Dimension(171, 35));
		textSearchName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(textSearchName);
		textSearchName.setColumns(15);

		JLabel lblTn = new JLabel("Địa chỉ:");
		lblTn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTn.setBounds(354, 28, 73, 17);
		panelSearch.add(lblTn);

		textSearchAddress = new JTextField();
		textSearchAddress.setSize(new Dimension(171, 35));
		textSearchAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textSearchAddress.setColumns(15);
		textSearchAddress.setBounds(437, 19, 171, 35);
		panelSearch.add(textSearchAddress);

		btnClear = new JButton("Làm mới");
		btnClear.setBackground(Color.CYAN);
		btnClear.setIcon(new ImageIcon(VendorView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClear.setBounds(712, 68, 155, 38);
		panelNorth.add(btnClear);

		Panel panel = new Panel();
		panel.setBounds(10, 155, 939, 244);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tableModel = new DefaultTableModel(new String[] { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp",
				"\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Số Fax" }, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh sách nhà cung cấp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã nhà cung cấp: ");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên nhà cung cấp:");
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
		boxID_1_1.add(textPhone);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1_1.add(horizontalStrut);

		Box boxFullName_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxFullName_1_1);

		JLabel labelFax = new JLabel("Số fax:");
		labelFax.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1_1.add(labelFax);

		textFax = new JTextField();
		boxFullName_1_1.add(textFax);
		labelPhone.setPreferredSize(labelID.getPreferredSize());
		labelAddress.setPreferredSize(labelFullName.getPreferredSize());
		labelFax.setPreferredSize(labelFullName.getPreferredSize());

		VendorController vendorController = new VendorController(this);
		table.addMouseListener(vendorController);
		btnAdd.addActionListener(vendorController);
		btnRemove.addActionListener(vendorController);
		btnUpdate.addActionListener(vendorController);
		btnFind.addActionListener(vendorController);
		btnClear.addActionListener(vendorController);
		btnExport.addActionListener(vendorController);

		loadDatabase();
	}

	public void loadDatabase() {
		list = new ArrayList<VendorEntity>();
		table.removeAll();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		int numberOrder = 1;
		for (VendorEntity vendorEntity : list) {
			tableModel.addRow(new Object[] { numberOrder++, vendorEntity.getVendorId(), vendorEntity.getVendorName(),
					vendorEntity.getAddress(), vendorEntity.getPhone(), vendorEntity.getFax() });
		}
	}

	public void handlerActionAdd() {
		if(mEmployeeEntity.getRoleId() != 4 && mEmployeeEntity.getRoleId() != 3) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String vendorName = textFullName.getText().trim();
		String address = textAddress.getText().trim();
		String phone = textPhone.getText().trim();
		String fax = textFax.getText().trim();

		if (checkData()) {
			VendorEntity vendorEntity = new VendorEntity(vendorName, address, phone, fax);
			vendorEntity = service.insert(vendorEntity);
			if (vendorEntity != null) {
				JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp mới thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp mới không thành công");
			}
			handlerActionClear();
		}
	}

	public void handlerActionRemove() {
		if(mEmployeeEntity.getRoleId() != 4 && mEmployeeEntity.getRoleId() != 3) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int row = table.getSelectedRow();
		if (row >= 0) {
			int option = JOptionPane.showConfirmDialog(null, "Xac nhan xoa", "Canh bao", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				String vendorID = textID.getText();
				if (!vendorID.equals("")) {
					if (service.remove(Integer.parseInt(vendorID)) != 0) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa không thành công");
					}
				}
				handlerActionClear();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
		}
	}

	public void handlerActionUpdate() {
		if(mEmployeeEntity.getRoleId() != 4 && mEmployeeEntity.getRoleId() != 3) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int row = table.getSelectedRow();
		if (row >= 0) {
			int vendorID = Integer.parseInt(textID.getText().trim());
			String vendorName = textFullName.getText().trim();
			String address = textAddress.getText().trim();
			String phone = textPhone.getText().trim();
			String fax = textFax.getText().trim();

			if (checkData()) {
				VendorEntity vendorEntity = new VendorEntity(vendorID, vendorName, address, phone, fax);
				if (service.update(vendorEntity) != 0) {
					JOptionPane.showMessageDialog(null, "Sửa thành công");
					handlerActionClear();
					loadDatabase();
				} else {
					JOptionPane.showMessageDialog(null, "Sửa không thành công");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa!");
		}

	}

	private boolean checkData() {
		String vendorName = textFullName.getText().trim();
		String address = textAddress.getText().trim();
		String phone = textPhone.getText().trim();
		String fax = textFax.getText().trim();
		
		Pattern p = Pattern.compile("[a-zA-Z ]+", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(vendorName);
		if (!(vendorName.length() > 0 && m.find())) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập họ và tên đúng dịnh dạng");
			return false;
		}

		p = Pattern.compile("[a-zA-Z0-9, ]+", Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(address);
		if (!(address.length() > 0 && m.find())) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ đúng dịnh dạng");
			return false;
		}

		if (!phone.matches("^0\\d{9}")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại gồm 10 số và bắt đầu từ số 0");
			return false;
		}

		if (!(fax.length() > 0 && fax.matches("^\\+84 \\(\\d\\)( \\d{4}){2}"))) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số fax đúng định dạng +84 (x) xxxx xxxx");
			return false;
		}
		return true;
	}

	public void handlerActionFind() {
		String name = textSearchName.getText().trim();
		String address = textSearchAddress.getText().trim();
		System.out.println(name);
		System.out.println(address);
		list = service.findByNameAndAddress(name, address);
		tableModel.setRowCount(0);
		int numberOrder = 1;
		for (VendorEntity vendorEntity : list) {
			tableModel.addRow(new Object[] { numberOrder++, vendorEntity.getVendorId(), vendorEntity.getVendorName(),
					vendorEntity.getAddress(), vendorEntity.getPhone(), vendorEntity.getFax() });
		}
	}

	public void handlerActionClear() {
		textID.setText("");
		textFullName.setText("");
		textAddress.setText("");
		textPhone.setText("");
		textFax.setText("");
		textSearchName.setText("");
		textSearchAddress.setText("");
		table.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void showInfor() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			table.setRowSelectionInterval(index, index);
			VendorEntity vendorEntity = list.get(index);
			textID.setText(vendorEntity.getVendorId() + "");
			textFullName.setText(vendorEntity.getVendorName());
			textAddress.setText(vendorEntity.getAddress());
			textPhone.setText(vendorEntity.getPhone());
			textFax.setText(vendorEntity.getFax());
		}
	}

	public void handlerExport() {
		ExcelUtils.exportExcelVendor(list);

	}
}
