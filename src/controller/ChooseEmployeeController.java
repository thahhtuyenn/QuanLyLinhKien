package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChooseCustomerView;
import view.statistics.ChooseEmployeeView;

public class ChooseEmployeeController implements ActionListener, MouseListener {
	private ChooseEmployeeView chooseEmployeeView;

	public ChooseEmployeeController(ChooseEmployeeView chooseEmployeeView) {
		this.chooseEmployeeView = chooseEmployeeView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(chooseEmployeeView.btnClean)) {
			chooseEmployeeView.chucNangLamMoi();
		} else if (o.equals(chooseEmployeeView.btnExit)) {
			chooseEmployeeView.dispose();
		} else if (o.equals(chooseEmployeeView.btnFind)) {
			chooseEmployeeView.chucNangFind();
		} else if (o.equals(chooseEmployeeView.btnChoose)) {
			chooseEmployeeView.chucNangChon();
		}

	}

}
