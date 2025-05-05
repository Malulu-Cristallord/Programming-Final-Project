import java.sql.*;
public class SQLPage {

	String server = "jdbc:mysql://140.119.19.73:3315/";
	String database = "113306019";
	String url = server + database + "?useSSL=false";
	String username = "113306019";
	String password = "i9368";
	
	public void connect() {
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			System.out.println("DB Connected");
			Statement stat = conn.createStatement();
			String query;
			boolean success;
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void showResultSet(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
		System.out.printf("%15s", metaData.getColumnLabel(i));
		}
		System.out.println();
		while (result.next()) {
		for (int i = 1; i <= columnCount; i++) {
		System.out.printf("%15s", result.getString(i));
		}
		System.out.println();
		}
	}
}
