package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import util.Jdbc;

public class Cuisine {
	private int cuisineNo;
	private String cuisineName;
	private String tableNameString = "cuisine";
	private String cuisineNoString = "cuisineNo";
	private String cuisineNameString = "cuisineName";

	private String pKey = cuisineNoString;

	public Cuisine() {
		// TODO Auto-generated constructor stub
	}

	public void initializeCuisine() {
		Jdbc db = new Jdbc();
		db.connectUser();
		/**
		 * Source file to read data from.
		 */
		String dataFileName = "./DataFiles/cuisine.txt";
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

				/**
				 * Printing the value read from file to the console
				 */
				System.out.println(value1 + " " + value2);
				db.queryDb(db.getConnection(), "INSERT INTO `cuisine` (`cuisineNo`, `cuisineName`) VALUES ('" + value1 + "', '" + value2 + "')");
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

	public String getCuisineNoString() {
		return cuisineNoString;
	}

	public String getCuisineNameString() {
		return cuisineNameString;
	}

	public String getPkeyString() {
		return pKey;
	}
}
