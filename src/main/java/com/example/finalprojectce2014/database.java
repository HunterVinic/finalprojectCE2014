package com.example.finalprojectce2014;
import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/StudentDatabase", "root","");
            return  connect;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
