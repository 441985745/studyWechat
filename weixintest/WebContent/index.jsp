<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>前端定位模块</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <style>
        
    </style>
<!-- <script type='text/javascript' src="./jquery-1.11.0.min.js"></script>  -->
<script type="text/javascript"
		src="<%=basePath%>static/jQuery WeUI_files/jquery-2.1.4.js"></script>
<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js "></script>
</head>
<body>
	   <input type="text" id="addr" width="200px" height="100px"/>
	   <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbc263ebd77c5ded9&redirect_uri=http://javaweb.55555.io/weixintest/TestServlet&response_type=code&scope=snsapi_userinfo&state=">点击此处</a>
<script type="text/JavaScript">
    var geolocation = new qq.maps.Geolocation("KLXBZ-YNGW3-3C63Y-YDBOU-TNIOK-63BK5", "myapp"); 
    var positionNum = 0;
    var options = {timeout: 8000};
    
    function showPosition(position) {
        var adCode = position.adCode;//邮政编码
        var nation = position.nation;//中国
        var city = position.city; //城市
        var addr = position.addr; //详细地址
        var lat = position.lat; //
        var lng = position.lng; //火星坐标 //TODO 实现业务代码逻辑 
        console.log(addr);
        $("#addr").val(addr);
     }; 
     
    function showErr() { 
        //TODO 如果出错了调用此方法 
	console.log("error");
    }; 

$(function(){ //定位
       geolocation.getLocation(showPosition, showErr, options);
}) 
/* var geolocation = new qq.maps.Geolocation('KLXBZ-YNGW3-3C63Y-YDBOU-TNIOK-63BK5','myapp');


document.getElementById("pos-area").style.height = (document.body.clientHeight - 110) + 'px';

var options = {timeout: 9000};
var positionNum = 0;

function getCurLocation() {
    geolocation.getLocation(showPosition, showErr, options);
}
function showPosition(position) {
	console.log(position);
	//$("#addr").val(position.nation+position.city+position.addr);
    positionNum ++;
    document.getElementById("demo").innerHTML += "序号：" + positionNum;
    document.getElementById("demo").appendChild(document.createElement('pre')).innerHTML = JSON.stringify(position, null, 4);
    document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
};

function showErr() {
    positionNum ++;
    document.getElementById("demo").innerHTML += "序号：" + positionNum;
    document.getElementById("demo").appendChild(document.createElement('p')).innerHTML = "定位失败！";
    document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
};

function showWatchPosition() {
    document.getElementById("demo").innerHTML += "开始监听位置！<br /><br />";
    geolocation.watchPosition(showPosition);
    document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
};

function showClearWatch() {
    geolocation.clearWatch();
    document.getElementById("demo").innerHTML += "停止监听位置！<br /><br />";
    document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
}; */
</script>
 
</body>
</html>