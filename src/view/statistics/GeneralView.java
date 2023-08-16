package view.statistics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import service.CustomerService;
import service.EmployeeService;
import service.ProductService;
import service.VendorService;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class GeneralView extends JPanel {
	private VendorService vendorService = new VendorService();
	private EmployeeService employeeService = new EmployeeService();
	private CustomerService customerService = new CustomerService();
	private ProductService productService = new ProductService();
	private JLabel countVendor;
	private JLabel countCustomer;
	private JLabel countEmployee;
	private JLabel countProduct;

	public GeneralView() {
		setLayout(new GridLayout(0, 4, 0, 0));

		JPanel product = new JPanel();
		add(product);
		product.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sản phẩm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 10, 69, 51);
		product.add(lblNewLabel);

		countProduct = new JLabel("25");
		countProduct.setHorizontalAlignment(SwingConstants.CENTER);
		countProduct.setFont(new Font("Times New Roman", Font.BOLD, 16));
		countProduct.setBounds(121, 76, 69, 51);
		product.add(countProduct);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GeneralView.class.getResource("/images/icons8_google_mobile_100px.png")));
		lblNewLabel_2.setBounds(10, 30, 100, 97);
		product.add(lblNewLabel_2);

		JPanel product_1 = new JPanel();
		product_1.setLayout(null);
		add(product_1);

		JLabel lblNhnVin = new JLabel("Nhân viên");
		lblNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhnVin.setBounds(121, 10, 69, 51);
		product_1.add(lblNhnVin);

		countEmployee = new JLabel("25");
		countEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		countEmployee.setFont(new Font("Times New Roman", Font.BOLD, 16));
		countEmployee.setBounds(121, 76, 69, 51);
		product_1.add(countEmployee);

		JLabel employee = new JLabel("");
		employee.setIcon(new ImageIcon(GeneralView.class.getResource("/images/icons8_assistant_100px.png")));
		employee.setBounds(10, 30, 100, 97);
		product_1.add(employee);

		JPanel product_1_1 = new JPanel();
		product_1_1.setLayout(null);
		add(product_1_1);

		JLabel lblKhchHng = new JLabel("Khách hàng");
		lblKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhchHng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblKhchHng.setBounds(101, 10, 89, 51);
		product_1_1.add(lblKhchHng);

		countCustomer = new JLabel("25");
		countCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		countCustomer.setFont(new Font("Times New Roman", Font.BOLD, 16));
		countCustomer.setBounds(121, 76, 69, 51);
		product_1_1.add(countCustomer);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(GeneralView.class.getResource("/images/icons8_person_male_100px.png")));
		lblNewLabel_2_1_1.setBounds(10, 30, 100, 97);
		product_1_1.add(lblNewLabel_2_1_1);

		JPanel product_1_1_1 = new JPanel();
		product_1_1_1.setLayout(null);
		add(product_1_1_1);

		JLabel lblNhCungCp = new JLabel("Nhà cung cấp");
		lblNhCungCp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhCungCp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhCungCp.setBounds(90, 10, 100, 51);
		product_1_1_1.add(lblNhCungCp);

		countVendor = new JLabel("25");
		countVendor.setHorizontalAlignment(SwingConstants.CENTER);
		countVendor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		countVendor.setBounds(121, 76, 69, 51);
		product_1_1_1.add(countVendor);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setIcon(new ImageIcon(GeneralView.class.getResource("/images/icons8_company_100px.png")));
		lblNewLabel_2_1_1_1.setBounds(10, 30, 100, 97);
		product_1_1_1.add(lblNewLabel_2_1_1_1);

		handlerShowData();
	}

	public void handlerShowData() {
		countVendor.setText(vendorService.count() + "");
		countCustomer.setText(customerService.count() + "");
		countEmployee.setText(employeeService.count() + "");
		countProduct.setText(productService.count() + "");

	}
}
