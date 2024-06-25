/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busticketbooking;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author THÁI NGUYÊN
 */
public class database {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/busdata", "root", "");
            System.out.println("connect db success");
//            jdbc:mysql://localhost:3307/busdata?autoReconnect=true&useSSL=false
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
