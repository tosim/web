<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <title>用户界面</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="/static/css/global.css">

  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-container">
  <div style="padding: 25px 0 35px 0" class="layui-row">
    <div class="layui-col-md2">
      <div>&nbsp;</div>
    </div>
    <div class="layui-col-md8 center">
      <div>简易设备管理系统</div>
    </div>
    <div class="layui-col-md1">
      <div>${sessionScope.user.loginName} ${sessionScope.user.type != 0 ? "" : "[admin]"}</div>
    </div>
    <div class="layui-col-md1">
      <div>
        <form action="/sessions/0" method="post">
          <input type="hidden" name="_method" value="DELETE"/>
          <button  class="layui-btn  layui-btn-xs layui-btn-primary" lay-submit lay-filter="logout">注销</button>
        </form>
      </div>
    </div>
  </div>
  <c:if test="${sessionScope.user.type == 0}">
  <div class="layui-row">
    <div class="layui-col-md2">
      <div>&nbsp;</div>
    </div>
    <div style="border:1px solid #eee;padding-top: 15px;" class="layui-col-md8">
      <form class="layui-form" action="/equipments" method="post">
        <div class="layui-form-item">
          <label class="layui-form-label">名称</label>
          <div class="layui-input-inline">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">编码</label>
          <div class="layui-input-inline">
            <input type="text" name="code" required  lay-verify="required" placeholder="请输入编码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">价格</label>
          <div class="layui-input-inline">
            <input type="text" name="price" required  lay-verify="required" placeholder="请输入用价格" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">描述</label>
          <div class="layui-input-block">
            <textarea name="description" placeholder="请输入内容" class="layui-textarea"></textarea>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">领用人</label>
          <div class="layui-input-block">
            <select name="userId" lay-verify="required">
              <c:forEach var="user" items="${applicationScope.userList}">
                <option value="${user.id}">${user.loginName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">存放地点</label>
          <div class="layui-input-inline">
            <input type="text" name="place" required  lay-verify="required" placeholder="请输入存放地点" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button  class="layui-btn layui-btn-primary" lay-submit lay-filter="addEquipment">添加设备</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
          </div>
        </div>
      </form>
    </div>
    <div class="layui-col-md2">
      <div>&nbsp;</div>
    </div>
  </div>
  </c:if>
  <div class="layui-row">
    <div class="layui-col-md2"><div>&nbsp;</div></div>
    <div class="layui-col-md8">
      <table class="layui-table">
        <thead>
        <tr>
          <th>序号</th>
          <th>资产编码</th>
          <th>名称</th>
          <th>价格</th>
          <th>存放地点</th>
          <th>领用人</th>
          <th>领用时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="equipment" items="${sessionScope.equipmentList}" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${equipment.code}</td>
            <td>${equipment.name}</td>
            <td>${equipment.price}</td>
            <td>${equipment.place}</td>
            <td>${equipment.user.realName}</td>
            <td>${equipment.addTime}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="layui-col-md2"><div>&nbsp;</div></div>
  </div>
</div>

<script src="/static/layui/layui.all.js"/>
<script>
    layui.use('form', function(){
        var form = layui.form;
        var element = layui.element;
    });
</script>
</body>
</html>