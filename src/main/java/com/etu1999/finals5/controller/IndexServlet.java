package com.etu1999.finals5.controller;

import com.etu1999.finals5.entity.symptome.Symptome;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/forms")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Symptome symptome = new Symptome();
        Connection con = null;
        try {
            con = symptome.createConnection();
            List<Symptome> symptomes = symptome.findAll(con);
            req.setAttribute("symptomes", symptomes);
            con.close();

            RequestDispatcher rd = req.getRequestDispatcher("forms.jsp");
            rd.forward(req, resp);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
