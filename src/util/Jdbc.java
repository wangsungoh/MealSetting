package util;
import java.sql.*;

import model.Cuisine;
import model.Meal;
import model.Member;
import model.OrderList;

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
			Statement s = con.createStatement();
            s.executeUpdate("USE " + dbName);
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
		createTableCuisine(connection);
		createTableMeal(connection);
		createTableOrderList(connection);
				
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
    	
        String queryCreateTable = "CREATE TABLE `" + dbName + "`.`" + member.getTableNameString() + "` ("
        		  + "`" + member.getMemberNoString() + "` INT NOT NULL AUTO_INCREMENT,"
        		  + "`" + member.getMemberNameString() + "` VARCHAR(20) NULL,"
        		  + "`" + member.getPasswdString() + "` VARCHAR(4) NULL,"
        		  + "PRIMARY KEY (`" + member.getPkeyString() + "`))";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(queryCreateTable);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
    }
    
    private void createTableCuisine(final Connection connection) {
    	Cuisine cuisine = new Cuisine();
    	
        String queryCreateTable = "CREATE TABLE `" + dbName + "`.`" + cuisine.getTableNameString() + "` ("
        		  + "`" + cuisine.getCuisineNoString() + "` INT NOT NULL AUTO_INCREMENT,"
        		  + "`" + cuisine.getCuisineNameString() + "` VARCHAR(10) NULL,"
        		  + "PRIMARY KEY (`" + cuisine.getPkeyString() + "`))";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(queryCreateTable);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
    }

    private void createTableMeal(final Connection connection) {
    	Meal meal = new Meal();

        String queryCreateTable = "CREATE TABLE `" + dbName + "`.`" + meal.getTableNameString() + "` ("
        		  + "`" + meal.getMealNoString() + "` INT NOT NULL AUTO_INCREMENT,"
        		  + "`" + meal.getCuisineNoString() + "` INT NULL,"
        		  + "`" + meal.getMealNameString() + "` VARCHAR(20) NULL,"
           		  + "`" + meal.getPriceString() + "` INT NULL,"
           		  + "`" + meal.getMaxCountString() + "` INT NULL,"
           		  + "`" + meal.getTodayMealString() + "` TINYINT(1) NULL,"
        		  + "PRIMARY KEY (`" + meal.getPkeyString() + "`))";
        try {
            statement = connection.createStatement();
            //The next line has the issue
            statement.executeUpdate(queryCreateTable);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
    }

    private void createTableOrderList(final Connection connection) {
    	OrderList orderlist = new OrderList();

    	String queryCreateTable = "CREATE TABLE `" + dbName + "`.`" + orderlist.getTableNameString() + "` ("
        		  + "`" + orderlist.getOrderNoString() + "` INT NOT NULL AUTO_INCREMENT,"
        		  + "`" + orderlist.getCuisineNoString() + "` INT NULL,"
        		  + "`" + orderlist.getMealNoString() + "` INT NULL,"
        		  + "`" + orderlist.getMemberNoString() + "` INT NULL,"
                  + "`" + orderlist.getOrderCountString() + "` INT NULL,"
                  + "`" + orderlist.getAmountString() + "` INT NULL,"
                  + "`" + orderlist.getOrderDateString() + "` DATETIME NULL,"
        		  + "PRIMARY KEY (`" + orderlist.getPkeyString() + "`))";
 
    	try {
            statement = connection.createStatement();
            //The next line has the issue
            statement.executeUpdate(queryCreateTable);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
    }

    public int queryDb(final Connection connection, final String query) {
    	int result = -1;
    	
        try {
        	if (query != null && query.length() >= 0) {
                Statement s = connection.createStatement();
    			result = s.executeUpdate(query);        		
        	} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return result;
    }
    
    public ResultSet selectDb(final Connection connection, final String query) {
    	ResultSet rs = null;
        PreparedStatement pstmt = null;
        
        try {
			pstmt = connection.prepareStatement(query);
	        rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return rs;
    }
}//end Jdbc
