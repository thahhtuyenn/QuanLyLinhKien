package view.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.border.LineBorder;

import controller.MainController;
import entity.EmployeeEntity;
import entity.RoleEntity;
import interfaces.IChangePassword;
import service.RoleService;
import view.categoryProduct.CategoryProductView;
import view.changePassword.ChangePasswordView;
import view.customer.CustomerView;
import view.employee.EmployeeView;
import view.login.LoginView;
import view.order.SalesOrderView;
import view.product.ProductView;
import view.promotion.PromotionView;
import view.purchasing.PurchasingOrderView;
import view.role.RolesView;
import view.sales.PurchasingView;
import view.sales.SalesView;
import view.statistics.StatisticsView;
import view.vendor.VendorView;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

public class MainView extends JFrame {

	private JPanel contentPane;
	public JButton btnSales;
	public JButton btnProduct;
	public JButton btnSalesOrder;
	public JButton btnCategoryProduct;
	public JButton btnPurchasingOrder;
	public JButton btnPromotion;
	public JButton btnCustomer;
	public JButton btnEmployee;
	public JButton btnVendor;
	private MainController controller;
	public JButton btnPurchasing;
	public JLabel btnLogout;
	public JLabel btnSetting;
	private JPanel panelMain;
	private JPanel StartPanel;
	private JLabel lblHello;
	private JLabel lblTitle;
	public JButton btnRole;
	public JButton btnStatistics;
	public JButton btnChoose = new JButton();
	private EmployeeEntity mEmployee;
	private RoleService roleService = new RoleService();
	private JPanel menu;

