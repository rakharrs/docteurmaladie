package com.etu1999.finals5.controller;

import com.etu1999.finals5.Dokotera;
import com.etu1999.finals5.entity.maladie.Maladie;
import com.etu1999.finals5.entity.medicament.Medicament;
import com.etu1999.finals5.entity.medicament.Prescription;
import com.etu1999.finals5.entity.patient.Patient;
import com.etu1999.finals5.entity.symptome.Symptome;
import com.etu1999.finals5.utils.Misc;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

@WebServlet("/prescriptions")
public class PatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String symptomes = req.getParameter("symptomes");
        String valeurs = req.getParameter("valeurs");
        String datenaissance = req.getParameter("datenaissance");
        Date birthdate = Date.valueOf(datenaissance);

        Symptome symptome = new Symptome();
        Connection con = null;

        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setDateDeNaissance(Date.valueOf(datenaissance));
        try{
            con = symptome.createConnection();
            List<Symptome> symptomes1 = symptome.findAll(con);
            List<Medicament> medicaments = new Medicament().findAll(con);
            List<Maladie> maladies = new Maladie().findAll(con);

            patient.setSymptomePatients(Misc.getSymptomePatient(symptomes, valeurs, symptomes1));
            Prescription prescription = Dokotera.getPrescription(symptomes1, medicaments, patient);

            req.setAttribute("patient", patient);
            req.setAttribute("prescription", prescription);
            req.setAttribute("maladies", patient.getMaladies(maladies));

            RequestDispatcher rd = req.getRequestDispatcher("prescription.jsp");
            rd.forward(req, resp);

            con.close();
        }catch (Exception e){
            e.printStackTrace();
            String error = "Pas de solution";
            resp.sendRedirect("forms?error="+error);
        }

    }
}
