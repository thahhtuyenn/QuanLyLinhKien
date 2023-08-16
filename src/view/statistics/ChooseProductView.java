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
import controller.ChooseProductController;
import entity.EmployeeEntity;
import entity.ProductEntity;
import interfaces.ISendData;
import service.EmployeeService;
import service.ProductService;
import utils.PriceFormatter;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.Color;

public class ChooseProductView extends JFrame {

	private JPanel pnCenter;
	public JButton btnChoose;
	public JButton btnExit;
	public JButton btnClean;
	private JTextField textFindByName;
	private JTable tableProduct;
	private ISendData<ProductEntity> sendProduct = null;
	public JButton btnFind;
	private ProductService productService = new ProductService();
	private List<ProductEntity> list = new ArrayList<ProductEntity>();
	private DefaultTableModel tableProductModel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChooseProductView(ISendData<ProductEntity> ic) {
		sendProduct = ic;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Sản phẩm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 458);
		pnCenter = new JPanel();
		pnCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnCenter);
		pnCenter.setLayout(null);

		btnClean = new JButton("Làm mới");
		btnClean.setBackground(Color.CYAN);
		btnClean.setIcon(new ImageIcon(ChooseProductView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setBounds(521, 14, 129, 42);
		pnCenter.add(btnClean);

		String[] cols = new String[] { "M\u00E3 S\u1EA3n Ph\u1EA3m", "M\u00E3 Lo\u1EA1i", "T\u00EAn",
				"\u0110\u01A1n Gi\u00E1 (Tri\u1EC7u)", "S\u1ED1 L\u01B0\u1EE3ng", "Tr\u1EA1ng Th\u00E1i" };

		tableProductModel = new DefaultTableModel(cols, 0);
		tableProduct = new JTable(tableProductModel);
		JScrollPane scTableKhachHang = new JScrollPane(tableProduct);
		scTableKhachHang.setBounds(10, 81, 673, 283);
		pnCenter.add(scTableKhachHang);

		btnChoose = new JButton("Chọn");
		btnChoose.setBackground(Color.CYAN);
		btnChoose.setIcon(new ImageIcon(ChooseProductView.class.getResource("/images/icons8_ok_30px.png")));
		btnChoose.setBounds(208, 368, 104, 43);
		pnCenter.add(btnChoose);

		btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.CYAN);
		btnExit.setIcon(new ImageIcon(ChooseProductView.class.getResource("/images/icons8_cancel_30px_1.png")));
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

		ChooseProductController chooseProductController = new ChooseProductController(this);
		btnClean.addActionListener(chooseProductController);
		btnExit.addActionListener(chooseProductController);
		btnFind.addActionListener(chooseProductController);
		btnChoose.addActionListener(chooseProductController);

		loadDatabase();
	}

	public void chucNangLamMoi() {
		textFindByName.setText("");
		tableProduct.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void chucNangFind() {

		String name = textFindByName.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
			return;
		}
		list = productService.findByNameAndTotalDue(name, null, null);

		// load table
		tableProductModel.setRowCount(0);

		for (ProductEntity productEntity : list) {
			tableProductModel.addRow(new Object[] { productEntity.getProductId(), productEntity.getCategoryId(),
					productEntity.getName(), PriceFormatter.format(productEntity.getPrice()),
					productEntity.getQuantity(), productEntity.getStatus() });
		}
	}

	private void loadDatabase() {
		tableProductModel.setRowCount(0);
		tableProduct.setRowSelectionAllowed(false);
		list = productService.findAll();

		// load table
		tableProductModel.setRowCount(0);

		for (ProductEntity productEntity : list) {
			tableProductModel.addRow(new Object[] { productEntity.getProductId(), productEntity.getCategoryId(),
					productEntity.getName(), PriceFormatter.format(productEntity.getPrice()),
					productEntity.getQuantity(), productEntity.getStatus() });
		}
	}

	public void chucNangChon() {
		int index = tableProduct.getSelectedRow();
		if (index >= 0) {
			ProductEntity productEntity = list.get(index);
			sendProduct.send(productEntity);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khác hàng");
		}

	}
}
