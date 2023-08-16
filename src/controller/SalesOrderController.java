package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.order.SalesOrderView;

public class SalesOrderController implements ActionListener, MouseListener{
	private SalesOrderView salesOrderView;
	public SalesOrderController(SalesOrderView salesOrderView) {
		this.salesOrderView = salesOrderView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		salesOrderView.mouseClick();
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
		if(o.equals(salesOrderView.btnShowDetails)) {
			salesOrderView.handlerActionShowInfo();
		}else if(o.equals(salesOrderView.btnClear)) {
			salesOrderView.handlerActionClear();
		} else if(o.equals(salesOrderView.btnSearch)) {
			salesOrderView.handlerActionFind();
		} else if(o.equals(salesOrderView.btnExport)) {
			salesOrderView.handlerExport();
		}
	}

}
