package view.sales;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ChooseCustomerController;
import entity.CustomerEntity;
import interfaces.ISendData;
import service.CustomerService;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.Color;

public class ChooseCustomerView extends JFrame {

	private JPanel pnCenter;
	public DefaultTableModel tableKhachHangModel;
	public JButton btnChoose;
	public JButton btnExit;
	private ChooseCustomerController chooseCustCtrl;
	public JButton btnClean;
	private JTextField textFindByName;
	private JTable tableKhachHang;
	private ISendData<CustomerEntity> sendCustomer = null;
	public JButton btnFind;
	private CustomerService customerService = new CustomerService();
	private List<CustomerEntity> list = new ArrayList<CustomerEntity>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChooseCustomerView(ISendData<CustomerEntity> ic) {
		sendCustomer = ic;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Khách Hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 458);
		pnCenter = new JPanel();
		pnCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnCenter);
		pnCenter.setLayout(null);

		btnClean = new JButton("Làm mới");
		btnClean.setBackground(Color.CYAN);
		btnClean.setIcon(new ImageIcon(ChooseCustomerView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setBounds(521, 14, 129, 42);
		pnCenter.add(btnClean);

		tableKhachHangModel = new DefaultTableModel(
				new String[] { "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Trạng thái" }, 0);
		tableKhachHang = new JTable(tableKhachHangModel);
		JScrollPane scTableKhachHang = new JScrollPane(tableKhachHang);
		scTableKhachHang.setBounds(10, 81, 673, 283);
		pnCenter.add(scTableKhachHang);

		btnChoose = new JButton("Chọn");
		btnChoose.setBackground(Color.CYAN);
		btnChoose.setIcon(new ImageIcon(ChooseCustomerView.class.getResource("/images/icons8_ok_30px.png")));
		btnChoose.setBounds(208, 368, 104, 43);
		pnCenter.add(btnChoose);

		btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.CYAN);
		btnExit.setIcon(new ImageIcon(ChooseCustomerView.class.getResource("/images/icons8_cancel_30px_1.png")));
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

		chooseCustCtrl = new ChooseCustomerController(this);
		btnClean.addActionListener(chooseCustCtrl);
		btnExit.addActionListener(chooseCustCtrl);
		btnFind.addActionListener(chooseCustCtrl);
		btnChoose.addActionListener(chooseCustCtrl);

		loadDatabase();
	}

	public void chucNangLamMoi() {
		textFindByName.setText("");
		tableKhachHang.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void chucNangFind() {

		String name = textFindByName.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
			return;
		}
		list = customerService.findByFullNameAndAddress(name, "");
		// load table
		tableKhachHangModel.setRowCount(0);
		int stt = 1;
		for (CustomerEntity customerEntity : list) {
			tableKhachHangModel
					.addRow(new Object[] { stt++, customerEntity.getCustomerId(), customerEntity.getFullName(),
							customerEntity.getAddress(), customerEntity.getPhone(), customerEntity.getStatus() });

		}
	}

	private void loadDatabase() {
		tableKhachHangModel.setRowCount(0);
		tableKhachHang.setRowSelectionAllowed(false);
		list = customerService.findAll();

		int stt = 1;
		for (CustomerEntity customerEntity : list) {
			tableKhachHangModel
					.addRow(new Object[] { stt++, customerEntity.getCustomerId(), customerEntity.getFullName(),
							customerEntity.getAddress(), customerEntity.getPhone(), customerEntity.getStatus() });

		}
	}

	public void chucNangChon() {
		int index = tableKhachHang.getSelectedRow();
		if (index >= 0) {
			CustomerEntity customerEntity = list.get(index);
			sendCustomer.send(customerEntity);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khác hàng");
		}

	}
}
