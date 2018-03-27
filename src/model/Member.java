package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private int theLastMemberNo;

	List<Member> memberData = new ArrayList<Member>();

	public Member(int memberNo, String memberName, String passwd) {
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.passwd = passwd;
	}
	
	public List<Member> getMemberData() {
		return this.memberData;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public int gettheLastMemberNo() {
		getLastMemberNoOfTable();
		return this.theLastMemberNo;
	}
	
	private void getLastMemberNoOfTable() {
		Jdbc db = new Jdbc();
		db.connectUser();
		ResultSet rs = db.selectDb(db.getConnection(), "select memberNo from member order by memberNo desc limit 1");
		
		try {
			while(rs.next()) {
				int no = rs.getInt("memberNo");
				this.theLastMemberNo = no;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}
	
	public void insertNewMember(final String memberName, final String passwd) {
		Jdbc db = new Jdbc();
		db.connectUser();
		
		db.queryDb(db.getConnection(), "INSERT INTO `member` (`memberName`, `passwd`) VALUES ('" + memberName + "', '" + passwd + "')");
		
		db.closeConnection();
	}
	
	public void initializeMember() {
		Jdbc db = new Jdbc();
		db.connectUser();
		/**
		 * Source file to read data from.
		 */
		InputStream inputStream = Meal.class.getResourceAsStream("/DataFiles/member.txt");
		String line;

		/**
		 * Looping the read block until all lines in the file are read.
		 */
		try {
			BufferedReader bReader;
			bReader = new BufferedReader(new InputStreamReader(inputStream));
			bReader.readLine(); // this will read the first line

			while ((line = bReader.readLine()) != null) {
				/**
				 * Splitting the content of tabbed separated line
				 */
				String datavalue[] = line.split("\t");
				String value1 = datavalue[0];
				String value2 = datavalue[1];
				String value3 = datavalue[2];

				Member member = new Member(Integer.valueOf(value1), value2, value3);
				
				memberData.add(member);

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
	
	public int getMemberNo() {
		return this.memberNo;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public String getPasswd() {
		return this.passwd;
	}
}

