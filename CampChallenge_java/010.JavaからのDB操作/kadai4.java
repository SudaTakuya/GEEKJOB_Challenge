import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class kadai4 {

	public static void main(String[] args) {
		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
            PreparedStatement pstmt = con.prepareStatement("select * from profiles where profilesID=1");
            ResultSet rset = pstmt.executeQuery();
            while(rset.next()){
            	System.out.println("profilesID : " + rset.getInt("profilesID"));
            	System.out.println("name : " + rset.getString("name"));
            	System.out.println("tell : " + rset.getString("tell"));
            	System.out.println("age : " + rset.getInt("age"));
            	System.out.println("birthday : " + rset.getDate("birthday"));
            }

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
