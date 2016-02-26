package com.lx.git;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by douhua on 2/26/16.
 */
public class MysqlTester {


    public static void start() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/support_db";
        String user = "android";
        String password = "android@coohua";


        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MysqlTester.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MysqlTester.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }


}
