package com.rest.servlet;

import com.rest.domain.FoodType;
import com.rest.service.FoodTypeService;
import com.rest.utils.WebFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xuero on 2017/7/3.
 */
@WebServlet(name = "FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {

    FoodTypeService foodTypeService = WebFactory.getInstance("FoodTypeService",FoodTypeService.class);

    private String uri;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String method = request.getParameter("method");
        if("addFoodType".equals(method)){
            addFoodType(request,response);
        }else if("list".equals(method)){
            list(request,response);
        }else if("deleteCuisine".equals(method)){
            deleteCuisine(request,response);
        }else if("updateCuisine".equals(method)){
            updateCuisine(request,response);
        }else if("update".equals(method)){
            update(request,response);
        }else if("search".equals(method)){
            search(request,response);
        }
    }

    protected void deleteCuisine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String id = request.getParameter("id");

            foodTypeService.delete(Integer.parseInt(id));
            uri = "/foodType?method=list";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);
    }

    protected void updateCuisine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String id = request.getParameter("id");

            FoodType foodType = foodTypeService.findById(Integer.parseInt(id));
            request.setAttribute("cuisine",foodType);
            uri = "/backEnd/detail/updateCuisine.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            FoodType foodType = new FoodType();
            String id = request.getParameter("id");
            String typeName = request.getParameter("typeName");
            foodType.setTypeName(typeName);
            foodType.setId(Integer.parseInt(id));

            foodTypeService.update(foodType);
            uri = "/foodType?method=list";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);
    }

    protected void addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String typeName = request.getParameter("typeName");
            FoodType foodType = new FoodType();
            foodType.setTypeName(typeName);

            foodTypeService.save(foodType);
            uri = "/foodType?method=list";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FoodType> list = foodTypeService.getAll();
            request.setAttribute("cuisineList",list);
            uri="/backEnd/detail/cuisineList.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);

    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String typeName = request.getParameter("keyword");
            List<FoodType> list = foodTypeService.getAll(typeName);
            request.setAttribute("cuisineList",list);
            uri="/backEnd/detail/cuisineList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        request.getRequestDispatcher(uri).forward(request,response);

    }
}
