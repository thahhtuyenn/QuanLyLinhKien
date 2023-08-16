package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChooseVendorView;

public class ChooseVendorController implements ActionListener, MouseListener {
	private ChooseVendorView vendorView;

	public ChooseVendorController(ChooseVendorView vendorView) {
		this.vendorView = vendorView;
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
		if (o.equals(vendorView.btnClean)) {
			vendorView.handlerActionClean();
		} else if (o.equals(vendorView.btnExit)) {
			vendorView.dispose();
		} else if (o.equals(vendorView.btnFind)) {
			vendorView.handlerActionFInd();
		} else if (o.equals(vendorView.btnChoose)) {
			vendorView.handlerActionChoose();
		}

	}

}
