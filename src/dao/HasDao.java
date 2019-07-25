package dao;

import entities.Assignment;
import entities.Course;
import entities.Has;
import entities.Student;
import entities.Teaches;
import entities.Trainer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;

public class HasDao {

    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getHas = "select hid,oralmark ,totalmark,stid,asid from has ";
    private static final String courses = "select stid,has.asid,cid,oralmark,totalmark from has inner join put on has.asid=put.asid";

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(TeachesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TeachesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Has> getHas() {
        List<Has> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getHas);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student st = StudentDao.getStudentById(rs.getInt("stid"));
                Assignment as = AssignmentDao.getAssignmentById(rs.getInt("asid"));

                Has h = new Has(rs.getInt("hid"), rs.getInt("oralmark"), rs.getInt("totalmark"), st, as);
                System.out.println(h);

                list.add(h);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showAssignmentsPerCoursePerStudent() {
        try {
            PreparedStatement pst = getConnection().prepareStatement(courses);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student st = StudentDao.getStudentById(rs.getInt("stid"));
                Assignment as = AssignmentDao.getAssignmentById(rs.getInt("has.asid"));
                Course c = CourseDao.getCourseById(rs.getInt("cid"));

                System.out.print(st.getLname() + " ");
                System.out.print(as.getTitle() + " ");
                System.out.print(c.getTitle() + " ");
                System.out.print("Oralmark: " + rs.getInt("oralmark"));
                System.out.println(" Totalmark: " + rs.getByte("totalmark"));

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
