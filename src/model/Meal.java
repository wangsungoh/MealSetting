package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}
