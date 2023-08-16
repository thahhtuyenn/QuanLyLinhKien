package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.orderDetails.PurchaseOrderDetailView;

public class PurchaseOrderDetailController implements ActionListener {
	private PurchaseOrderDetailView purchaseOrderDetailView;
	
	public PurchaseOrderDetailController(PurchaseOrderDetailView purchaseOrderDetailView) {
		this.purchaseOrderDetailView = purchaseOrderDetailView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(purchaseOrderDetailView.btnExit)) {
			purchaseOrderDetailView.handlerActionExit();
		}
	}
}
