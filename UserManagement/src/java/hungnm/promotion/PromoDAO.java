/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.promotion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DButil.DButil;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author SE130008
 */
public class PromoDAO implements Serializable {

    public boolean insertToPromoList(String userId, int rank)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DButil.makeConnection();
            String sql = "INSERT INTO promotion(userId,rank) VALUES (?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, userId);
            stm.setInt(2, rank);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<PromoDTO> showHistoryOfPromotion() throws SQLException, NamingException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<PromoDTO> list = new ArrayList<>();
        try {
            con = DButil.makeConnection();
            if (con != null) {
                String sql = "select userId,rank,pDate from promotion "
                        + "order by pDate DESC";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    int rank = rs.getInt("rank");
                    String pDate = rs.getString("pDate");
                    
                    System.out.println("his:" + userId);
                    System.out.println("his:" + rank);
                    System.out.println("his:" + pDate);
                    
                    PromoDTO dto = new PromoDTO(userId, rank, pDate);

                    

                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
