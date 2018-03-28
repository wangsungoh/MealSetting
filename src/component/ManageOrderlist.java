package component;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Meal;
import model.Member;
import model.OrderList;

public class ManageOrderlist extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;

	Member member;
	Meal meal;
	OrderList orderList = null;
	List<OrderList> orderData = new ArrayList<OrderList>();
	Map<Integer, String> memberMap = new HashMap<Integer, String>();
	Map<Integer, String> menuMap = new HashMap<Integer, String>();


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
	public ManageOrderlist() {
		member = new Member();
		member.getAllMember();
		
		meal = new Meal();
		meal.getAllMealData();
		
		memberMap = member.getMemberMap();
		menuMap = meal.getMenuMap();
		orderList = new OrderList();
		orderList.getOrderlist();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel(new BorderLayout());
		panel_1.setBounds(0, 50, 690, 700);
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
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model);
		
		model.addColumn("종류");
		model.addColumn("메뉴명");
		model.addColumn("사원명");
		model.addColumn("결제수량");
		model.addColumn("총결제금액");
		model.addColumn("결제일");
	
		model.setNumRows(0);
		
		orderData = orderList.getOrderData();
		
		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);

		orderData.forEach((item) -> {
			System.out.println("GEEEEET >> " + item.getMemberNo() + " , " + memberMap.get(item.getMemberNo()));
			
			Object[] obj = { 
					convertCuisine(item.getCuisineNo()), 
					menuMap.get(item.getMealNo()), 
					memberMap.get(item.getMemberNo()), 
					item.getOrderCount(), 
					item.getAmount(), 
					item.getOrderDate().split(" ")[0] 
			};

			model.addRow(obj);
		});

	} // 생성자 
	
}
