
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.main.MainView;

public class MainController implements ActionListener, MouseListener {
	private MainView mView;

	public MainController(MainView view) {
		this.mView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(mView.btnSales)) {
			mView.showSalesView();
		} else if (o.equals(mView.btnSalesOrder)) {
			mView.showSalesOrderView();
		} else if (o.equals(mView.btnEmployee)) {
			mView.showEmployeeView();
		} else if (o.equals(mView.btnCustomer)) {
			mView.showCustomerView();
		} else if (o.equals(mView.btnVendor)) {
			mView.showVendorView();
		} else if (o.equals(mView.btnProduct)) {
			mView.showProductView();
		} else if (o.equals(mView.btnCategoryProduct)) {
			mView.showProductCategoryview();
		} else if (o.equals(mView.btnPurchasingOrder)) {
			mView.showPurchasingOrderView();
		} else if (o.equals(mView.btnPromotion)) {
			mView.showPromotionView();
		} else if (o.equals(mView.btnPurchasing)) {
			mView.showPurchasingView();
		} else if(o.equals(mView.btnRole)) {
			mView.showRolesView();
		} else if(o.equals(mView.btnStatistics)) {
			mView.showStatisticsView();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(mView.btnSetting)) {
			mView.showChangePasswordView();
		} else if(o.equals(mView.btnLogout)) {
			mView.showLoginView();
		}

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
