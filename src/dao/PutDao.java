/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Assignment;
import entities.Course;
import entities.Put;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;

/**
 *
 * @author George
 */
public class PutDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getPut = "select * from put order by cid";

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(PutDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PutDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Put> getPut() {
        List<Put> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getPut);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Course c = CourseDao.getCourseById(rs.getInt("cid"));
                Assignment as = AssignmentDao.getAssignmentById(rs.getInt("asid"));
                Date d = rs.getDate("subdatetime");
                LocalDate subdatetime;
                if (d != null) {
                    subdatetime = d.toLocalDate();
                } else {
                    subdatetime = null;
                }

                Put p = new Put(rs.getInt("pid"), as, c,subdatetime);
                list.add(p);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showPut() {
        List<Put> list = getPut();

        for (Put p : list) {
            System.out.println(p);
        }
    }

    
}
