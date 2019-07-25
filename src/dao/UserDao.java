
package dao;

import entities.User;
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


public class UserDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getUsers = "select * from user";

    
    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<User> getUsers() {
        List<User> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getUsers);
            ResultSet rs = pst.executeQuery();
                 while (rs.next()) {
                User u = new User(rs.getInt("uid"),rs.getString("uname"), rs.getString("upass"));
              
                list.add(u);
                 }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

}
    
    public static boolean checkUser(Scanner input){
        List<User> users = getUsers();
        boolean exists=false;
        do{
            System.out.println("Give username. Maybe its George");
            String username=input.nextLine();
            System.out.println("Give password. Maybe its test123");
            String password=input.nextLine();
            for(User u:users){
                if(u.getUname().equals(username)){
                    if(u.getUpass().equals(password)){
                        return true;
                    }else{
                        System.out.println("Wrong input. Try again.");
                    }
                }else{
                    System.out.println("Wrong input.Try again");
                }
            }
            
        }while(exists!=true);
        return exists;
    }
}
