package view.statistics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ChooseEmployeeController;
import entity.EmployeeEntity;
import interfaces.ISendData;
import service.EmployeeService;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.Color;

public class ChooseEmployeeView extends JFrame {

	private JPanel pnCenter;
	public JButton btnChoose;
	public JButton btnExit;
	public JButton btnClean;
	private JTextField textFindByName;
	private JTable tableEmployee;
	private ISendData<EmployeeEntity> sendEmployee = null;
	public JButton btnFind;
	private EmployeeService employeeService = new EmployeeService();
	private List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
	private DefaultTableModel tableEmployeeModel;
	private int roleId = 0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChooseEmployeeView(ISendData<EmployeeEntity> ic, int roleId) {
		sendEmployee = ic;
		this.roleId = roleId;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Nhân viên");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 458);
		pnCenter = new JPanel();
		pnCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnCenter);
		pnCenter.setLayout(null);

		btnClean = new JButton("Làm mới");
		btnClean.setBackground(Color.CYAN);
		btnClean.setIcon(new ImageIcon(ChooseEmployeeView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setBounds(521, 14, 129, 42);
		pnCenter.add(btnClean);

		tableEmployeeModel = new DefaultTableModel(
				new String[] { "Mã nhân viên", "Họ và tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Trạng thái" }, 0);
		tableEmployee = new JTable(tableEmployeeModel);
		JScrollPane scTableKhachHang = new JScrollPane(tableEmployee);
		scTableKhachHang.setBounds(10, 81, 673, 283);
		pnCenter.add(scTableKhachHang);

		btnChoose = new JButton("Chọn");
		btnChoose.setBackground(Color.CYAN);
		btnChoose.setIcon(new ImageIcon(ChooseEmployeeView.class.getResource("/images/icons8_ok_30px.png")));
		btnChoose.setBounds(208, 368, 104, 43);
		pnCenter.add(btnChoose);

		btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.CYAN);
		btnExit.setIcon(new ImageIcon(ChooseEmployeeView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExit.setBounds(348, 368, 104, 43);
		pnCenter.add(btnExit);

		textFindByName = new JTextField();
		textFindByName.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm b\u1EB1ng t\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		textFindByName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFindByName.setBounds(43, 10, 333, 42);
		pnCenter.add(textFindByName);
		textFindByName.setColumns(10);

		btnFind = new JButton("Tìm");
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		btnFind.setBounds(397, 14, 104, 42);
		pnCenter.add(btnFind);

		ChooseEmployeeController chooseEmployeeController = new ChooseEmployeeController(this);
		btnClean.addActionListener(chooseEmployeeController);
		btnExit.addActionListener(chooseEmployeeController);
		btnFind.addActionListener(chooseEmployeeController);
		btnChoose.addActionListener(chooseEmployeeController);

		loadDatabase();
	}

	public void chucNangLamMoi() {
		textFindByName.setText("");
		tableEmployee.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void chucNangFind() {

		String name = textFindByName.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
			return;
		}
		list = employeeService.findByFullNameAndRoleId(name, roleId);

		// load table
		tableEmployeeModel.setRowCount(0);

		for (EmployeeEntity employeeEntity : list) {
			tableEmployeeModel.addRow(new Object[] { employeeEntity.getEmployeeId(), employeeEntity.getFullName(),
					employeeEntity.getBirthDate(), employeeEntity.getAddress(), employeeEntity.getPhone(),
					employeeEntity.getStatus() });

		}
	}

	private void loadDatabase() {
		tableEmployeeModel.setRowCount(0);
		tableEmployee.setRowSelectionAllowed(false);
		list = employeeService.findByRoldId(roleId);

		// load table
		tableEmployeeModel.setRowCount(0);

		for (EmployeeEntity employeeEntity : list) {
			tableEmployeeModel.addRow(new Object[] { employeeEntity.getEmployeeId(), employeeEntity.getFullName(),
					employeeEntity.getBirthDate(), employeeEntity.getAddress(), employeeEntity.getPhone(),
					employeeEntity.getStatus() });

		}
	}

	public void chucNangChon() {
		int index = tableEmployee.getSelectedRow();
		if (index >= 0) {
			EmployeeEntity employeeEntity = list.get(index);
			sendEmployee.send(employeeEntity);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khác hàng");
		}

	}
}
