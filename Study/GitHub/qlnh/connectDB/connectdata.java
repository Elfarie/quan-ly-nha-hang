package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectdata {
		public static Connection getConnection() {
			try {
				String connectionUrl = "jdbc:sqlserver://stedy\\sqlexpress:1433;databaseName=UngDungRapViet;encrypt=true;trustServerCertificate=true;";
				String userName="sa";
				String password="1";
				Connection connection = DriverManager.getConnection(connectionUrl,userName,password);
				//System.out.println("thanh cong");
				return connection;
			
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
