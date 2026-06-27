package com.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewProfilesServlet")
public class ViewProfilesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<ProfileBean> profiles = new ArrayList<ProfileBean>();
        String search = request.getParameter("search");

        try {
            Connection conn = DBConnection.getConnection();

            String sql;
            PreparedStatement ps;

            if (search != null && !search.trim().equals("")) {
                sql = "SELECT * FROM profiles WHERE name LIKE ? OR studentId LIKE ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");
            } else {
                sql = "SELECT * FROM profiles";
                ps = conn.prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProfileBean profile = new ProfileBean();
                profile.setName(rs.getString("name"));
                profile.setStudentId(rs.getString("studentId"));
                profile.setProgram(rs.getString("program"));
                profile.setEmail(rs.getString("email"));
                profile.setHobbies(rs.getString("hobbies"));
                profile.setIntro(rs.getString("intro"));

                profiles.add(profile);
            }

            request.setAttribute("profiles", profiles);
            request.setAttribute("search", search);
            request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.toString());
        }
    }
}