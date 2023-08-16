package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.sales.PurchasingView;

public class PurchasingController implements ActionListener, MouseListener {
	private PurchasingView purchasingView;

	public PurchasingController(PurchasingView purchasingView) {
		this.purchasingView = purchasingView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		purchasingView.showInforTableProduct();
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
		if (o.equals(purchasingView.btnChooseVendor)) {
			purchasingView.showChooseVendorView();
		} else if (o.equals(purchasingView.btnAdd)) {
			purchasingView.handlerActionAdd();
		} else if (o.equals(purchasingView.btnClean)) {
			purchasingView.handlerActionClean();
		} else if (o.equals(purchasingView.btnDelete)) {
			purchasingView.handlerActionDelete();
		} else if (o.equals(purchasingView.btnExit)) {
			purchasingView.handlerActionExit();
		} else if (o.equals(purchasingView.btnUpdate)) {
			purchasingView.handlerActionUpdate();
		} else if (o.equals(purchasingView.btnCheckout)) {
			purchasingView.handlerActionCheckout();
		} else if(o.equals(purchasingView.btnFind)) {
			purchasingView.handlerActionFind();
		}
	}

}
