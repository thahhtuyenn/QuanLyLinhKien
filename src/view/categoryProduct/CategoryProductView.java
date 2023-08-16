package view.categoryProduct;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CategoryProductController;
import entity.CategoryEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import entity.VendorEntity;
import service.CategoryProductService;
import utils.ExcelUtils;
import view.employee.EmployeeView;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CategoryProductView extends JPanel {
	private JTextField textSearch;
	private JTextField textName;
	private JTextField textID;
	private JTable table;
	private JTextField textDescription;
	private List<CategoryEntity> list;
	private DefaultTableModel tableModel;
	public JButton btnAdd;
	public JButton btnFind;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnClear;
	private CategoryProductService service = new CategoryProductService();
	public JButton btnExport;
	private EmployeeEntity mEmployeeEntity;
	/**
	 * Create the panel.
	 */
	public CategoryProductView(EmployeeEntity employeeEntity) {
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
		btnFind.setIcon(new ImageIcon(CategoryProductView.class.getResource("/images/icon_search.png")));
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		panelControl.add(btnFind);

		btnExport = new JButton("Xuất");
		btnExport.setIcon(new ImageIcon(CategoryProductView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExport.setBackground(Color.CYAN);
		panelControl.add(btnExport);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(78, 51, 670, 65);
		panelSearch.setPreferredSize(new Dimension(10, 50));
		panelNorth.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblID = new JLabel("Tên loại:");
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

		String[] cols = new String[] { "STT", "M\u00E3 Lo\u1EA1i", "T\u00EAn Lo\u1EA1i", "M\u00F4 T\u1EA3" };
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 405, 939, 204);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut_1);

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã loại:");
		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Tên loại:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
		boxFullName.add(textName);

		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);
		panelInformationEmployee.add(Box.createVerticalStrut(25));
		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		Box boxFullName_1 = Box.createHorizontalBox();
		box1_1.add(boxFullName_1);

		JLabel labelAddress = new JLabel("Mô tả: ");
		labelAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(labelAddress);

		textDescription = new JTextField();
		textDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName_1.add(textDescription);

		Component verticalStrut = Box.createVerticalStrut(25);
		panelInformationEmployee.add(verticalStrut);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1_1.add(horizontalStrut);
		labelAddress.setPreferredSize(labelFullName.getPreferredSize());

		CategoryProductController Controller = new CategoryProductController(this);
		table.addMouseListener(Controller);
		btnAdd.addActionListener(Controller);
		btnRemove.addActionListener(Controller);
		btnUpdate.addActionListener(Controller);
		btnFind.addActionListener(Controller);
		btnClear.addActionListener(Controller);
		btnExport.addActionListener(Controller);
		loadDatabase();

	}

	public void loadDatabase() {
		list = new ArrayList<CategoryEntity>();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		int stt = 1;
		for (CategoryEntity entity : list) {
			tableModel.addRow(
					new Object[] { stt++, entity.getCategoryId(), entity.getCategoryName(), entity.getDescription() });
		}
	}

	public void handlerActionClear() {
		loadDatabase();
		textSearch.setText("");
		textID.setText("");
		textName.setText("");
		textDescription.setText("");
	}

	public void handlerActionRemove() {
		if(mEmployeeEntity.getRoleId() != 3 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int row = table.getSelectedRow();
		if (row >= 0) {
			int option = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa", "Cảnh Báo", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				String ID = textID.getText();
				if (!ID.equals("")) {
					if (service.removeOne(Integer.parseInt(ID)) != 0) {
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

	public void handlerActionAdd() {
		if(mEmployeeEntity.getRoleId() != 3 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String ID = textID.getText().trim();
		if (ID.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Ma Loai");
			return;
		}

		String fullname = textName.getText().trim();
		if (fullname.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Tên Sản Phẩm ");
			return;
		}

		String des = textDescription.getText().trim();
		if (des.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Mo Ta");
			return;
		}

		CategoryEntity entity = new CategoryEntity(Integer.parseInt(ID), fullname, des);
		entity = service.insertOne(entity);

		if (entity != null) {
			JOptionPane.showMessageDialog(null, "Thêm Loại Sản Phẩm Thành Công");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm Loại Sản Phẩm Không Thành Công");
		}
		handlerActionClear();

	}

	public void handlerActionUpdate() {
		if(mEmployeeEntity.getRoleId() != 3 && mEmployeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int row = table.getSelectedRow();
		if (row >= 0) {
			int ID = list.get(row).getCategoryId();
			String category = textID.getText().trim();
			String name = textName.getText().trim();
			String des = textDescription.getText().trim();

			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên loai sản phẩm");
				return;
			}

			if (category.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập Ma san pham");
				return;
			}

			if (des.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập Mo Ta");
				return;
			}

			CategoryEntity categoryEntity = new CategoryEntity(Integer.parseInt(category), name, des);
			if (service.updateOne(categoryEntity) != 0) {
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				handlerActionClear();
				loadDatabase();
			} else {
				JOptionPane.showMessageDialog(null, "Sửa không thành công");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa!");
		}

	}

	public void showMessage(String messsage, JTextField componant) {
		JOptionPane.showMessageDialog(this, messsage);
		componant.requestFocus();
	}

	public void handlerActionFind() {
		loadDatabase();
		String name = textSearch.getText().trim();
		System.out.println(name);
		list = service.findByCategoryName(name);
		tableModel.setRowCount(0);
		int stt = 1;
		System.out.println(list);
		for (CategoryEntity categoryEntity : list) {
			tableModel.addRow(new Object[] { stt++, categoryEntity.getCategoryId(), categoryEntity.getCategoryName(),
					categoryEntity.getDescription() });
		}
	}

	public void ShowInfor() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			table.setRowSelectionInterval(row, row);
			CategoryEntity entity = list.get(row);
			textSearch.setText(String.valueOf(entity.getCategoryName()));
			textID.setText(String.valueOf(entity.getCategoryId()));
			textName.setText(String.valueOf(entity.getCategoryName()));
			textDescription.setText(String.valueOf(entity.getDescription()));
		}
	}

	public void handlerExport() {
		ExcelUtils.exportExcelCategory(list);
	}
}
