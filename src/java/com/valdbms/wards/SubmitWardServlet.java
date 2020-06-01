
import com.valdbms.members.Member;
import com.valdbms.util.DateTimeUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
/**
 *
 * @author Ugimson
 * @since May 31, 2020 10:24:08 PM
 */
public class SubmitWardServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            String mobileNo = request.getParameter("mobileNo");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String role = request.getParameter("role");
            String lga = request.getParameter("lga");
            int ward = Integer.parseInt("ward");
            String bank = request.getParameter("bank");
            String accountNo = request.getParameter("accountNo");
            String email = request.getParameter("email");
            Date today = new Date();
            Timestamp dateCreated = new Timestamp(today.getTime());
            String addedBy = request.getParameter("addedBy");

            Member member = new Member();
            member.setMobileNo(mobileNo);
            member.setFirstName(firstName);
            member.setLastName(lastName);
            member.setRole(role);
            member.setLga(lga);
            member.setWard(ward);
            member.setBank(bank);
            member.setAccountNo(accountNo);
            member.setEmail(email);
            member.setDateAdded(DateTimeUtil.getTodayTimeZone());
            member.setAddedBy(addedBy);
        }
        catch(Exception xcp)
        {
            if(xcp instanceof IllegalStateException || xcp instanceof IllegalArgumentException)
            {
                request.setAttribute("errorMessage", xcp.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("new-member");
                dispatcher.forward(request, response);
            }
            else
                throw new RuntimeException(xcp);
        }
    }
}
