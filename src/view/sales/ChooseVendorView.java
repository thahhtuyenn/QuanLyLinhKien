package view.sales;

import java.awt.Color;
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
import controller.ChooseVendorController;
import entity.VendorEntity;
import interfaces.ISendData;
import service.VendorService;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChooseVendorView extends JFrame {

	private JPanel pnCenter;
	private DefaultTableModel tableVendorModel;
	private JTable tableVendor;
	private JTextField textFindByName;
	public JButton btnFind;
	public JButton btnExit;
	public JButton btnChoose;
	public JButton btnClean;
	private VendorService service = new VendorService();
	private List<VendorEntity> list = new ArrayList<VendorEntity>();
	private ISendData<VendorEntity> sendVendor = null;

	/**
	 * Launch the application.
	 *

	/**
	 * Create the frame.
	 */
	public ChooseVendorView(ISendData<VendorEntity> iv) {
		sendVendor = iv;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Nhà cung cấp");
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
		btnClean.setBounds(473, 14, 129, 42);
		pnCenter.add(btnClean);

		String[] cols = { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Fax" };
		tableVendorModel = new DefaultTableModel(cols, 0);
		tableVendor = new JTable(tableVendorModel);
		JScrollPane scTableKhachHang = new JScrollPane(tableVendor);
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
		textFindByName.setBounds(35, 14, 293, 42);
		pnCenter.add(textFindByName);
		textFindByName.setColumns(10);

		btnFind = new JButton("Tìm");
		btnFind.setBackground(Color.CYAN);
		btnFind.setBounds(348, 15, 104, 43);
		pnCenter.add(btnFind);

		ChooseVendorController controller = new ChooseVendorController(this);
		btnClean.addActionListener(controller);
		btnFind.addActionListener(controller);
		btnExit.addActionListener(controller);
		btnChoose.addActionListener(controller);
		tableVendor.addMouseListener(controller);
		loadDatabase();
	}

	private void loadDatabase() {
		tableVendor.removeAll();
		tableVendorModel.setRowCount(0);
		tableVendor.setRowSelectionAllowed(false);
		list = service.findAll();
		
		int orderNumber = 1;
		for (VendorEntity vendorEntity : list) {
			tableVendorModel.addRow(new Object[] {orderNumber++, vendorEntity.getVendorId(), vendorEntity.getVendorName(), vendorEntity.getAddress(),
					vendorEntity.getPhone(), vendorEntity.getFax()});
		}
	}

	public void handlerActionClean() {
		textFindByName.setText("");
		tableVendor.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void handlerActionFInd() {
		String name = textFindByName.getText().trim();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhà cung cấp");
			return;
		}
		list = service.findByNameAndAddress(name, "");
		tableVendorModel.setRowCount(0);
		int orderNumber = 1;
		for (VendorEntity vendorEntity : list) {
			tableVendorModel.addRow(new Object[] {orderNumber++, vendorEntity.getVendorId(), vendorEntity.getVendorName(), vendorEntity.getAddress(),
					vendorEntity.getPhone(), vendorEntity.getFax()});
		}
	}

	public void handlerActionChoose() {
		int index = tableVendor.getSelectedRow();
		if(index >= 0) {
			VendorEntity entity = list.get(index);
			sendVendor.send(entity);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
		}
	}
}
