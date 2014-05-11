<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transition.dtd">

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登陆成功</title>
	<link href="${base}/resources/js/jquerymap/jquery-jvectormap-1.2.2.css" type="text/css" rel="stylesheet" media="screen"/>
	<script src="${base}/resources/js/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${base}/resources/js/jquerymap/jquery-jvectormap-1.2.2.min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/jquerymap/jquery-map-ch-en.js"></script>
	
	
	<script type="text/javascript">
		
		var $map;
	
		var initMapSize = function(){
			 $map = $("#ch-map");
			$map.width($(document).width());
			$map.height($(document).height());
		};
		
		
		$(document).ready(function(){
			
			initMapSize();
			$map = $("#ch-map").vectorMap({
				map:'cn_mill_en',
				color: "#ff00ff", //"#B4B4B4" //地图颜色 ,
				onRegionOver: function(event, code){
					$map.css({cursor:'pointer'});
				},
				//鼠标移出省份区域，改回鼠标状态
			　　　　onRegionOut: function(event, code){
			　　　　　　$map.css({cursor:'auto'});
			　　　　},
			    onRegionClick: function(event, code){
			    	alert(code);
			    },
			  //显示各地区名称和活动
			　　onRegionLabelShow: function (event, label, code) {
				//label.html("<p style='color:red;background-color:green;'>"+code+"</p>");
			　　},
			  series: {
			      regions: [{
			        attribute: 'fill'
			      }]
			  }
			}); 
	
			initColor();
			
		});
		
		var initColor = function(){
			var colors = {"CN-54":"#ff00ff"};
			
			$map.setValues(colors);
		};
	
	</script>
	
	<style type="text/css">
		 /* #ch-map {
			width:400px;
			height:400px;
		} */
	</style>
	
	</head>
	
	<body id="home">
		<div>
			<div id="ch-map"></div>
		</div>
		
		
	</body>
</html>