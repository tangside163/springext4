<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="${base}/resources/js/bootstrap3/css/bootstrap.css">

<script src="${base}/resources/js/jquery/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${base}/resources/js/bootstrap3/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery/jquery.cookie.js"></script>	
<style type="text/css">
	
	body {
  padding-top: 100px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
	
</style>

	<script type="text/javascript">
		
		//自动登录
		function autoLogin() {
			var cookie = $.cookie("tangsi");
			if(cookie != null) {
				$("#username").val(cookie.split("_")[0]);
				$("#password").val(cookie.split("_")[1]);
				$("button[type=submit]").click();
			}
		}
	
		$(document).ready(function(){
			
			setTimeout(autoLogin,3000);//自动登录
			
			$("button[type=submit]").click(function(){
				
				if($("#remember-me").prop("checked") == true) {
					var username = $("#username").val();
					var password = $("#password").val();
					var regexp1 = /\S+/g;//非空白字符匹配
					var regexp2 = /\S+/g;//非空白字符匹配
					if( regexp1.test(username) &&  regexp2.test(password)) {
						//添加cookie
						//var cookietime = new Date()+3600*24*5;  //五天
						$.cookie("tangsi", username+"_"+password,{path:"/",expires:5}); //五天有效期
					}
				}else {
					$.cookie("tangsi",null); //删除cookie
				}
				return true;
			});
			
		});
	
	</script>

</head>
<body >
	<div class="container">

      <form class="form-signin" role="form" method="post" action="${base}/user/login">
        <input id="username" type="text" name="username" class="form-control" placeholder="Username" required autofocus>
        <input id="password" type="password" name="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input  id="remember-me" type="checkbox" value="yes" name="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
      </form>

    </div>
</body>
</html>