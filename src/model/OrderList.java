package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import util.Jdbc;

public class OrderList {
	private int orderNo;
	private int cuisineNo;
	private int mealNo;
	private int memberNo;
	private int orderCount;
	private int amount;
	private String orderDate;

	private String tableNameString = "orderlist";

	private String orderNoString = "orderNo";
	private String cuisineNoString = "cuisineNo";
	private String mealNoString = "mealNo";
	private String memberNoString = "memberNo";
	private String orderCountString = "orderCount";
	private String amountString = "amount";
	private String orderDateString = "orderDate";

	private String pKey = orderNoString;

	List<OrderList> orderData = new ArrayList<OrderList>();

	public OrderList() {
		
	}
	
	public OrderList(final int orderNo, final int cuisineNo, final int mealNo, final int memberNo, final int orderCount, final int amount, final String orderDate) {
		this.orderNo = orderNo;
		this.cuisineNo = cuisineNo;
		this.mealNo = mealNo;
		this.memberNo = memberNo;
		this.orderCount = orderCount;
		this.amount = amount;
		this.orderDate = orderDate;
	}
	
	public void insertNewOrder(final int cuisineNo, final int memberNo, final String orderDate, final DefaultTableModel model) {
		Jdbc db = new Jdbc();
		db.connectUser();

		for(int row = 0 ; row < model.getRowCount(); row++) {
			int mealNo = (int) model.getValueAt(row,  0);
			int orderCount = (int) model.getValueAt(row,  2);
			int amount = (int) model.getValueAt(row,  3);

			db.queryDb(db.getConnection(), "INSERT INTO `meal`.`orderlist` "
					+ "(`cuisineNo`, `mealNo`, `memberNo`, `orderCount`, `amount`, `orderDate`) "
					+ "VALUES "
					+ "('" + cuisineNo + "', '" + mealNo + "', '" + memberNo + "', '" + orderCount + "', '" + amount + "', '" + orderDate + "')");
		}

		db.closeConnection();
	}

	public List<OrderList> getOrderData() {
		return this.orderData;
	}

	public void getOrderlist() {
		Jdbc db = new Jdbc();
		db.connectUser();
		ResultSet rs = db.selectDb(db.getConnection(), "SELECT * FROM meal.orderlist");
		orderData.clear();

		try {
			while(rs.next()) {
				int orderNo = rs.getInt("orderNo");
				int cuisineNo = rs.getInt("cuisineNo");
				int mealNo = rs.getInt("mealNo");
				int memberNo = rs.getInt("memberNo");
				int orderCount = rs.getInt("orderCount");
				int amount = rs.getInt("amount");
				String orderDate = rs.getString("orderDate");

				OrderList orderlist = new OrderList(orderNo, cuisineNo, mealNo, memberNo, orderCount, amount, orderDate);
				
				orderData.add(orderlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}
	
	public void initializeOrderList() {
		Jdbc db = new Jdbc();
		db.connectUser();
		/**
		 * Source file to read data from.
		 */
		InputStream inputStream = Meal.class.getResourceAsStream("/DataFiles/orderlist.txt");
		String line;

		/**
		 * Looping the read block until all lines in the file are read.
		 */
		try {
			BufferedReader bReader;
			bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			bReader.readLine(); // this will read the first line

			while ((line = bReader.readLine()) != null) {
				/**
				 * Splitting the content of tabbed separated line
				 */
				String datavalue[] = line.split("\t");
				String value1 = datavalue[0];
				String value2 = datavalue[1];
				String value3 = datavalue[2];
				String value4 = datavalue[3];
				String value5 = datavalue[4];
				String value6 = datavalue[5];
				String value7 = datavalue[6];

				/**
				 * Printing the value read from file to the console
				 */
				System.out.println(value1 + " " + value2 + " " + value3 + " " + value4 + " " + value5 + " " + value6 + " " + value7);
				db.queryDb(db.getConnection(), "INSERT INTO `orderlist` "
						+ "(`orderNo`, `cuisineNo`, `mealNo`, `memberNo`, `orderCount`, `amount`, `orderDate`) "
						+ "VALUES ('" 
						+ value1 
						+ "', '" + value2 
						+ "', '" + value3 
						+ "', '" + value4 
						+ "', '" + value5
						+ "', '" + value6
						+ "', '" + value7
						+ "')");
			}

			bReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		db.closeConnection();
	}

	public String getTableNameString() {
		return tableNameString;
	}

	public String getOrderNoString() {
		return orderNoString;
	}

	public String getCuisineNoString() {
		return cuisineNoString;
	}

	public String getMealNoString() {
		return mealNoString;
	}

	public String getMemberNoString() {
		return memberNoString;
	}

	public String getOrderCountString() {
		return orderCountString;
	}

	public String getAmountString() {
		return amountString;
	}

	public String getOrderDateString() {
		return orderDateString;
	}

	public String getPkeyString() {
		return pKey;
	}

	/*
	 * 	private int orderNo;
	private int cuisineNo;
	private int mealNo;
	private int memberNo;
	private int orderCount;
	private int amount;
	private String orderDate;

	 */
	
	public int getOrderNo() {
		return this.orderNo;
	}

	public int getCuisineNo() {
		return this.cuisineNo;
	}

	public int getMealNo() {
		return this.mealNo;
	}
	
	public int getMemberNo() {
		return this.memberNo;
	}
	
	public int getOrderCount() {
		return this.orderCount;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getOrderDate() {
		return this.orderDate;
	}
}
