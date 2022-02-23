package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Book;
import com.pojo.PageBean;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/book/*")
public class bookServlet extends baseServlet{

    BookService bookService = new BookServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //调用service查询
        List<Book> books = bookService.selectAll();

        //转为json
        String jsonString = JSON.toJSONString(books);


        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为json对象
        Book book = JSON.parseObject(params, Book.class);

        //调用service添加
        bookService.add(book);

        //相应成功标识
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();



        //转换为json对象
        Book book = JSON.parseObject(params, Book.class);

        System.out.println(book);
        //调用service添加
        bookService.update(book);

        //相应成功标识
        response.getWriter().write("success");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为json对象
        Integer id = Integer.parseInt(params);

        //调用service删除
        bookService.delete(id);

        //相应成功标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为int[]数组
        int[] ids= JSON.parseObject(params, int[].class);

        //调用service删除
        bookService.deleteByIds(ids);

        //相应成功标识
        response.getWriter().write("success");
    }

    //分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接收参数：当前页码、每页展示条数
        String _current = request.getParameter("current");
        String _size = request.getParameter("size");
        int current = Integer.parseInt(_current);
        int size = Integer.parseInt(_size);

        //调用service查询
        PageBean<Book> pageBean = bookService.selectByPage(current, size);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);


        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    //分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接收参数：当前页码、每页展示条数
        String _current = request.getParameter("current");
        String _size = request.getParameter("size");
        int current = Integer.parseInt(_current);
        int size = Integer.parseInt(_size);

        //获取查询条件
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为Book对象
        Book book = JSON.parseObject(params, Book.class);


        //调用service查询
        PageBean<Book> pageBean = bookService.selectByPageAndCondition(current, size, book);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);


        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
