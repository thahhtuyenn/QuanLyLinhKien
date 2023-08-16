package view.sales;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.SalesController;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import entity.PromotionEntity;
import entity.RoleEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;
import interfaces.ISendData;
import service.CategoryProductService;
import service.ProductService;
import service.PromotionService;
import service.SalesOrderService;
import utils.PriceFormatter;
import utils.WritePDF;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.CardLayout;

public class SalesView extends JPanel {
	private JTextField textFindByName;
	private DefaultTableModel tableModelSanPham, tableModelHoaDon;
	private JTable tableProduct, tableHoaDon;
	private JTextField textProductId;
	private JTextField textCategoryID;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textQuantityOrder;
	private JTextField textTotalDue;
	private JTextField textCustomer;
	private JTextField textEmployee;
	public JButton btnChooseEmployee;
	private JTextField textOrderDate;
	private JTextField textPromotion;
	public JButton btnChoosePromotion;
	public JButton btnRemove;
	public JButton btnUpdate;
	public JButton btnCheckout;
	public JButton btnExit;
	public JButton btnAdd;
	public JButton btnFind;
	public JButton btnChooseCustomer;
	private SalesController salesCtrl;
	private CategoryProductService categoryProductService = new CategoryProductService();
	private SalesOrderService salesOrderService = new SalesOrderService();

	/* Danh sách sản phẩm */
	private List<ProductEntity> listProduct = new ArrayList<ProductEntity>();

	/* Service Sản phẩm */
	private ProductService productService = new ProductService();

	/* PromotionService */
	PromotionService promotionService = new PromotionService();

	/* Khuyễn mãi mặc định không có khuyễn mãi */
	private PromotionEntity promotionEntity = promotionService.findById(1);

	/* Khách hàng được chọn */
	private CustomerEntity customerEntity = null;

	private EmployeeEntity employeeEntity = null;

	/* Danh sách chi tiết hóa đơn */
	private List<SalesOrderDetailEntity> listOrderDetail = new ArrayList<SalesOrderDetailEntity>();
	public JButton btnCleanProduct;

