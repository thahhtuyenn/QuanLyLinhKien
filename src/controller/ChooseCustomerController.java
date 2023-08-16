package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChooseCustomerView;

public class ChooseCustomerController implements ActionListener, MouseListener{
	private ChooseCustomerView custView;
	public ChooseCustomerController(ChooseCustomerView custView) {
		this.custView = custView;
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
		if(o.equals(custView.btnClean)) {
			custView.chucNangLamMoi();
		}else if(o.equals(custView.btnExit)) {
			custView.dispose();
		}else if(o.equals(custView.btnFind)) {
			custView.chucNangFind();
		}else if(o.equals(custView.btnChoose)) {
			custView.chucNangChon();
		}
		
	}

}
