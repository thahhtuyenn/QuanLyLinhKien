package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.promotion.PromotionView;

public class PromotionController implements ActionListener, MouseListener{
	private PromotionView promotionView;
	public PromotionController(PromotionView promotionView) {
		this.promotionView = promotionView;		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		promotionView.mouseClick();
		
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
		if(o.equals(promotionView.btnAdd)) {
			promotionView.handlerActionAdd();
		}else if(o.equals(promotionView.btnClear)) {
			promotionView.handlerActionClear();
		}else if(o.equals(promotionView.btnRemove)) {
			promotionView.handlerActionRemove();
		}else if(o.equals(promotionView.btnUpdate)) {
			promotionView.handlerActionUpdate();
		}else if(o.equals(promotionView.btnSearch)) {
			promotionView.handlerActionSearch();
		} else if(o.equals(promotionView.btnExport)) {
			promotionView.handlerExport();
		}
		
	}
}
