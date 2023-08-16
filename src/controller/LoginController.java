package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.login.LoginView;

public class LoginController implements ActionListener{

	private LoginView loginview;
	
	public LoginController(LoginView loginView) {
		this.loginview = loginView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(loginview.btnLogin)) {
			loginview.login();
		} else if(o.equals(loginview.btnExit)) {
			loginview.exit();
		}
	}

}
