package dao;

import entities.Course;
import entities.Stream;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;

public class CourseDao {

    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getCourses = "SELECT * FROM course";
    private static final String getCourseById = "select * from course where cid=?";
    private static final String insertCourse = "insert into course(tid,sid,title,startdate,enddate) values(?,?,?,?,?)";

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Course> getCourses() {
        List<Course> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getCourses);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Date d = rs.getDate("startdate");
                LocalDate startDate = d.toLocalDate();
                d = rs.getDate("enddate");
                LocalDate endDate = d.toLocalDate();
                int tid = rs.getInt("tid");
                int sid = rs.getInt("sid");
                Type type = TypeDao.getTypeById(tid);
                Stream stream = StreamDao.getStreamById(sid);

                Course c = new Course(rs.getInt("cid"), type, stream, rs.getString("title"), startDate, endDate);
                list.add(c);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showCourses() {
        List<Course> list = getCourses();

        for (Course c : list) {
            System.out.println(c);
        }
    }

    public static Course getCourseById(int cid) {
        Course c = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getCourseById);
            pst.setInt(1, cid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Date d = rs.getDate("startdate");
            LocalDate startDate = d.toLocalDate();
            d = rs.getDate("enddate");
            LocalDate endDate = d.toLocalDate();
            int tid = rs.getInt("tid");
            int sid = rs.getInt("sid");
            Type type = TypeDao.getTypeById(tid);
            Stream stream = StreamDao.getStreamById(sid);

            c = new Course(cid, type, stream, rs.getString("title"), startDate, endDate);

            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    public static void insertCourse(Scanner input) {
        System.out.println("Give course title");
        String title = input.nextLine();
        System.out.println("Give course start date");
        Date sdate = checkDate(input);
        System.out.println("Give course end date");
        Date edate = checkDate(input);
        System.out.println("Select course stream or press 0 for new!");
        StreamDao.showStream();
        int stream = input.nextInt();
        input.nextLine();
        if (stream == 0) {
            StreamDao.insertStream(input);
            stream = StreamDao.getLastStream();
        }
        System.out.println("Select course type or press 0 for new!");
        TypeDao.showType();
        int type = input.nextInt();
        input.nextLine();
        if (type == 0) {
            TypeDao.insertType(input);
            type = TypeDao.getLastType();
        }

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCourse);
            pst.setInt(1, type);
            pst.setInt(2, stream);
            pst.setString(3, title);
            pst.setDate(4, sdate);
            pst.setDate(5, edate);
            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Course inserted successfully");
            } else {
                System.out.println("Course not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Date checkDate(Scanner input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Date d = null;
        try {

            String date = input.nextLine();
            LocalDate ld = LocalDate.parse(date, formatter);
            d = Date.valueOf(ld);

        } catch (Exception e) {
            System.out.println("Invalid input.Try again in dd/mm/yyyy format!");
            checkDate(input);
        }

        return d;

    }

}
