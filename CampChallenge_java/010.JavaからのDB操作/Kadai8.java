package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Kadai8
 */
@WebServlet("/Kadai8")
public class Kadai8 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai8.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");

			Connection con = null;

			try{
				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
	            PreparedStatement pstmt = con.prepareStatement("select * from profiles where name like ?");
	            pstmt.setString(1, "%"+name+"%");
	            ResultSet rset = pstmt.executeQuery();

	            int resultCount=0;
	            while(rset.next()){
	            	System.out.println("profilesID : " + rset.getInt("profilesID"));
	            	System.out.println("neme : " + rset.getString("name"));
	            	System.out.println("tell : " + rset.getString("tell"));
	            	System.out.println("age : " + rset.getInt("age"));
	            	System.out.println("birtday : " + rset.getDate("birthday"));
	            	System.out.println("==================================");
	            	resultCount ++;
	            }
	            System.out.println(resultCount + "件の検索結果です");

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

			RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai8.jsp");
			dispatcher.forward(request, response);
	}

}
