package com.rest.servlet;

import com.rest.domain.Food;
import com.rest.domain.FoodType;
import com.rest.service.FoodService;
import com.rest.service.FoodTypeService;
import com.rest.utils.FileUtile;
import com.rest.utils.WebFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xuero on 2017/7/17.
 */
public class FoodServlet extends HttpServlet {

    FoodService service = WebFactory.getInstance("FoodService",FoodService.class);
    FoodTypeService foodTypeService = WebFactory.getInstance("FoodTypeService",FoodTypeService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");

        if(action.equals("foodList")){
            foodList(req,resp);
        }else if(action.equals("deleteFood")){
            deleteFood(req,resp);
        }else if(action.equals("searchFood")){
            searchFood(req,resp);
        }else if(action.equals("updateShow")){
            updateShow(req,resp);
        }else if(action.equals("updateFood")){
            updateFood(req,resp);
        }else if(action.equals("addShow")){
            addShow(req,resp);
        }else if(action.equals("addFood")){
            addFood(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    protected void foodList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List foods = service.findAllFood();
        req.setAttribute("foods",foods);

        String uri = "/backEnd/detail/foodList.jsp";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void deleteFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        service.deleteFood(Integer.parseInt(id));

        foodList(req,resp);
    }

    protected void searchFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        List foods = service.findAllFoodByName(key);
        req.setAttribute("foods",foods);

        String uri = "/backEnd/detail/foodList.jsp";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void updateShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Map food = service.findFoodById(Integer.parseInt(id));
        req.setAttribute("food",food);

        List types = foodTypeService.getAll();
        req.setAttribute("foodType",types);

        String uri = "/backEnd/detail/updateFood.jsp";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void updateFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setFileSizeMax(1024*1024*20);
        List<FileItem> fileItems;
        Food food = new Food();
        try {
            fileItems = fileUpload.parseRequest(req);
            Iterator<FileItem> iterator = fileItems.iterator();
            FileItem var1;
            while(iterator.hasNext()){
                var1 = iterator.next();
                if(var1.isFormField()){
                    if(var1.getFieldName()=="img"){
                       if(food.getImg() != null && !food.getImg().trim().equals("")){
                           continue;
                       }
                    }
                    processField(var1,food);
                }else {
                    processFile(var1,food);
                }
            }
            System.out.println(food);
            service.updateFood(food);
            foodList(req,resp);
        }catch (FileUploadBase.FileSizeLimitExceededException e){
            System.out.println(e.getMessage());
        }catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    private void processField(FileItem var1,Food food) {
        String value =var1.getString();
        String name=var1.getFieldName();
        Class c = food.getClass();
        objectInject(food,name,value);
    }

    private void processFile(FileItem var1,Food food) {
        String fileName = var1.getName();
        if (fileName != null) {
            fileName = FilenameUtils.getName(fileName);
        }

        if (fileName != null && !"".equals(fileName.trim())) {

            if(FileUtile.isPicture(fileName)) {
                try {
                    InputStream inputStream = var1.getInputStream();

                    String realPath = this.getServletContext().getRealPath("/backEnd/img");
                    //String realPath = "C:\\Users\\xuero\\Desktop\\serverImage";
                    File dircetory = new File(realPath);
                    if (!dircetory.exists()) {
                        dircetory.mkdir();
                    }

                    fileName = UUID.randomUUID() + "_" + fileName;
                    File file = new File(dircetory, fileName);

                    FileOutputStream fos = new FileOutputStream(file);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = inputStream.read(b)) != -1) {
                        fos.write(b, 0, len);
                    }
                    fos.close();
                    inputStream.close();

                    objectInject(food, "img", "backEnd/img/"+fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                throw new RuntimeException("Not a Picture File");
            }
        }



    }

    private void objectInject(Object obj,String method,String value){
        String methodName = "set"+ method.substring(0,1).toUpperCase() + method.substring(1);
        Class c = obj.getClass();

        Method[] methods = c.getMethods();
        for (Method m : methods){
            try {
                if(m.getName().equals(methodName)){

                    Class<?> type = m.getParameterTypes()[0];
                    if(type == int.class){
                        int var2 = Integer.parseInt(value);
                        m.invoke(obj,var2);
                    }else if(type == double.class){
                        double var3 = Double.parseDouble(value);
                        m.invoke(obj,var3);
                    }else {
                        m.invoke(obj,value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private String makeChildDirectory(File storyDirectory){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        File file = new File(storyDirectory,date);
        if(!file.exists()){
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    protected void addShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List types = foodTypeService.getAll();
        req.setAttribute("foodType",types);

        String uri = "/backEnd/detail/saveFood.jsp";
        req.getRequestDispatcher(uri).forward(req,resp);
    }

    protected void addFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setFileSizeMax(1024*1024*20);
        List<FileItem> fileItems;
        Food food = new Food();
        try {
            fileItems = fileUpload.parseRequest(req);
            Iterator<FileItem> iterator = fileItems.iterator();
            FileItem var1;
            while(iterator.hasNext()){
                var1 = iterator.next();
                if(var1.isFormField()){
                    processField(var1,food);
                }else {
                    processFile(var1,food);
                }
            }
            System.out.println(food);
            service.addFood(food);
            String url = "/food?action=foodList";
            req.getRequestDispatcher(url).forward(req,resp);
        }catch (FileUploadBase.FileSizeLimitExceededException e){
            System.out.println(e.getMessage());
        }catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
