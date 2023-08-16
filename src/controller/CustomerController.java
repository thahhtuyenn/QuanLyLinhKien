package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.customer.CustomerView;
import view.employee.EmployeeView;

public class CustomerController implements ActionListener,MouseListener{
	private CustomerView customerView;

	public CustomerController(CustomerView customerView) {
		this.customerView = customerView;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		customerView.actionShowInfo();
		
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
		if (customerView.btnAdd.equals(o)) {
			customerView.handlerActionAdd();
		} else if (customerView.btnClear.equals(o)) {
			customerView.handlerActionClear();
		} else if (customerView.btnRemove.equals(o)) {
			customerView.handlerActionRemove();
		} else if (customerView.btnUpdate.equals(o)) {
			customerView.handlerActionUpdate();
		} else if (customerView.btnFind.equals(o)) {
			customerView.handlerActionFind();
		} else if(customerView.btnExport.equals(o)) {
			customerView.handlerExport();
		}

	}

}
