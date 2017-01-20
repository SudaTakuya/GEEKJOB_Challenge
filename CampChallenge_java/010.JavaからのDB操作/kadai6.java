import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class kadai6 {

	public static void main(String[] args) {
		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
            PreparedStatement pstmt = con.prepareStatement("delete from profiles where profilesID=6");
            pstmt.executeUpdate();

            pstmt = con.prepareStatement("select * from profiles");
            ResultSet rset = pstmt.executeQuery();
            while(rset.next()){
            	System.out.println("profilesID : " + rset.getInt("profilesID"));
            	System.out.println("neme : " + rset.getString("name"));
            	System.out.println("tell : " + rset.getString("tell"));
            	System.out.println("age : " + rset.getInt("age"));
            	System.out.println("birtday : " + rset.getDate("birthday"));
            	System.out.println("==================================");
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
