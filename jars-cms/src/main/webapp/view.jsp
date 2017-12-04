<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${jar.name} jar包下载_Jar包下载网</title>
    <meta name="keywords" content="${jar.name}"/>
    <meta name="description" content="${jar.name}"/>
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

        function download(jarId) {
            window.open("${pageContext.request.contextPath}/jar/download/" + jarId + ".html");
        }
    </script>
</head>
<body>

<jsp:include page="/foreground/common/header.jsp"/>

<div class="container">

    <div class="row" style="padding-top: 30px;">
        <div class="col-md-6" align="center">
            <div class="input-group">
                <input type="text" value="${q}" id="sInput" class="form-control" placeholder="请输入英文jar包名称，多个关键字之间用空格隔开">
                <span class="input-group-addon btn btn-info" id="search">搜索一下</span>
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
        <div class="col-md-8">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/jar_show_icon.png"/>
                    Jar包信息
                </div>
                <div style="padding: 20px;">
                    <table>
                        <tr>
                            <td><strong><big>jar包名称：</big></strong></td>
                            <td>${jar.name}</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td><strong><big>jar包类型：</big></strong></td>
                            <td>
                                <c:choose>
                                    <c:when test="${jar.type == 'jar'}">
                                        二进制Jar包
                                    </c:when>
                                    <c:when test="${jar.type == 'javadoc'}">
                                        JavaDoc文档
                                    </c:when>
                                    <c:when test="${jar.type == 'sources'}">
                                        Java源码
                                    </c:when>
                                    <c:otherwise>
                                        未知
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><strong><big>更新时间：</big></strong></td>
                            <td><fmt:formatDate value="${jar.updateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td><strong><big>整理人：</big></strong></td>
                            <td>ArawN</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><strong><big>访问次数：</big></strong></td>
                            <td>${jar.click}</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td><strong><big>下载次数：</big></strong></td>
                            <td>${jar.downHit}</td>
                        </tr>
                        <tr>
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="8">
                                <button type="button" class="btn btn-danger" onclick="download('${jar.jarId}')">
                                    下载该jar包资源
                                </button>
                                <br/><br/>&nbsp;&nbsp;(建议使用迅雷下载)
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="data_list">
                    <div class="data_list_title">
                        <img src="${pageContext.request.contextPath}/static/images/jar_about_icon.png"/>
                        相关jar包资源
                    </div>
                    <div style="padding: 20px;">
                        <table>
                            <c:choose>
                                <c:when test="${relJarList.size() > 0}">
                                    <c:forEach var="jar" items="${relJarList }" varStatus="status">
                                        <c:if test="${status.index % 2 == 0}">
                                            <tr>
                                        </c:if>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/jar/view/${jar.jarId}.html" title="${jar.name}" target="_blank">${jar.name}</a>
                                        </td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <c:if test="${status.index % 2 == 1 || status.last}">
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    未找到相关资源
                                </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>

            </div>
        </div>

        <div class="col-md-4">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/tag_icon.png"/>
                    jar包标签
                </div>
                <div>
                    <ul id="jars" class="resultJars">
                        <c:forEach var="tag" items="${tagList}">
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