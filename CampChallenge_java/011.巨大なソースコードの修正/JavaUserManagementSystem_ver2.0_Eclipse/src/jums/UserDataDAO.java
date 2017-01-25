package jums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import base.DBManager;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {

    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }

    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    //変更点 検索結果を複数件表示する必要があるが、最初の一件のみしか取得できていない
    //返り値をArrayList<UserDataDTO>に変更し、ResultSetの全件を取得できるよう変更
    public ArrayList<UserDataDTO> search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        ArrayList<UserDataDTO> resultList = new ArrayList<>();

        try{
            con = DBManager.getConnection();

            //変更点 入力項目数に関わらず、prepareStatementのパラメータが3つとなっている
            //入力項目が存在したらHashMapに項目、パラメータ番号を格納して、
            //必要に応じてパラメータのセットを行うように変更
            //また、Stringインスタンスの再生成を減少させるため、StringBilderで文字列の追加をするように変更
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM user_t");
            Map<String,Integer> map = new HashMap<>();
            int count = 0;
            if (!ud.getName().equals("")) {
                sql.append(" WHERE name like ?");
                count++;
                map.put("name", count);
            }
            if (ud.getBirthday()!=null) {
                if(count == 0){
                    sql.append(" WHERE birthday like ?");
                }else{
                    sql.append(" AND birthday like ?");
                }
                count++;
                map.put("birthday", count);
            }
            if (ud.getType()!=0) {
                if(count == 0){
                    sql.append(" WHERE type like ?");
                }else{
                    sql.append(" AND type like ?");
                }count++;
                map.put("type", count);
            }

            st =  con.prepareStatement(sql.toString());
            for(String key: map.keySet()){
            	if(key.equals("name")){
            		 st.setString(map.get("name"), "%"+ud.getName()+"%");
            	}
            	if(key.equals("birthday")){
            		st.setString(map.get("birthday"), "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
            	}
            	if(key.equals("type")){
            		st.setInt(map.get("type"), ud.getType());
            	}
            }

            ResultSet rs = st.executeQuery();
            //変更点 ResultSetの全要素をArrayListに格納するように変更
            int listcount = 0;
            while(rs.next()){
            	resultList.add(new UserDataDTO());
            	resultList.get(listcount).setUserID(rs.getInt(1));
            	resultList.get(listcount).setName(rs.getString(2));
            	resultList.get(listcount).setBirthday(rs.getDate(3));
            	resultList.get(listcount).setTell(rs.getString(4));
            	resultList.get(listcount).setType(rs.getInt(5));
            	resultList.get(listcount).setComment(rs.getString(6));
            	resultList.get(listcount).setNewDate(rs.getTimestamp(7));
            	listcount++;
            }

            System.out.println("search completed");

            //変更点 返り値をArrayListに変更
            return resultList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE userID = ?";

            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());

            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));

            System.out.println("searchByID completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

    //変更点 データベースの更新処理を行うメソッドを追加
    public void update(UserDataDTO udd) throws SQLException{
    	 Connection con = null;
         PreparedStatement st = null;
         try{
             con = DBManager.getConnection();
             st = con.prepareStatement("update user_t set name=?,birthday=?,tell=?,type=?,comment=?,newDate=?"
             		+ " where userID=?");
             st.setString(1, udd.getName());
             st.setDate(2, new java.sql.Date(udd.getBirthday().getTime()));
             st.setString(3, udd.getTell());
             st.setInt(4, udd.getType());
             st.setString(5, udd.getComment());
             st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
             st.setInt(7, udd.getUserID());

             st.executeUpdate();

         }catch(SQLException e){
             System.out.println(e.getMessage());
             throw new SQLException(e);
         }finally{
             if(con != null){
                 con.close();
             }
         }
    }

    //変更点 データベースの削除処理を行うメソッドを追加
    public void delete(int id) throws SQLException{
   	 	Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("delete from user_t where userID=?");
            st.setInt(1, id);
            st.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
   }
}
