package com.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.pojo.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet(name = "addServlet", value = "/addServlet")
public class addServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为json对象
        Book book = JSON.parseObject(params, Book.class);

        //调用service添加
        bookService.add(book);

        //相应成功标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
