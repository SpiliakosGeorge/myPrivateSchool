
package dao;

import entities.Trainer;
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


public class TrainerDao {
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getTrainers = "SELECT * FROM trainer";
    private static final String getTrainerById = "SELECT * FROM trainer where trid=?";
    private static final String insertTrainer = "insert into trainer(fname,lname,subject) values(?,?,?)";


    private static  Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Trainer> getTrainers() {
        List<Trainer> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainers);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {   
                Trainer t = new Trainer(rs.getInt("trid"),rs.getString("fname"), rs.getString("lname"),rs.getString("subject"));
                list.add(t);
            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showTrainers(){
        List <Trainer> list = getTrainers();
       
        for (Trainer t: list){
            System.out.println(t);
        }
    }

    public static Trainer getTrainerById(int trid) {
        Trainer tr = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainerById);
            pst.setInt(1, trid);
            ResultSet rs = pst.executeQuery();
            rs.next();
             tr = new Trainer(trid,rs.getString("fname"), rs.getString("lname"),rs.getString("subject"));
              
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tr;

    }
    
    public static void insertTrainer(Scanner input) {
        System.out.println("Give trainer First name");
        String fname = input.nextLine();
        System.out.println("Give trainer Last name");
        String lname = input.nextLine();
        System.out.println("Give trainer subject");
        String subject = input.nextLine();
       

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainer);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, subject);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Trainer inserted successfully");
            } else {
                System.out.println("Trainer not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
