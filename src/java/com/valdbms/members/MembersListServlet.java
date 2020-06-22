/*
 * Copyright (c) 2018, Xyneex Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact Xyneex Technologies, #1 Orok Orok Street, Calabar, Nigeria.
 * or visit www.xyneex.com if you need additional information or have any
 * questions.
 */
package com.valdbms.members;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.valdbms.banks.BankDAO;
import com.valdbms.roles.RolesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Jevison7x
 */
public class MembersListServlet extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            if(request.getParameter("json") != null)
            {
                response.setContentType("application/json");
                try(PrintWriter out = response.getWriter())
                {
                    boolean filtering = false;
                    String role = request.getParameter("columns[4][search][value]");
                    String state = request.getParameter("columns[6][search][value]");
                    String lga = request.getParameter("columns[7][search][value]");
                    String ward = request.getParameter("columns[8][search][value]");
                    String bank = request.getParameter("columns[9][search][value]");
                    if(!role.isEmpty())
                        filtering = true;
                    else if(!state.isEmpty())
                        filtering = true;
                    else if(!lga.isEmpty())
                        filtering = true;
                    else if(!ward.isEmpty())
                        filtering = true;
                    else if(!bank.isEmpty())
                        filtering = true;
                    int toIndex = Integer.parseInt(request.getParameter("length"));
                    int fromIndex = Integer.parseInt(request.getParameter("start"));
                    int colIndex = Integer.parseInt(request.getParameter("order[0][column]"));
                    String sortColumn = null;
                    switch(colIndex)
                    {
                        case 0:
                            sortColumn = Member.SN;
                            break;
                        case 1:
                            sortColumn = Member.TITLE;
                            break;
                        case 2:
                            sortColumn = Member.FIRST_NAME;
                            break;
                        case 3:
                            sortColumn = Member.LAST_NAME;
                            break;
                        case 4:
                            sortColumn = Member.ROLE;
                            break;
                        case 5:
                            sortColumn = Member.MOBILE_NO;
                            break;
                        case 6:
                            sortColumn = Member.STATE;
                            break;
                        case 7:
                            sortColumn = Member.LGA;
                            break;
                        case 8:
                            sortColumn = Member.WARD;
                            break;
                        case 9:
                            sortColumn = Member.BANK;
                            break;
                        case 10:
                            sortColumn = Member.ACCOUNT_NO;
                            break;
                        case 11:
                            sortColumn = Member.ACCOUNT_NAME;
                            break;
                        case 12:
                            sortColumn = Member.EMAIL;
                            break;
                        default:
                            sortColumn = Member.SN;
                            break;
                    }
                    int echo = Integer.parseInt(request.getParameter("draw"));
                    String sortDirection = request.getParameter("order[0][dir]");
                    int iTotalRecords = MembersDAO.getMembersTotalCount(); // total number of records (unfiltered)
                    JsonObject jsonResponse = new JsonObject();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    if(filtering == true)
                    {
                        Map<String, String> filterParameters = new HashMap<>();
                        filterParameters.put("role", role);
                        filterParameters.put("state", state);
                        filterParameters.put("lga", lga);
                        filterParameters.put("ward", ward);
                        filterParameters.put("bank", bank);
                        HttpSession session = request.getSession(false);
                        session.setAttribute("filterParameters", filterParameters);
                        List<Member> filteredMembers = MembersDAO.filteredMembers(role, state, lga, ward, bank);
                        if(filteredMembers.size() < 10 || filteredMembers.size() - fromIndex < 10)
                            toIndex = filteredMembers.size();
                        else
                            toIndex = fromIndex + toIndex;
                        List<Member> filteredMembersSubList = filteredMembers.subList(fromIndex, toIndex);
                        jsonResponse.addProperty("iTotalDisplayRecords", filteredMembers.size());
                        jsonResponse.add("aaData", gson.toJsonTree(filteredMembersSubList));
                    }
                    else
                    {
                        String sSearch = StringEscapeUtils.unescapeHtml(request.getParameter("search[value]"));
                        List<Member> members = MembersDAO.getSearchResults(sSearch, sortColumn, sortDirection, fromIndex, toIndex);
                        int iTotalDisplayRecords = MembersDAO.getSearchTotal(sSearch, sortColumn, sortDirection);
                        jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
                        jsonResponse.addProperty("sSearch", sSearch);
                        jsonResponse.add("aaData", gson.toJsonTree(members));
                    }
                    jsonResponse.addProperty("sEcho", echo);
                    jsonResponse.addProperty("iTotalRecords", iTotalRecords);
                    out.print(jsonResponse);
                }
                catch(Exception xcp)
                {
                    xcp.printStackTrace(System.err);
                }
            }
            else
            {
                HttpSession session = request.getSession(false);
                Set<String> selectedPhoneNumbers = (Set<String>)session.getAttribute("selectedPhoneNumbers");
                if(selectedPhoneNumbers != null)
                    selectedPhoneNumbers = new TreeSet<>();
                session.setAttribute("selectedPhoneNumbers", selectedPhoneNumbers);
                List<String> roles = RolesDAO.getDistinctRoles();
                List<String> banksList = BankDAO.getDistinctBanks();
                request.setAttribute("roles", roles);
                request.setAttribute("banksList", banksList);
                request.getRequestDispatcher("members-page").forward(request, response);
            }
        }
        catch(Exception xcp)
        {
            throw new RuntimeException(xcp);
        }
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
