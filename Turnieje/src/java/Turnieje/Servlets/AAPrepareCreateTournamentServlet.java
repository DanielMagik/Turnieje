/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Turnieje.Servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.aei.io.turnieje.model.datamodel.Discipline;
import pl.polsl.aei.io.turnieje.model.datamodel.Team;
import pl.polsl.aei.io.turnieje.model.datamodel.TournamentMode;
import pl.polsl.aei.io.turnieje.model.repository.IDisciplineRepository;
import pl.polsl.aei.io.turnieje.model.repository.ITeamRepository;
import pl.polsl.aei.io.turnieje.model.repository.RepositoryProvider;

/**
 *
 * @author Daniel-Laptop
 */
@WebServlet(name = "PrepareCreateTournamentServlet", urlPatterns = {"/PrepareCreateTournamentServlet"})
public class AAPrepareCreateTournamentServlet extends HttpServlet {
    
    RepositoryProvider repositoryProvider;
    ITeamRepository teamRepository;
    IDisciplineRepository disciplineRepository;

    @Override
    public void init() {
        repositoryProvider = RepositoryProvider.getInstance();
        teamRepository = repositoryProvider.getTeamRepository();
        disciplineRepository = repositoryProvider.getDisciplineRepository();
    }

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
        
        Set<Team> allTeams = teamRepository.getAll();
        Set<String> allTeamsNames = new HashSet<>();
        for(Team team: allTeams)
        {
            allTeamsNames.add(team.getName());
        }
        Set<String> teamsInTournamentNames = new HashSet<>();
        
        Set<String> allDisciplinesNames = new HashSet<>();
        Set<Discipline> allDisciplines = disciplineRepository.getAll();
        for(Discipline discipline: allDisciplines)
        {
            allDisciplinesNames.add(discipline.getName());
        }
        
        Set<String> allModes = new HashSet<>();
        for(TournamentMode tournamentMode: TournamentMode.values())
        {
            allModes.add(tournamentMode.name());
        }
        
        HttpSession session = request.getSession(true);
        session.setAttribute("allTeams", allTeamsNames);
        session.setAttribute("teamsInTournament", teamsInTournamentNames);
        session.setAttribute("notTeamDisciplines", allDisciplinesNames);
        session.setAttribute("tournamentModes", allModes);
        
        response.sendRedirect("/Turnieje/TournamentCreateManage/CreateTournament.jsp");
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
