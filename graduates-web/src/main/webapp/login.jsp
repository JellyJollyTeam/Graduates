<%-- 
    Document   : login
    Created on : 2012-10-24, 12:59:51
    Author     : fantasy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Graduates 用户登录</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet" /> 
        <link href="/css/login.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container" id="login">
            <form class="form-horizontal" action="post" method="post" >
                <div class="control-group">
                <label class="control-label" for="inputEmail">用户名</label>
                    <div class="controls">
                    <input type="text" id="inputEmail" placeholder="Username"/>
                    </div>
                </div>
                <div class="control-group">
                  <label class="control-label" for="inputPassword">密码</label>
                  <div class="controls">
                    <input type="password" id="inputPassword" placeholder="Password"/>
                  </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                      <label class="checkbox">
                        <input type="checkbox"/> 记住我
                      </label>
                      <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
