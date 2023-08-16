package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.changePassword.ChangePasswordView;

public class ChangePasswordController implements ActionListener {
	private ChangePasswordView mView;

	public ChangePasswordController(ChangePasswordView view) {
		mView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(mView.btnConfirm)) {
			mView.handlerActionConfirm();
		} else if(o.equals(mView.btnExit)) {
			mView.handlerActionExit();
		}

	}

}
