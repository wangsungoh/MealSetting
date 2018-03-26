package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Jdbc;

public class Meal {
	private int mealNo;
	private int cuisineNo;
	private String mealName;
	private int price;
	private int maxCount;
	private int todayMeal;
	
	private String tableNameString = "meal";
	
 	private String mealNoString = "mealNo";
 	private String cuisineNoString = "cuisineNo";
 	private String mealNameString = "mealName";
 	private String priceString = "price";
 	private String maxCountString = "maxCount";
 	private String todayMealString = "todayMeal";

	private String pKey = mealNoString;
	List<Meal> mealData = new ArrayList<Meal>();

	public Meal(int mealNo, int cuisineNo, String mealName, int price, int maxCount, int todayMeal) {
		this.mealNo = mealNo;
		this.cuisineNo = cuisineNo;
		this.mealName = mealName;
		this.price = price;
		this.maxCount = maxCount;
		this.todayMeal = todayMeal;
	}
	
	public Meal() {
		
	}
	
	public void getMealUseCuisineNo(final int cuisineNo) {
		Jdbc db = new Jdbc();
		db.connectUser();
		ResultSet rs = db.selectDb(db.getConnection(), "select * from meal where cuisineNo=" + String.valueOf(cuisineNo));
		
		try {
			while(rs.next()) {
				int mealNo = rs.getInt("mealNo");
				int cuisineNo1 = rs.getInt("cuisineNo");
				String mealName = rs.getString("mealName");
				int price = rs.getInt("price");
				int maxCount = rs.getInt("maxCount");
				int todayMeal = rs.getInt("todayMeal");

				Meal meal = new Meal(mealNo, cuisineNo1, mealName, price, maxCount, todayMeal);
				
				mealData.add(meal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}

	public List<Meal> getMealData() {
		return this.mealData;
	}
	
	public void initializeMeal() {
		Jdbc db = new Jdbc();
		db.connectUser();
		/**
		 * Source file to read data from.
		 */
		String dataFileName = "./DataFiles/meal.txt";
		String line;

		/**
		 * Looping the read block until all lines in the file are read.
		 */
		try {
			BufferedReader bReader;
			bReader = new BufferedReader(new FileReader(dataFileName));
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

				/**
				 * Printing the value read from file to the console
				 */
				System.out.println(value1 + " " + value2 + " " + value3 + " " + value4 + " " + value5 + " " + value6);
				db.queryDb(db.getConnection(), "INSERT INTO `meal` "
						+ "(`mealNo`, `cuisineNo`, `mealName`, `price`, `maxCount`, `todayMeal`) "
						+ "VALUES ('" 
						+ value1 
						+ "', '" + value2 
						+ "', '" + value3 
						+ "', '" + value4 
						+ "', '" + value5
						+ "', '" + value6
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

	public String getMealNoString() {
		return mealNoString;
	}

	public String getCuisineNoString() {
		return cuisineNoString;
	}

	public String getMealNameString() {
		return mealNameString;
	}

	public String getPriceString() {
		return priceString;
	}

	public String getMaxCountString() {
		return maxCountString;
	}

	public String getTodayMealString() {
		return todayMealString;
	}

	public String getPkeyString() {
		return pKey;
	}
	
	public int getMealNo() { return this.mealNo; }
	public int getCuisineNo() { return this.cuisineNo; }
	public String getMealName() { return this.mealName; }
	public int getPrice() { return this.price; }
	public int getMaxCount() { return this.maxCount; }
	public int getTodayMeal() { return this.todayMeal; }

}
