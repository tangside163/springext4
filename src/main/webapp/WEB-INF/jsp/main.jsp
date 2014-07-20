<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎来到tangsi的娱乐桌面平台</title>
    <link rel="stylesheet" href="${base}/resources/js/ext4/css/ext-all.css"/>
    <link rel="stylesheet" href="${base}/resources/js/desktop/css/desktop.css"/>
    <script src="${base}/resources/js/ext4/ext-all-rtl.js"></script>
    <script type="text/javascript">



       var base = '${base}';
       Ext.Loader.setConfig({enabled:true});
       Ext.Loader.setPath({
            'Ext.ux.desktop': '${base}/resources/js/desktop/js',
            'MyDesktop': '${base}/resources/js/desktop'
        });
        Ext.require('MyDesktop.App');

        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
        });

    </script>
    </head>
	
	<body id="home">

	</body>
</html>