package com.web.servlet.login;

import com.pojo.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取复选框数据
        String remember = request.getParameter("remember");
        User user = userService.login(username, password);


        String contextPath = request.getContextPath();
        if (user != null) {
            //登陆成功，跳转到查询所有页面

            //判断用户是否选择记住我
            if("1".equals(remember))
            {
                //用户勾选remember，发送Cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                c_username.setMaxAge(60*60*24*1);
                c_password.setMaxAge(60*60*24*1);

                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            //将登陆成功后的user对象存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if(user.getPrivilege() == 1)
                response.sendRedirect("libarary.html");
            else if(user.getPrivilege() == 0)
                response.sendRedirect("management.html");
        } else {
            //登陆失败
            //存储错误信息到request中
            request.setAttribute("login_Msg", "用户名或者密码错误");

            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
