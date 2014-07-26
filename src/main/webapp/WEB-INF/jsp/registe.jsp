<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
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
  padding-top: 150px;
  padding-bottom: 40px;
  background: #eee url(../resources/image/registe_background.jpg) no-repeat;
}

.form-registe {
  max-width: 650px;
  padding: 15px;
  margin: 0 auto;
}
.form-registe .form-signin-heading,
.form-registe .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-registe .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  margin-left:100px;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.form-registe .form-control:focus {
  z-index: 2;
}
.form-registe input[type="text"] {
  display:inline; 
  width:300px;	
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-registe input[type="password"] {
  display:inline;	
  width:300px;
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.form-registe button,a  {
   margin-left:100px;
  display:block;
  width:300px;
}

.form-registe span {
	color:red;
}

	
</style>

	<script type="text/javascript">
	</script>

</head>
<body >
	<div class="container">

      <form class="form-registe" role="form" method="post" action="${base}/user/registe">
        <input id="username" type="text" name="username" class="form-control" placeholder="username" required autofocus>
        <input id="password" type="password" name="password" class="form-control" placeholder="password" required>
        <input id="repeat_password" type="password" name="repeat_password" class="form-control" placeholder="confirm password" required>
        <input id="email" type="text" name="email" class="form-control" placeholder="email" required>
        <input id="phone" type="text" name="phone" class="form-control" placeholder="phone" required>
        <input id="nickname" type="text" name="nickname" class="form-control" placeholder="nickname" >
        <br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">confirm</button>
      </form>

    </div>
</body>
</html>