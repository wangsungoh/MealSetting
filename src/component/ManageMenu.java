package component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Meal;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ManageMenu extends JFrame implements DocumentListener {
	private static final int BOOLEAN_COLUMN = 0;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	Meal meal = null;
	int cuisineNo = 1;
	List<Meal> mealData = new ArrayList<Meal>();
	List<Object> tableData = new ArrayList<Object>();
	Meal currentMeal = null;
	Boolean allSelectBtn = true;
	JButton btnSelectAll = null;
	String selectMealStr = "";
	int selectMealNo = -1;

	public class CheckBoxModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();

			if (column == BOOLEAN_COLUMN) {
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(column);
				Boolean checked = (Boolean) model.getValueAt(row, column);
				if (checked) {
//					System.out.println(columnName + ": " + true);
				} else {
//					System.out.println(columnName + ": " + false);
					allSelectBtn = true;
					btnSelectAll.setEnabled(allSelectBtn);
				}
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public ManageMenu() {
		meal = new Meal();
		meal.getMealUseCuisineNo(cuisineNo);

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
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				default:
					return String.class;
				}
			}
		};

		JPanel tablePanel = new JPanel(new BorderLayout());
		//		table = new JTable(model) {
		//			public boolean isCellEditable(int row, int column){
		//				return false;
		//			}	
		//		};
		table = new JTable(model);
		table.getModel().addTableModelListener(new CheckBoxModelListener());

		model.addColumn("select");
		model.addColumn("mealName");
		model.addColumn("price");
		model.addColumn("maxCount");
		model.addColumn("todayMeal");

		model.setNumRows(0);
		mealData = meal.getMealData();

		mealData.forEach((item) -> {
			Object[] obj = { false, item.getMealName(), item.getPrice(), item.getMaxCount(), item.getTodayMeal(), item.getMealNo() };

			tableData.add(obj);

			currentMeal = item;
			//			currentMeal.setCheckbox();

			model.addRow(obj);
		});

		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);

		btnSelectAll = new JButton("모두 선택");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				for (int row = 0; row < rowCount; row ++) {
//					System.out.println(model.getValueAt(row, 0) + " - " + model.getValueAt(row, 1));
					model.setValueAt(new Boolean(true), row, 0);
				}
				allSelectBtn = false;
				btnSelectAll.setEnabled(allSelectBtn);
			}
		});
		btnSelectAll.setBounds(0, 9, 92, 29);
		contentPane.add(btnSelectAll);

		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(239, 9, 66, 29);
		contentPane.add(btnSearch);

		JButton btnEdit = new JButton("수정");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				String mealName = "";
				int checkedCount = 0;
				int selectPrice = 0;
				int selectMaxCount = 0;
				
				for (int row = 0; row < rowCount; row++) {
					Boolean checked = (Boolean)model.getValueAt(row, 0);
					if (checked) {
						checkedCount++;
//						System.out.println(checked + ", " + model.getValueAt(row, 1) + ", " + 
//						model.getValueAt(row, 2) + ", " + model.getValueAt(row, 3));
						
						mealName = (String) model.getValueAt(row, 1);
						selectPrice = (int) model.getValueAt(row, 2);
						selectMaxCount = (int) model.getValueAt(row, 3);
						
						selectMealStr = (String) model.getValueAt(row, 1);
					}
				}
				
				if (checkedCount > 1) {
					JOptionPane.showMessageDialog(contentPane,
							"하나씩 수정가능합니다.",
							"Message",
							JOptionPane.ERROR_MESSAGE);

					return;
				} else if (checkedCount == 0) {
					JOptionPane.showMessageDialog(contentPane,
							"수정할 메뉴를 선택해주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
					
					return;
				} else {
//					System.out.println("EDIT");

					mealData.forEach((item) -> {
						if (selectMealStr.equals(item.getMealName())) {
//							System.out.println(selectMealStr + ", " + item.getMealName() + selectMealStr.equals(item.getMealName()));
							selectMealNo = item.getMealNo();
						}
					});
					
					RegMenu editMenu = new RegMenu(selectMealNo, cuisineNo, mealName, selectPrice, selectMaxCount);
					editMenu.setVisible(true);
				}
			}
		});

		btnEdit.setBounds(317, 9, 66, 29);
		contentPane.add(btnEdit);

		JButton btnRemove = new JButton("삭제");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				int checkedCount = 0;
				List<Integer> removeMealNoList = new ArrayList<Integer>();
				
				for (int row = 0; row < rowCount; row++) {
					Boolean checked = (Boolean)model.getValueAt(row, 0);
					if (checked) {
						checkedCount++;
						selectMealStr = (String) model.getValueAt(row, 1);

						mealData.forEach((item) -> {
							if (selectMealStr.equals(item.getMealName())) {
//								System.out.println(selectMealStr + ", " + item.getMealName() + selectMealStr.equals(item.getMealName()));
								removeMealNoList.add(item.getMealNo());
							}
						});
					}
				}
				
				if (checkedCount == 0) {
					JOptionPane.showMessageDialog(contentPane,
							"삭제할 메뉴를 선택해주세요.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
					
					return;
				} else {
//					System.out.println("DELETE");
					
					meal.deleteMenu(removeMealNoList);
					meal.getMealUseCuisineNo(cuisineNo);

					mealData = meal.getMealData();
					model.setNumRows(0);

					mealData.forEach((item) -> {
						Object[] obj = { false, item.getMealName(), item.getPrice(), item.getMaxCount(), item.getTodayMeal() };

						tableData.add(obj);

						currentMeal = item;

						model.addRow(obj);
					});
				}
			}
		});
		btnRemove.setBounds(393, 9, 64, 29);
		contentPane.add(btnRemove);

		JButton btnSelectToday = new JButton("오늘의 메뉴선정");
		btnSelectToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				int checkedCount = 0;
				List<Integer> selectTodayMealNoList = new ArrayList<Integer>();
				
				for (int row = 0; row < rowCount; row++) {
					Boolean checked = (Boolean)model.getValueAt(row, 0);
					if (checked) {
						checkedCount++;
						selectMealStr = (String) model.getValueAt(row, 1);

						mealData.forEach((item) -> {
							if (selectMealStr.equals(item.getMealName())) {
//								System.out.println(selectMealStr + ", " + item.getMealName() + selectMealStr.equals(item.getMealName()));
								selectTodayMealNoList.add(item.getMealNo());
							}
						});
					}
				}
				
				if (checkedCount >= 25) {
					JOptionPane.showMessageDialog(contentPane,
							"25개를 초과할 수 없습니다.",
							"Message",
							JOptionPane.ERROR_MESSAGE);
					
					return;
				} else {					
					meal.selectTodayMeal(selectTodayMealNoList);
					meal.getMealUseCuisineNo(cuisineNo);

					mealData = meal.getMealData();
					model.setNumRows(0);

					mealData.forEach((item) -> {
						Object[] obj = { false, item.getMealName(), item.getPrice(), item.getMaxCount(), item.getTodayMeal() };

						tableData.add(obj);

						currentMeal = item;

						model.addRow(obj);
					});
				}				
			}
		});
		btnSelectToday.setBounds(469, 9, 136, 29);
		contentPane.add(btnSelectToday);

		JButton btnClose = new JButton("닫기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(607, 9, 65, 29);
		contentPane.add(btnClose);

		JLabel lblNewLabel = new JLabel("종류:");
		lblNewLabel.setBounds(104, 9, 41, 29);
		contentPane.add(lblNewLabel);

		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"한식", "중식", "일식", "양식"}));
		comboBoxType.setSelectedIndex(0);
		comboBoxType.setBounds(135, 9, 92, 29);
		comboBoxType.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				allSelectBtn = true;
				btnSelectAll.setEnabled(allSelectBtn);

				cuisineNo = comboBoxType.getSelectedIndex()+1;
				meal.getMealUseCuisineNo(cuisineNo);

				mealData = meal.getMealData();
				model.setNumRows(0);

				mealData.forEach((item) -> {
					Object[] obj = { false, item.getMealName(), item.getPrice(), item.getMaxCount(), item.getTodayMeal() };

					tableData.add(obj);

					currentMeal = item;
					//					currentMeal.setCheckbox(checkBox);

					model.addRow(obj);
				});
			}
		});

		contentPane.add(comboBoxType);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}
}
