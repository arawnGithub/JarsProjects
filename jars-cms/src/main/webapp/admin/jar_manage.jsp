<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Jar包管理页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        function searchJar() {
            $("#dg").datagrid("load", {
                "sName":$("#s_name").val()
            });
        }
    </script>
</head>
<body style="margin: 1px">
<table id="dg" title="Jar包管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/jar/list.do"
       fit="true" toolbar="#tb">
    <thead>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="jarId" width="100">jar包ID</th>
        <th field="name" width="100">jar包名称</th>
        <th field="type" width="50" align="center">jar包类型</th>
        <th field="createTime" width="50" align="center">创建时间</th>
        <th field="updateTime" width="50" align="center">更新时间</th>
    </thead>
</table>

<div id="tb">
    <div>

    </div>

    <div>
        &nbsp;jar包名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode == 13) searchJar()"/>
        <a href="javascript:searchJar()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
</body>
</html>
