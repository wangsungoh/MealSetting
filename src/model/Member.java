package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.mysql.jdbc.Connection;

import util.Jdbc;

public class Member {
	private int memberNo;
	private String memberName;
	private String passwd;
	private String tableNameString = "member";
	private String memberNoString = "memberNo";
	private String memberNameString = "memberName";
	private String passwdString = "passwd";

	private String pKey = memberNoString;

	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public void initializeMember() {
		Jdbc db = new Jdbc();
		db.connectUser();
		/**
		 * Source file to read data from.
		 */
		String dataFileName = "./DataFiles/member.txt";
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

				/**
				 * Printing the value read from file to the console
				 */
				System.out.println(value1 + " " + value2 + " " + value3);
				db.queryDb(db.getConnection(), "INSERT INTO `member` (`memberNo`, `memberName`, `passwd`) VALUES ('" + value1 + "', '" + value2 + "', '" + value3 + "')");
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

	public String getMemberNoString() {
		return memberNoString;
	}

	public String getMemberNameString() {
		return memberNameString;
	}

	public String getPasswdString() {
		return passwdString;
	}

	public String getPkeyString() {
		return pKey;
	}
}