	/**
	 * Create the frame.
	 */
	public MainView(EmployeeEntity e) {
		this.mEmployee = e;
		controller = new MainController(this);
		setTitle("Qu\u1EA3n l\u00FD linh ki\u1EC7n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/images/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(100, 100, 1200, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		menu = new JPanel();
		menu.setBorder(new LineBorder(new Color(130, 135, 144)));
		menu.setBounds(5, 65, 202, 619);
		menu.setLayout(null);

		btnSales = new JButton("B\u00E1n h\u00E0ng");
		btnSales.setBounds(0, 10, 200, 40);
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setBackground(Color.WHITE);
		btnSales.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSales.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_small_business_30px_3.png")));

		btnPurchasing = new JButton("Nh\u1EADp h\u00E0ng");
		btnPurchasing.setBounds(0, 50, 200, 40);
		btnPurchasing.setHorizontalAlignment(SwingConstants.LEFT);

		btnPurchasing.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_downloads_30px.png")));
		btnPurchasing.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPurchasing.setFocusable(false);
		btnPurchasing.setBackground(Color.WHITE);

		btnSales.setPreferredSize(new Dimension(175, 40));

		btnProduct = new JButton("S\u1EA3n ph\u1EA9m");
		btnProduct.setBounds(0, 110, 200, 40);
		btnProduct.setHorizontalAlignment(SwingConstants.LEFT);
		btnProduct.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_multiple_smartphones_30px.png")));
		btnProduct.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnProduct.setFocusable(false);
		btnProduct.setBackground(Color.WHITE);

		btnSalesOrder = new JButton("H\u00F3a \u0111\u01A1n");
		btnSalesOrder.setBounds(0, 150, 200, 40);
		btnSalesOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalesOrder.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_agreement_30px.png")));

		btnSalesOrder.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSalesOrder.setFocusable(false);
		btnSalesOrder.setBackground(Color.WHITE);

		btnCategoryProduct = new JButton("Lo\u1EA1i s\u1EA3n ph\u1EA9m");
		btnCategoryProduct.setBounds(0, 191, 200, 40);
		btnCategoryProduct.setHorizontalAlignment(SwingConstants.LEFT);
		btnCategoryProduct.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_dossier_folder_30px.png")));
		btnCategoryProduct.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCategoryProduct.setFocusable(false);
		btnCategoryProduct.setBackground(Color.WHITE);

		btnPurchasingOrder = new JButton("Phi\u1EBFu nh\u1EADp");
		btnPurchasingOrder.setBounds(0, 232, 200, 40);
		btnPurchasingOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnPurchasingOrder.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_truck_30px.png")));
		btnPurchasingOrder.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPurchasingOrder.setFocusable(false);
		btnPurchasingOrder.setBackground(Color.WHITE);

		btnPromotion = new JButton("Khuy\u1EBFn m\u00E3i");
		btnPromotion.setBounds(0, 273, 200, 40);
		btnPromotion.setHorizontalAlignment(SwingConstants.LEFT);
		btnPromotion.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_gift_30px.png")));
		btnPromotion.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPromotion.setFocusable(false);
		btnPromotion.setBackground(Color.WHITE);

		btnCustomer = new JButton("Kh\u00E1ch h\u00E0ng");
		btnCustomer.setBounds(0, 335, 200, 40);
		btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomer.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_user_30px.png")));
		btnCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCustomer.setFocusable(false);
		btnCustomer.setBackground(Color.WHITE);

		btnEmployee = new JButton("Nh\u00E2n vi\u00EAn");
		btnEmployee.setBounds(0, 376, 200, 40);
		btnEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmployee.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_assistant_30px.png")));
		btnEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnEmployee.setFocusable(false);
		btnEmployee.setBackground(Color.WHITE);

		btnVendor = new JButton("Nh\u00E0 cung c\u1EA5p");
		btnVendor.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_truck_30px.png")));
		btnVendor.setBounds(0, 419, 200, 40);
		btnVendor.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendor.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnVendor.setFocusable(false);
		btnVendor.setBackground(Color.WHITE);

		btnRole = new JButton("Quyền");
		btnRole.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_police_badge_30px.png")));
		btnRole.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnRole.setFocusable(false);
		btnRole.setBackground(Color.WHITE);
		btnRole.setBounds(0, 481, 200, 40);

		JPanel toolbar = new JPanel();
		toolbar.setBorder(new LineBorder(new Color(130, 135, 144)));
		toolbar.setBounds(5, 5, 1171, 50);
		contentPane.add(toolbar);
		toolbar.setLayout(null);

		lblTitle = new JLabel("QU\u1EA2N L\u00DD LINH KI\u1EC6N");
		lblTitle.setLocation(212, 5);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setSize(949, 41);
		toolbar.add(lblTitle);

		btnSetting = new JLabel("");
		btnSetting.setHorizontalAlignment(SwingConstants.CENTER);
		btnSetting.setIcon(new ImageIcon(MainView.class.getResource("/images/icon_setting.png")));
		btnSetting.setBounds(152, 5, 50, 45);
		toolbar.add(btnSetting);

		btnLogout = new JLabel(mEmployee.getFullName());
		btnLogout.setIcon(new ImageIcon(MainView.class.getResource("/images/icon_exit.png")));
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogout.setBounds(10, 0, 138, 50);
		toolbar.add(btnLogout);

		panelMain = new JPanel();
		panelMain.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.setBounds(217, 65, 959, 619);
		contentPane.add(panelMain);
		panelMain.setLayout(new CardLayout(0, 0));

		StartPanel = new JPanel();
		panelMain.add(StartPanel, "name_625299501390300");
		StartPanel.setLayout(new BorderLayout(0, 0));

		lblHello = new JLabel(String.format("Xin chào, %s", mEmployee.getFullName()));
		lblHello.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		StartPanel.add(lblHello);

		btnStatistics = new JButton("Thống kê");
		btnStatistics.setIcon(new ImageIcon(MainView.class.getResource("/images/icons8_bar_chart_30px.png")));
		btnStatistics.setBounds(0, 540, 200, 40);
		btnStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		btnStatistics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnStatistics.setFocusable(false);
		btnStatistics.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(menu);
		scrollPane.createVerticalScrollBar();
		scrollPane.setBounds(0, 61, 200, 619);
		contentPane.add(scrollPane);

		btnSales.addActionListener(controller);
		btnPurchasing.addActionListener(controller);
		btnProduct.addActionListener(controller);
		btnSalesOrder.addActionListener(controller);
		btnCategoryProduct.addActionListener(controller);
		btnPurchasingOrder.addActionListener(controller);
		btnPromotion.addActionListener(controller);
		btnCustomer.addActionListener(controller);
		btnEmployee.addActionListener(controller);
		btnVendor.addActionListener(controller);
		btnLogout.addMouseListener(controller);
		btnSetting.addMouseListener(controller);
		btnRole.addActionListener(controller);
		btnStatistics.addActionListener(controller);

		authorization();

	}

	public void showSalesOrderView() {
		chooseButton(btnSalesOrder);
		lblTitle.setText("Hóa đơn");
		replacePanelMain(new SalesOrderView());
	}

	public void replacePanelMain(JComponent component) {
		// remove
		this.panelMain.removeAll();
		this.panelMain.repaint();
		this.panelMain.revalidate();

		// add
		this.panelMain.add(component, BorderLayout.CENTER);
		this.panelMain.repaint();
		this.panelMain.revalidate();
	}

	public void showEmployeeView() {
		chooseButton(btnEmployee);
		lblTitle.setText("Nhân viên");
		replacePanelMain(new EmployeeView(mEmployee));
	}

	public void showCustomerView() {
		chooseButton(btnCustomer);
		lblTitle.setText("Khách hàng");
		replacePanelMain(new CustomerView(mEmployee));

	}

	public void showVendorView() {
		chooseButton(btnVendor);
		lblTitle.setText("Nhà cung cấp");
		replacePanelMain(new VendorView(mEmployee));
	}

	public void showChangePasswordView() {
		new ChangePasswordView(mEmployee, new IChangePassword() {

			@Override
			public void sendPassword(String password) {
				mEmployee.setPassword(password);
			}
		}).setVisible(true);

	}

	public void showSalesView() {
		chooseButton(btnSales);
		lblTitle.setText("Bán hàng");
		replacePanelMain(new SalesView(mEmployee));

	}

	public void showProductView() {
		chooseButton(btnProduct);
		lblTitle.setText("Sản phẩm");
		replacePanelMain(new ProductView(mEmployee));

	}

	public void showProductCategoryview() {
		chooseButton(btnCategoryProduct);
		lblTitle.setText("Loại sản phẩm");
		replacePanelMain(new CategoryProductView(mEmployee));
	}

	public void showPurchasingOrderView() {
		chooseButton(btnPurchasingOrder);
		lblTitle.setText("Phiếu nhập hàng");
		replacePanelMain(new PurchasingOrderView());

	}

	public void showPromotionView() {
		chooseButton(btnPromotion);
		lblTitle.setText("Khuyến mãi");
		replacePanelMain(new PromotionView(mEmployee));
	}

	public void showPurchasingView() {
		chooseButton(btnPurchasing);
		lblTitle.setText("Nhập hàng");
		replacePanelMain(new PurchasingView(mEmployee));

	}

	public void showRolesView() {
		chooseButton(btnRole);
		lblTitle.setText("Quyền");
		replacePanelMain(new RolesView());

	}

	public void chooseButton(JButton btn) {
		btnChoose.setBackground(Color.WHITE);
		btn.setBackground(Color.cyan);
		btnChoose = btn;
	}

	public void showLoginView() {
		int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?");
		if (option == JOptionPane.OK_OPTION) {
			new LoginView().setVisible(true);
			this.dispose();
		}
	}

	public void showStatisticsView() {
		chooseButton(btnStatistics);
		lblTitle.setText("Thống kê");
		replacePanelMain(new StatisticsView());
	}

	public void authorization() {
		RoleEntity role = roleService.findById(mEmployee.getRoleId());
		// Admin
		// Quản lí
		// Nhân viên bán
		// Nhân viên nhập hàng
		if (role.getRoleId() == 1) {
			menu.add(btnSales);
			menu.add(btnPurchasing);
			menu.add(new JSeparator());
			menu.add(btnProduct);
			menu.add(btnSalesOrder);
			menu.add(btnCategoryProduct);
			menu.add(btnPurchasingOrder);
			menu.add(btnPromotion);
			menu.add(new JSeparator());
			menu.add(btnCustomer);
			menu.add(btnEmployee);
			menu.add(btnVendor);
			menu.add(new JSeparator());
			menu.add(btnRole);
			menu.add(btnStatistics);
		} else if (role.getRoleId() == 3) {
			// menu.add(btnSales);
			menu.add(btnPurchasing);
			menu.add(new JSeparator());
			menu.add(btnProduct);
			// menu.add(btnSalesOrder);
			menu.add(btnCategoryProduct);
			menu.add(btnPurchasingOrder);
			// menu.add(btnPromotion);
			menu.add(new JSeparator());
			// menu.add(btnCustomer);
			// menu.add(btnEmployee);
			menu.add(btnVendor);
			menu.add(new JSeparator());
			// menu.add(btnRole);
			// menu.add(btnStatistics);

		} else if (role.getRoleId() == 2) {
			menu.add(btnSales);
			// menu.add(btnPurchasing);
			menu.add(new JSeparator());
			menu.add(btnProduct);
			menu.add(btnSalesOrder);
			// menu.add(btnCategoryProduct);
			// menu.add(btnPurchasingOrder);
			menu.add(btnPromotion);
			menu.add(new JSeparator());
			menu.add(btnCustomer);
			// menu.add(btnEmployee);
			// menu.add(btnVendor);
			menu.add(new JSeparator());
			// menu.add(btnRole);
		} else if (role.getRoleId() == 4) {
			menu.add(btnSales);
			menu.add(btnPurchasing);
			menu.add(new JSeparator());
			menu.add(btnProduct);
			menu.add(btnSalesOrder);
			menu.add(btnCategoryProduct);
			menu.add(btnPurchasingOrder);
			menu.add(btnPromotion);
			menu.add(new JSeparator());
			menu.add(btnCustomer);
			menu.add(btnEmployee);
			menu.add(btnVendor);
			menu.add(new JSeparator());
			// menu.add(btnRole);
			menu.add(btnStatistics);
		}

	}
}
