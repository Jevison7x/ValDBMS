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
package com.valdbms.security;

import com.valdbms.adminpins.AdminPinDAO;
import com.valdbms.users.User;
import com.valdbms.users.UserDAO;
import com.valdbms.util.DateTimeUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ugimson
 */
public class CreateAccountServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Digester digester = new Digester();
            String encPassword = digester.doDigest(password);
            if(UserDAO.getUser(email) != null)
                throw new IllegalStateException("The email adress: " + email + " has already been taken.");
            if(UserDAO.getUserByEmail(userName) != null)
                throw new IllegalStateException("The username: " + userName + " has already been taken.");
            User user = validateUser(request);
            if(UserDAO.createNewUser(user, encPassword))
                response.sendRedirect("login-page?login=1");
            else
            {
                request.setAttribute("errorMessage", "There was an unknown error, your account was not created successfully!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("create-account");
                dispatcher.forward(request, response);
            }
        }
        catch(Exception xcp)
        {
            if(xcp instanceof IllegalStateException || xcp instanceof IllegalArgumentException)
            {
                request.setAttribute("errorMessage", xcp.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("create-account");
                dispatcher.forward(request, response);
            }
            else
                throw new RuntimeException(xcp);
        }

    }

    private User validateUser(HttpServletRequest request)
    {
        String userName = request.getParameter("userName").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String conpassword = request.getParameter("coNpassword").trim();
        String pin = request.getParameter("pin").trim();
        if(userName.isEmpty())
            throw new IllegalArgumentException("The username must not be empty");
        else if(userName.length() < 5)
            throw new IllegalArgumentException("Username should be at least 5 characters.");
        else if(password.isEmpty())
            throw new IllegalArgumentException("Please enter a password.");
        else if(password.length() < 6)
            throw new IllegalArgumentException("Password should be at least 6 characters.");
        else if(!password.equals(conpassword))
            throw new IllegalArgumentException("The passwords did not match.");
        else if(!AdminPinDAO.pinIsValid(pin))
            throw new IllegalArgumentException("The pin you entered is invalid.");
        else
        {
            User user = new User();
            user.setEmail(email);
            user.setUserName(userName);
            user.setDateCreated(DateTimeUtil.getTodayTimeZone());
            return user;
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
