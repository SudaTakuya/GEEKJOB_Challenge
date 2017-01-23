package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	//データベース接続を行い、入力された商品をitemsテーブルに登録するメソッド
	public static void insert(ItemDTO itemDTO){
		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
			PreparedStatement pstmt = con.prepareStatement("insert into items(name,price) values(?,?)");
			pstmt.setString(1, itemDTO.getName());
			pstmt.setInt(2, itemDTO.getPrice());
			pstmt.executeUpdate();

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

	}

	//データベース接続を行い、itemsテーブルの名前、値段の一覧を格納したArrayListを取得するメソッド
		public static List<ItemDTO> select(){

			Connection con = null;
			ResultSet rset = null;
			List<ItemDTO> list = new ArrayList<>();

			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
				PreparedStatement pstmt = con.prepareStatement("select name,price from items");
				rset = pstmt.executeQuery();

				int count = 0;
				while(rset.next()){
					list.add(new ItemDTO());
					list.get(count).setName(rset.getString("name"));
					list.get(count).setPrice(rset.getString("price"));
					count++;
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

			return list;

		}

}
