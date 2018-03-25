import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Cuisine;
import model.Member;
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
		
		db.initializeDb();
		
		member.initializeMember();
		cuisine.initializeMember();
		
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
