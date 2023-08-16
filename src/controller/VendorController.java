package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.vendor.VendorView;

public class VendorController implements ActionListener, MouseListener {
	private VendorView vendorView;

	public VendorController(VendorView vendorView) {
		this.vendorView = vendorView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(vendorView.btnAdd)) {
			vendorView.handlerActionAdd();
		} else if (o.equals(vendorView.btnRemove)) {
			vendorView.handlerActionRemove();
		} else if (o.equals(vendorView.btnUpdate)) {
			vendorView.handlerActionUpdate();
		} else if (o.equals(vendorView.btnFind)) {
			vendorView.handlerActionFind();
		} else if (o.equals(vendorView.btnClear)) {
			vendorView.handlerActionClear();
		} else if (o.equals(vendorView.btnExport)) {
			vendorView.handlerExport();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		vendorView.showInfor();
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

}
