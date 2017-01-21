package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserData;

/**
 * Servlet implementation class Kadai11
 */
@WebServlet("/Kadai11")
public class Kadai11 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai11.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserData userData = new UserData();
		if(userData.blankCheck(request.getParameter("ID"), request.getParameter("ID"), request.getParameter("ID"),
				request.getParameter("ID"),request.getParameter("ID"),request.getParameter("ID"),
				request.getParameter("ID"))){

			try{
				userData.setID(request.getParameter("ID"));
				userData.setName(request.getParameter("name"));
				userData.setTell(request.getParameter("tell"));
				userData.setAge(request.getParameter("age"));
				userData.setBirthday(request.getParameter("year"), request.getParameter("month"), request.getParameter("day"));
			}catch(NumberFormatException e){
				System.out.println("ID,年齢は数字で入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai11.jsp");
				dispatcher.forward(request, response);
				return;
			}

			Connection con = null;

			try{
				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
	            PreparedStatement pstmt = con.prepareStatement("update profiles set name=?,tell=?,age=?,birthday=?"
	            		+ "where profilesID=?");
	            pstmt.setString(1, userData.getName());
	            pstmt.setString(2, userData.getTell());
	            pstmt.setInt(3, userData.getAge());
	            pstmt.setDate(4,new java.sql.Date(userData.getBirthday().getTimeInMillis()));
	            pstmt.setInt(5, userData.getID());
	            int updateNum = pstmt.executeUpdate();
	            System.out.println(updateNum + "件のデータが更新されました");

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



		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai11.jsp");
		dispatcher.forward(request, response);


	}


}
