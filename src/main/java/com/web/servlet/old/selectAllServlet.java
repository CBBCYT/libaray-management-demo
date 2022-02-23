package com.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.pojo.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "selectAllServlet", value = "/selectAllServlet")
public class selectAllServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Book> books = bookService.selectAll();

        //转为json
        String jsonString = JSON.toJSONString(books);


        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
