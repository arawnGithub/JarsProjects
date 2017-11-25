<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript">
        $(function () {
            // 给span注册点击事件
            $("#search").click(function () {
                search();
            });

            // 给搜索文本框注册enter回车事件
            $("#sInput").keydown(function (e) {
                if (e.keyCode == 13) {
                    search();
                }
            });

            function search() {
                var q = $("#sInput").val();
                if (q == null || q == "") {
                    $("#sInput").focus();
                    return;
                }
                window.location.href = "${pageContext.request.contextPath}/jar/query.do?q=" + q;
            }
        });
    </script>
</head>
<body>

<jsp:include page="/foreground/common/header.jsp"/>

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
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <ul id="jars" class="indexJars">
                <font color="red">&nbsp;&nbsp;猜你喜欢：</font>
                <hr style="margin-top: 2px; margin-bottom: 10px;">
                <c:forEach var="tag" items="${tagList}">
                    <li><a href="${pageContext.request.contextPath}/jar/query.do?q=${tag.name}" target="_blank"
                           title="${tag.name}.jar下载">${tag.name}.jar</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-2"></div>
    </div>

    <jsp:include page="/foreground/common/footer.jsp"/>
</div>
</body>
</html>