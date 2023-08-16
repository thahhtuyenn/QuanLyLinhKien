package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChooseCustomerView;
import view.statistics.ChooseEmployeeView;
import view.statistics.ChooseProductView;

public class ChooseProductController implements ActionListener, MouseListener {
	private ChooseProductView chooseProductView;

	public ChooseProductController(ChooseProductView chooseProductView) {
		this.chooseProductView = chooseProductView;
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
		if (o.equals(chooseProductView.btnClean)) {
			chooseProductView.chucNangLamMoi();
		} else if (o.equals(chooseProductView.btnExit)) {
			chooseProductView.dispose();
		} else if (o.equals(chooseProductView.btnFind)) {
			chooseProductView.chucNangFind();
		} else if (o.equals(chooseProductView.btnChoose)) {
			chooseProductView.chucNangChon();
		}

	}

}
