/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.role;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import DButil.DButil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SE130008
 */
public class RoleDAO implements Serializable{
    public List<RoleDTO> findRole() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RoleDTO> result = null;
        try{
            con = DButil.makeConnection();
            String sql = "SELECT type, id "
                    + "FROM tbl_Role";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                String type = rs.getString("type");
                int id = rs.getInt("id");
                RoleDTO dto = new RoleDTO(type, id);
                result.add(dto);
            }                   
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return result;
    }
}
