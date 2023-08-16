package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.categoryProduct.CategoryProductView;
import view.employee.EmployeeView;

public class CategoryProductController implements MouseListener, ActionListener {

	private CategoryProductView categoryProductView;

	public CategoryProductController(CategoryProductView categoryProductView) {
		this.categoryProductView = categoryProductView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		categoryProductView.ShowInfor();

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
		if (categoryProductView.btnAdd.equals(o)) {
			categoryProductView.handlerActionAdd();
		} else if (categoryProductView.btnClear.equals(o)) {
			categoryProductView.handlerActionClear();
		} else if (categoryProductView.btnRemove.equals(o)) {
			categoryProductView.handlerActionRemove();
		} else if (categoryProductView.btnUpdate.equals(o)) {
			categoryProductView.handlerActionUpdate();
		} else if (categoryProductView.btnFind.equals(o)) {
			categoryProductView.handlerActionFind();
		} else if(categoryProductView.btnExport.equals(o)) {
			categoryProductView.handlerExport();
		}

	}

}
