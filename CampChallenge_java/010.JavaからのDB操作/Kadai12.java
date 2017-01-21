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

import model.UserData;

/**
 * Servlet implementation class Kadai12
 */
@WebServlet("/Kadai12")
public class Kadai12 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai12.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserData userData = new UserData();
		if(userData.blankCheckFor12(request.getParameter("name"), request.getParameter("age"),
				request.getParameter("year"), request.getParameter("month"), request.getParameter("day"))){
			try{
				userData.setName(request.getParameter("name"));
				userData.setAge(request.getParameter("age"));
				userData.setBirthday(request.getParameter("year"), request.getParameter("month"), request.getParameter("day"));
			}catch(NumberFormatException e){
				System.out.println("年齢は数字で入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai12.jsp");
				dispatcher.forward(request, response);
				return;
			}

			Connection con = null;

			try{
				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
	            PreparedStatement pstmt = con.prepareStatement("select * from profiles where"
	            		+ " name=? and age=? and birthday=?");
	            pstmt.setString(1, userData.getName());
	            pstmt.setInt(2,userData.getAge());
	            pstmt.setDate(3, new java.sql.Date(userData.getBirthday().getTimeInMillis()));
	            ResultSet rset = pstmt.executeQuery();

	            int resultCount = 0;
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
		}else{
			System.out.println("未入力の項目があります");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai12.jsp");
		dispatcher.forward(request, response);
	}

}
