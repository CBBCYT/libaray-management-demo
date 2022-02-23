package com.web.servlet.login;

import com.pojo.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    private UserService userService= new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");



        //获取用户输入验证码
        String checkCode = request.getParameter("checkCode");
        //获取程序生成验证码
        HttpSession session = request.getSession();
        String checkCodeGen = session.getAttribute("checkCodeGen").toString();

        if(!checkCodeGen.equalsIgnoreCase(checkCode))
        {
            request.setAttribute("register_Msg","验证码错误，注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean flag = userService.register(user);

        if(flag)
        {
            //注册成功
            request.setAttribute("register_Msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else
        {
            //注册失败
            request.setAttribute("register_Msg","用户名已存在，注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
