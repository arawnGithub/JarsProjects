<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页_Jar包下载网</title>
    <meta name="keywords" content="Jar包下载,JavaDoc下载,JavaSource下载,Java文档下载,Java源码下载"/>
    <meta name="description" content="Jar包下载网是一家Jar包资源整合下载网站"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jar.css">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
    </style>
</head>
<body>

<div class="header_top">
    <div class="w960">
        <span class="time">Jar包下载网</span>
        <div class="toplinks">
            [&nbsp;<a href="http://sighttp.qq.com/authd?IDKEY=b97fe785cac444840e41ff0dfc1c6c67fa2eb229a524f0ab" target="_blank">联系站长</a>&nbsp;]
        </div>
    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-12" align="center" style="padding-top: 50px;">
            <a href="#" target="_blank">
                <img alt="jar包下载网" src="${pageContext.request.contextPath}/static/images/logo.png">
            </a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6" align="center" style="padding-top: 20px;">
            <div class="input-group">
                <input type="text" class="form-control input-lg" id="sInput" placeholder="请输入英文jar包名称，多个关键字之间用空格隔开">
                <span class="input-group-addon btn btn-info" id="search">搜索一下</span>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <div align="center" style="padding-top: 120px">
                Copyright © Jar包下载网
            </div>
        </div>
    </div>
</div>
</body>
</html>