	/**
	 * Create the panel.
	 */
	public SalesView(EmployeeEntity e) {
		this.employeeEntity = e;
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

		btnCleanProduct = new JButton("Làm mới");
		btnCleanProduct.setBackground(Color.CYAN);
		btnCleanProduct.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCleanProduct.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnCleanProduct.setBounds(352, 7, 129, 42);
		pnLeft.add(btnCleanProduct);

		tableModelSanPham = new DefaultTableModel(new String[] { "STT", "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm",
				"Đơn giá", "Số lượng tồn", "Trạng thái" }, 0);
		tableProduct = new JTable(tableModelSanPham);
		JScrollPane scTable = new JScrollPane(tableProduct);
		scTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scTable.setBounds(10, 53, 484, 334);
		pnLeft.add(scTable);

		JPanel panelImage = new JPanel();
		panelImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelImage.setBounds(10, 408, 200, 200);
		pnLeft.add(panelImage);
		panelImage.setLayout(new CardLayout(0, 0));

		textProductId = new JTextField();
		textProductId.setBorder(new TitledBorder(null, "M\u00E3 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		textProductId.setEditable(false);
		textProductId.setBounds(213, 411, 129, 42);
		pnLeft.add(textProductId);
		textProductId.setColumns(10);

		textCategoryID = new JTextField();
		textCategoryID.setEditable(false);
		textCategoryID.setColumns(10);
		textCategoryID.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textCategoryID.setBounds(352, 411, 129, 42);
		pnLeft.add(textCategoryID);

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
		textQuantityOrder.setBounds(219, 533, 118, 42);
		pnLeft.add(textQuantityOrder);

		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_add_30px.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd.setBackground(Color.CYAN);
		btnAdd.setBounds(352, 532, 129, 43);
		pnLeft.add(btnAdd);

		btnFind = new JButton("Tìm");
		btnFind.setIcon(new ImageIcon(SalesView.class.getResource("/images/icon_search.png")));
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

		textCustomer = new JTextField();
		textCustomer.setEditable(false);
		textCustomer.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textCustomer.setBounds(10, 65, 168, 43);
		pnRight.add(textCustomer);
		textCustomer.setColumns(10);

		btnChooseCustomer = new JButton("New button");
		btnChooseCustomer.setBackground(Color.CYAN);
		btnChooseCustomer.setBounds(183, 70, 41, 37);
		pnRight.add(btnChooseCustomer);

		textEmployee = new JTextField();
		textEmployee.setEditable(false);
		textEmployee.setColumns(10);
		textEmployee.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textEmployee.setBounds(233, 65, 168, 43);
		pnRight.add(textEmployee);

		btnChooseEmployee = new JButton("New button");
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

		textPromotion = new JTextField();
		textPromotion.setEditable(false);
		textPromotion.setColumns(10);
		textPromotion.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textPromotion.setBounds(233, 119, 168, 43);
		pnRight.add(textPromotion);

		tableModelHoaDon = new DefaultTableModel(new String[] { "STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền" }, 0);
		tableHoaDon = new JTable(tableModelHoaDon);
		JScrollPane scTableHoaDon = new JScrollPane(tableHoaDon);
		scTableHoaDon.setBounds(10, 173, 436, 321);
		pnRight.add(scTableHoaDon);
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tableProduct.getColumnModel().getColumn(3).setCellRenderer(right);

		btnChoosePromotion = new JButton("New button");
		btnChoosePromotion.setBackground(Color.CYAN);
		btnChoosePromotion.setBounds(405, 123, 41, 37);
		pnRight.add(btnChoosePromotion);

		btnRemove = new JButton("Xóa");
		btnRemove.setBackground(Color.CYAN);
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnRemove.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_delete_forever_30px_1.png")));
		btnRemove.setBounds(45, 505, 98, 43);
		pnRight.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_wrench_30px.png")));
		btnUpdate.setBounds(173, 505, 98, 43);
		pnRight.add(btnUpdate);

		btnCheckout = new JButton("Thanh Toán");
		btnCheckout.setBackground(Color.CYAN);
		btnCheckout.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCheckout.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_us_dollar_30px.png")));
		btnCheckout.setBounds(152, 558, 144, 43);
		pnRight.add(btnCheckout);

		btnExit = new JButton("Hủy");
		btnExit.setBackground(Color.CYAN);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setIcon(new ImageIcon(SalesView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExit.setBounds(299, 504, 102, 43);
		pnRight.add(btnExit);

		salesCtrl = new SalesController(this);
		btnChooseCustomer.addActionListener(salesCtrl);
		btnChoosePromotion.addActionListener(salesCtrl);
		tableProduct.addMouseListener(salesCtrl);
		tableHoaDon.addMouseListener(salesCtrl);
		btnAdd.addActionListener(salesCtrl);
		btnCheckout.addActionListener(salesCtrl);
		btnCleanProduct.addActionListener(salesCtrl);
		btnExit.addActionListener(salesCtrl);
		btnRemove.addActionListener(salesCtrl);
		btnUpdate.addActionListener(salesCtrl);
		btnFind.addActionListener(salesCtrl);

		textPromotion.setText(promotionEntity.getPromotionName());
		Date currentDate = new Date(System.currentTimeMillis());
		textOrderDate.setText(currentDate.toString());
		textEmployee.setText(employeeEntity.getFullName());
		textTotalDue.setText(PriceFormatter.format(0));

		loadDatabaseTableProduct();
	}

	public void loadDatabaseTableProduct() {
		listProduct = new ArrayList<ProductEntity>();
		tableModelSanPham.setRowCount(0);
		tableProduct.setRowSelectionAllowed(false);
		listProduct = productService.findAll();

		int stt = 1;
		for (ProductEntity productEntity : listProduct) {
			tableModelSanPham.addRow(new Object[] { stt++, productEntity.getProductId(),
					categoryProductService.findById(productEntity.getCategoryId()).getCategoryName(),
					productEntity.getName(), PriceFormatter.format(productEntity.getPrice()),
					productEntity.getQuantity(), productEntity.getStatus() });
		}
	}

	public void showChooseCustomerView() {
		new ChooseCustomerView(new ISendData<CustomerEntity>() {

			@Override
			public void send(CustomerEntity o) {
				customerEntity = o;
				textCustomer.setText(customerEntity.getFullName());

			}
		}).setVisible(true);
	}

	public void showChoosePromotionView() {
		new ChoosePromotionView(new ISendData<PromotionEntity>() {

			@Override
			public void send(PromotionEntity o) {
				promotionEntity = o;
				textPromotion.setText(promotionEntity.getPromotionName());
				updateSalesOrder();
			}
		}).setVisible(true);
	}

	public void handlerActionAddOrderDetail() {
		int select = tableProduct.getSelectedRow();
		if (select >= 0) {
			if (textQuantityOrder.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
				return;
			}

			/* Chọn sản phẩm */
			ProductEntity product = listProduct.get(select);
			int soLuong = 0;
			try {
				soLuong = Integer.parseInt(textQuantityOrder.getText().trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số nguyên dương!");
				return;
			}

			if (soLuong <= 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng > 0!");
				return;
			}

			if (soLuong > product.getQuantity()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng nhỏ hơn số lượng tồn!");
				return;
			}

			/* Kiểm tra tồn tại trong order detail chưa */
			boolean isExits = false;
			for (SalesOrderDetailEntity salesOrderDetailEntity : listOrderDetail) {
				if (product.getProductId() == salesOrderDetailEntity.getProductId()) {
					isExits = true;
				}
			}

			if (isExits) {
				JOptionPane.showMessageDialog(this, "Sản phẩm đã được chọn");
				return;
			}

			double thanhTien = soLuong * product.getPrice();

			SalesOrderDetailEntity o = new SalesOrderDetailEntity(product.getProductId(), soLuong, thanhTien, product);

			/* Thêm vào list detail entity */
			listOrderDetail.add(o);

			handlerActionCleanProduct();
			updateSalesOrder();

		}
	}

	private void updateSalesOrder() {
		/* Khuyễn mãi */
		/* Tổng tiền */
		double xx = 0.0;
		for (SalesOrderDetailEntity salesOrderDetailEntity : listOrderDetail) {
			xx += salesOrderDetailEntity.getSubTotal();
		}

		double total = xx - xx * (promotionEntity.getPromotionPercent() / 100);
		textTotalDue.setText(PriceFormatter.format(total));

		tableModelHoaDon.setRowCount(0);
		for (SalesOrderDetailEntity salesOrderDetailEntity : listOrderDetail) {
			tableModelHoaDon.addRow(new Object[] { tableModelHoaDon.getRowCount() + 1,
					salesOrderDetailEntity.getProduct().getName(), salesOrderDetailEntity.getOrderQuantity(),
					PriceFormatter.format(salesOrderDetailEntity.getProduct().getPrice()),
					PriceFormatter.format(salesOrderDetailEntity.getSubTotal()) });
		}

	}

	public void actionShowInfoTableProduct() {
		int select = tableProduct.getSelectedRow();
		if (select >= 0) {
			ProductEntity product = listProduct.get(select);
			textProductId.setText(product.getProductId() + "");
			textCategoryID.setText(categoryProductService.findById(product.getCategoryId()).getCategoryName());
			textName.setText(product.getName());
			textPrice.setText(PriceFormatter.format(product.getPrice()));
		}
	}

	public void handlerActionFind() {
		if (textFindByName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm");
			return;
		} else {
			tableProduct.removeAll();
			tableModelSanPham.setRowCount(0);
			tableProduct.setRowSelectionAllowed(false);
			String name = null;

			name = textFindByName.getText().trim();
			listProduct = productService.findByNameAndTotalDue(name, null, null);

			int orderNumber = 1;
			for (ProductEntity productEntity : listProduct) {
				tableModelSanPham.addRow(new Object[] { orderNumber++, productEntity.getProductId(),
						categoryProductService.findById(productEntity.getCategoryId()), productEntity.getName(),
						PriceFormatter.format(productEntity.getPrice()), productEntity.getQuantity(),
						productEntity.getStatus() });
			}
		}
	}

	public void handlerActionCleanProduct() {
		this.textFindByName.setText("");
		textProductId.setText("");
		textCategoryID.setText("");
		textName.setText("");
		textPrice.setText("");
		textQuantityOrder.setText("");
		loadDatabaseTableProduct();

	}

	public void handlerActionCheckout() {
		if(employeeEntity.getRoleId() != 2) {
			JOptionPane.showMessageDialog(this, "Bạn không có quyền này");
			return;
		}  
		
		if (customerEntity == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
			return;
		}

		int qtyOrderDetail = this.listOrderDetail.size();
		if (qtyOrderDetail <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
			return;
		}

		double totalDue = PriceFormatter.parse(textTotalDue.getText().trim());
		SalesOrderEntity salesOrderEntity = new SalesOrderEntity(employeeEntity.getEmployeeId(),
				customerEntity.getCustomerId(), promotionEntity.getPromotionId(), totalDue);
		
		Integer salesOrderIdSaved = salesOrderService.insertOne(salesOrderEntity, listOrderDetail);
		if (salesOrderIdSaved != null) {
			JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
			int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn không?");
			if(JOptionPane.OK_OPTION == option) {
				new WritePDF().writeHoaDon(salesOrderIdSaved);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại do lỗi hệ thống");
		}
		loadDatabaseTableProduct();
		handlerActionCleanOrder();
	}

	public void handlerActionCleanOrder() {
		promotionEntity = promotionService.findById(1);
		textPromotion.setText(promotionEntity.getPromotionName());

		customerEntity = null;
		textCustomer.setText("");

		textTotalDue.setText("0Đ");

		tableModelHoaDon.setRowCount(0);
		listOrderDetail.clear();
	}

	public void handlerActionRemove() {
		int index = tableHoaDon.getSelectedRow();
		if (index >= 0) {
			listOrderDetail.remove(index);
			tableModelHoaDon.removeRow(index);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa");
		}

	}

	// lũi lũi đã chỉnh sửa
	public void handlerActionUpdate() {
		int index = tableHoaDon.getSelectedRow();
		if (index >= 0) {
			SalesOrderDetailEntity salesOrderDetailEntity = this.listOrderDetail.get(index);
			// Lấy ra số lượng tồn
			int qty = salesOrderDetailEntity.getProduct().getQuantity();

			String numberNew = JOptionPane.showInputDialog(this, "Vui lòng nhập số lượng mới");
			// Nếu nhập lớn hơn số lượng tồn
			if (Integer.valueOf(numberNew) > qty || Integer.valueOf(numberNew) <= 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng nhỏ hơn bằng " + qty + " và số lương > 0!");
				return;
			}

			salesOrderDetailEntity.setOrderQuantity(Integer.valueOf(numberNew));

			this.listOrderDetail.set(index, salesOrderDetailEntity);

			double subTotal = Integer.valueOf(numberNew) * salesOrderDetailEntity.getProduct().getPrice();
			DecimalFormat df = new DecimalFormat("#,###Đ");

			salesOrderDetailEntity.setSubTotal(subTotal);

			tableModelHoaDon.setValueAt(df.format(subTotal), index, 4);
			updateSalesOrder();

		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa!");
		}

	}

	public void handlerActionExit() {
		textPromotion.setText(promotionEntity.getPromotionName());
		Date currentDate = new Date(System.currentTimeMillis());
		textOrderDate.setText(currentDate.toString());
		textEmployee.setText(employeeEntity.getFullName());
		textTotalDue.setText(PriceFormatter.format(0));
		textCustomer.setText("");
		textPromotion.setText("");
		listOrderDetail.clear();
		updateSalesOrder();
	}
}
