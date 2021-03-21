package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.DaoFactory;
import models.Ad;
import models.User;


@WebServlet(name = "AddPostServlet", urlPatterns = "/post/add")
@MultipartConfig
public class AddPostServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2915480862501091189L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }else{    
            request.getRequestDispatcher("/WEB-INF/postAdd.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("error","You need to be logged in to make a post");
            response.sendRedirect("/login");
            return;
        }else{    
            boolean empty = request.getPart("title").toString().isEmpty() ||
                            request.getPart("description").toString().isEmpty() ||
                            request.getPart("price").toString().isEmpty();
            if(empty){
                request.setAttribute("error","Post missing specified fields");
                request.getRequestDispatcher("/WEB-INF/postAdd.jsp").forward(request, response);
            }


            User user = (User) request.getSession().getAttribute("user"); // grab the user info
            Ad ad;
            try{ //try creating the ad bean
                ad = new Ad(
                    user.getId(), 
                    request.getParameter("title"), 
                    request.getParameter("description"), 
                    Long.valueOf(request.getParameter("price")));

                //start on gathering images
                List<String> images = new ArrayList<>();
                if(request.getPart("image") != null){ //check if there is an image or not
                    try{
                        //TODO: check image to see if its valid

                        Part image = request.getPart("image"); //get the image data
                        InputStream filecontent = image.getInputStream(); //create file reading stream
                        ByteArrayOutputStream out = new ByteArrayOutputStream(); //a buffer to read the stream to a byte array to avoid file writing
                        int read = 0; //specifies where we are reading in the file
                        final byte[] bytes = new byte[1024];
    
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        String encodedString = Base64.getEncoder().encodeToString(out.toByteArray()); //we take that byte array and convert it to an encodedString
                        System.out.println(encodedString);
                        
                        images.add("data:image/jpeg;base64," + encodedString); //finally we get that encoded string and pass it to the image array
                    }catch(Exception e){
                        request.setAttribute("error","Image upload error");
                        request.getRequestDispatcher("/WEB-INF/postAdd.jsp").forward(request, response);
                    }
                }
                ad.setImages(images);
                DaoFactory.getAdsDao().insert(ad);
    
                request.setAttribute("message","New post created!");
                request.getRequestDispatcher("/WEB-INF/postAdd.jsp").forward(request, response);

            }catch(NumberFormatException e){
        
                request.setAttribute("error",e.toString());
                request.getRequestDispatcher("/WEB-INF/postAdd.jsp").forward(request, response);
            }
            
        }
        
    }

}