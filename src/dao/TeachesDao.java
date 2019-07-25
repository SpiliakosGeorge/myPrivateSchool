
package dao;

import entities.Course;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;


public class TeachesDao {

    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getTeaches = "select * from teaches order by cid";
    private static final String insertIntoTeaches= "insert into teaches(trid,cid) values(?,?)";

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

    private static List<Teaches> getTeaches() {
        List<Teaches> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTeaches);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Course c = CourseDao.getCourseById(rs.getInt("cid"));
                Trainer tr = TrainerDao.getTrainerById(rs.getInt("trid"));

                Teaches a = new Teaches(rs.getInt("teid"), tr, c);
                list.add(a);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showTeaches() {
        List<Teaches> list = getTeaches();

        for (Teaches a : list) {
            System.out.println(a);
        }
    }

    
    public static void insertIntoTeaches(Scanner input){
        System.out.println("Select trainer");
        TrainerDao.showTrainers();
        int trid = input.nextInt();
        input.nextLine();
        System.out.println("Select in which course you want him to teach");
        CourseDao.showCourses();
        int cid = input.nextInt();
        input.nextLine();
        
        List<Teaches>list=getTeaches();
        for(Teaches t:list){
            if(t.getCourse().getCid()==cid){
                if(t.getTrainer().getTrid()==trid){
                    System.out.println("Trainer already teaches in this course");
                    return;
                }
            }
        }
        
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertIntoTeaches);
            pst.setInt(1, trid);
            pst.setInt(2, cid);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Trainer inserted successfully in this course");
            } else {
                System.out.println("Trainer not inserted in this course");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(TeachesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
