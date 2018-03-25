package model;

import java.io.File;

public class Member {
	private int memberNo;
	private String memberName;
	private String passwd;
	private String tableNameString = "member";
	private String memberNoString = "memberNo";
	private String memberNameString = "memberName";
	private String passwdString = "passwd";
	
	private final String rawFilePath = "";
	
	private String pKey = memberNoString;
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println("FFFF : " + fileEntry.getName());
	        }
	    }
	}

	public Member() {
		// TODO Auto-generated constructor stub

		final File folder = new File("./DataFiles");
		listFilesForFolder(folder);
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

