package component;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.OrderList;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class OrderStat extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	OrderList orderList = null;
	List<OrderList> orderData = new ArrayList<OrderList>();
	int totalSum = 0;
	
	private String convertCuisine(final int cuisineNo) {
		switch(cuisineNo) {
		case 1:
			return "한식";
		case 2:
			return "중식";
		case 3:
			return "일식";
		case 4:
			return "양식";
		default:
			return "한식";
		}
	}

	/**
	 * Create the frame.
	 */
	public OrderStat(Map<Integer, Integer> orderSumMap) {
		orderList = new OrderList();
		orderList.getOrderlist();

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel(new BorderLayout());
		panel_1.setBounds(7, 33, 241, 153);
		contentPane.add(panel_1);

		model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};

		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model);
		Color color = Color.BLACK;
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		
		model.addColumn("종류");
		model.addColumn("주문수량");

		model.setNumRows(0);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(6, 0);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);

		for(int cuisineNo = 1; cuisineNo<=4; cuisineNo++) {
//			System.out.println(String.format("%d %s %d", cuisineNo, convertCuisine(cuisineNo), orderSumMap.get(cuisineNo)));
			Object[] obj = { 
					convertCuisine(cuisineNo), 
					orderSumMap.get(cuisineNo)
			};

			totalSum += orderSumMap.get(cuisineNo);
			model.addRow(obj);
		}
		
		table.setRowSorter(new TableRowSorter(model));

		JButton btnClose = new JButton("닫기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(207, 6, 40, 20);
		contentPane.add(btnClose);
		
		JLabel labelSum = new JLabel("합계 : ");
		labelSum.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSum.setBounds(160, 198, 87, 16);
		contentPane.add(labelSum);
		
		labelSum.setText(String.format("합계 : %d", totalSum));
	}
}
