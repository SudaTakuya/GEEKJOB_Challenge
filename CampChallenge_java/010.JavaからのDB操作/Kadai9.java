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
 * Servlet implementation class Kadai9
 */
@WebServlet("/Kadai9")
public class Kadai9 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai9.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserData inData = new UserData();
		if(inData.blankCheck(request.getParameter("ID"), request.getParameter("ID"), request.getParameter("ID"),
			request.getParameter("ID"),request.getParameter("ID"),request.getParameter("ID"),
			request.getParameter("ID"))){

			try{
				inData.setID(request.getParameter("ID"));
				inData.setName(request.getParameter("name"));
				inData.setTell(request.getParameter("tell"));
				inData.setAge(request.getParameter("age"));
				inData.setID(request.getParameter("ID"));
				inData.setBirthday(request.getParameter("year"), request.getParameter("month"),request.getParameter("day"));
			}catch(NumberFormatException e){
				System.out.println("ID,年齢は数字で入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai9.jsp");
				dispatcher.forward(request, response);
				return;
			}

			System.out.println(inData.getID());
			System.out.println(inData.getName());
			System.out.println(inData.getTell());
			System.out.println(inData.getAge());
			System.out.println(inData.getBirthday().getTime());

			Connection con = null;

			try{
				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
	            PreparedStatement pstmt = con.prepareStatement("insert into profiles values(?,?,?,?,?)");
	            pstmt.setInt(1, inData.getID());
	            pstmt.setString(2, inData.getName());
	            pstmt.setString(3, inData.getTell());
	            pstmt.setInt(4, inData.getAge());
	            pstmt.setDate(5, new java.sql.Date(inData.getBirthday().getTimeInMillis()));
	            pstmt.executeUpdate();
	            System.out.println("挿入が完了しました");

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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai9.jsp");
		dispatcher.forward(request, response);

	}

}
