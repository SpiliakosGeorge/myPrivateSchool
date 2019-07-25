package dao;

import entities.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivateschoolwsql.MyPrivateSchoolwSQL;

public class StudentDao {

    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getStudents = "SELECT * FROM student";
    private static final String getStudentById = "SELECT * FROM student where stid=?";
    private static final String insertStudent = "insert into student(fname,lname,dob,tuitionfees) values(?,?,?,?)";

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Student> getStudents() {
        List<Student> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudents);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Date d = rs.getDate("dob");
                LocalDate ld;
                if (d != null) {
                    ld = d.toLocalDate();
                } else {
                    ld = null;
                }

                Student s = new Student(rs.getInt("stid"), rs.getString("fname"), rs.getString("lname"), ld, rs.getFloat("tuitionfees"));
                list.add(s);

            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showStudents() {
        List<Student> list = getStudents();

        for (Student s : list) {
            System.out.println(s);
        }
    }

    public static Student getStudentById(int stid) {
        Student st = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudentById);
            pst.setInt(1, stid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Date d = rs.getDate("dob");
            LocalDate ld;
            if (d != null) {
                ld = d.toLocalDate();
            } else {
                ld = null;
            }

            st = new Student(stid, rs.getString("fname"), rs.getString("lname"), ld, rs.getFloat("tuitionfees"));

            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;

    }

    public static void insertStudent(Scanner input) {
        System.out.println("Give student First name");
        String fname = input.nextLine();
        System.out.println("Give student Last name");
        String lname = input.nextLine();
        Date dob = checkDate(input);
        System.out.println("Give student tuition fees");
        float tuitionfees = input.nextFloat();
        input.nextLine();

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStudent);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setDate(3, dob);
            pst.setFloat(4, tuitionfees);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Student inserted successfully");
            } else {
                System.out.println("Student not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Date checkDate(Scanner input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        Date d=null;
        try {
            System.out.println("Give date of birth in dd/mm/yyyy format");
           String date = input.nextLine();
            LocalDate ld= LocalDate.parse(date, formatter);
           d= Date.valueOf(ld);
            
        } catch (Exception e) {
            System.out.println("Invalid input.Try again");
            checkDate(input);
        }
        
        return d;

    }

}
