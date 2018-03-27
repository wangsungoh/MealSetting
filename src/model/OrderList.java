package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

}
