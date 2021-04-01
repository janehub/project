<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>뉴스</title>
<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- BootStrap CSS 파일 -->
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<!-- mainPage_new CSS -->
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
</head>


<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<!-- BootStrap JavaScript 파일-->
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>
<script type="text/javascript">
function gotoNews() {
	location.href="https://www.poultry.or.kr/bbs/board.php?bo_table=news";
}

</script>
<body>
   <div id="container">
   <%@include file="/WEB-INF/jsp/template_v3.jsp" %>

<%
   String url = "https://www.poultry.or.kr/bbs/board.php?bo_table=news";
   Document doc = null;
   
   try {
      doc = Jsoup.connect(url).get();
   } catch (Exception e) {
      e.printStackTrace();
   }

   Elements element = doc.select(".boardList");
   
   String title = element.select(".title").text();
   
   StringBuilder sb = new StringBuilder();
   StringBuilder asb = new StringBuilder();
   String uri = "";   
   String urlresult = "";
   String uriarray[];
   uriarray = new String[15];
   
   
   for(Element el : element.select(".subject.tal")) {
	   
	  sb.append(el.text()+"\n");
      
   }
   
   for(Element el : element.select(".subject.tal a")) {
      uri = el.getElementsByAttribute("href").attr("href");
      urlresult = "https://www.poultry.or.kr" + uri.substring(2);  
   }
%>
      
      
<!-- middleDiv-->
      <div id="middleDiv">
         <div id="title">육계관련 뉴스</div>
         <div id="passInfo"></div>
            <pre id="crawl">
<%=sb%>
<input type="button" onclick="gotoNews()" value="뉴스 더 보기"/>
            </pre>
      </div>

<!-- rightDiv-->
      <%@include file="/WEB-INF/jsp/template_right.jsp" %>

   </div>
</html>