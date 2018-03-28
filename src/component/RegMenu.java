package component;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Meal;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class RegMenu extends JFrame {

	private JPanel contentPane;
	Meal meal;
	List<Meal> mealData = new ArrayList<Meal>();

	private int cuisineNo = 1;
	private JTextField textFieldMenuName;
	/**
	 * Create the frame.
	 */
	public RegMenu(final int selectMealNo, final int cuNo, final String mealName, final int price, final int maxCount) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		meal = new Meal();
		meal.getMealUseCuisineNo(cuisineNo);
		mealData = meal.getMealData();

		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"한식", "중식", "일식", "양식"}));
		comboBoxType.setSelectedIndex(cuNo - 1);
		comboBoxType.setBounds(156, 17, 161, 27);
		comboBoxType.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				cuisineNo = comboBoxType.getSelectedIndex()+1;
				meal.getMealUseCuisineNo(cuisineNo);

				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealName());
				});
			}
		});
		contentPane.add(comboBoxType);

		JComboBox comboBoxMaxCount = new JComboBox();
		comboBoxMaxCount.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));
		comboBoxMaxCount.setSelectedItem(String.valueOf(maxCount));
		comboBoxMaxCount.setBounds(156, 200, 161, 27);
		contentPane.add(comboBoxMaxCount);

		JLabel lblNewLabel = new JLabel("종류");
		lblNewLabel.setBounds(6, 22, 100, 16);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("메뉴명");
		label.setBounds(6, 83, 100, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("가격");
		label_1.setBounds(6, 144, 100, 16);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("조리가능수량");
		label_2.setBounds(6, 205, 100, 16);
		contentPane.add(label_2);

		JComboBox comboBoxPrice = new JComboBox();
		comboBoxPrice.setModel(new DefaultComboBoxModel(new String[] {"1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000", "5500", "6000", "6500", "7000", "7500", "8000", "8500", "9000", "9500", "10000", "10500", "11000", "11500", "12000"}));
		comboBoxPrice.setSelectedItem(String.valueOf(price));
		comboBoxPrice.setBounds(156, 139, 161, 27);
		contentPane.add(comboBoxPrice);

		JButton btnRegMenu = new JButton("수정");
		btnRegMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menuName = textFieldMenuName.getText();
				if (menuName.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
							"메뉴명을 입력해주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
				} else {
					mealData.forEach((item) -> {
						if (item.getMealName().equals(menuName)) {
							System.out.println(item.getMealName());

							textFieldMenuName.setText("");
							return;
						}				
					});

					JOptionPane.showMessageDialog(contentPane,
							"메뉴가 수정되었습니다.",
							"Message",
							JOptionPane.INFORMATION_MESSAGE);

					String price = (String ) comboBoxPrice.getSelectedItem();
					String maxCount = (String) comboBoxMaxCount.getSelectedItem();

					//						meal.insertNewMenu(cuisineNo, menuName, price, maxCount);
					meal.updateMenu(selectMealNo, cuisineNo, menuName, price, maxCount);

					System.out.println(String.format("%d %s %s %s", cuisineNo, menuName, maxCount, price));

					textFieldMenuName.setText("");
					comboBoxType.setSelectedIndex(0);
					comboBoxMaxCount.setSelectedIndex(1);
					comboBoxPrice.setSelectedIndex(10);

					dispose();
				}
			}
		});

		btnRegMenu.setBounds(36, 239, 117, 29);
		contentPane.add(btnRegMenu);

		JButton btnClose = new JButton("닫기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(189, 239, 117, 29);
		contentPane.add(btnClose);

		textFieldMenuName = new JTextField(mealName);
		textFieldMenuName.setBounds(156, 78, 161, 26);
		contentPane.add(textFieldMenuName);
		textFieldMenuName.setColumns(10);
	}

	public RegMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		meal = new Meal();
		meal.getMealUseCuisineNo(cuisineNo);
		mealData = meal.getMealData();

		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"한식", "중식", "일식", "양식"}));
		comboBoxType.setSelectedIndex(0);
		comboBoxType.setBounds(156, 17, 161, 27);
		comboBoxType.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				cuisineNo = comboBoxType.getSelectedIndex()+1;
				meal.getMealUseCuisineNo(cuisineNo);

				mealData = meal.getMealData();

				mealData.forEach((item) -> {
					System.out.println(item.getMealName());
				});
			}
		});
		contentPane.add(comboBoxType);

		JComboBox comboBoxMaxCount = new JComboBox();
		comboBoxMaxCount.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));
		comboBoxMaxCount.setSelectedIndex(1);
		comboBoxMaxCount.setBounds(156, 200, 161, 27);
		contentPane.add(comboBoxMaxCount);

		JLabel lblNewLabel = new JLabel("종류");
		lblNewLabel.setBounds(6, 22, 100, 16);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("메뉴명");
		label.setBounds(6, 83, 100, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("가격");
		label_1.setBounds(6, 144, 100, 16);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("조리가능수량");
		label_2.setBounds(6, 205, 100, 16);
		contentPane.add(label_2);

		JComboBox comboBoxPrice = new JComboBox();
		comboBoxPrice.setModel(new DefaultComboBoxModel(new String[] {"1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000", "5500", "6000", "6500", "7000", "7500", "8000", "8500", "9000", "9500", "10000", "10500", "11000", "11500", "12000"}));
		comboBoxPrice.setSelectedIndex(10);
		comboBoxPrice.setBounds(156, 139, 161, 27);
		contentPane.add(comboBoxPrice);

		JButton btnRegMenu = new JButton("등록");
		btnRegMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menuName = textFieldMenuName.getText();
				if (menuName.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
							"메뉴명을 입력해주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
				} else {
					mealData.forEach((item) -> {
						if (item.getMealName().equals(menuName)) {
							System.out.println(item.getMealName());


							textFieldMenuName.setText("");
							return;
						}				
					});

					if (textFieldMenuName.getText().isEmpty()) {

						JOptionPane.showMessageDialog(contentPane,
								"동일한 메뉴가 있습니다.",
								"Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"메뉴가 등록되었습니다.",
								"Message",
								JOptionPane.INFORMATION_MESSAGE);

						// cuisineNo
						// menuName, 
						String price = (String ) comboBoxPrice.getSelectedItem();
						String maxCount = (String) comboBoxMaxCount.getSelectedItem();

						meal.insertNewMenu(cuisineNo, menuName, price, maxCount);
						System.out.println(String.format("%d %s %s %s", cuisineNo, menuName, maxCount, price));

						textFieldMenuName.setText("");
						comboBoxType.setSelectedIndex(0);
						comboBoxMaxCount.setSelectedIndex(1);
						comboBoxPrice.setSelectedIndex(10);
					}
				}
			}
		});
		btnRegMenu.setBounds(36, 239, 117, 29);
		contentPane.add(btnRegMenu);

		JButton btnClose = new JButton("닫기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(189, 239, 117, 29);
		contentPane.add(btnClose);

		textFieldMenuName = new JTextField();
		textFieldMenuName.setBounds(156, 78, 161, 26);
		contentPane.add(textFieldMenuName);
		textFieldMenuName.setColumns(10);
	}
}
