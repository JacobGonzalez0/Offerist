package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import dao.DaoFactory;
import models.User;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean empty = request.getParameter("username").isEmpty() ||
                        request.getParameter("email").isEmpty() ||
                        request.getParameter("password").isEmpty() ||
                        request.getParameter("confirm").isEmpty();
        boolean confirmPassword = request.getParameter("password").equals(request.getParameter("confirm"));
        boolean userExists = DaoFactory.getUsersDao().exists( 
            request.getParameter("username"), 
            request.getParameter("email"));

        if(empty){
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(!confirmPassword){
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(userExists){
            request.setAttribute("error", "User/email already exists");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }{
            try{
                //create user and send it off, we dont put any values into more memory than it should
                DaoFactory.getUsersDao().insert(new User(
                    request.getParameter("username") ,
                    request.getParameter("email"),
                    BCrypt.hashpw( request.getParameter("password") , BCrypt.gensalt())
                ));
                request.setAttribute("messsage", "Registered " + request.getParameter("username") + " Successfully! Please login.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }catch(Exception e){
                System.err.println(e);
                request.setAttribute("error", e.toString());
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

}