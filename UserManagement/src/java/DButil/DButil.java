/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DButil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author SE130008
 */
public class DButil implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException{
        Context contex = new InitialContext();
        Context tomcatCtx = (Context) contex.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCtx.lookup("LAB01");
        return ds.getConnection();
    }
}
