import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Cuisine;
import model.Meal;
import model.Member;
import model.OrderList;
import util.Jdbc;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setTitle("메인");
		frame.setBounds(100, 100, 239, 195);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		RegUserFrame regUserFrame = new RegUserFrame(frame);
		
		JButton btnRegUserButton = new JButton("사원등록");
		btnRegUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				regUserFrame.setVisible(true);
			}
		});
		btnRegUserButton.setBounds(6, 5, 227, 37);
		frame.getContentPane().add(btnRegUserButton);
		
		JButton btnUserButton = new JButton("사용자");
		btnUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUserButton.setBounds(6, 47, 227, 37);
		frame.getContentPane().add(btnUserButton);
		
		JButton btnManagerButton = new JButton("관리자");
		btnManagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManagerButton.setBounds(6, 89, 227, 37);
		frame.getContentPane().add(btnManagerButton);
		
		JButton btnExitButton = new JButton("종료");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitButton.setBounds(6, 131, 227, 37);
		frame.getContentPane().add(btnExitButton);
	}
}
