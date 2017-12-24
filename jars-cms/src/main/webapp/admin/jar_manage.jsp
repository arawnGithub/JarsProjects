<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        var url;

        $(function() {
            $('#dlg').dialog({
                onClose:function() {
                    resetValue();
                }
            });
        });

        function searchJar() {
            $("#dg").datagrid("load", {
                "sName":$("#s_name").val()
            });
        }

        function deleteJar() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据");
                return;
            }

            var jarIdArr = [];
            for (var i = 0; i < selectedRows.length; i++) {
                jarIdArr.push(selectedRows[i].jarId);
            }

            var jarIds = jarIdArr.join(",");
            $.messager.confirm("系统提示", "您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗", function(r) {
                if (r) {
                    $.post("${pageContext.request.contextPath}/admin/jar/delete.do", {jarIds:jarIds}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "数据删除成功");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "数据删除失败");
                        }

                    }, "json");
                }
            });
        }

        function saveJar() {
            $("#fm").form("submit", {
                url:url,
                onSubmit:function() {
                    return $(this).form("validate");
                },
                success:function(result) {
                    result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功");
                        closeJarDialog();
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败");
                    }
                }
            });
        }

        function openJarAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加Jar包信息");
            url = "${pageContext.request.contextPath}/admin/jar/save.do";
        }

        function openJarModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要修改的数据");
                return;
            }

            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "修改Jar包信息");
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/admin/jar/save.do?jarId=" + row.jarId;
        }

        function closeJarDialog() {
            resetValue();
            $("#dlg").dialog("close");
        }

        function resetValue() {
            $("#name").val("");
            $("#path").val("");
            $("#type").val("");
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
        <th field="jarId" width="100">Jar包ID</th>
        <th field="name" width="100">Jar包名称</th>
        <th field="type" width="50" align="center">Jar包类型</th>
        <th field="createTime" width="50" align="center">创建时间</th>
        <th field="updateTime" width="50" align="center">更新时间</th>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openJarAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openJarModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteJar()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;Jar包名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode == 13) searchJar()"/>
        <a href="javascript:searchJar()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 600px;height: 200px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>Jar包名称：</td>
                <td>
                    <input type="text" id="name" name="name" class="easyui-validatebox" required="true" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>Jar包资源路径：</td>
                <td>
                    <input type="text" id="path" name="path" class="easyui-validatebox" validtype="url" required="true" style="width: 350px"/>
                </td>
            </tr>
            <tr>
                <td>Jar包类型：</td>
                <td>
                    <select id="type" name="type" class="easyui-validatebox" required="true" style="width: 200px">
                        <option value="">请选择...</option>
                        <option value="jar">jar</option>
                        <option value="javadoc">javadoc</option>
                        <option value="sources">sources</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveJar()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeJarDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>