package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.purchasing.PurchasingOrderView;

public class PurchasingOrderController implements ActionListener, MouseListener{

	private PurchasingOrderView purchasingOrderView;
	
	public PurchasingOrderController(PurchasingOrderView purchasingOrderView) {
		this.purchasingOrderView = purchasingOrderView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(purchasingOrderView.btnShowDetails)) {
			purchasingOrderView.handlerActionShowDetail();
		} else if(o.equals(purchasingOrderView.btnSearch)) {
			purchasingOrderView.handlerActionFind();
		} else if(o.equals(purchasingOrderView.btnClear)) {
			purchasingOrderView.handlerActionClean();
		} else if(o.equals(purchasingOrderView.btnExport)) {
			purchasingOrderView.handlerExport();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		purchasingOrderView.showInfor();
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
