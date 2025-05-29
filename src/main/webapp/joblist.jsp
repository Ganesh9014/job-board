<%@ page import="java.util.*, java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Job Listings</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .job-container {
            max-width: 900px;
            margin: auto;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .job-card {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s ease;
        }

        .job-card:hover {
            transform: translateY(-4px);
        }

        .job-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .company {
            font-size: 16px;
            color: #555;
            margin-bottom: 10px;
        }

        .details {
            font-size: 14px;
            color: #777;
        }

        .apply-link {
            margin-top: 10px;
        }

        .apply-link a {
            color: #fff;
            background-color: #4CAF50;
            padding: 8px 16px;
            border-radius: 6px;
            text-decoration: none;
        }

        .apply-link a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Job Listings</h1>
    <div class="job-container">
    <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobboard", "root", "Ganesh54@");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM jobs");

            while (rs.next()) {
    %>
        <div class="job-card">
            <div class="job-title"><%= rs.getString("title") %></div>
            <div class="company"><%= rs.getString("company") %> - <%= rs.getString("location") %></div>
            <div class="details">Experience: <%= rs.getString("experience") %></div>
            <div class="apply-link">
                <a href="<%= rs.getString("apply_link") %>" target="_blank">Apply Now</a>
            </div>
        </div>
    <%
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    %>
    </div>
</body>
</html>
