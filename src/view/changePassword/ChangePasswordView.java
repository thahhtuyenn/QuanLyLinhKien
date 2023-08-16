package view.changePassword;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.ChangePasswordController;
import entity.EmployeeEntity;
import interfaces.IChangePassword;
import service.EmployeeService;
import utils.PasswordUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ChangePasswordView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JPasswordField passwordRe;
	private EmployeeEntity mEmployee;
	public JButton btnConfirm;
	public JButton btnExit;
	private EmployeeService service = new EmployeeService();
	private IChangePassword iChangePassword;

	/**
	 * Create the frame.
	 */
	public ChangePasswordView(EmployeeEntity employeeEntity, IChangePassword changePassword) {
		iChangePassword = changePassword;
		mEmployee = employeeEntity;
		setTitle("Đổi mật khẩu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 390);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		passwordOld = new JPasswordField();
		passwordOld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordOld.setBorder(new TitledBorder(null, "M\u1EADt kh\u1EA9u c\u0169", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		passwordOld.setBounds(58, 40, 300, 50);
		contentPane.add(passwordOld);

		passwordNew = new JPasswordField();
		passwordNew.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordNew.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"M\u1EADt kh\u1EA9u m\u1EDBi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		passwordNew.setBounds(57, 103, 300, 50);
		contentPane.add(passwordNew);

		passwordRe = new JPasswordField();
		passwordRe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordRe.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		passwordRe.setBounds(57, 165, 300, 50);
		contentPane.add(passwordRe);

		btnConfirm = new JButton("Đồng ý");
		btnConfirm.setBackground(Color.CYAN);
		btnConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnConfirm.setIcon(new ImageIcon(ChangePasswordView.class.getResource("/images/icons8_ok_30px.png")));
		btnConfirm.setBounds(57, 257, 108, 39);
		contentPane.add(btnConfirm);

		btnExit = new JButton("Hủy");
		btnExit.setIcon(new ImageIcon(ChangePasswordView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setBackground(Color.CYAN);
		btnExit.setBounds(249, 257, 108, 39);
		contentPane.add(btnExit);

		ChangePasswordController controller = new ChangePasswordController(this);
		btnConfirm.addActionListener(controller);
		btnExit.addActionListener(controller);
	}

	public void handlerActionConfirm() {
		String oldPass = String.valueOf(passwordOld.getPassword());
		if (oldPass.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ");
			passwordOld.requestFocus();
			return;
		}
		String newPass = String.valueOf(passwordNew.getPassword());
		if (newPass.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
			passwordNew.requestFocus();
			return;
		}
		String reNewPass = String.valueOf(passwordRe.getPassword());
		if (reNewPass.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập lại mật khẩu");
			passwordRe.requestFocus();
			return;
		}
		if (!(newPass.equals(reNewPass))) {
			JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không chính xác");
			passwordRe.requestFocus();
			return;
		}
		if (!PasswordUtils.toSHA1(oldPass).equals(mEmployee.getPassword())) {
			JOptionPane.showMessageDialog(this, "Nhập khẩu cũ không chính xác");
			passwordRe.requestFocus();
			return;
		}

		int option = JOptionPane.showConfirmDialog(btnConfirm,
				String.format("Bạn có muốn đổi mật khẩu thành: %s ?", newPass));
		if (option == JOptionPane.OK_OPTION) {
			// Đổi mật khẩu
			int status = service.updatePasswordByUserName(mEmployee.getUserName(), newPass);
			if (status != 0) {
				JOptionPane.showMessageDialog(this, String.format("Mật khẩu mới của bạn là: %s", newPass));
				iChangePassword.sendPassword(PasswordUtils.toSHA1(newPass));
				handlerActionExit();
			} else {
				JOptionPane.showMessageDialog(this, String.format("Hệ thống đang lỗi"));
			}

		}

	}

	public void handlerActionExit() {
		this.dispose();

	}
}
