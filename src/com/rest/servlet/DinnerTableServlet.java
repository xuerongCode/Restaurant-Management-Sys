package com.rest.servlet;

import com.rest.domain.DinnerTable;
import com.rest.service.DinnerTableService;
import com.rest.utils.WebFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by xuero on 2017/7/14.
 */
@WebServlet(name = "DinnerTableServlet")
public class DinnerTableServlet extends HttpServlet {
    DinnerTableService service = WebFactory.getInstance("DinnerTableService",DinnerTableService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        if(action.equals("addTable")){
            addTable(req,resp);
        }else if (action.equals("deleteTable")){
            deleteTable(req,resp);
        }else if (action.equals("reserveTable")){
            reserveTable(req,resp);
        }else if (action.equals("searchTable")){
            searchTable(req,resp);
        }else if (action.equals("listTable")){
            listTable(req,resp);
        }else if(action.equals("cancelTable")){
            cancelTable(req,resp);
        }else if(action.equals("doReserve")){
            doReserve(req,resp);
        }else {
            //report error
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    protected void listTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        List<DinnerTable> allTables = service.getAllTables();
        req.setAttribute("tableList",allTables);

        String uri = "/backEnd/detail/boardList.jsp";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void addTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String tableName = req.getParameter("tableName" );

        //have not checked illegal input yet
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setTableName(tableName);
        //default set to 1
        dinnerTable.setTableStatus(1);

        service.addTable(dinnerTable);

        String uri = "/dinnerTable?action=listTable";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void deleteTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String tableId = req.getParameter("tableId" );

        //remember check number
        service.deleteTable(Integer.parseInt(tableId));

        String uri = "/dinnerTable?action=listTable";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void searchTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String match = req.getParameter("key" );
        String uri = "/backEnd/detail/boardList.jsp";
        List<DinnerTable> specificTables = service.getSpecificTables(match);
        req.setAttribute("tableList",specificTables);

        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void reserveTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String id = req.getParameter("id" );
        DinnerTable table = service.getTableById(Integer.parseInt(id));
        System.out.println(table);
        String uri;
        if(table != null && table.getTableStatus() == 1) {
            req.setAttribute("table", table);

            uri = "/backEnd/detail/reserveView.jsp";
        }else {
            uri = "/dinnerTable?action=listTable";
        }

        req.getRequestDispatcher(uri).forward(req, resp);
    }

    protected void cancelTable(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        String id = req.getParameter("id");
        service.cancelTable(Integer.parseInt(id));

        String uri = "/dinnerTable?action=listTable";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void doReserve(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        String date = req.getParameter("reserveDate");
        String id = req.getParameter("id");
        System.out.println(date);
        service.reserveTable(Integer.parseInt(id),date);

        String uri = "/dinnerTable?action=listTable";
        req.getRequestDispatcher(uri).forward(req,resp);
    }
}
