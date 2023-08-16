package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.role.RolesView;

public class RoleController implements ActionListener, MouseListener{
	private RolesView rolesView;
	public RoleController(RolesView rolesView) {
		this.rolesView = rolesView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(rolesView.btnAdd.equals(object)) {
			rolesView.handlerActionAdd();
		} else if(rolesView.btnClear.equals(object)) {
			rolesView.handlerActionClear();
		} else if(rolesView.btnRemove.equals(object)) {
			rolesView.handlerActionRemove();
		} else if(rolesView.btnUpdate.equals(object)) {
			rolesView.handlerActionUpdate();
		} else if(rolesView.btnFind.equals(object)) {
			rolesView.handlerActionFind();
		} else if(rolesView.btnExport.equals(object)) {
			rolesView.handlerExport();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		rolesView.actionShowInfo();
		
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
