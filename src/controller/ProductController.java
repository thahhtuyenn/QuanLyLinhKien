package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utils.ExcelUtils;
import view.product.ProductView;

public class ProductController implements ActionListener, MouseListener {

	private ProductView productView;

	public ProductController(ProductView productView) {
		this.productView = productView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		productView.ShowInfor();

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
		if (o.equals(productView.btnExport)) {
			productView.handlerExport();
		} else if (productView.btnAdd.equals(o)) {
			productView.handlerActionAdd();
		} else if (productView.btnClean.equals(o)) {
			productView.handlerActionClean();
		} else if (productView.btnRemove.equals(o)) {
			productView.handlerActionRemove();
		} else if (productView.btnUpdate.equals(o)) {
			productView.handlerActionUpdate();
		} else if (productView.btnFind.equals(o)) {
			productView.handlerActionFind();
		} else if (productView.btnExport.equals(o)) {
			productView.handlerExport();
		}

	}

}
