import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Meal;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TicketFrame extends JFrame implements ActionListener{
	private JFrame frame;

	JPanel rootJp = new JPanel();
	JPanel topJp = new JPanel();
	JPanel centerJp = new JPanel();
	JPanel bottomJp = new JPanel();

	JButton jb1 = new JButton("식권 발매 프로그램");
	JButton jb2 = new JButton(new ImageIcon("./resources/menu_1.png"));
	JButton jb3 = new JButton(new ImageIcon("./resources/menu_2.png"));
	JButton jb4 = new JButton(new ImageIcon("./resources/menu_3.png"));
	JButton jb5 = new JButton(new ImageIcon("./resources/menu_4.png"));
	JButton jb6 = new JButton("시계");
	JButton jb7= new JButton("닫기");

	Calendar calendar1 = Calendar.getInstance(); 
	int year = calendar1.get(Calendar.YEAR);
	int month = calendar1.get(Calendar.MONTH)+1;
	int day = calendar1.get(Calendar.DAY_OF_MONTH);
	int hour = calendar1.get(Calendar.HOUR_OF_DAY); 
	int min = calendar1.get(Calendar.MINUTE); 
	int sec = calendar1.get(Calendar.SECOND); 

	javax.swing.Timer timer; 
	/**
	 * 
	 * Create the frame.
	 */
	public TicketFrame(JFrame main_frame) {
		setResizable(false);
		setTitle("식권 발매 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 570);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		timer = new javax.swing.Timer(1000, this); 
		timer.setInitialDelay(0); 
		timer.start(); 

		rootJp.setLayout(new BorderLayout());
		topJp.setLayout(new GridLayout(1, 0));
		centerJp.setLayout(new GridLayout(2, 2));
		bottomJp.setLayout(new GridLayout(2, 0));
		jb1.setEnabled(false);

		topJp.add(jb1);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meal meal = new Meal();
				meal.getMealUseCuisineNo(1);
				List<Meal> mealData = new ArrayList<Meal>();
				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealNo() + ", " 
							+ item.getCuisineNo() + ", "
							+ item.getMealName() + ", "
							+ item.getPrice() + ", "
							+ item.getMaxCount() + ", "
							+ item.getTodayMeal() + ", "
							);
				});

				PaymentFrame paymentFrame = new PaymentFrame(mealData, "한식");
				paymentFrame.setVisible(true);
			}
		});
		jb2.setToolTipText("한식");

		centerJp.add(jb2);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meal meal = new Meal();
				meal.getMealUseCuisineNo(2);
				List<Meal> mealData = new ArrayList<Meal>();
				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealNo() + ", " 
							+ item.getCuisineNo() + ", "
							+ item.getMealName() + ", "
							+ item.getPrice() + ", "
							+ item.getMaxCount() + ", "
							+ item.getTodayMeal() + ", "
							);
				});

				PaymentFrame paymentFrame = new PaymentFrame(mealData, "중식");
				paymentFrame.setVisible(true);
			}
		});
		jb3.setToolTipText("중식");
		centerJp.add(jb3);
		jb4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meal meal = new Meal();
				meal.getMealUseCuisineNo(3);
				List<Meal> mealData = new ArrayList<Meal>();
				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealNo() + ", " 
							+ item.getCuisineNo() + ", "
							+ item.getMealName() + ", "
							+ item.getPrice() + ", "
							+ item.getMaxCount() + ", "
							+ item.getTodayMeal() + ", "
							);
				});

				PaymentFrame paymentFrame = new PaymentFrame(mealData, "일식");
				paymentFrame.setVisible(true);
			}
		});
		jb4.setToolTipText("일식");
		centerJp.add(jb4);
		jb5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meal meal = new Meal();
				meal.getMealUseCuisineNo(4);
				List<Meal> mealData = new ArrayList<Meal>();
				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealNo() + ", " 
							+ item.getCuisineNo() + ", "
							+ item.getMealName() + ", "
							+ item.getPrice() + ", "
							+ item.getMaxCount() + ", "
							+ item.getTodayMeal() + ", "
							);
				});

				PaymentFrame paymentFrame = new PaymentFrame(mealData, "양식");
				paymentFrame.setVisible(true);
			}
		});
		jb5.setToolTipText("양식");
		centerJp.add(jb5);
		jb6.setEnabled(false);
		jb6.setForeground(Color.WHITE);
		jb6.setBackground(Color.BLACK);

		bottomJp.add(jb6);
		jb7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				main_frame.setVisible(true);
			}
		});
		bottomJp.add(jb7);

		rootJp.add(topJp, BorderLayout.NORTH);
		rootJp.add(centerJp, BorderLayout.CENTER);
		rootJp.add(bottomJp, BorderLayout.SOUTH);

		getContentPane().add(rootJp);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		++sec; 
		Calendar calendar2 = Calendar.getInstance(); 
		year = calendar1.get(Calendar.YEAR);
		month = calendar1.get(Calendar.MONTH)+1;
		day = calendar1.get(Calendar.DAY_OF_MONTH);
		hour = calendar2.get(Calendar.HOUR_OF_DAY); 
		min = calendar2.get(Calendar.MINUTE); 
		sec = calendar2.get(Calendar.SECOND);
		jb6.setText("현재시간 : " + year + "년 " + month + "월 " + day + "일 " + hour + "시 " + min + "분 " + sec + "초");
	}

}
