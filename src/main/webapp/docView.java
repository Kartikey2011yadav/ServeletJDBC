package main.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/docView")
public class docView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str_id = req.getParameter("id");
        PrintWriter out =  resp.getWriter();
        if (str_id != null && !str_id.isEmpty()) {
            try {
                int id = Integer.parseInt(str_id);
                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava", "root", "Kartikey2011");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Student_Record WHERE id=" + id);

                if (rs.next()) {
                    Blob blob = rs.getBlob("doc");
                    InputStream inputStream = blob.getBinaryStream();
                    BufferedImage image = ImageIO.read(inputStream);
                    resp.setContentType("image/jpeg");
                    OutputStream outputStream = resp.getOutputStream();
                    ImageIO.write(image, "jpg", outputStream);
                }
                else
                {
                    out.println("Image not found for ID: " + id);
                }
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());

            }
        }
    }

}
