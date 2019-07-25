package dao;

import entities.Stream;
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

public class StreamDao {

    private static final String URL = "jdbc:mysql://localhost:3306/myprivateschool?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "1234";
    private static Connection conn;
    private static final String getStreamById = "SELECT * FROM stream where sid=?";
    private static final String getLastStream = "SELECT MAX(sid) as sid from stream";
    private static final String getStream = "select * from stream";
    private static final String insertStream = "insert into stream(sdescr) values (?)";

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(StreamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private static void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StreamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Stream getStreamById(int sid) {
        Stream s = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStreamById);
            pst.setInt(1, sid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new Stream(rs.getInt("sid"), rs.getString("sdescr"));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    private static List<Stream> getStream() {
        List<Stream> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStream);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Stream s = new Stream(rs.getInt("sid"), rs.getString("sdescr"));
                list.add(s);
            }
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public static void showStream() {
        List<Stream> list = getStream();

        for (Stream s : list) {
            System.out.println(s);
        }
    }

    public static void insertStream(Scanner input) {
        System.out.println("Give Stream name");
        String stream = input.nextLine();

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStream);
            pst.setString(1, stream);
            int result = pst.executeUpdate();

            pst.close();
            conn.close();

        } catch (SQLException ex) {

            Logger.getLogger(StreamDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getLastStream() {
        int sid = 0;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getLastStream);
            ResultSet rs = pst.executeQuery();
            rs.next();
            sid = (rs.getInt("sid"));
            closeConnections(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(MyPrivateSchoolwSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sid;

    }

}
