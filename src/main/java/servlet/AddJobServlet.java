package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import utils.DBUtil;

@WebServlet("/addJob")
public class AddJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String location = request.getParameter("location");
        String experience = request.getParameter("experience");
        String applyLink = request.getParameter("apply_link");

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO jobs (title, company, location, experience, apply_link) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, location);
            ps.setString(4, experience);
            ps.setString(5, applyLink);

            ps.executeUpdate();
            conn.close();

            response.sendRedirect("jobs"); // Redirect to job listing after adding
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
