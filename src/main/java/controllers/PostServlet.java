package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DaoFactory;

@WebServlet(name = "PostServlet", urlPatterns = "/post")
public class PostServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2915480862501091189L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("id") == null){
            request.setAttribute("error", "No post specified");
            request.getRequestDispatcher("/WEB-INF/post.jsp").forward(request, response);
        }else{
            try{
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("ad", DaoFactory.getAdsDao().byId(id).get(0) );
                request.getRequestDispatcher("/WEB-INF/post.jsp").forward(request, response);

            }catch(Exception e){
                System.err.println(e);
                request.setAttribute("error", "Unable to find post");
                request.getRequestDispatcher("/WEB-INF/post.jsp").forward(request, response);
            }
        }
        

        
        
    }

}