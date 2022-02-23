package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Borrow;
import com.pojo.BorrowInfo;
import com.pojo.User;
import com.service.BookService;
import com.service.BorrowService;
import com.service.UserService;
import com.service.impl.BookServiceImpl;
import org.junit.Test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/*")
public class userServlet extends baseServlet{

    BorrowService borrowService = new BorrowService();
    BookService bookService = new BookServiceImpl();
    UserService userService = new UserService();

    @Test
    public void test()
    {
        int userId = 5;
        String userName = "venti";

        List<Borrow> borrows = borrowService.selectByIds(userId);
        List<BorrowInfo> binfo = new ArrayList<BorrowInfo>();

        for(Borrow b :borrows)
        {
            int bookId = b.getBookId();
            String bookName = bookService.selectById(bookId).getBookName();
            binfo.add(new BorrowInfo(userName,bookName,b));
        }

        //转为json
        String jsonString = JSON.toJSONString(binfo);

        System.out.println(jsonString);
    }


    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        int userId = user.getId();
        String userName = user.getUsername();

        List<Borrow> borrows = borrowService.selectByIds(userId);
        List<BorrowInfo> binfo = new ArrayList<BorrowInfo>();


        for(Borrow b :borrows)
        {
            int bookId = b.getBookId();
            String bookName = bookService.selectById(bookId).getBookName();
            binfo.add(new BorrowInfo(userName,bookName,b));
        }

        //转为json
        String jsonString = JSON.toJSONString(binfo);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //接收数据
        BufferedReader reader = request.getReader();
        String username = reader.readLine();

        //调用service
        User user = userService.selectByUsername(username);

        //转为json
        String jsonString = JSON.toJSONString(user);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updatePass(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);

        //调用service
        User user = userService.selectByUsername(username);
        user.setPassword(password);
        userService.updatePass(user);

        System.out.println(user);

        response.getWriter().write("success");
    }
}
