<%@ page import="top.tosim.eqmanager.entity.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>登陆</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="/static/css/global.css">

  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<%--${applicationScope.userList}--%>
  <div class="layui-container">
    <div class="layui-row">
      <div class="layui-col-md3">
        <div>&nbsp;</div>
      </div>
      <div class="layui-col-md6">
        <div style="padding: 25px 0 35px 0" class="center">简易设备管理系统</div>
      </div>
      <div class="layui-col-md3">
        <div>&nbsp;</div>
      </div>
    </div>
    <div class="layui-row">
      <div class="layui-col-md3">
        <div>&nbsp;</div>
      </div>
      <div class=" layui-col-md6">
        <form class="layui-form" action="/sessions" method="POST">
          <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
              <input type="text" name="userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
              <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
          </div>

          <div class="layui-form-item">
            <div class="layui-input-block">
              <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">登陆</button>
              <a href="/users/new" class="layui-btn layui-btn-primary">注册</a>
            </div>
          </div>
        </form>
      </div>
      <div class="layui-col-md3">
        <div>&nbsp;</div>
      </div>
    </div>
  </div>

  <script src="/static/layui/layui.js"/>
  <script>
      //Demo
      layui.use('form', function(){
          var form = layui.form;

          //监听提交
          form.on('submit(login)', function(data){
              layer.msg(JSON.stringify(data.field));
              return false;
          });
      });
  </script>
</body>
</html>