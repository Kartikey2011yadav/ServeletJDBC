package main.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

@WebServlet("/success")
@MultipartConfig(
        fileSizeThreshold = 1024 * 10,  // 10 KB
        maxFileSize = 1024 * 300,       // 300 KB
        maxRequestSize = 1024 * 1024    // 1 MB
)
public class success extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("Name");
        String email = req.getParameter("Email");
        String interest = req.getParameter("tech");

        Part filePart = req.getPart("attachment");
        String fileName = filePart.getSubmittedFileName();

        for (Part part : req.getParts()) {
            part.write("/run/media/darth-kartikey/Drive/ServeletJDBC/src/main/temp/" + fileName);
        }
        PrintWriter out =  resp.getWriter();
        out.println("<script type='text/javascript'>");
        out.println("window.alert('User or password incorrect');");
        out.println("location='index.html';");
        out.println("</script>");

        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava", "root", "Kartikey2011");
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Student_Record(id int AUTO_INCREMENT,name VARCHAR(30),email VARCHAR(30),interest VARCHAR(25),doc MEDIUMBLOB)");

            stat.executeUpdate("INSERT INTO Student_Record(name,email,interest) VALUES('"+name+"','"+email+"','"+interest+"');");
            System.out.println("Record Inserted");
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }

        System.out.println(name+" "+email+" "+interest);

    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }
}
