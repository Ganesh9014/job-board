package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import utils.DBUtil;

@WebServlet("/jobs")
public class JobListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String, String>> jobs = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM jobs");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> job = new HashMap<>();
                job.put("title", rs.getString("title"));
                job.put("company", rs.getString("company"));
                job.put("location", rs.getString("location"));
                job.put("experience", rs.getString("experience"));
                job.put("apply_link", rs.getString("apply_link"));
                jobs.add(job);
            }

            request.setAttribute("jobs", jobs);
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("joblist.jsp");
        dispatcher.forward(request, response);
    }
}
