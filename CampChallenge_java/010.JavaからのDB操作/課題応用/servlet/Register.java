package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ItemDAO;
import db.ItemDTO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		ItemDTO itemDTO = new ItemDTO();
		try{
			itemDTO.setName(request.getParameter("name"));
			itemDTO.setPrice(request.getParameter("price"));
		}catch(NumberFormatException e){		//値段がint値に変換できない場合
			String error = "値段は数字を入力してください";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errorRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if(!itemDTO.blankCheck()){		//未入力チェック
			String error = "入力されていない項目があります";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errorRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//データベースにレコードを追加
		ItemDAO.insert(itemDTO);

		request.setAttribute("itemDTO", itemDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/RegisterResult.jsp");
		dispatcher.forward(request, response);
	}

}
