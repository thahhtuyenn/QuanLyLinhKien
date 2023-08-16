package view.sales;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.PurchasingController;
import entity.CategoryEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import entity.VendorEntity;
import interfaces.ISendData;
import service.CategoryProductService;
import service.ProductService;
import service.PurchasingOrderService;
import utils.PriceFormatter;
import utils.WritePDF;
import view.vendor.VendorView;

public class PurchasingView extends JPanel {
	private JTextField textFindByName;
	private DefaultTableModel tableProductModel, tablePurchaseModel;
	private JTable tableProduct, tablePurchase;
	private JTextField textID;
	private JTextField textCateogryProduct;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textQuantityOrder;
	private JTextField textTotalDue;
	private JTextField textVendor;
	private JTextField textEmployee;
	private JButton btnChooseEmployee;
	private JTextField textOrderDate;
	public JButton btnDelete;
	public JButton btnUpdate;
	public JButton btnCheckout;
	public JButton btnExit;
	public JButton btnClean;
	public JButton btnChooseVendor;
	public JButton btnAdd;
	public JButton btnFind;
	private PurchasingController purchasingController;
	private EmployeeEntity employeeEntity = null;
	private VendorEntity vendorEntity = null;
	private List<ProductEntity> listProduct = new ArrayList<ProductEntity>();
	private List<PuschaseOrderDetailEntity> listOrderDetail = new ArrayList<PuschaseOrderDetailEntity>();
	private PurchasingOrderService servicePurchaseOrder = new PurchasingOrderService();
	private ProductService serviceProduct = new ProductService();
	private CategoryProductService serviceCategory = new CategoryProductService();

