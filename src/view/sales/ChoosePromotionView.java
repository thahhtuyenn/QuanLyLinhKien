package view.sales;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ChoosePromotionController;
import entity.CustomerEntity;
import entity.PromotionEntity;
import interfaces.ISendData;
import service.PromotionService;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class ChoosePromotionView extends JFrame {

	private JPanel pnCenter;
	public DefaultTableModel tableKhuyenMaiModel;
	public JTable tableKhuyenMai;
	public JButton btnChon;
	public JButton btnThoat;
	private ChoosePromotionController choosePromoCtrl;
	private ISendData<PromotionEntity> iSendData = null;
	public JButton btnFind;
	public JButton btnClean;
	private JTextField textSearchByName;
	private List<PromotionEntity> list = new ArrayList<PromotionEntity>();
	private PromotionService promotionService = new PromotionService();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChoosePromotionView(ISendData<PromotionEntity> ic) {
		iSendData = ic;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Khuyến Mãi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 427);
		pnCenter = new JPanel();
		pnCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

		tableKhuyenMaiModel = new DefaultTableModel(new String[] { "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện",
				"Số phần trăm", "Ngày bắt đầu", "Ngày kết thúc" }, 0);
		pnCenter.setLayout(null);
		tableKhuyenMai = new JTable(tableKhuyenMaiModel);
		JScrollPane scTableKhuyenMai = new JScrollPane(tableKhuyenMai);
		scTableKhuyenMai.setBounds(10, 77, 597, 236);
		pnCenter.add(scTableKhuyenMai);

		setContentPane(pnCenter);

		btnChon = new JButton("Chọn");
		btnChon.setBackground(Color.CYAN);
		btnChon.setIcon(new ImageIcon(ChoosePromotionView.class.getResource("/images/icons8_ok_30px.png")));
		btnChon.setBounds(183, 323, 104, 43);
		pnCenter.add(btnChon);

		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.CYAN);
		btnThoat.setIcon(new ImageIcon(ChoosePromotionView.class.getResource("/images/icons8_cancel_30px_1.png")));
		btnThoat.setBounds(310, 323, 104, 43);
		pnCenter.add(btnThoat);

		textSearchByName = new JTextField();
		textSearchByName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textSearchByName.setColumns(10);
		textSearchByName.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm b\u1EB1ng t\u00EAn", TitledBorder.LEADING,

				TitledBorder.TOP, null, null));
		textSearchByName.setBounds(10, 21, 333, 42);
		pnCenter.add(textSearchByName);

		btnFind = new JButton("Tìm");
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFind.setBackground(Color.CYAN);
		btnFind.setBounds(364, 25, 104, 42);
		pnCenter.add(btnFind);

		btnClean = new JButton("Làm mới");
		btnClean.setIcon(new ImageIcon(ChoosePromotionView.class.getResource("/images/icons8_data_backup_30px.png")));
		btnClean.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClean.setBackground(Color.CYAN);
		btnClean.setBounds(488, 25, 129, 42);
		pnCenter.add(btnClean);

		choosePromoCtrl = new ChoosePromotionController(this);
		btnThoat.addActionListener(choosePromoCtrl);
		btnClean.addActionListener(choosePromoCtrl);
		btnFind.addActionListener(choosePromoCtrl);
		btnChon.addActionListener(choosePromoCtrl);

		loadDatabase();
	}

	public void chucNangLamMoi() {
		textSearchByName.setText("");
		tableKhuyenMai.setRowSelectionAllowed(false);
		loadDatabase();
	}

	public void chucNangFind() {

		String name = textSearchByName.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mãi");
			return;
		}
		list = promotionService.findByName(name);
		// load table
		tableKhuyenMaiModel.setRowCount(0);
		int stt = 1;
		for (PromotionEntity promotionEntity : list) {
			tableKhuyenMaiModel.addRow(new Object[] { stt++, promotionEntity.getPromotionId(),
					promotionEntity.getPromotionName(), promotionEntity.getPromotionPercent(),
					promotionEntity.getStartDate(), promotionEntity.getEndDate() });
		}
	}

	private void loadDatabase() {
		tableKhuyenMaiModel.setRowCount(0);
		tableKhuyenMai.setRowSelectionAllowed(false);
		list = promotionService.findAllPromotion();

		int stt = 1;
		for (PromotionEntity promotionEntity : list) {
			tableKhuyenMaiModel.addRow(new Object[] { stt++, promotionEntity.getPromotionId(),
					promotionEntity.getPromotionName(), promotionEntity.getPromotionPercent(),
					promotionEntity.getStartDate(), promotionEntity.getEndDate() });
		}
	}

	public void chucNangChon() {
		int index = tableKhuyenMai.getSelectedRow();
		if (index >= 0) {
			PromotionEntity promotionEntity = list.get(index);
			iSendData.send(promotionEntity);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyễn mãi");
		}

	}
}
