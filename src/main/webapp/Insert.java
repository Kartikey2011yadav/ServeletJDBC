package main.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert {
    String name,email,interest;

    public Insert(String name, String email, String interest) {
        this.name = name;
        this.email = email;
        this.interest = interest;
    }

    boolean uploadData(){
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava", "root", "Kartikey2011");
            Statement stat = conn.createStatement();
//            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Student_Record(id int AUTO_INCREMENT,name VARCHAR(30),email VARCHAR(30),interest VARCHAR(25),doc MEDIUMBLOB)");

            stat.executeUpdate("INSERT INTO Student_Record(name,email,interest) VALUES('"+name+"','"+email+"','"+interest+"');");
            System.out.println("Record Inserted");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
            return false;
        }
    }
}
