package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DaoFactory;
import models.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = -7489880363745101054L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean empty = request.getParameter("username").isEmpty() ||
                        request.getParameter("password").isEmpty();
        if(empty){
            request.setAttribute("error", "Please enter a username/password");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }else{

            if(DaoFactory.getUsersDao().checkPassword( request.getParameter("username"), request.getParameter("password"))){
                User user = DaoFactory.getUsersDao().byUsername(
                    request.getParameter("username")
                );
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/user?id=" + user.getId());
            }else{
                request.setAttribute("error", "Incorrect Password");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            };
            
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
    }

}