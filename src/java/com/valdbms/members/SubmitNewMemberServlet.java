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

import com.valdbms.banks.Bank;
import com.valdbms.banks.BankDAO;
import com.valdbms.users.User;
import com.valdbms.util.DateTimeUtil;
import com.valdbms.wards.Ward;
import com.valdbms.wards.WardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ugimson
 * @since May 31, 2020 10:24:08 PM
 */
public class SubmitNewMemberServlet extends HttpServlet
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        String title = request.getParameter("title");
        String mobileNo = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String role = request.getParameter("role");
        String state = request.getParameter("state");
        String lga = request.getParameter("lga");
        String ward = request.getParameter("ward");
        String bank = request.getParameter("bank");
        String accountNo = request.getParameter("accountNo");
        String accountName = request.getParameter("accountName");
        String email = request.getParameter("email");
        String addedBy = user.getUserName();
        try
        {
            Ward wardObj = WardDAO.getWard(state, lga, ward);
            if(wardObj == null)
            {
                wardObj = new Ward();
                wardObj.setWard(ward);
                wardObj.setLga(lga);
                wardObj.setState(state);
                WardDAO.createNewWard(wardObj);
            }

            Bank bankObj = BankDAO.getBank(bank);
            if(bankObj == null)
            {
                bankObj = new Bank();
                bankObj.setBank(bank);
                BankDAO.createBank(bankObj);
            }

            Member member = new Member();
            member.setTitle(title);
            member.setMobileNo(mobileNo);
            member.setFirstName(firstName);
            member.setMiddleName(middleName);
            member.setLastName(lastName);
            member.setRole(role);
            member.setState(state);
            member.setLga(lga);
            member.setWard(ward);
            member.setBank(bank);
            member.setAccountNo(accountNo);
            member.setAccountName(accountName);
            member.setEmail(email);
            member.setDateAdded(DateTimeUtil.getTodayTimeZone());
            member.setAddedBy(addedBy);
            MembersDAO.createNewMember(member);
            out.print("success");
        }
        catch(Exception xcp)
        {
            if(xcp instanceof EntityExistsException)
                out.print("The phone number " + mobileNo + " already exists.");
            else
                out.print(xcp.getMessage());
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
