package main.webapp;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

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
        String tech = req.getParameter("tech");

        Part filePart = req.getPart("attachment");
        String fileName = filePart.getSubmittedFileName();

        for (Part part : req.getParts()) {
            part.write("/run/media/darth-kartikey/Drive/ServeletJDBC/src/main/temp/" + fileName);
        }
        PrintWriter out =  resp.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('User or password incorrect');");
        out.println("</script>");
        resp.sendRedirect("index.html");

        System.out.println(name+" "+email+" "+tech);
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }
}
