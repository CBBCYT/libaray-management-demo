package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.*;
import com.service.BookService;
import com.service.BorrowService;
import com.service.impl.BookServiceImpl;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/borrow/*")
public class borrowServlet extends baseServlet {

    BorrowService borrowService = new BorrowService();
    BookService bookService = new BookServiceImpl();

    @Test
    public void test()
    {

        //调用service
        borrowService.deleteById(5);
        /*Book book = bookService.selectById(bookId);
        if(book.getRemainNumber()<book.getTotalNumber())
        {
            book.setRemainNumber(book.getRemainNumber()+1);
        }
        bookService.update(book);*/
    }

    private String getTime(String borrowTime)
    {
        String month = borrowTime.substring(5,7);
        String year = borrowTime.substring(0,4);
        int newMonth = Integer.parseInt(month)+1;
        int newYear = Integer.parseInt(year);
        newMonth = (newMonth)%12;
        if(newMonth==1)
        {
            newYear += 1;
        }
        month = Integer.toString(newMonth);
        year = Integer.toString(newYear);
        String returnTime = year + "-" + month + "-" + borrowTime.substring(7);

        return returnTime;
    }

    public void borrow(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为json对象
        Book book = JSON.parseObject(params, Book.class);

        //System.out.println(book);

        if (book.getRemainNumber() < 1) {
            //余量不足，借书失败
            response.getWriter().write("余量不足，借书失败");

        } else {
            //借书成功
            //获取用户数据
            HttpSession session = request.getSession();

            User user = (User)session.getAttribute("user");

            int userId = user.getId();

            //获取时间
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String borrowTime = formatter.format(date);
            String returnTime = getTime(borrowTime);

            //设置
            Borrow borrow = new Borrow();
            borrow.setUserId(userId);
            borrow.setBookId(book.getId());
            borrow.setBorrowTime(borrowTime);
            borrow.setReturnTime(returnTime);

            //调用service借阅
            borrowService.borrow(borrow);

            book.setRemainNumber(book.getRemainNumber()-1);
            bookService.update(book);

            //相应成功标识
            response.getWriter().write("success");
        }
    }

    public void returnBook(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //接收数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转换为json对象
        Integer id = Integer.parseInt(params);

        Borrow borrow = borrowService.selectById(id);

        //获取数据
        int bookId = borrow.getBookId();

        //调用service
        borrowService.deleteById(id);
        Book book = bookService.selectById(bookId);
        if(book.getRemainNumber()<book.getTotalNumber())
        {
            book.setRemainNumber(book.getRemainNumber()+1);
        }
        bookService.update(book);

        //相应成功标识
        response.getWriter().write("success");

    }

}
