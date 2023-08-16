package view.role;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

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

import controller.RoleController;
import entity.RoleEntity;
import service.RoleService;
import utils.ExcelUtils;
import view.employee.EmployeeView;

public class RolesView extends JPanel {

	private JTextField textSearch;
	private JTable table;
	private JTextField textDescriptionRole;
	private DefaultTableModel tableModel;
	private JTextField textRoleName;
	private JTextField textRoleID;
	private List<RoleEntity> list = new ArrayList<>();
	private RoleService service = new RoleService();
	public JButton btnAdd;
	public JButton btnClear;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnFind;
	public JButton btnExport;

	/**
	 * Create the panel.
	 */
	public RolesView() {
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
		btnFind.setIcon(new ImageIcon(RolesView.class.getResource("/images/icon_search.png")));
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		panelControl.add(btnFind);
		
		btnExport = new JButton("Xuất");
		btnExport.setBackground(Color.CYAN);
		btnExport.setIcon(new ImageIcon(RolesView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(78, 51, 670, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên quyền:");
		lblID.setBounds(10, 28, 93, 17);
		lblID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(lblID);

		textSearch = new JTextField();
		textSearch.setLocation(101, 19);
		textSearch.setSize(new Dimension(559, 35));
		textSearch.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelSearch.add(textSearch);
		textSearch.setColumns(15);

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

		tableModel = new DefaultTableModel(new String[] { "STT", "Mã quyền", "Tên quyền", "Chi tiết quyền" }, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch quy\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin quy\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut_1);

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã quyền");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textRoleID = new JTextField();
		textRoleID.setEditable(false);
		textRoleID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textRoleID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên quyền:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textRoleName = new JTextField();
		textRoleName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textRoleName);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);
		panelInformationEmployee.add(Box.createVerticalStrut(25));
		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		JLabel labelBirthDate = new JLabel("Chi tiết quyền");
		labelBirthDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(labelBirthDate);

		textDescriptionRole = new JTextField();
		textDescriptionRole.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(textDescriptionRole);

		Component verticalStrut = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1_1.add(horizontalStrut);

		labelBirthDate.setPreferredSize(labelID.getPreferredSize());
		RoleController roleController = new RoleController(this);

		btnAdd.addActionListener(roleController);
		btnClear.addActionListener(roleController);
		table.addMouseListener(roleController);
		btnRemove.addActionListener(roleController);
		btnUpdate.addActionListener(roleController);
		btnFind.addActionListener(roleController);
		btnExport.addActionListener(roleController);
		loadDatabase();

	}

	public void loadDatabase() {
		list = new ArrayList<RoleEntity>();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		int stt = 1;
		for (RoleEntity roleEntity : list) {
			tableModel.addRow(new Object[] { stt++, roleEntity.getRoleId(), roleEntity.getRoleName(),
					roleEntity.getDescription() });
		}
	}

	public void showMessage(String messsage, JTextField componant) {
		JOptionPane.showMessageDialog(this, messsage);
		componant.requestFocus();
	}

	public void handlerActionAdd() {
		String roleName = textRoleName.getText().trim();
		if (roleName.equals("")) {
			showMessage("Vui lòng không bỏ trống tên quyền", textRoleName);
			return;
		}

		String roleDecription = textDescriptionRole.getText().trim();
		if (roleDecription.equals("")) {
			showMessage("Vui lòng không bỏ trống chi tiết quyền", textDescriptionRole);
			return;
		}

		RoleEntity newRole = service.insert(new RoleEntity(roleName, roleDecription));
		if (newRole != null) {
			showMessage("Thêm thành công", textRoleName);
			loadDatabase();
		} else {
			showMessage("Thêm không thành công", textRoleName);
		}
	}

	public void handlerActionRemove() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
			if (option == JOptionPane.CANCEL_OPTION && option == JOptionPane.NO_OPTION) {
				return;
			}
			String roleId = textRoleID.getText();
			if (!roleId.equals("")) {
				int status = service.remove(Integer.valueOf(roleId));
				if (status != 0) {
					showMessage("Xóa thành công", textDescriptionRole);
					handlerActionClear();
				} else {
					showMessage("Xóa không thành công", textDescriptionRole);
				}
			}
		} else {
			showMessage("Vui lòng chọn dòng để xóa", textDescriptionRole);
		}
	}

	public void handlerActionUpdate() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			String roleId = textRoleID.getText().trim();
			String roleName = textRoleName.getText().trim();
			String roleDescription = textDescriptionRole.getText().trim();
			RoleEntity entity = new RoleEntity(Integer.valueOf(roleId), roleName, roleDescription);
			int status = service.updateOne(entity);
			if (status != 0) {
				showMessage("Sửa thành công", textDescriptionRole);
				handlerActionClear();
			} else {
				showMessage("Sửa không thành công", textDescriptionRole);
			}
		} else {
			showMessage("Vui lòng chọn dòng để sửa", textDescriptionRole);
		}
	}

	public void actionShowInfo() {
		int index = table.getSelectedRow();
		if (index >= 0) {
			RoleEntity entity = list.get(index);
			this.textRoleID.setText(entity.getRoleId() + "");
			this.textRoleName.setText(entity.getRoleName());
			this.textDescriptionRole.setText(entity.getDescription());
		}

	}

	public void handlerActionClear() {
		this.textRoleID.setText("");
		this.textRoleName.setText("");
		this.textDescriptionRole.setText("");
		this.textSearch.setText("");
		loadDatabase();
	}

	public void handlerActionFind() {
		String roleName = this.textSearch.getText();
		if (roleName.equals("")) {
			showMessage("Vui lòng nhập tên quyền để tìm kiếm", textSearch);
			return;
		}
		list = service.findByRoleName(roleName);
		tableModel.setRowCount(0);
		int stt = 1;
		for (RoleEntity roleEntity : list) {
			tableModel.addRow(new Object[] { stt++, roleEntity.getRoleId(), roleEntity.getRoleName(),
					roleEntity.getDescription() });
		}
	}
	public void handlerExport() {
		ExcelUtils.exportExcelRole(list);
	}

}
