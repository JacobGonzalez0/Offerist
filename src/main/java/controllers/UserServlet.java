package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DaoFactory;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("id") == null){
            request.setAttribute("error", "No user specified");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }else{
            try{
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("ads", DaoFactory.getAdsDao().byUserId(id) );
                request.setAttribute("user", DaoFactory.getUsersDao().byId(id) );
                request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);

            }catch(Exception e){
                System.err.println(e);
                request.setAttribute("error", "Unable to find user");
                request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            }
        }
        

        
        
    }

}