package main.webapp;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class docInsert {
    String path;

    public docInsert(String path) {
        this.path = path;
    }

    boolean uploadDoc(){
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava", "root", "Kartikey2011");

            Statement statement= conn.createStatement();
            int id = 0;
            ResultSet res= statement.executeQuery("Select MAX(id) from Student_Record;");
            while(res.next())
            {
                id = res.getInt("MAX(id)");
            }

            String query="UPDATE Student_Record SET doc=? WHERE id=?;";
            PreparedStatement stat= conn.prepareStatement(query);

            File file = new File("/run/media/darth-kartikey/Drive/servletJDBC/src/main/temp/"+path);
            FileInputStream filestream=new FileInputStream(file);

            stat.setBinaryStream(1,filestream,filestream.available());
            stat.setInt(2,id);
            stat.executeUpdate();

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
