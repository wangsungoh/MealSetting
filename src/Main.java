import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Cuisine;
import model.Meal;
import model.Member;
import model.OrderList;
import util.Jdbc;

public class Main {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		Jdbc db = new Jdbc();
		Member member = new Member();
		Cuisine cuisine = new Cuisine();
		Meal meal = new Meal();
		OrderList orderlist = new OrderList();
		
		db.initializeDb();
		
		member.initializeMember();
		cuisine.initializeCuisine();
		meal.initializeMeal();
		orderlist.initializeOrderList();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
