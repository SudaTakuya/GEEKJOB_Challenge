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

/**
 * Servlet implementation class Kadai10
 */
@WebServlet("/Kadai10")
public class Kadai10 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai10.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int ID = 0;
	    try{
	    	ID = Integer.parseInt(request.getParameter("ID"));
	    }catch(NumberFormatException e){
	    	System.out.println("数字を入力してください");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai10.jsp");
			dispatcher.forward(request, response);
			return;
	    }

		Connection con = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","root","");
            PreparedStatement pstmt = con.prepareStatement("delete from profiles where profilesID=?");
            pstmt.setInt(1, ID);
            int deleteNum = pstmt.executeUpdate();
            System.out.println(deleteNum + "件の削除が完了しました");

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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/kadai10.jsp");
		dispatcher.forward(request, response);
	}

}
