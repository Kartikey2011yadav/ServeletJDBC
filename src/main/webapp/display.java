package main.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import static java.util.jar.Attributes.Name.CONTENT_TYPE;
@WebServlet("/display.html")
public class display extends HttpServlet {
    Connection conn=null;
    public display() {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/AdvJava","root","Kartikey2011");
        }
        catch (SQLException e)
        {
            System.out.println("Exception:"+e.getMessage());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Statement statement = null;
        ResultSet rs = null;
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery( "SELECT * FROM Student_Record" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            System.err.println( "no data" );
        }
        resp.setContentType(String.valueOf(CONTENT_TYPE));
        try
        {
            PrintWriter out = resp.getWriter();

            showTable( rs, out, "/display.html" );

            conn.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            System.err.println( "can not display table" );
        }
    }
    private void showTable( ResultSet rs, PrintWriter out, String uri ) throws
            Exception
    {
        boolean hasNext = false;
        out.println( "<html lang=\"en\">\n" +
                "<head>\n" +
                "\n" +
                "    <title>Java Servelet Demo Application</title>\n" +
                "\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "\n" +
                "    <!-- Stylesheet -->\n" +
                "    <link class=\"native-dark-class-original\" href=\"font-awesome.css\" rel=\"stylesheet\">\n" +
                "    <link class=\"native-dark-class-original\" href=\"style-display.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <!-- //Stylesheet -->\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<link class=\"native-dark-class-original\" href=\"font-awesome.min.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "<div class=\"center-container\">\n" +
                "\n" +
                "    <h1>Student details Table Display</h1>\n" +
                "\n" +
                "    <div class=\"container\">\n" +
                "        <table>\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>ID</th>\n" +
                "                <th>Name</th>\n" +
                "                <th>Email</th>\n" +
                "                <th>Interest</th>\n" +
                "                <th>Doc.</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>" );

        ResultSetMetaData rsmd = rs.getMetaData();
        StringBuffer sb = new StringBuffer( "<tr>" );
        out.println( sb.toString() );
        while ( rs.next() )
        {
            sb = new StringBuffer( "<tr>" );

            int id = rs.getInt("id");
            String email = rs.getString("email");
            String interest = rs.getString("interest");
            String name = rs.getString("name");

            sb.append("<td>").append(id).append("</td>");
            sb.append("<td>").append(name).append("</td>");
            sb.append("<td>").append(email).append("</td>");
            sb.append("<td>").append(interest).append("</td>");
            sb.append("<td><a href='/servletJDBC/docView.jsp?id=").append(id).append("'>").append("view Doc").append("</a></td>");

            sb.append( "</tr>" );
            out.println( sb.toString() );

        }
        out.println( "</tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<!--js-->\n" +
                "<script src=\"./Career Appeal Form Responsive Widget Template __ w3layouts_files/jquery-2.2.3.min.js\"></script>\n" +
                "<script src=\"./Career Appeal Form Responsive Widget Template __ w3layouts_files/jquery.vide.min.js\"></script>\n" +
                "<!--//js-->\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>" );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet( req, resp );
    }
}
