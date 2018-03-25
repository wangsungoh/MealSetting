package util;
import java.sql.*;

import model.Member;

public class Jdbc {
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/";
    private String userPass = "?user=root&password=1234";
    private String dbName = "meal";
    private String userName = "root";
    private String password = "1234";

    private String newUser = "user";
    private String newUserPasswd = "1234";
    
    private PreparedStatement preStatement;
    private Statement statement;
    private ResultSet result;
    private Connection con;

	public Jdbc() {

	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	public void closeConnection() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectUser() {
        try {
			Class.forName(jdbcDriver);
	        con = DriverManager.getConnection(dbAddress + "?autoReconnect=true&useSSL=false", newUser, newUserPasswd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void initializeDb() {
        try {
			Class.forName(jdbcDriver);
	        con = DriverManager.getConnection(dbAddress + "?autoReconnect=true&useSSL=false", userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Connection connection = getConnection();
		
		dropAndCreateDatabase(connection);
		createUser(connection, newUser, newUserPasswd);

		createTableMember(connection);
		closeConnection();
	}
	
    private void dropAndCreateDatabase(final Connection connection) {
        try {
            Statement s = connection.createStatement();
            int myResult = s.executeUpdate("DROP DATABASE IF EXISTS " + dbName);
            myResult = s.executeUpdate("CREATE DATABASE " + dbName + " DEFAULT CHARACTER SET UTF8");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUser(final Connection connection, final String user, final String passwd) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("USE mysql");
            s.executeUpdate("DROP USER IF EXISTS '" + newUser + "'@'localhost'");
            s.executeUpdate("GRANT SELECT, INSERT, UPDATE, DELETE ON "+ dbName +".* TO '"
            + newUser + "'@'localhost' IDENTIFIED BY '" + newUserPasswd + "'");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createTableMember(final Connection connection) {
    	Member member = new Member();
    	
        String myTableName = "CREATE TABLE `" + dbName + "`.`" + member.getTableNameString() + "` ("
        		  + "`" + member.getMemberNoString() + "` INT NOT NULL AUTO_INCREMENT,"
        		  + "`" + member.getMemberNameString() + "` VARCHAR(20) NULL,"
        		  + "`" + member.getPasswdString() + "` VARCHAR(4) NULL,"
        		  + "PRIMARY KEY (`memberNo`))";
        try {
            statement = connection.createStatement();
            //The next line has the issue
            statement.executeUpdate(myTableName);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
    }
}//end Jdbc
