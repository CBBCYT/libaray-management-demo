<%--
  Created by IntelliJ IDEA.
  User: 32692
  Date: 2022/1/27
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <span id="register_err" class="err_msg" >${register_Msg}</span>
                    <br>
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/libaray-management-demo/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    <!--刷新验证图片-->
    document.getElementById("changeImg").onclick = function (){
        document.getElementById("checkCodeImg").src = "/libaray-management-demo/checkCodeServlet?" + new Date().getMilliseconds();
    }

    <!--验证用户名是否存在-->
    document.getElementById("username").onblur = function (){
        //获取发送数据
        var username = this.value;

        //创建核心对象
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        //发送请求
        xhttp.open("GET", "http://localhost:8080/libaray-management-demo/usernameServlet?username=" + username);
        xhttp.send();

        //获取响应
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText == "false")
                {
                    document.getElementById("username_err").style.display = '';
                }
                else
                {
                    document.getElementById("username_err").style.display = 'none';
                }
            }
        };
    }

</script>

</body>
</html>
