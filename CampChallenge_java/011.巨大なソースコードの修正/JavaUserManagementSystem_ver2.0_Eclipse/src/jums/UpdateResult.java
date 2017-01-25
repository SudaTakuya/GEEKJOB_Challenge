package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class UpdateResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	//変更点 UpdateResultを実装
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();

    	try{
	    	//変更点 直リンク防止の処理を追加
        	String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

	    	UserDataDTO detailData = (UserDataDTO) session.getAttribute("detailData");

	    	//入力された項目をUserDateBeansに格納
	    	UserDataBeans udb = new UserDataBeans();
	    	udb.setName(request.getParameter("name"));
	        udb.setYear(request.getParameter("year"));
	        udb.setMonth(request.getParameter("month"));
	        udb.setDay(request.getParameter("day"));
	        udb.setType(request.getParameter("type"));
	        udb.setTell(request.getParameter("tell"));
	        udb.setComment(request.getParameter("comment"));

	        UserDataDTO udd = new UserDataDTO();
	        //UserDatDTOに変換し、ID情報を補完する
	        udb.UD2DTOMapping(udd);
	        udd.setUserID(detailData.getUserID());

	        //データベースの更新を行う
	        UserDataDAO.getInstance().update(udd);

	        //セッションに保存された詳細データを削除
	        session.removeAttribute("detailData");

	        //更新に使用されたUserDataDTOをリクエストに格納
	        request.setAttribute("updateData", udd);

	        request.getRequestDispatcher("/updateresult.jsp").forward(request, response);

	    }catch(Exception e){
	        //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
	        request.setAttribute("error", e.getMessage());
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
