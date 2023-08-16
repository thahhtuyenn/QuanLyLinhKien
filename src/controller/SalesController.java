package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChooseCustomerView;
import view.sales.SalesView;

public class SalesController implements ActionListener, MouseListener {
	private SalesView salesView;

	public SalesController(SalesView salesView) {
		this.salesView = salesView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		salesView.actionShowInfoTableProduct();
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
		if (o.equals(salesView.btnChooseCustomer)) {
			salesView.showChooseCustomerView();
		} else if (o.equals(salesView.btnChoosePromotion)) {
			salesView.showChoosePromotionView();
		} else if (o.equals(salesView.btnAdd)) {
			salesView.handlerActionAddOrderDetail();
		} else if (o.equals(salesView.btnCleanProduct)) {
			salesView.handlerActionCleanProduct();
		} else if (o.equals(salesView.btnCheckout)) {
			salesView.handlerActionCheckout();
		} else if (o.equals(salesView.btnRemove)) {
			salesView.handlerActionRemove();
		} else if (o.equals(salesView.btnUpdate)) {
			salesView.handlerActionUpdate();
		} else if (o.equals(salesView.btnExit)) {
			salesView.handlerActionExit();
		} else if (o.equals(salesView.btnFind)) {
			salesView.handlerActionFind();
		}

	}

}
