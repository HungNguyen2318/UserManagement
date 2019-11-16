/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.user;

import DButil.DButil;
import hungnm.role.RoleDAO;
import hungnm.role.RoleDTO;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author SE130008
 */
public class UserDAO implements Serializable {

    public String checkLogin(String userId, String password)
            throws SQLException, NamingException, NoSuchAlgorithmException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = null;
        password = getSHA_256SecurePassword(password);
        try {
            con = DButil.makeConnection();
            System.out.println("ket noi vao db");
            String sql = "select username, r.type AS type "
                    + "FROM tbl_Role r inner join tbl_User u "
                    + "ON r.id = u.roleId "
                    + "where userId = ? and password = ? and status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userId);
            stm.setString(2, password);
            stm.setString(3, "active");
            rs = stm.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("type");
                result = username + ":" + role;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

    public String getSHA_256SecurePassword(String passwordToHash) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        byte[] temp = new byte[16];
        byte[] bytes = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(temp);
        bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;

    }

    public List<UserDTO> findByLikeName(String searchValue, String roleSearchValue)
            throws SQLException, NamingException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = null;
        List<UserDTO> listUser = new ArrayList<>();
        System.out.println("role: " + roleSearchValue);
        try {
            con = DButil.makeConnection();
            sql = "SELECT userId, password, username, email, phone,image, r.type AS type "
                    + "FROM tbl_Role r inner join tbl_User u "
                    + "ON r.id = u.roleId "
                    + "WHERE u.username LIKE ? AND status = ?";
            if(roleSearchValue!=null){
                sql = sql + " AND type = ?";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            stm.setString(2, "active");
            if(roleSearchValue!=null){
                stm.setString(3, roleSearchValue);
            }           
            rs = stm.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("userId");
                String password = rs.getString("password");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String image = rs.getString("image");
                String role = rs.getString("type");
                
                System.out.println(userId);
                System.out.println(password);
                System.out.println(username);
                System.out.println(email);
                System.out.println(phone);
                System.out.println(image);
                System.out.println(role);
                
                dto = new UserDTO(userId, password, username, email, phone, image, role, image);
                listUser.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listUser;
    }
    
    public UserDTO findByUserId(String searchValue)
            throws SQLException, NamingException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
      
        try {
            con = DButil.makeConnection();
            String sql = "SELECT userId, password, username, email, phone,image, r.type AS type "
                    + "FROM tbl_Role r inner join tbl_User u "
                    + "ON r.id = u.roleId "
                    + "WHERE u.userId = ? AND status = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, searchValue);
            stm.setString(2, "active");           
            rs = stm.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("userId");
                String password = rs.getString("password");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String image = rs.getString("image");
                String role = rs.getString("type");
                
                System.out.println(userId);
                System.out.println(password);
                System.out.println(username);
                System.out.println(email);
                System.out.println(phone);
                System.out.println(image);
                System.out.println(role);
                
                dto = new UserDTO(userId, password, username, email, phone, image, role, image);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    
    public boolean createAccount(UserDTO userDTO) 
        throws NamingException, SQLException, NoSuchAlgorithmException {
            //1.Open connection
            Connection con = null;
            PreparedStatement stm = null;
            RoleDAO roleDAO = new RoleDAO();

            try {
                con = DButil.makeConnection();
                System.out.println("co connection");
                if (con != null) {
                    System.out.println("vao dc r");
                    //2.Create sql String
                    String sql = "INSERT INTO tbl_User(userId,password,username, email, phone , image , roleId, status) "
                            + "values (?,?,?,?,?,?,?,?)";
                    //3.Create Statement and set values to parameters
                    stm = con.prepareStatement(sql);
                    stm.setString(1, userDTO.getUserId());
                    stm.setString(2, getSHA_256SecurePassword(userDTO.getPassword()));
                    stm.setString(3, userDTO.getUsername());
                    stm.setString(4, userDTO.getEmail());
                    stm.setString(5, userDTO.getPhone());
                    stm.setString(6, userDTO.getImage());
                    stm.setInt(7, roleDAO.findIdByType(userDTO.getRole()));
                    stm.setString(8, "active");
                    //4.Execute Query
                    int row = stm.executeUpdate();
                    //5.Process result
                    if (row > 0) {
                        return true;
                    }
                }//end if con existed 
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
    
    
}
