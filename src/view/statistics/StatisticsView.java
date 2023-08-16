package view.statistics;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.main.MainView;

import java.awt.BorderLayout;

public class StatisticsView extends JPanel {

	/**
	 * Create the panel.
	 */
	public StatisticsView() {
		setBounds(new Rectangle(0, 0, 959, 619));
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 5, 939, 604);

		tabbedPane.addTab("Tổng quát", new ImageIcon(MainView.class.getResource("/images/icons8_bar_chart_30px.png")),
				new GeneralView());
		tabbedPane.addTab("Bán ra", new ImageIcon(StatisticsView.class.getResource("/images/icons8_small_business_30px_3.png")),
				new SalesStatisticsView());
		tabbedPane.addTab("Nhập vào", new ImageIcon(StatisticsView.class.getResource("/images/icons8_downloads_30px.png")),
				new PurchasingStatisticsView());
		add(tabbedPane);
	}
}
