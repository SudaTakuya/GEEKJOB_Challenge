import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class kadai7 {

	public static void main(String[] args) {
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
            PreparedStatement pstmt = con.prepareStatement("update profiles set name=?,age=?,birthday=? "
            		+ "where profilesID=?");
            Calendar cal = Calendar.getInstance();
            cal.set(1967, 10, 6);
            pstmt.setString(1, "松岡　修造");
            pstmt.setInt(2,48);
            pstmt.setDate(3, new java.sql.Date(cal.getTimeInMillis()));
            pstmt.setInt(4, 1);

			con.close();

		}catch(SQLException e){
			System.out.println("接続時にエラーが発生しました。" + e.toString());
		}catch(Exception e){
			System.out.println("接続時にエラーが発生しました。" + e.toString());
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					System.out.println("切断時にエラーが発生しました。" + e.toString());
				}
			}
		}
	}

}
