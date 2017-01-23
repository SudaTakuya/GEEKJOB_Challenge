package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDAO;
import db.UserDTO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		UserDTO userDTO = new UserDTO();
		userDTO.setName(request.getParameter("name"));
		userDTO.setPassward(request.getParameter("passward"));

		if(!userDTO.blankCheck()){		//未入力チェック
			String error = "未入力の項目があります";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errorLogin.jsp");
			dispatcher.forward(request, response);

		}else if(!UserDAO.login(userDTO)){		//ユーザー登録チェック
			String error = "ユーザー登録されていません";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errorLogin.jsp");
			dispatcher.forward(request, response);

		}else{
			//ユーザー登録されている場合はセッションにユーザー名を保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userDTO.getName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/menu.jsp");
			dispatcher.forward(request, response);

		}
	}

}
