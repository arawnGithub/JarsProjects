<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Jar包下载网后台主页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function() {
            $('#dlg').dialog({
                onClose:function() {
                    resetValue();
                }
            });
        });

        function openTab(text, url, iconCls) {
            if ($("#tabs").tabs("exists", text)) {
                $("#tabs").tabs("select", text);
            } else {
                var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/" + url + "'></iframe>";
                $("#tabs").tabs("add",{
                    title:text,
                    iconCls:iconCls,
                    closable:true,
                    content:content
                });
            }
        }

        function openPasswordModifyDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "修改密码");
        }

        function modifyPassword() {
            $("#fm").form("submit",{
                url:"${pageContext.request.contextPath}/admin/manager/modifyPassword.do",
                onSubmit:function() {
                    var password = $("#password").val();
                    var rePassword = $("#rePassword").val();
                    if (!$(this).form("validate")) {
                        return false;
                    }
                    if (password != rePassword) {
                        $.messager.alert("系统提示","新密码与确认新密码不一致");
                        return false;
                    }
                    return true;

                },
                success:function(result) {
                    result = eval('(' + result + ')');
                    if (result.success) {
                        $.messager.alert("系统提示","密码修改成功，下一次登录生效");
                        closePasswordModifyDialog();
                    } else {
                        $.messager.alert("系统提示","密码修改失败");
                    }
                }
            });
        }

        function closePasswordModifyDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function resetValue() {
            $("#newPassword").val("");
            $("#rePassword").val("");
        }

        function logout() {
            $.messager.confirm("系统提示","您确定要退出系统吗",function(r) {
                if(r) {
                    window.location.href="${pageContext.request.contextPath}/admin/manager/logout.do";
                }
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
    <table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                <img alt="logo" src="${pageContext.request.contextPath}/static/images/logo.png">
            </td>
            <td valign="bottom" align="right" width="50%">
                <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${currentUser.username}</font>
            </td>
        </tr>
    </table>
</div>

<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div style="padding-top: 10px;padding-left: 10px;">
        <a href="javascript:openTab('Jar包信息管理', 'jar_manage.jsp', 'icon-jgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-jgl'" style="width: 150px;">Jar包信息管理</a>
        <a href="javascript:openTab('Tag标签信息管理', 'tag_manage.jsp', 'icon-jgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-jgl'" style="width: 150px;">Tag标签信息管理</a>
        <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
        <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
    </div>
</div>

<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
        </div>
    </div>
</div>

<div region="south"  style="height: 25px;padding: 5px" align="center">
    Copyright © Jar包下载网
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 200px;padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" id="username" name="username" value="${currentUser.username}" readonly="readonly" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input type="password" id="password" name="password" class="easyui-validatebox" required="true" style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td>
                    <input type="password" id="rePassword" name="rePassword" class="easyui-validatebox" required="true" style="width: 200px;"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>