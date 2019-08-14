package package_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuctionBackend1 {
	
	 public static String url = "jdbc:mysql://localhost:3306/dbauction";
	 public static String driver = "com.mysql.cj.jdbc.Driver";
	 public static String user = "root";
     public static String pass = "yashshete";

	public static void main(String[] args) {
    
    LoginFrame lf = new LoginFrame();
    lf.setVisible(true);
	}
	static Connection connection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,user,pass);
		return con;
	}

}
