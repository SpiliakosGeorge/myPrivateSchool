
package dao;

import entities.Attends;
import entities.Course;
import entities.Student;
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


public class AttendsDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getAttends = "select * from attends order by cid";
    private static final String manyCourses = "select (attends.stid),count(attends.stid)  from student inner join attends on student.stid=attends.stid group by attends.stid having count(attends.stid)>1";
    private static final String insertAttends = "insert into attends (cid,stid) values(?,?)";

 private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(AttendsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AttendsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Attends> getAttends() {
        List<Attends> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAttends);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
              Course c= CourseDao.getCourseById(rs.getInt("cid"));
              Student st=StudentDao.getStudentById(rs.getInt("stid"));
               
              Attends a=new Attends(rs.getInt("aid"),c,st);
              list.add(a);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showAttends() {
        List<Attends> list = getAttends();

        for (Attends a : list) {
            System.out.println(a);
        }
    }

    public static void showManyCourses(){
        try {
            PreparedStatement pst = getConnection().prepareStatement(manyCourses);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              Student st=StudentDao.getStudentById(rs.getInt("attends.stid"));
              System.out.println(st.getFname()+" "+st.getLname()+" Courses attending: "+ rs.getInt("count(attends.stid)"));
              
            }
            
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void insertIntoAttends(Scanner input){
        System.out.println("Select student");
        StudentDao.showStudents();
        int stid = input.nextInt();
        input.nextLine();
        System.out.println("Select in which course you want him to attend");
        CourseDao.showCourses();
        int cid = input.nextInt();
        input.nextLine();
        
        List<Attends>list=getAttends();
        for(Attends a:list){
            if(a.getCourse().getCid()==cid){
                if(a.getStudent().getStid()==stid){
                    System.out.println("Student already attends this course");
                    return;
                }
            }
        }
        
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAttends);
            pst.setInt(1, cid);
            pst.setInt(2, stid);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Student inserted successfully in this course");
            } else {
                System.out.println("Student not inserted in this course");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(AttendsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
