package view.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import entity.EmployeeEntity;
import service.EmployeeService;
import view.main.MainView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JPasswordField textPassword;
	public JButton btnLogin;
	public JButton btnExit;
	private EmployeeService service = new EmployeeService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setFont(new Font("Times New Roman", Font.PLAIN, 15));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(LoginView.class.getResource("/images/icons8_windows_phone_store_30px.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 742, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(147, 112, 219));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_right = new JPanel();
		panel_right.setBackground(new Color(148, 0, 211));
		panel_right.setBounds(352, 10, 366, 426);
		contentPane.add(panel_right);
		panel_right.setLayout(null);

		JPanel panel_login = new JPanel();
		panel_login.setBounds(10, 0, 346, 416);
		panel_right.add(panel_login);
		panel_login.setLayout(null);

		JLabel lbl_login = new JLabel("ĐĂNG NHẬP");
		lbl_login.setForeground(new Color(255, 255, 255));
		lbl_login.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lbl_login.setBounds(105, 26, 132, 24);
		panel_login.add(lbl_login);

		JLabel lbl_user = new JLabel("Tài khoản:");
		lbl_user.setForeground(new Color(255, 255, 255));
		lbl_user.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_user.setBounds(30, 101, 80, 20);
		panel_login.add(lbl_user);

		textUserName = new JTextField();
		textUserName.setBounds(110, 95, 226, 35);
		panel_login.add(textUserName);
		textUserName.setColumns(10);

		JLabel lbl_password = new JLabel("Mật khẩu:");
		lbl_password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setBounds(30, 146, 80, 20);
		panel_login.add(lbl_password);

		textPassword = new JPasswordField();
		textPassword.setBounds(110, 140, 226, 35);
		panel_login.add(textPassword);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(210, 293, 126, 113);
		panel_login.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/images/bt21_login.png")));

		LoginController loginControll = new LoginController(this);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(Color.CYAN);
		btnLogin.setForeground(new Color(148, 0, 211));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLogin.setBounds(46, 225, 100, 30);
		btnLogin.addActionListener(loginControll);
		panel_login.add(btnLogin);

		btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.CYAN);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnExit.setForeground(new Color(148, 0, 211));
		btnExit.setBounds(209, 225, 100, 30);
		btnExit.addActionListener(loginControll);
		panel_login.add(btnExit);

		JLabel lbl_backgr = new JLabel("");
		lbl_backgr.setIcon(new ImageIcon(LoginView.class.getResource("/images/background_login_1.jpg")));
		lbl_backgr.setBounds(0, 0, 352, 426);
		panel_login.add(lbl_backgr);

		JLabel lblNewLabel = new JLabel("SPRITES");
		lblNewLabel.setForeground(new Color(250, 240, 230));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(72, 24, 130, 30);
		contentPane.add(lblNewLabel);

		JLabel lblrobot = new JLabel("");
		lblrobot.setIcon(new ImageIcon(LoginView.class.getResource("/images/robot_login.png")));
		lblrobot.setBounds(10, 10, 332, 426);
		contentPane.add(lblrobot);
	}

	public void login() {
		String userName = textUserName.getText().trim();
		if (userName.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản");
			textUserName.requestFocus();
			return;
		}
		String password = String.copyValueOf(textPassword.getPassword());
		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
			textPassword.requestFocus();
			return;
		}
		EmployeeEntity employeeEntity = service.findByUserNameAndPassword(userName, password);

		if (employeeEntity != null && employeeEntity.getStatus() == 1) {
			this.dispose();
			new MainView(employeeEntity).setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
			textUserName.requestFocus();
		}

	}

	public void exit() {
		this.dispose();
	}
}
