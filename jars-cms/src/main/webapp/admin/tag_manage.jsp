<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tag标签管理页面</title>
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

        function searchTag(){
            $("#dg").datagrid("load",{
                "sName":$("#s_name").val()
            });
        }

        function deleteTag() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length == 0){
                $.messager.alert("系统提示","请选择要删除的数据");
                return;
            }
            var idArr = [];
            for(var i = 0; i < selectedRows.length; i++){
                idArr.push(selectedRows[i].id);
            }
            var ids = idArr.join(",");
            $.messager.confirm("系统提示","您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗",function(r) {
                if(r) {
                    $.post("${pageContext.request.contextPath}/admin/tag/delete.do", {ids:ids}, function(result) {
                        if (result.success) {
                            $.messager.alert("系统提示","数据删除成功");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示","数据删除失败");
                        }
                    }, "json");
                }
            });
        }

        function openTagAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle", "添加Tag标签信息");
            url = "${pageContext.request.contextPath}/admin/tag/save.do"
        }

        function openTagModifyDialog() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length != 1) {
                $.messager.alert("系统提示","请选择一条要修改的数据");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "修改Tag标签信息");
            $("#fm").form("load",row);
            url = "${pageContext.request.contextPath}/admin/tag/save.do?id=" + row.id;
        }

        function saveTag() {
            $("#fm").form("submit",{
                url:url,
                onSubmit:function() {
                    return $(this).form("validate");
                },
                success:function(result) {
                    var result=eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示","保存成功");
                        closeTagDialog();
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示","保存失败");
                    }
                }
            });
        }

        function closeTagDialog(){
            resetValue();
            $("#dlg").dialog("close");
        }

        function resetValue(){
            $("#name").val("");
        }
    </script>
</head>
<body style="margin: 1px">
<table id="dg" title="Tag标签管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/tag/list.do"
       fit="true" toolbar="#tb">
    <thead>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">主键ID</th>
        <th field="name" width="200" align="center">标签名称</th>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openTagAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openTagModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteTag()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;标签名称：&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode == 13) searchTag()"/>
        <a href="javascript:searchTag()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 500px;height: 150px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>标签名称：</td>
                <td>
                    <input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveTag()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeTagDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>