import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class kadai2 {

	public static void main(String[] args) {
		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
            PreparedStatement pstmt = con.prepareStatement("insert into profiles values(?,?,?,?,?)");
            Calendar cal = Calendar.getInstance();
            cal.set(1999, 11, 31);
            pstmt.setInt(1, 6);
            pstmt.setString(2, "田中　太郎");
            pstmt.setString(3, "090-9999-9999");
            pstmt.setInt(4, 30);
            pstmt.setDate(5,new java.sql.Date(cal.getTimeInMillis()));
            pstmt.executeUpdate();
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
