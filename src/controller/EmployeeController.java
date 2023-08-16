package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.employee.EmployeeView;

public class EmployeeController implements ActionListener, MouseListener {
	private EmployeeView employeeView;

	public EmployeeController(EmployeeView employeeView) {
		this.employeeView = employeeView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		employeeView.actionShowInfo();

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
		if (employeeView.btnAdd.equals(o)) {
			employeeView.handlerActionAdd();
		} else if (employeeView.btnClear.equals(o)) {
			employeeView.handlerActionClear();
		} else if (employeeView.btnRemove.equals(o)) {
			employeeView.handlerActionRemove();
		} else if (employeeView.btnUpdate.equals(o)) {
			employeeView.handlerActionUpdate();
		} else if (employeeView.btnFind.equals(o)) {
			employeeView.handlerActionFind();
		} else if (employeeView.btnExport.equals(o)) {
			employeeView.handlerExport();
		}

	}

}
