package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	//名前とパスワードがuser_infoテーブルに存在するか確認するメソッド
	public static boolean login(UserDTO userDTO){

		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
			PreparedStatement pstmt = con.prepareStatement("select * from user_info where name=? and passward=?");
		    pstmt.setString(1, userDTO.getName());
		    pstmt.setString(2, userDTO.getPassward());
		    ResultSet rset = pstmt.executeQuery();
		    if(rset.next()){
		    	return true;
		    }

		    con.close();

		}catch(SQLException e){
			System.out.println("接続時にエラーが発生しました");
		}catch(Exception e){
			System.out.println("接続時にエラーが発生しました");
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					System.out.println("切断時にエラーが発生しました");
				}
			}
		}

		return false;
	}


}
