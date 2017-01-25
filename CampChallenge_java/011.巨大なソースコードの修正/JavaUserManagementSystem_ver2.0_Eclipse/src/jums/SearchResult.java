package jums;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class SearchResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    		request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
    		HttpSession session = request.getSession();

        try{
        	//変更点 直リンク防止の処理を追加
        	String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

            //フォームからの入力を取得して、JavaBeansに格納
            UserDataBeans udb = new UserDataBeans();
            //変更点 遷移先から戻ってきた場合は、セッションに保存した入力項目を利用するように変更
            if(request.getParameter("return").equals("no")){
            	//変更点 GETリクエストで送られた名前文字列を正しい文字コードへ変換する処理を追加
            	String nametmp = new String (request.getParameter("name").getBytes("ISO-8859-1"));
                udb.setName(URLDecoder.decode(nametmp,"UTF-8"));
                udb.setYear(request.getParameter("year"));
                udb.setType(request.getParameter("type"));
                session.setAttribute("searchParameter", udb);
            }else{
            	udb = (UserDataBeans)session.getAttribute("searchParameter");
            }

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO searchData = new UserDataDTO();
            udb.UD2DTOMapping(searchData);

            //変更点 検索結果を全件受け取れるようにArrayListの検索結果を受け取るように変更
            ArrayList<UserDataDTO> resultData = UserDataDAO .getInstance().search(searchData);

            request.setAttribute("resultData", resultData);

            request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
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
