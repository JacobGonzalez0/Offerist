package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DaoFactory;

@WebServlet(name = "IndexServlet", urlPatterns = "/")
public class IndexServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2461742577999832417L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.setAttribute("catagories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}