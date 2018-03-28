import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.table.DefaultTableModel;

import component.Ticket;
import model.Meal;
import model.Member;
import model.OrderList;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ListSelectionModel;

public class PaymentFrame extends JFrame implements DocumentListener {
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	Meal currentMeal = null;
	List<Object> tableData = new ArrayList<Object>();
	Object[][] data = {};
	private int currentMealCount = 0;
	Member member;
	int cuisineType = -1;

	static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}

	/**
	 * Create the frame.
	 */
	public PaymentFrame(List<Meal> mealData, String title, JFrame main_frame) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		member = new Member();

		switch(title) {
		case "한식":
			this.cuisineType = 1;
			break;
		case "중식":
			this.cuisineType = 2;
			break;
		case "일식":
			this.cuisineType = 3;
			break;
		case "양식":
			this.cuisineType = 4;
			break;
		}
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setBounds(412, 10, 130, 35);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("총결제금액: ");
		lblNewLabel_1.setBounds(667, 80, 130, 15);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("원");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(823, 80, 188, 15);
		contentPane.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(667, 437, 392, 26);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel label_1 = new JLabel("선택품명:");
		label_1.setBounds(5, 8, 64, 15);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(label_1);

		textField = new JTextField();
		textField.getDocument().addDocumentListener(this);

		textField.setBounds(294, 5, 91, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		//		textField.setText(String.valueOf(currentMealCount));

		JLabel label_3 = new JLabel("수량:");
		label_3.setBounds(239, 8, 43, 15);
		panel_2.add(label_3);

		JLabel label_2 = new JLabel("음식이름");
		label_2.setBounds(81, 8, 126, 15);
		panel_2.add(label_2);

		JPanel panel = new JPanel();
		panel.setBounds(12, 80, 643, 417);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 4));

		mealData.forEach((item) -> {
			System.out.println(item.getMealNo() + ", " 
					+ item.getCuisineNo() + ", "
					+ item.getMealName() + ", "
					+ item.getPrice() + ", "
					+ item.getMaxCount() + ", "
					+ item.getTodayMeal() + ", "
					);

			if (item.getMaxCount() > 1 && item.getTodayMeal() == 1) {
				JButton jbtn = new JButton(item.getMealName()+"("+item.getPrice()+")");
				jbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						label_2.setText(item.getMealName());
						currentMeal = item;
						currentMeal.setBtn(jbtn);
					}
				});

				panel.add(jbtn);
			}
		});


		JPanel panel_1 = new JPanel(new BorderLayout());
		panel_1.setBounds(666, 105, 393, 322);
		contentPane.add(panel_1);

		String[] columnNames = {"상품번호",
				"품명",
				"수량",
		"금액"};

		DefaultTableModel model = new DefaultTableModel(); 

		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column){
				return false;
			}	
		};

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();

				if (mouseEvent.getClickCount() == 2) {
					int row = table.rowAtPoint(point);

					if (row >= 0) {
						int selectMealNo = (int)model.getValueAt(row, 0);
						mealData.forEach((item) -> {
							if (item.getMealNo() == selectMealNo) {
								System.out.println("test " + row + ", selectMealNo : " + selectMealNo);

								model.removeRow(row);
								item.getBtn().setEnabled(true);

								return;
							}
						});	
					}
				}
			}
		});

		model.addColumn("상품번호");
		model.addColumn("품명");
		model.addColumn("수량");
		model.addColumn("금액");

		panel_1.add(table, BorderLayout.CENTER);		
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(667, 473, 392, 24);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("입력");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ttt = textField.getText();
				int countOfMeal = 0;

				if (ttt.length() > 0) {
					countOfMeal = Integer.parseInt(ttt);
				} else {
					countOfMeal = 0;
				}

				if (currentMeal == null && label_2.getText().equals("음식이름")) {
					JOptionPane.showMessageDialog(main_frame,
							"메뉴를 선택해 주세요..",
							"Message",
							JOptionPane.ERROR_MESSAGE);
				} else if (countOfMeal == 0) {
					JOptionPane.showMessageDialog(main_frame,
							"수량을입력해주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
				} else if (currentMeal.getMaxCount() < countOfMeal) {
					JOptionPane.showMessageDialog(main_frame,
							"조리가능수량이 부족합니다.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println(currentMeal.getMealName() + ", " + countOfMeal);

					Object[] obj = {currentMeal.getMealNo(), currentMeal.getMealName(), countOfMeal, countOfMeal*currentMeal.getPrice()};
					tableData.add(obj);
					tableData.toArray( data );

					data = append(data, obj);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(obj);
					int sum = 0;
					for (int i = 0; i < model.getRowCount(); i++) {
						sum += (int)model.getValueAt(i, 3);
					}

					DecimalFormat db = new DecimalFormat("###,###,###,###");

					System.out.println(String.valueOf(sum));
					label.setText(db.format(sum)+" 원");

					label_2.setText("음식이름");
					textField.setText("0");
					currentMeal.getBtn().setEnabled(false);
				}
			}
		});

		btnNewButton.setBounds(25, 0, 97, 23);
		panel_3.add(btnNewButton);

		JButton button = new JButton("결제");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				if (model.getRowCount() == 0 || currentMeal == null && label_2.getText().equals("음식이름")) {
					JOptionPane.showMessageDialog(main_frame,
							"매뉴를 선택해 주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				List<String> memberNameList = new ArrayList<String>();
				member.getAllMember();
				member.getMemberData().forEach((item)-> {
					System.out.println(">>>>> " + item.getMemberNo() + " " + item.getMemberName() + " " + item.getPasswd());
					memberNameList.add(String.valueOf(item.getMemberNo()));
				});

				String[] appArray = new String[memberNameList.size()];
				appArray = memberNameList.toArray(appArray);

				JPanel fields = new JPanel(new GridLayout(2, 1));
				JTextField field = new JTextField(10);
				JPasswordField passwordField = new JPasswordField();
				JComboBox<String> comboBox = new JComboBox<>(appArray);

				fields.add(new JLabel("사원번호"));
				fields.add(comboBox);
				fields.add(new JLabel("패스워드"));
				fields.add(passwordField);

				int result = JOptionPane.showConfirmDialog(null, fields, "Breakfast", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				case JOptionPane.OK_OPTION:

					String cMemberPasswd = "";

					int sMemberNo = Integer.valueOf((String) comboBox.getSelectedItem());

					for(int i = 0; i < member.getMemberData().size(); i++) {
						if (member.getMemberData().get(i).getMemberNo() == sMemberNo) {
							System.out.println("sMemberNo : " + sMemberNo +" , " + member.getMemberData().get(i).getMemberNo() + ", " + member.getMemberData().get(i).getPasswd());
							cMemberPasswd = member.getMemberData().get(i).getPasswd();
						}
					}

					if (!cMemberPasswd.equals(String.valueOf(passwordField.getPassword()))){
						JOptionPane.showMessageDialog(main_frame,
								"패스워드가 일치하지 않습니다.",
								"Message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(main_frame,
								"결제가 완료되었습니다.\r\n식권을 출력합니다.",
								"Message",
								JOptionPane.INFORMATION_MESSAGE);

						List<Object> tableData = new ArrayList<Object>();

						Calendar calendar1 = Calendar.getInstance(); 

						int year = calendar1.get(Calendar.YEAR);
						int month = calendar1.get(Calendar.MONTH)+1;
						int day = calendar1.get(Calendar.DAY_OF_MONTH);
						int hour = calendar1.get(Calendar.HOUR_OF_DAY); 
						int min = calendar1.get(Calendar.MINUTE); 
						int sec = calendar1.get(Calendar.SECOND);
						String ticketSerial = String.format("%d%d%d%d%d%d-%d", year, month, day, hour, min, sec, sMemberNo );
						String orderDate = String.format("%d-%d-%d %d:%d:%d", year, month, day, hour, min, sec);
						for(int i = 0 ; i < model.getRowCount(); i++) {
							int mealCount = (int) model.getValueAt(i, 2);
							Color ticketColor;
							if (i%2 == 0) {
								ticketColor = Color.PINK;
							} else {
								ticketColor = Color.BLUE;
							}

							for(int cnt = 1; cnt <= mealCount; cnt++ ){
								List<Object> colData = new ArrayList<Object>();

								colData.add(ticketSerial);
								colData.add(model.getValueAt(i, 0));
								colData.add(model.getValueAt(i, 1));
								String menuString = String.format("메뉴 : %s", model.getValueAt(i, 2));
								colData.add(menuString);

								int mealPrice = (int)model.getValueAt(i, 3)/mealCount;
								DecimalFormat db = new DecimalFormat("###,###,###,###");

								colData.add(db.format(mealPrice)+"원");

								String mealCntPer = String.format("%d/%d", cnt, mealCount);
								colData.add(mealCntPer);
								colData.add(ticketColor);

								tableData.add(colData);
							}
						}
						
						OrderList order = new OrderList();
						order.insertNewOrder(cuisineType, sMemberNo, orderDate, model);

						Meal meal = new Meal();
						meal.updateMeal(model);
						
						Ticket ticket = new Ticket(main_frame, tableData);
						ticket.setVisible(true);
					}

					break;
				}
			}
		});
		button.setBounds(147, 0, 97, 23);
		panel_3.add(button);

		JButton button_1 = new JButton("닫기");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(269, 0, 97, 23);
		panel_3.add(button_1);
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}
}
