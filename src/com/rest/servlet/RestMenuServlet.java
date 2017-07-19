package com.rest.servlet;

import com.rest.domain.*;
import com.rest.service.DinnerTableService;
import com.rest.service.FoodService;
import com.rest.service.FoodTypeService;
import com.rest.utils.WebFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by xuero on 2017/7/18.
 */
public class RestMenuServlet extends HttpServlet {

    DinnerTableService service = WebFactory.getInstance("DinnerTableService",DinnerTableService.class);
    FoodService foodService = WebFactory.getInstance("FoodService",FoodService.class);
    FoodTypeService foodTypeService = WebFactory.getInstance("FoodTypeService",FoodTypeService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");

        if(action == null){
            action="listIdleTables";
        }
        if(action.equals("listIdleTables")){
            listIdleTables(req,resp);
        }else if(action.equals("listFood")){
            listFood(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void listIdleTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<DinnerTable> idleTables = service.getIdleTables();
        req.setAttribute("dinnerTables",idleTables);


        String url = "/frontEnd/index.jsp";
        req.getRequestDispatcher(url).forward(req,resp);
    }

    protected void listFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tableId = req.getParameter("tableId");
        if (req.getSession().getAttribute("dinnerTable") == null && tableId != null) {

            DinnerTable table = service.getTableById(Integer.parseInt(tableId));

            HttpSession session = req.getSession();
            session.setAttribute("dinnerTable",table);
        }

        PageBean<Food> pb = new PageBean<Food>();

        String curPage = req.getParameter("currentPage");
        if(curPage == null ){
            pb.setCurrentPage(0);
        }else {
            pb.setCurrentPage(Integer.parseInt(curPage));
        }

        Condition condition = new Condition();
        String foodTypeid = req.getParameter("foodType_id");
        if(foodTypeid != null && !"".equals(foodTypeid.trim())){
            condition.setFoodTypeId(Integer.parseInt(foodTypeid));
            req.setAttribute("foodType",foodTypeid);
        }else {
            req.setAttribute("foodType",null);
        }

        String foodName = req.getParameter("foodName");
        if(foodName != null && !"".equals(foodName.trim())){
            condition.setFoodName(foodName);
        }

        pb.setCondition(condition);

        foodService.getAll(pb);

        req.setAttribute("pb",pb);

        List<FoodType> foodTypes = foodTypeService.getAll();
        req.setAttribute("foodTypes",foodTypes);

        String url = "/frontEnd/detail/menu.jsp";
        req.getRequestDispatcher(url).forward(req,resp);
    }
}
