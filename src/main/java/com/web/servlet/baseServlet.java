package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//替换httpserlet，进行方法分发
public class baseServlet extends HttpServlet {

    //根据请求路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String requestURI = req.getRequestURI();

        //获取最后一段路径，方法名
        int index = requestURI.lastIndexOf('/');
        String methodName = requestURI.substring(index+1);

        //获取字节码对象（谁调用方法）
        Class<? extends baseServlet> cls = this.getClass();

        try {
            //获取调用的方法
            Method method = cls.getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
