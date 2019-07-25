
package dao;

import entities.Assignment;
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


public class AssignmentDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getAssignments = "select * from assignment";
    private static final String getAssignmentById = "select * from assignment where asid=?";
    private static final String insertAssignment = "insert into assignment(title,descr) values(?,?)";

    private static  Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Assignment> getAssignments() {
        List<Assignment> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignments);
            ResultSet rs = pst.executeQuery();
                 while (rs.next()) {
                Assignment as = new Assignment(rs.getInt("asid"),rs.getString("title"), rs.getString("descr"));
              
                list.add(as);
                 }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

}

    public static void showAssignments(){
        List <Assignment> list = getAssignments();
       
        for (Assignment as: list){
            System.out.println(as);
        }
    }

    public static Assignment getAssignmentById(int asid) {
        Assignment as = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentById);
            pst.setInt(1, asid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            as = new Assignment(asid,rs.getString("title"), rs.getString("descr"));
              
                
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return as;

    }
    
    public static void insertAssignment(Scanner input) {
        System.out.println("Give assignment title");
        String title = input.nextLine();
        System.out.println("Give assignment description");
        String description = input.nextLine();
       

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignment);
            pst.setString(1, title);
            pst.setString(2, description);
           
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Assignment inserted successfully");
            } else {
                System.out.println("Assignment not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
