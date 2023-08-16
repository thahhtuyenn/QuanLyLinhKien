package view.product;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProductController;
import entity.CategoryEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import service.CategoryProductService;
import service.ProductService;
import utils.ExcelUtils;
import utils.PriceFormatter;
import view.employee.EmployeeView;
import view.order.SalesOrderView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;

public class ProductView extends JPanel {

	private JTable table;
	private JTextField textID;
	private JTextField textCategoryProduct;
	private JTextField textSearchID;
	private JTextField textPriceFrom;
	private JTextField textPriceTo;
	private JTextField textName;
	private JTextField textQuantity;
	private JTextField textPrice;
	private List<ProductEntity> list;
	private DefaultTableModel tableModel;
	private ProductService service = new ProductService();
	private CategoryProductService serviceCategory = new CategoryProductService();
	private List<CategoryEntity> categories;
	private DefaultComboBoxModel<CategoryEntity> defaultComboBoxModel;
	private JComboBox<CategoryEntity> categoryComboBox;
	public JButton btnAdd;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnFind;
	public JButton btnClean;
	public JButton btnExport;
	public EmployeeEntity employeeEntity = null;

	/**
	 * Create the panel.
	 */
	public ProductView(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
		setBounds(new Rectangle(0, 0, 959, 619));
		setLayout(null);

		JPanel panelControl = new JPanel();
		panelControl.setBounds(10, 10, 936, 61);
		add(panelControl);

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
		btnFind.setIcon(new ImageIcon(ProductView.class.getResource("/images/icon_search.png")));
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		panelControl.add(btnFind);

		btnExport = new JButton("Xuất");
		btnExport.setIcon(new ImageIcon(ProductView.class.getResource("/images/icons8_ms_excel_30px.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExport.setBackground(Color.CYAN);
		panelControl.add(btnExport);

		JPanel panelSeach = new JPanel();
		panelSeach.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSeach.setBounds(10, 69, 520, 79);
		add(panelSeach);
		panelSeach.setLayout(null);

		textSearchID = new JTextField();
		textSearchID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textSearchID.setBounds(102, 27, 408, 42);
		panelSeach.add(textSearchID);
		textSearchID.setColumns(10);

		JLabel lblTimMaHoaDon = new JLabel("Tìm sản phẩm :");
		lblTimMaHoaDon.setBounds(10, 41, 105, 13);
		panelSeach.add(lblTimMaHoaDon);
		lblTimMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JPanel panelTongTien = new JPanel();
		panelTongTien.setLayout(null);
		panelTongTien.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u1ED5ng Ti\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTongTien.setBounds(540, 69, 206, 79);
		add(panelTongTien);

		JLabel lblTu = new JLabel("Từ :");
		lblTu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTu.setBounds(10, 36, 45, 13);
		panelTongTien.add(lblTu);

		JLabel lblNewLabel_1_1_1 = new JLabel("Đến :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(104, 36, 45, 13);
		panelTongTien.add(lblNewLabel_1_1_1);

		textPriceFrom = new JTextField();
		textPriceFrom.setBounds(41, 23, 57, 35);
		panelTongTien.add(textPriceFrom);
		textPriceFrom.setColumns(10);

		textPriceTo = new JTextField();
		textPriceTo.setColumns(10);
		textPriceTo.setBounds(142, 23, 57, 35);
		panelTongTien.add(textPriceTo);

		btnClean = new JButton("Làm Mới");
		btnClean.setBackground(Color.CYAN);
		btnClean.setIcon(new ImageIcon(SalesOrderView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setBounds(756, 96, 121, 39);
		add(btnClean);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 936, 201);
		add(scrollPane);

		String[] cols = new String[] { "STT", "M\u00E3 S\u1EA3n Ph\u1EA3m", "Tên Lo\u1EA1i", "T\u00EAn",
				"\u0110\u01A1n Gi\u00E1 (Tri\u1EC7u)", "S\u1ED1 L\u01B0\u1EE3ng", "Tr\u1EA1ng Th\u00E1i" };
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

		JPanel panelInformationEmployee = new JPanel();
		panelInformationEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelInformationEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInformationEmployee.setBounds(10, 384, 936, 225);
		add(panelInformationEmployee);
		panelInformationEmployee.setLayout(new BoxLayout(panelInformationEmployee, BoxLayout.Y_AXIS));

		Box box1 = Box.createHorizontalBox();

		Box boxID = Box.createHorizontalBox();
		JLabel labelID = new JLabel("Mã sản phẩm:");

		labelID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textID = new JTextField();
		textID.setEditable(false);
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID.add(labelID);
		boxID.add(textID);

		Box boxFullName = Box.createHorizontalBox();
		JLabel labelFullName = new JLabel("Loại sản phẩm:");
		labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		textCategoryProduct = new JTextField();
//		textCategoryProduct.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxFullName.add(labelFullName);
//		boxFullName.add(textCategoryProduct);
		
		categories = new ArrayList<CategoryEntity>();
		categories = serviceCategory.findAll();
		defaultComboBoxModel = new DefaultComboBoxModel<CategoryEntity>();
		defaultComboBoxModel.addAll(categories);
		categoryComboBox = new JComboBox<CategoryEntity>(defaultComboBoxModel);
		categoryComboBox.setSelectedIndex(0);
		boxFullName.add(categoryComboBox);
		
		box1.add(boxID);
		box1.add(Box.createHorizontalStrut(25));
		box1.add(boxFullName);

		panelInformationEmployee.add(box1);

		Component boxz = Box.createVerticalStrut(25);
		panelInformationEmployee.add(boxz);

		Box box1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1);

		Box boxID_1 = Box.createHorizontalBox();
		box1_1.add(boxID_1);

		JLabel lblTnKhchHng = new JLabel("Tên sản phẩm:");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(lblTnKhchHng);

		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1.add(textName);

		Component horizontalStrut = Box.createHorizontalStrut(25);
		box1_1.add(horizontalStrut);

		Box box = Box.createHorizontalBox();
		box1_1.add(box);

		JLabel lblNgyLp = new JLabel("Số lượng:");
		lblNgyLp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		box.add(lblNgyLp);

		textQuantity = new JTextField();
		textQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		box.add(textQuantity);

		Component boxz_1 = Box.createVerticalStrut(25);
		panelInformationEmployee.add(boxz_1);

		Box box1_1_1 = Box.createHorizontalBox();
		panelInformationEmployee.add(box1_1_1);

		Box boxID_1_1 = Box.createHorizontalBox();
		box1_1_1.add(boxID_1_1);

		JLabel lblTngTin = new JLabel("Đơn giá:");
		lblTngTin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(lblTngTin);

		textPrice = new JTextField();
		textPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		boxID_1_1.add(textPrice);

		labelID.setPreferredSize(lblTnKhchHng.getPreferredSize());
		lblTngTin.setPreferredSize(lblTnKhchHng.getPreferredSize());
		lblNgyLp.setPreferredSize(lblTnKhchHng.getPreferredSize());
		labelFullName.setPreferredSize(lblTnKhchHng.getPreferredSize());

		ProductController Controller = new ProductController(this);
		table.addMouseListener(Controller);
		btnFind.addActionListener(Controller);
		btnClean.addActionListener(Controller);
		btnAdd.addActionListener(Controller);
		btnRemove.addActionListener(Controller);
		btnUpdate.addActionListener(Controller);
		btnExport.addActionListener(Controller);

		loadDatabase();

	}

	public void loadDatabase() {
		list = new ArrayList<ProductEntity>();
		tableModel.setRowCount(0);
		table.setRowSelectionAllowed(false);
		list = service.findAll();

		CategoryEntity categoryEntity = null;
		int stt = 1;
		for (ProductEntity productEntity : list) {
			categoryEntity = serviceCategory.findById(productEntity.getCategoryId());
			tableModel.addRow(new Object[] { stt++, productEntity.getProductId(),categoryEntity.getCategoryName(),
					productEntity.getName(), PriceFormatter.format(productEntity.getPrice()),
					productEntity.getQuantity(), productEntity.getStatus() });
		}

	}

	public void showMessage(String messsage, JTextField componant) {
		JOptionPane.showMessageDialog(this, messsage);
		componant.requestFocus();
	}

	public void handlerActionAdd() {
		if(employeeEntity.getRoleId() != 3 && employeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		String fullname = textName.getText().trim();
		if (fullname.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Tên Sản Phẩm ");
			return;
		}

		String totalDue = textPrice.getText().trim();
		if (totalDue.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Giá Tiền");
			return;
		}

		String productQuantity = textQuantity.getText().trim();
		if (productQuantity.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Số Lượng Sản Phẩm");
			return;
		}

		
		CategoryEntity cateChoose = (CategoryEntity) this.categoryComboBox.getSelectedItem();
		int CategoryId = cateChoose.getCategoryId();

		ProductEntity productEntity = new ProductEntity(CategoryId, fullname, Double.valueOf(totalDue),
				Integer.valueOf(productQuantity), 1);

		productEntity = service.insertOne(productEntity);


		if (productEntity != null) {
			JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm Thành Công");

		} else {
			JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm Không Thành Công");
		}
		handlerActionClean();

	}

	public void handlerActionClean() {
		loadDatabase();
		textName.setText("");
		textPrice.setText("");
		textPriceFrom.setText("");
		textPriceTo.setText("");
		textQuantity.setText("");
		textSearchID.setText("");
		textID.setText("");
//		textCategoryProduct.setText("");
		categoryComboBox.setSelectedIndex(0);

	}

	public void handlerActionRemove() {
		if(employeeEntity.getRoleId() != 3 && employeeEntity.getRoleId() != 4) {
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
				handlerActionClean();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
		}

	}

	public void handlerActionUpdate() {
		if(employeeEntity.getRoleId() != 3 && employeeEntity.getRoleId() != 4) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}
		int row = table.getSelectedRow();
		if (row >= 0) {
			int ID = list.get(row).getProductId();
			String name = textName.getText().trim();
			String totalDue = textPrice.getText().trim();
			String productQuantity = textQuantity.getText().trim();

			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm");
				return;
			}

			if (totalDue.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập Giá Sản Phẩm");
				return;
			}

			if (productQuantity.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
				return;
			}

			CategoryEntity cateChoose = (CategoryEntity) this.categoryComboBox.getSelectedItem();
			int CategoryId = cateChoose.getCategoryId();

//			ProductEntity productEntity = new ProductEntity(ID, name, Double.parseDouble(totalDue),
//					Integer.parseInt(productQuantity), CategoryId);
			ProductEntity productEntity = new ProductEntity(ID,CategoryId, name, Double.valueOf(totalDue),
					Integer.valueOf(productQuantity), 1);
			System.out.println(productEntity);
			if (service.updateOne(productEntity) != 0) {
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				handlerActionClean();
				loadDatabase();
			} else {
				JOptionPane.showMessageDialog(null, "Sửa không thành công");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa!");
		}

	}

	public void handlerActionFind() {
		loadDatabase();
		String name = textSearchID.getText().trim();
		Double priceFrom = null;
		if (!textPriceFrom.getText().trim().equals("")) {
			priceFrom = Double.valueOf(textPriceFrom.getText().trim());
		}

		Double priceTo = null;
		if (!textPriceTo.getText().trim().equals("")) {
			priceTo = Double.valueOf(textPriceTo.getText().trim());
		}

		tableModel.setRowCount(0);
		list = service.findByNameAndTotalDue(name, priceFrom, priceTo);

		CategoryEntity categoryEntity = null;
		int stt = 1;
		for (ProductEntity productEntity : list) {
			categoryEntity = serviceCategory.findById(productEntity.getCategoryId());
			tableModel.addRow(new Object[] { stt++, productEntity.getProductId(),categoryEntity.getCategoryName(),
					productEntity.getName(), productEntity.getPrice(), productEntity.getQuantity(),
					productEntity.getStatus() });
		}

	}

	public void ShowInfor() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			ProductEntity entity = list.get(row);
			textID.setText(String.valueOf(entity.getProductId()));
			textName.setText(String.valueOf(entity.getName()));
			textPrice.setText(PriceFormatter.format(entity.getPrice()));
			textQuantity.setText(String.valueOf(entity.getQuantity()));
//			textCategoryProduct.setText(String.valueOf(entity.getCategoryId()));
			CategoryEntity categoryEntity = serviceCategory.findById(entity.getCategoryId());
//			categoryComboBox.setSelectedItem(categoryEntity);
			defaultComboBoxModel.setSelectedItem(categoryEntity);
		}

	}

	public void handlerExport() {
		ExcelUtils.exportExcelProduct(list);
	}

}
