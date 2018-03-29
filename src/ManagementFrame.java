import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.ImagePanel;
import component.ManageMenu;
import component.ManageOrderlist;
import component.OrderStat;
import component.RegMenu;
import model.OrderList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagementFrame extends JFrame {

	private JPanel contentPane;

	OrderList orderList = null;
	
	/**
	 * Create the frame.
	 */
	public ManagementFrame(JFrame main_frame) {
		orderList = new OrderList();
		
		setTitle("관리");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(16, 45, 574, 289);
		contentPane.add(imagePanel);
		
		JButton btnRegMenu = new JButton("메뉴등록");
		btnRegMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegMenu regMenu = new RegMenu();
				
				regMenu.setVisible(true);
			}
		});
		btnRegMenu.setBounds(6, 6, 117, 29);
		contentPane.add(btnRegMenu);

		JButton btnManageMenu = new JButton("메뉴관리");
		btnManageMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageMenu manageMenu = new ManageMenu();
				
				manageMenu.setVisible(true);
			}
		});
		btnManageMenu.setBounds(129, 6, 117, 29);
		contentPane.add(btnManageMenu);
		
		JButton btnOrderList = new JButton("결제조회");
		btnOrderList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageOrderlist manageOrderlist = new ManageOrderlist();
				
				manageOrderlist.setVisible(true);
			}
		});
		btnOrderList.setBounds(252, 6, 117, 29);
		contentPane.add(btnOrderList);
		
		JButton btnMenuOrder = new JButton("메뉴별 주문현황");
		btnMenuOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderList.getOrderSummary();
				
				OrderStat orderStat = new OrderStat(orderList.getOrderSumMap());
				orderStat.setVisible(true);
			}
		});
		btnMenuOrder.setBounds(375, 6, 135, 29);
		contentPane.add(btnMenuOrder);
		
		JButton btnClose = new JButton("종료");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				main_frame.setVisible(true);
			}
		});
		btnClose.setBounds(522, 6, 75, 29);
		contentPane.add(btnClose);
	}
}
