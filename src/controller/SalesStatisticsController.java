package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.statistics.SalesStatisticsView;

public class SalesStatisticsController implements ActionListener {
	private SalesStatisticsView salesStatisticsView;

	public SalesStatisticsController(SalesStatisticsView salesStatisticsView) {
		this.salesStatisticsView = salesStatisticsView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(salesStatisticsView.btnClear)) {
			salesStatisticsView.handlerActionClear();
		} else if(o.equals(salesStatisticsView.btnChooseCustomer)) {
			salesStatisticsView.showChooseCustomerView();
		} else if(o.equals(salesStatisticsView.btnChooseEmployee)) {
			salesStatisticsView.showChooseEmployeeView();
		} else if(o.equals(salesStatisticsView.btnChooseProduct)) {
			salesStatisticsView.showChooseProductView();
		} else if(o.equals(salesStatisticsView.btnFind)) {
			salesStatisticsView.handlerActionFind();
		}

	}

}
