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
            part.write("/run/media/darth-kartikey/Drive/servletJDBC/src/main/temp/" + fileName);
        }
        PrintWriter out =  resp.getWriter();
        System.out.println(name+" "+email+" "+interest);
        Insert data = new Insert(name,email,interest);
        docInsert doc = new docInsert(fileName);

        if (!data.uploadData()) System.out.println("Couldn't upload data...");
        else if (!doc.uploadDoc()) System.out.println("unable to upload doc...");
        else {
            out.println("<script type='text/javascript'>");
            out.println("window.alert('Student Record Inserted Successfully....');");
            out.println("location='index.html';");
            out.println("</script>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {

        doGet(request, response);
    }
}
