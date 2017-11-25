<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${q}_查询结果页面_Jar包下载网</title>
    <meta name="keywords" content="${q}-查询结果页面" />
    <meta name="description" content="${q}-查询结果页面" />
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

<jsp:include page="/foreground/common/header.jsp"/>

<div class="container">

    <div class="row" style="padding-top: 30px;">
        <div class="col-md-6" align="center">
            <div class="input-group">
                <input type="text" value="${q}" id="sInput" class="form-control" placeholder="请输入英文jar包名称，多个关键字之间用空格隔开"><span class="input-group-addon btn btn-info" id="search">搜索一下</span>
            </div>
        </div>

        <div class="col-md-6">
            <div style="padding-top: 10px;">
                <a href="${pageContext.request.contextPath}/jar/query.do?q=spring" title="spring.jar下载">spring</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=hibernate" title="hibernate.jar下载">hibernate</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=mybatis" title="mybatis.jar下载">mybatis</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=log4j" title="log4j.jar下载">log4j</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=struts" title="struts.jar下载">struts</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=hadoop" title="hadoop.jar下载">hadoop</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=spark" title="spark.jar下载">spark</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=solr" title="solr.jar下载">solr</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=poi" title="poi.jar下载">poi</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jar/query.do?q=itext" title="itext.jar下载">itext</a>&nbsp;&nbsp;
            </div>
        </div>
    </div>

    <div class="row" style="padding-top: 20px;">
        <div class="col-md-8"></div>
        <div class="col-md-4">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/tag_icon.png"/>
                    jar包标签
                </div>
                <div>
                    <ul id="jars" class="resultJars">
                        <c:forEach var="tag" items="${tagList }">
                            <li><a href='${pageContext.request.contextPath}/jar/query.do?q=${tag.name}' target='_blank' title='${tag.name}.jar下载'>${tag.name}.jar</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/foreground/common/footer.jsp"/>
</div>
</body>
</html>