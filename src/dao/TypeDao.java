
package dao;

import entities.Student;
import entities.Type;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;


public class TypeDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getTypeById = "SELECT * FROM type where tid=?";
    private static final String getType = "select * from type";
    private static final String insertType = "insert into type (tdescr) values(?)";
    private static final String getLastType = "SELECT MAX(tid) as tid from type";
    
    private static  Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Type getTypeById(int tid) {
        Type t=null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTypeById);
            pst.setInt(1, tid);
            ResultSet rs = pst.executeQuery();
            rs.next(); 
                 t = new Type(rs.getInt("tid"), rs.getString("tdescr"));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;

    }

    
    private static List<Type> getType() {
        List<Type> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getType);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Type s = new Type(rs.getInt("tid"), rs.getString("tdescr"));
                list.add(s);
            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showType() {
        List<Type> list = getType();

        for (Type t : list) {
            System.out.println(t);
        }
    }

    public static void insertType(Scanner input) {
        System.out.println("Give Type ");
        String type = input.nextLine();

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertType);
            pst.setString(1, type);
            int result = pst.executeUpdate();
            pst.close();
            conn.close();

        } catch (SQLException ex) {

            Logger.getLogger(StreamDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getLastType() {
        int tid = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getLastType);
            ResultSet rs = pst.executeQuery();
            rs.next();
            tid = (rs.getInt("tid"));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tid;

    }


    
    
}
