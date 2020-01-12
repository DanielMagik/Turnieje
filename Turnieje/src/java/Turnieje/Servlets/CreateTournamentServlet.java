/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet responsible for creating the tournament.
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
@WebServlet(name = "CreateTournamentServlet", urlPatterns = {"/CreateTournament"})
public class CreateTournamentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String JSONString = request.getParameter("JSONFromCreateTournament");
        JSONObject JSON = new JSONObject(JSONString);

        String tournamentName = JSON.getString("name");
        String type = JSON.getString("type");
        String startDate = JSON.getString("startDate");
        String endDate = JSON.getString("endDate");
        String discipline = JSON.getString("discipline");
        String teamSize = JSON.getString("teamSize");
        
        //sprawdzenie czy bangla
        System.out.print(tournamentName);
        System.out.print(type);
        System.out.print(startDate);
        System.out.print(endDate);
        System.out.print(discipline);
        System.out.print(teamSize);
        

        JSONArray teams = JSON.getJSONArray("teamsToAdd");
        //wypisanie dodanych uzytkonwikow w ramach testu czy dziala
        for (int i = 0; i < teams.length(); i++) 
        {
            System.out.print(teams.getString(i));
        }

        Cookie cookie = new Cookie("aboutTournament", JSONString);
        response.addCookie(cookie);
        
        response.sendRedirect("/Turnieje/TournamentCreateManage/TournamentCreated.jsp?tournamentName=" + tournamentName);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
