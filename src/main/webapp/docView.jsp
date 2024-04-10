<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="main.webapp.JDBC.conn.JDBCConn" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.*"%>
<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Display</title>
</head>
<body>
    <div class="sub-head-w3-agileits">
        <h2>Image Display</h2>
        <p>Enter The Student ID</p>
    </div>
        <form method="POST" action="">
            <input name="id" type="text" placeholder="Student ID">
            <br>
            <div>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Clear"/>
            </div>
            <br>
        </form>
        <%
            String str_id = request.getParameter("id");
            if (str_id != null && !str_id.isEmpty()) {
                try {
                    int id = Integer.parseInt(str_id);
                    conn = JDBCConn.getConn();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM Student_Record WHERE id=" + id);

                    if (rs.next()) {
                        Blob blob = rs.getBlob("doc");
                        InputStream inputStream = blob.getBinaryStream();
                        BufferedImage image = ImageIO.read(inputStream);
                        response.setContentType("image/jpeg");
                        OutputStream outputStream = response.getOutputStream();
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
        %>
    </div>
</body>
</html>
