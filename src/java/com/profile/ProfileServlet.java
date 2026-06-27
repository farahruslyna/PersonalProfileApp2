package com.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfileBean profile = new ProfileBean();

        profile.setName(request.getParameter("name"));
        profile.setStudentId(request.getParameter("studentId"));
        profile.setProgram(request.getParameter("program"));
        profile.setEmail(request.getParameter("email"));
        profile.setHobbies(request.getParameter("hobbies"));
        profile.setIntro(request.getParameter("intro"));

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO profiles (name, studentId, program, email, hobbies, intro) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentId());
            ps.setString(3, profile.getProgram());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntro());

            ps.executeUpdate();

            request.setAttribute("profile", profile);
            request.getRequestDispatcher("profile.jsp").forward(request, response);

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.toString());
        }
    }
}