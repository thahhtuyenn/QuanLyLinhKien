package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.sales.ChoosePromotionView;

public class ChoosePromotionController implements ActionListener, MouseListener {
	private ChoosePromotionView promoView;

	public ChoosePromotionController(ChoosePromotionView promoView) {
		this.promoView = promoView;
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
		if (o.equals(promoView.btnClean)) {
			promoView.chucNangLamMoi();
		} else if (o.equals(promoView.btnThoat)) {
			promoView.dispose();
		} else if (o.equals(promoView.btnFind)) {
			promoView.chucNangFind();
		} else if (o.equals(promoView.btnChon)) {
			promoView.chucNangChon();
		}

	}

}
