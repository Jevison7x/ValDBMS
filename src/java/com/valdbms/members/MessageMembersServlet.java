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

import com.valdbms.util.HttpClientAcceptSelfSignedCertificate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jevison7x
 */
public class MessageMembersServlet extends HttpServlet
{
    private static final String SENDER = "ValOzigbo";
    private static final String API_KEY = "fHTGT6PiRxIE1ZrMvOTDcsu0cM5yiQKz2EnWAXxQqkMt6UlAUHwHGIBFxQW4";

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
        PrintWriter out = response.getWriter();
        try
        {
            HttpSession session = request.getSession(false);
            Set<String> phonenumbers = (Set<String>)session.getAttribute("selectedPhoneNumbers");
            Map<String, String> filterParameters = (Map<String, String>)session.getAttribute("filterParameters");
            if(phonenumbers == null)
                if(filterParameters != null)
                {
                    String role = filterParameters.get("role");
                    String state = filterParameters.get("state");
                    String lga = filterParameters.get("lga");
                    String ward = filterParameters.get("ward");
                    String bank = filterParameters.get("bank");
                    List<Member> filteredMembers = MembersDAO.filteredMembers(role, state, lga, ward, bank);
                    phonenumbers = new TreeSet<>();
                    for(Member member : filteredMembers)
                        phonenumbers.add(member.getMobileNo());
                }
            String originalMessage = request.getParameter("message");
            if(phonenumbers != null)
                for(String phoneNumber : phonenumbers)
                {
                    String smsMessage = originalMessage;
                    if(originalMessage.contains("${firstName}") || originalMessage.contains("${lastName}"))
                    {
                        Member member = MembersDAO.getMember(phoneNumber);
                        String firstName = member.getFirstName();
                        String lastName = member.getLastName();
                        smsMessage = smsMessage.replace("${firstName}", firstName);
                        smsMessage = smsMessage.replace("${lastName}", lastName);
                    }
                    HttpClientAcceptSelfSignedCertificate.bulkSmsApiConnection(API_KEY, SENDER, phoneNumber, smsMessage);
                    out.print(phoneNumber + ", ");
                }
            else
                out.print("empty phone numbers");
        }
        catch(Exception xcp)
        {
            xcp.printStackTrace(out);
            xcp.printStackTrace(System.err);
        }
        finally
        {
            out.close();
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
