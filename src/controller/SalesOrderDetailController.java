package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.orderDetails.SalesOrderDetailView;

public class SalesOrderDetailController implements ActionListener{
	private SalesOrderDetailView salOrderDetailView;
	public SalesOrderDetailController(SalesOrderDetailView salOrderDetailView) {
		this.salOrderDetailView = salOrderDetailView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(salOrderDetailView.btnExists)) {
			salOrderDetailView.handlerActionExists();
		}
		
	}
	
}
