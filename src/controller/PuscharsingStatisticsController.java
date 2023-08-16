package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.statistics.PurchasingStatisticsView;

public class PuscharsingStatisticsController implements ActionListener{
	private PurchasingStatisticsView purchasingStatisticsView;

	public PuscharsingStatisticsController(PurchasingStatisticsView purchasingStatisticsView) {
		this.purchasingStatisticsView = purchasingStatisticsView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(purchasingStatisticsView.btnClear)) {
			purchasingStatisticsView.handlerActionClear();
		} else if (o.equals(purchasingStatisticsView.btnChooseVendor)) {
			purchasingStatisticsView.showChooseVendorView();;
		} else if (o.equals(purchasingStatisticsView.btnChooseEmployee)) {
			purchasingStatisticsView.showChooseEmployeeView();
		} else if (o.equals(purchasingStatisticsView.btnChooseProduct)) {
			purchasingStatisticsView.showChooseProductView();
		} else if (o.equals(purchasingStatisticsView.btnFind)) {
			purchasingStatisticsView.handlerActionFind();
		}

	}
}