	public PurchasingView(EmployeeEntity e) {
		employeeEntity = e;
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setSize(959, 619);
		setLayout(null);

		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLeft.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pnLeft.setBounds(0, 0, 504, 619);
		add(pnLeft);
		pnLeft.setLayout(null);

		textFindByName = new JTextField();
		textFindByName.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm b\u1EB1ng t\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		textFindByName.setBounds(23, 7, 219, 42);
		pnLeft.add(textFindByName);
		textFindByName.setColumns(10);

		btnClean = new JButton("Làm mới");
		btnClean.setBackground(Color.CYAN);
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setBounds(352, 7, 129, 42);
		pnLeft.add(btnClean);

		String[] cols = { "STT", "M\u00E3 sản phẩm", "Lo\u1EA1i sản phẩm", "T\u00EAn sản phẩm",
				"\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng t\u1ED3n", "Tr\u1EA1ng th\u00E1i" };
		tableProductModel = new DefaultTableModel(cols, 0);
		tableProduct = new JTable(tableProductModel);
		JScrollPane scTable = new JScrollPane(tableProduct);
		scTable.setBounds(10, 53, 484, 334);
		pnLeft.add(scTable);

		JPanel pnHinhAnhSP = new JPanel();
		pnHinhAnhSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnHinhAnhSP.setBounds(10, 397, 200, 200);
		pnLeft.add(pnHinhAnhSP);

		textID = new JTextField();
		textID.setBorder(new TitledBorder(null, "M\u00E3 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		textID.setEditable(false);
		textID.setBounds(213, 411, 129, 42);
		pnLeft.add(textID);
		textID.setColumns(10);

		textCateogryProduct = new JTextField();
		textCateogryProduct.setEditable(false);
		textCateogryProduct.setColumns(10);
		textCateogryProduct.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textCateogryProduct.setBounds(352, 411, 129, 42);
		pnLeft.add(textCateogryProduct);

		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00EAn s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textName.setBounds(213, 469, 129, 42);
		pnLeft.add(textName);

		textPrice = new JTextField();
		textPrice.setEditable(false);
		textPrice.setColumns(10);
		textPrice.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u0110\u01A1n gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textPrice.setBounds(352, 469, 129, 42);
		pnLeft.add(textPrice);

		textQuantityOrder = new JTextField();
		textQuantityOrder.setColumns(10);
		textQuantityOrder.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"S\u1ED1 l\u01B0\u1EE3ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textQuantityOrder.setBounds(213, 537, 129, 42);
		pnLeft.add(textQuantityOrder);

		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_add_30px.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd.setBackground(Color.CYAN);
		btnAdd.setBounds(352, 536, 129, 43);
		pnLeft.add(btnAdd);

		btnFind = new JButton("Tìm");
		btnFind.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icon_search.png")));
		btnFind.setBackground(Color.CYAN);
		btnFind.setBounds(252, 7, 89, 42);
		pnLeft.add(btnFind);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(487, 10, 0, 0);
		add(lblNewLabel);

		JPanel pnRight = new JPanel();
		pnRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnRight.setBounds(503, 0, 456, 619);
		add(pnRight);
		pnRight.setLayout(null);

		textTotalDue = new JTextField();
		textTotalDue.setEditable(false);
		textTotalDue.setBorder(
				new TitledBorder(null, "T\u1ED5ng ti\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textTotalDue.setBounds(10, 11, 436, 43);
		pnRight.add(textTotalDue);
		textTotalDue.setColumns(10);

		textVendor = new JTextField();
		textVendor.setEditable(false);
		textVendor.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textVendor.setBounds(10, 65, 168, 43);
		pnRight.add(textVendor);
		textVendor.setColumns(10);

		btnChooseVendor = new JButton("New button");
		btnChooseVendor.setBackground(Color.CYAN);
		btnChooseVendor.setBounds(183, 70, 41, 37);
		pnRight.add(btnChooseVendor);

		textEmployee = new JTextField();
		textEmployee.setEditable(false);
		textEmployee.setColumns(10);
		textEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textEmployee.setBounds(233, 65, 168, 43);
		pnRight.add(textEmployee);

		btnChooseEmployee = new JButton("New button");
		btnChooseEmployee.setBackground(Color.CYAN);
		btnChooseEmployee.setEnabled(false);
		btnChooseEmployee.setBounds(405, 70, 41, 37);
		pnRight.add(btnChooseEmployee);

		textOrderDate = new JTextField();
		textOrderDate.setEditable(false);
		textOrderDate.setBorder(
				new TitledBorder(null, "Ng\u00E0y l\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textOrderDate.setBounds(10, 119, 168, 43);
		pnRight.add(textOrderDate);
		textOrderDate.setColumns(10);

		tablePurchaseModel = new DefaultTableModel(new String[] { "STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền" },
				0);
		tablePurchase = new JTable(tablePurchaseModel);
		JScrollPane scTableHoaDon = new JScrollPane(tablePurchase);
		scTableHoaDon.setBounds(10, 173, 436, 321);
		pnRight.add(scTableHoaDon);

		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(Color.CYAN);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_delete_forever_30px_1.png")));
		btnDelete.setBounds(45, 505, 98, 43);
		pnRight.add(btnDelete);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_wrench_30px.png")));
		btnUpdate.setBounds(176, 505, 98, 43);
		pnRight.add(btnUpdate);

		btnCheckout = new JButton("Thanh Toán");
		btnCheckout.setBackground(Color.CYAN);
		btnCheckout.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCheckout.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_us_dollar_30px.png")));
		btnCheckout.setBounds(150, 559, 144, 43);
		pnRight.add(btnCheckout);

		btnExit = new JButton("Hủy");
		btnExit.setBackground(Color.CYAN);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setIcon(new ImageIcon(PurchasingView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExit.setBounds(299, 505, 102, 43);
		pnRight.add(btnExit);

		purchasingController = new PurchasingController(this);
		btnChooseVendor.addActionListener(purchasingController);

		Date currentDate = new Date(System.currentTimeMillis());
		textOrderDate.setText(currentDate.toString());
		textEmployee.setText(employeeEntity.getFullName());
		textTotalDue.setText(PriceFormatter.format(0));
		btnAdd.addActionListener(purchasingController);
		btnCheckout.addActionListener(purchasingController);
		btnClean.addActionListener(purchasingController);
		btnDelete.addActionListener(purchasingController);
		btnUpdate.addActionListener(purchasingController);
		btnExit.addActionListener(purchasingController);
		btnFind.addActionListener(purchasingController);
		tableProduct.addMouseListener(purchasingController);
		tablePurchase.addMouseListener(purchasingController);
		loadDatabase();
	}

	private void loadDatabase() {
		tableProduct.removeAll();
		tableProductModel.setRowCount(0);
		tableProduct.setRowSelectionAllowed(false);
		listProduct = serviceProduct.findAll();

		CategoryEntity categoryEntity = null;
		int orderNumber = 1;
		for (ProductEntity productEntity : listProduct) {
			categoryEntity = serviceCategory.findById(productEntity.getCategoryId());
			tableProductModel.addRow(new Object[] { orderNumber++, productEntity.getProductId(), 
					categoryEntity.getCategoryName(),productEntity.getName(), 
					PriceFormatter.format(productEntity.getPrice()),productEntity.getQuantity(), 
					productEntity.getStatus() });
		}
	}

	public void showChooseVendorView() {
		new ChooseVendorView(new ISendData<VendorEntity>() {

			@Override
			public void send(VendorEntity o) {
				vendorEntity = o;
				textVendor.setText(vendorEntity.getVendorName());
			}
		}).setVisible(true);
	}

	public void showInforTableProduct() {
		int index = tableProduct.getSelectedRow();
		if (index >= 0) {
			ProductEntity product = listProduct.get(index);
			textID.setText(product.getProductId() + "");
			textCateogryProduct.setText(product.getCategoryId() + "");
			textName.setText(product.getName());
			textPrice.setText(PriceFormatter.format(product.getPrice()));
		}
	}

	public void cleanPurchaseOrderDetails() {
		textTotalDue.setText("");
		textVendor.setText("");
		tablePurchase.removeAll();
		tablePurchaseModel.setRowCount(0);
		listOrderDetail.clear();
	}

	public void handlerActionClean() {
		textFindByName.setText("");
		textID.setText("");
		textName.setText("");
		textCateogryProduct.setText("");
		textPrice.setText("");
		textQuantityOrder.setText("");
		vendorEntity = null;
		loadDatabase();
	}

	public void handlerActionAdd() {
		int index = tableProduct.getSelectedRow();
		if (index >= 0) {
			if (textQuantityOrder.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
				return;
			}

			ProductEntity product = listProduct.get(index);
			Boolean isExists = false;
			for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : listOrderDetail) {
				if (product.getProductId() == puschaseOrderDetailEntity.getProductId()) {
					isExists = true;
				}
			}

			if (isExists) {
				JOptionPane.showMessageDialog(null, "Sản phẩm đã được chọn");
				return;
			}

			try {
				int quantity = Integer.parseInt(textQuantityOrder.getText());
				double subTotal = quantity * product.getPrice();
				PuschaseOrderDetailEntity podEntity = new PuschaseOrderDetailEntity(product.getProductId(), quantity,
						subTotal, product);
				listOrderDetail.add(podEntity);
				handlerActionClean();
				updatePuschaseOrder();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng là số");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm");
			return;
		}
	}

	private void updatePuschaseOrder() {
		double totalDue = 0;
		for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : listOrderDetail) {
			totalDue += puschaseOrderDetailEntity.getSubTotal();
		}

		textTotalDue.setText(PriceFormatter.format(totalDue));
		tablePurchase.removeAll();
		tablePurchaseModel.setRowCount(0);
		int orderNumber = 1;
		for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : listOrderDetail) {
			tablePurchaseModel.addRow(new Object[] { orderNumber++, puschaseOrderDetailEntity.getProduct().getName(),
					puschaseOrderDetailEntity.getOrderQuantity(),
					PriceFormatter.format(puschaseOrderDetailEntity.getProduct().getPrice()),
					PriceFormatter.format(puschaseOrderDetailEntity.getSubTotal()) });
		}
	}

	public void handlerActionUpdate() {
		int index = tablePurchase.getSelectedRow();
		if (index >= 0) {
			PuschaseOrderDetailEntity puschaseEntity = listOrderDetail.get(index);
			try {
				int quantityNew = Integer.parseInt(JOptionPane.showInputDialog(this, "Vui lòng nhập số lượng mới"));
				if (quantityNew <= 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng lớn hơn 0");
					return;
				}
				puschaseEntity.setOrderQuantity(quantityNew);
				puschaseEntity.setSubTotal(quantityNew * puschaseEntity.getProduct().getPrice());
				listOrderDetail.set(index, puschaseEntity);
				updatePuschaseOrder();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng là số");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa");
		}
	}

	public void handlerActionDelete() {
		int index = tablePurchase.getSelectedRow();
		if (index >= 0) {
			if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				listOrderDetail.remove(index);
				tablePurchaseModel.removeRow(index);
				updatePuschaseOrder();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa");
		}
	}

	public void handlerActionFind() {
		loadDatabase();
		if (textFindByName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm");
			return;
		} else {
			tableProduct.removeAll();
			tableProductModel.setRowCount(0);
			tableProduct.setRowSelectionAllowed(false);
			String name = null;

			name = textFindByName.getText().trim();
			listProduct = serviceProduct.findByNameAndTotalDue(name, null, null);

			int orderNumber = 1;
			for (ProductEntity productEntity : listProduct) {
				tableProductModel.addRow(
						new Object[] { orderNumber++, productEntity.getProductId(), productEntity.getCategoryId(),
								productEntity.getName(), PriceFormatter.format(productEntity.getPrice()),
								productEntity.getQuantity(), productEntity.getStatus() });
			}
		}
	}

	public void handlerActionCheckout() {
		if(employeeEntity.getRoleId() != 3) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		} 
		if (vendorEntity == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
			return;
		}

		int quantityOrderDetail = listOrderDetail.size();
		if (quantityOrderDetail <= 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thêm sản phẩm vào giỏ hàng");
			return;
		}

		double totalDue = PriceFormatter.parse(textTotalDue.getText().trim());
		PuschaseOrderEntity puschaseOrderEntity = new PuschaseOrderEntity(vendorEntity.getVendorId(),
				employeeEntity.getEmployeeId(), totalDue);
		
		Integer puschaseIdSaved = servicePurchaseOrder.insertOne(puschaseOrderEntity, listOrderDetail);

		if (puschaseIdSaved != null) {
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
			int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn không?");
			if(JOptionPane.OK_OPTION == option) {
				new WritePDF().writePhieuNhap(puschaseIdSaved);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thất bại do lỗi hệ thống");
		}
		loadDatabase();
		handlerActionClean();
		cleanPurchaseOrderDetails();
	}

	public void handlerActionExit() {
		textVendor.setText("");
		textTotalDue.setText(PriceFormatter.format(0));
		listOrderDetail.clear();
		tablePurchase.removeAll();
		tablePurchaseModel.setRowCount(0);
	}
}
