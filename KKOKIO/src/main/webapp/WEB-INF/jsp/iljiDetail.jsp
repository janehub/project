
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.next.kko.dtos.RecordIljiDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<%
   response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>농장일지_일지정보</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
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

<style type="text/css">
   
   li {
      border: 1px solid black;
   }

   .tabss li {
      list-style: none;
      display: inline;
   }
   
   .tabss a {
      padding: 5px 20px;
      display: inline-block;
      text-decoration: none;
   }
   
   a{
   	  cursor: pointer;
   }

	.tab-content a{
		font-size: 25px;
	}
/* 테이블 영역 */
.deathCntWeightDiv{
	width: 370px;
	height: 120px;
	border: 1px solid #bcc98e;
}

.buildtempDiv{
	width: 350px;
	float: right;
	height: 120px;
	border: 1px solid #bcc98e;
}

.etcDiv{
	height: 80px;
	margin: 10px 0;
	border: 1px solid #bcc98e;
}

.medicineDiv{
	height: 80px;
	border: 1px solid #bcc98e;
}

/* 스크롤 */
.scrolldeathCntWeight{
	height: 80px;
	overflow-y: scroll;
	overflow-x: hidden;
}
.scrollBuilttemp{
	height: 98px;
	overflow-y: scroll;
	overflow-x: hidden;
}

.scrollEtc{
	height: 58px;
	overflow-y: scroll;
	overflow-x: hidden;
}

.scrollMedicine{
	height: 58px;
	overflow-y: scroll;
	overflow-x: hidden;
}

/* 테이블 */
.deathCntWeightTable, .buildtempTable, .etcTable, .medicineTable{
/* 	border: 1px solid red; */
	text-align: center;
}
/* 헤더 */
.deathCntWeightHeader th, .buildtempHeader th, .etcHeader th, .medicineHeader th{
	background: #638270;
	text-align: center;
}

.deathCntWeightFooter th{
	background: #638270;
	text-align: center;
}



/* .deathCntWeightTable th, .buildtempTable th, .etcTable th, .medicineTable th{ */
/* 	background: #638270; */
/* 	text-align: center; */
/* } */

/* 홀수 행 색상 배경*/
.deathCntWeightTable tr:nth-child(odd) {
    background: #bcc98e;
}

.buildtempTable tr:nth-child(odd) {
    background: #bcc98e;
}

.etcTable tr:nth-child(odd) {
    background: #bcc98e;
}

.medicineTable tr:nth-child(odd) {
    background: #bcc98e;
}

/* 짝수 행 색상 배경*/
.deathCntWeightTable tr:nth-child(even) {
    background: #F2F2F2;
}

.buildtempTable tr:nth-child(even) {
    background: #F2F2F2;
}

.etcTable tr:nth-child(even) {
    background: #F2F2F2;
}
.medicineTable tr:nth-child(even) {
    background: #F2F2F2;
}


.t1c1, .th1c1{
	width: 78px;
}

.t1c2, .th1c2{
	width: 140px;
}

.t1c3{
	width: 140px;
	text-align: right;
	padding-right: 15px;
}

.th1c3{
	width: 150px;
	text-align: right;
/* 	padding-right: 62px; */
}

.t2c1, .th2c1{
	width: 94px;
}

.t2c2, .th2c2{
	width: 120px;
}

.t2c3{
	width: 120px;
}

.th2c3{
	width: 140px;
}

.t3c1, .th3c1{
	width: 100px;
}

.t3c1{
	text-align: left;
	padding-left: 5px;
}

.t3c2, .th3c2{
	width: 518px;
}

.t3c2{
	text-align: left;
	padding-left: 5px;
}

.t3c3{
	width: 110px;
}

.th3c3{
	width: 120px;
}

.t4c1, .th4c1{
	width: 100px;
}

.t4c1{
	text-align: left;
	padding-left: 5px;	
}

.t4c2, .th4c2{
	width: 518px;
}

.t4c2{
	text-align: left;
	padding-left: 5px;
}

.t4c3{
	width: 110px;
}

.th4c3{
	width: 120px;
}

.etcTable input{
	width: 100%;
	border: none;
    background: rgba(255,255,255,0.8);
    border-bottom: 1px solid #bcc98e;
}

.buildtempTable input{
	width: 40px;
    text-align: center;
	border: none;
    background: rgba(255,255,255,0.8);
    border-bottom: 1px solid #bcc98e;
}

.deathCntWeightTable input{
	width: 60px;
    text-align: center;
	border: none;
    background: rgba(255,255,255,0.8);
    border-bottom: 1px solid #bcc98e;
}

.btnDiv{
	text-align: right;
    padding-top: 10px;
}
</style>

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
<%
	Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
%>
<script type="text/javascript">
$(document).ready(function(){
   $("div>p").show();
   
   $("#tab2").hide();
   $("#tab3").hide();
   $("#tab4").hide();
   $("#tab5").hide();
   $("#tab6").hide();
   $("#tab7").hide();

    
   $("a[href*=tab1]").click(function(){
      $("#tab1").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab1").show();
   });
   
   $("a[href*=tab2]").click(function(){
      $("#tab2").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab2").show();
   });
   
   $("a[href*=tab3]").click(function(){
      $("#tab3").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab3").show();
   });
   
   $("a[href*=tab4]").click(function(){
      $("#tab4").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab4").show();
   });
   
   $("a[href*=tab5]").click(function(){
      $("#tab5").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab5").show();
   });
   
   $("a[href*=tab6]").click(function(){
      $("#tab6").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab6").show();
   });
   
   $("a[href*=tab7]").click(function(){
      $("#tab7").siblings().hide();
      $("#list").show();
      $("#passInfo").show();
      $("#title").show();
      $("#tabs").show();
      $("#tab7").show();   
   });

});

$(function(){
   $("h2>div").eq(0).show();
   
   $("h2>div").eq(0).siblings("div").hide();
   
   $("div>h2").eq(0).addClass("active");
   
//    $("h3").click(function(){
// //         alert($(this).next().val());
// //      일령볼때 사용가능
//       $(this).next().stop().slideToggle();
//       $(this).next().siblings("p").stop().slideUp();
//       $(this).toggleClass("active");
      
//       $(this).siblings("h3").each(function(){
//          if($(this).hasClass("active")){
//             $(this).removeClass("active");
//          }
//       });
//    });
   
   $(".tab-content h2").click(function(){
      $(this).next().siblings("div").stop().slideUp();
      $(this).next().stop().slideToggle();
      $(this).toggleClass("active");
      

      $(this).siblings("h2").each(function(){
         if($(this).hasClass("active")){
            $(this).removeClass("active");
         }
      });
      
   });
});

function formatDate(date) {
    var d = new Date(date);
    var month = '' + (d.getMonth() + 1);
    var day = '' + d.getDate();
    var year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return year+"년 "+month+"월 "+day+"일";
}



function showillyung(illyung) {
   
   $.ajax({
      url:"./deathcountSearch.do",
      type:"GET",
      dataType:"json",
      data: '&illyung='+illyung,
      success:function(json){
         
//     	  alert(JSON.stringify(json));
//     	  $("#illyunghere"+illyung).html(html);
    	  var html="";
//     	  html+=JSON.stringify(json)+"<br><br><br>";
    	  
          var jsonObj = JSON.parse(json.jlist);
          
          //동 갯수 -세션에서 가져오기
          var dongnum=0;
		<%
		for(int i=0;i<Integer.parseInt(smap.get("DONGNUMBER"));i++){
			System.out.println(i);
		%>
			dongnum++;
			
		<%
		}
		%>
		
		var dong_deathcount= new Array(); //폐사수 -배열선언
		var dong_weight=new Array(); //중량 -배열선언
		
		var dong_deathcount_seq= new Array(); //폐사수 seq -배열선언
		var dong_weight_seq=new Array(); //중량 seq -배열선언
		
		var etc="";
		etc="<table class='etcHeader'><tr><th class='th3c1'>동</th><th class='th3c2'>비고</th><th class='th3c3'>시간</th></tr></table>";
		etc+="<div class='scrollEtc'><table class='etcTable'>"
		
		var buildtemp="";
		buildtemp+="<table class='buildtempHeader'><tr><th class='th2c1'>동</th><th class='th2c2'>사육장온도</th><th class='th2c3'>시간</th></tr></table>";
		buildtemp+="<div class='scrollBuilttemp'><table class='buildtempTable'>"
		
		var medicine="";
		medicine+="<table class='medicineHeader'><tr><th class='th4c1'>동</th><th class='th4c2'>약품</th><th class='th4c3'>시간</th></tr></table>";
		medicine+="<div class='scrollMedicine'><table class='medicineTable'>";
		
        for(var i=0; i < jsonObj.length; i++) {
        	
        	//폐사수
        	if(Number(jsonObj[i].deathcount)>0){
        		dong_deathcount[Number(jsonObj[i].distinctdong)]=Number(jsonObj[i].deathcount);	//폐사수 값
        		dong_deathcount_seq[Number(jsonObj[i].distinctdong)]=jsonObj[i].recordilji_seq;	//폐사수 seq
//         		html+= Number(jsonObj[i].deathcount)+":"+Number(jsonObj[i].distinctdong)+"<br><br>";
        	}
        	
        	//중량
        	if(Number(jsonObj[i].weight)>0){
        		dong_weight[Number(jsonObj[i].distinctdong)]=Number(jsonObj[i].weight);	//중량 값
        		dong_weight_seq[Number(jsonObj[i].distinctdong)]=jsonObj[i].recordilji_seq;	//중량 seq
//         		html+= Number(jsonObj[i].deathcount)+":"+Number(jsonObj[i].distinctdong)+"<br><br>";
        	}
        	
        	//비고
       		if(jsonObj[i].etc!=undefined){
       			etc+="<tr>";
       			etc+="<td class='t3c1'>"+jsonObj[i].distinctdong+"</td>";
       			etc+="<td class='t3c2'>";
       			etc+="<input type='text' name='etc_value' value='";
       			etc+=jsonObj[i].etc;
       			etc+="'>";
       			etc+="</td><td class='t3c3'>";
       			etc+=(jsonObj[i].recordtime).substring((jsonObj[i].recordtime).indexOf(" "),(jsonObj[i].recordtime).lastIndexOf("."));
       			etc+="<input type='hidden' name='etc_seq' value='"+jsonObj[i].recordilji_seq+"'>";
       			etc+="</td>";
       			etc+="</tr>";
       		}
        	
        	//사육장 온도
        	if(jsonObj[i].buildtemp!=undefined && (Number(jsonObj[i].buildtemp)>0)){
        		buildtemp+="<tr>";
        		buildtemp+="<td class='t2c1'>"+jsonObj[i].distinctdong+"</td>";
        		buildtemp+="<td class='t2c2'>";
        		buildtemp+="<input type='text' name='buildtemp_value' value='";
        		buildtemp+=jsonObj[i].buildtemp;
        		buildtemp+="'>";
        		buildtemp+="\u2103"+"</td><td class='t2c3'>";
        		buildtemp+=(jsonObj[i].recordtime).substring((jsonObj[i].recordtime).indexOf(" "),(jsonObj[i].recordtime).lastIndexOf("."));
        		buildtemp+="<input type='hidden' name='buildtemp_seq' value='"+jsonObj[i].recordilji_seq+"'>";
        		buildtemp+="</td>";
        		buildtemp+="</tr>";
        	}
        	//약품
       		if(jsonObj[i].medicine!=undefined){
       			medicine+="<tr>";
       			medicine+="<td class='t4c1'>"+jsonObj[i].distinctdong+"</td>";
       			medicine+="<td class='t4c2'>";
       			medicine+="<input type='text' name='medicine_value' value='";
       			medicine+=jsonObj[i].medicine;
       			medicine+="'>";
       			medicine+="</td><td class='t4c3'>";
       			medicine+=(jsonObj[i].recordtime).substring((jsonObj[i].recordtime).indexOf(" "),(jsonObj[i].recordtime).lastIndexOf("."));
       			medicine+="<input type='hidden' name='medicine_seq' value='"+jsonObj[i].recordilji_seq+"'>";
       			medicine+="</td>";
       			medicine+="</tr>";
       		}
        }
        
        etc+="</table></div>";
        buildtemp+="</table></div>";
        medicine+="</table>";
 
//         for(var i=0;i<dong_deathcount.length;i++){ //폐사수 배열 출력
//             html+= i+ " / "+dong_deathcount[i]+"<br>";
//         }
        
//         for(var i=0;i<dong_deathcount.length;i++){ //중량 배열 출력
//             html+= i+ " / "+dong_weight[i]+"<br>";
//         }       
        
        var total_deathcount=0;
        var total_weight=0;
        var weight_cnt=0;
        var weight_avg=0;
        
		html+="<table class='deathCntWeightHeader'>";
		html+="<tr><th class='th1c1'>동</th><th class='th1c2'>폐사수</th><th class='th1c3'>중량</th></tr></table>";
		html+="<div class='scrolldeathCntWeight'><table class='deathCntWeightTable'>";
		for(var k=1;k<=dongnum;k++){
			html+="<tr><td class='t1c1'>"+k+"</td><td class='t1c2'>";
			if(dong_deathcount[k]!=undefined){			//폐사수가 있을 때
				html+="<input type='text' name='deathcount_value' value='";
				html+=dong_deathcount[k];
				html+="'>";
				html+="<input type='hidden' name='deathcount_seq' value='";
				html+=dong_deathcount_seq[k];
				html+="'>";
				
				//전체 폐사수 계산
				total_deathcount+=dong_deathcount[k];
			}else{										//폐사수가 없을 때 - 배열 순서/갯수 맞추기
				html+="<input type='text' name='deathcount_value' value='";
				html+="-";
				html+="'>";
				html+="<input type='hidden' name='deathcount_seq' value=''>";
			}
			html+="</td><td class='t1c3'>";
			if(dong_weight[k]!=undefined){				//중량이 있을 때
				html+="<input type='text' name='weight_value' value='";
				html+=(dong_weight[k]).toFixed(2);
				html+="'>";
				html+="g";
				
				html+="<input type='hidden' name='weight_seq' value='";
				html+=dong_deathcount_seq[k];
				html+="'>";
				//전체 중량 계산
				total_weight+=dong_weight[k];
				weight_cnt++;
			}else{
				html+="<input type='text' name='weight_value' value='-'>";
				html+="g";
				html+="<input type='hidden' name='weight_seq' value=''>";
			}
			html+="</td>";
			html+="</tr>";
		}

		html+="</table></div>";
		html+="<table class='deathCntWeightFooter'>"
		html+="<tr><th class='th1c1'>전체</th><th class='th1c2'>";
		html+=total_deathcount;
		html+="</th><th class='th1c3'>";
		if(weight_cnt>0){
			weight_avg=(total_weight/weight_cnt).toFixed(2);
			html+=weight_avg+"g";
		}else{
			html+="-g";			
		}
		html+="</th></tr>";
		html+="</table>"
        
		//div 씌우기
		html="<div class='deathCntWeightDiv'>"+html+"</div>";
		etc="<div class='etcDiv'>"+etc+"</div>";
		buildtemp="<div class='buildtempDiv'>"+buildtemp+"</div>";
		medicine="<div class='medicineDiv'>"+medicine+"</div>";
        
		//수정하기 버튼
		var modifyBtn="<div class='btnDiv'><input type='button' onclick='modifyIlji()' value='수정하기'></div>";
		
		//form 씌우기
		var frm="<form id='frm'>";
		frm+="<input type='hidden' name='illyung' value='"+illyung+"'>";
		frm+=buildtemp+html+etc+medicine+modifyBtn+"</form>";
 		//화면에 표출하기
        $("#illyunghere"+illyung).html(frm);
		
// 		$("#illyunghere"+illyung).html(j);
//          var week_deathcount = 0;
//          var week_day_weight = 0;
//          var total_day1_deathcount_dong1 = 0;
//          var total_day1_weight_dong1 = 0;
//          var total_day1_weight_dong2 = 0;
//          var total_day1_weight_dong3 = 0;
//          var sum_build_temp = "";
//          var sum_etc = "";
//          var sum_medicine = "";
//          var time = "";
//          var test = "123";
         
         
//          for(var i=0; i < jsonObj.length; i++) {
            
//                if(jsonObj[i].distinctdong == 1) {
//                   total_day1_deathcount_dong1 += Number(jsonObj[i].deathcount);
//                   total_day1_weight_dong1 += Number(jsonObj[i].weight);
//                } else if(jsonObj[i].distinctdong == 2) {
//                   total_day1_deathcount_dong2 += Number(jsonObj[i].deathcount);
//                   total_day1_weight_dong2 += Number(jsonObj[i].weight);
//                } else if(jsonObj[i].distinctdong == 3) {
//                   total_day1_deathcount_dong3 += Number(jsonObj[i].deathcount);
//                   total_day1_weight_dong3 += Number(jsonObj[i].weight);
//                }
            
//             if(jsonObj[i].buildtemp != 0) {
//                sum_build_temp += "사육장 온도 :" + jsonObj[i].buildtemp + "/";
//             }
            
//             if(jsonObj[i].etc != undefined) {
//                sum_etc += "비고 : " + jsonObj[i].etc + "/";
//             }
            
//             if(jsonObj[i].medicine != undefined) {
//                sum_medicine += "약품 : " + jsonObj[i].medicine + "/";
//             }
//          }
         
         
// //          for(var j=0; j < jsonObj.length; j++) {
// //             if(jsonObj[j].illyung == illyung) {
// //                alert(jsonObj[j].illyung);
// //                break;
// //             }
// //          }
         
// //          alert("받은 일령 " + illyung);
// //          alert("데이터베이스 값 "+ jsonObj[0].illyung);
// //          alert("데이터베이스 두번째 값 " + jsonObj[1].illyung);
         
//          var html="";
//          html+="<form action='./modifyIlji.do' method='POST'>";
//          html+="<div style='height:180px;'>";
//          html+="<table>";
//          html+="<tr>";
//          html+="<td style='width:100px;'></td>";
//          html+="<td>1동</td>";
//          html+="<td>2동</td>";
//          html+="<td>3동</td>";
//          html+="</tr>";
//          html+="<tr>";
//          html+="<td>폐사수</td>";
         
//         for(var j=0; j < jsonObj.length; j++) {
// 	         if(jsonObj[j].illyung == illyung) {
// 	            html+="<input type='hidden' name='illyung' value='"+jsonObj[j].illyung+"'/>";
// 				break;
// 	         }
//         }
         
//          html+="<td><input type='text' name='deathdong1' value='"+total_day1_deathcount_dong1+"'/></td>";
//          html+="<td><input type='text' name='deathdong2' value='"+total_day1_deathcount_dong2+"'/></td>";
//          html+="<td><input type='text' name='deathdong3' value='"+total_day1_deathcount_dong3+"'/></td>";
//          html+="</tr>";
//          html+="<tr>";
//          html+="<td>중량</td>";
//          html+="<td><input type='text' name='weightdong1' value='"+ total_day1_weight_dong1 +"'/></td>";
//          html+="<td><input type='text' name='weightdong2' value='"+ total_day1_weight_dong2 +"'/></td>";
//          html+="<td><input type='text' name='weightdong3' value='"+ total_day1_weight_dong3 +"'/></td>";
//          html+="</tr>";
//          html+="<p></p>";
//          html+="<tr>";
//          html+="<td>사육장 온도</td>";
//          html+="<td colspan='"+3+"'><input type='text' value='"+sum_build_temp+"' readonly='readonly' style='width:505px'/></td>";
//          html+="</tr>";
//          html+="<tr>";
//          html+="<td>시간별 비고</td>";
//          html+="<td colspan='"+3+"'><input type='text' value='"+sum_etc+"' readonly='readonly' style='width:505px'/></td>";
//          html+="</tr>";
//          html+="<tr>";
//          html+="<td>약품</td>";
//          html+="<td colspan='"+3+"'><input type='text' value='"+sum_medicine+"' readonly='readonly' style='width:505px'/></td>";
//          html+="</tr>";
//          html+="</table>";
//          html+="<input type='submit' name='edit' value='수정하기'/>";
//          html+="</div>";
//          html+="</form>";
         
         
//          $("#illyunghere"+illyung).html(html);
      }
   });
}

function modifyIlji(){
// 	alert("수정");

//전송	
	var frm=document.getElementById("frm");
	var formData = new FormData(frm);
// 	alert(frm.innerHTML);

	
// 	frm.action="./iljiModify.do";
// 	frm.method="post";
// 	frm.submit();
	
	$.ajax({
		url:"./iljiModify.do",
		type:"POST",
    	processData:false,
    	contentType: false,
  		data: formData,
        cache: false,
        success: function(msg){
//         	alert(msg);
			if(msg=="success"){
				alert("수정이 성공적으로 완료되었습니다.");
			}else{
				alert("실패했습니다.");
			}
        }
	});


}
</script>
<body>
   <div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>

<!-- middleDiv-->
      <div id="middleDiv">
            <div id="title">일지 정보 </div>
<!--          <div id="passInfo">기본파스정보</div> -->
         
         <div id="list">
            <ul id="tabs" class="tabss">
                  <li><a href="#tab1">1주령</a></li>
                  <li><a href="#tab2">2주령</a></li>
                  <li><a href="#tab3">3주령</a></li>
                  <li><a href="#tab4">4주령</a></li>
                  <li><a href="#tab5">5주령</a></li>
                  <li><a href="#tab6">6주령</a></li>
                  <li><a href="#tab7">7주령</a></li>
            </ul>
         </div>
      
         <div id="tab1" class="tab-content">
               <h2><a onclick="showillyung('1')">1일령</a></h2>
                  <div id="illyunghere1">
<!--                   	<input type="hidden" name="illyung" value="1"/> -->
                  </div>
               <h2><a onclick="showillyung('2')">2일령</a></h2>
                  <div id="illyunghere2"></div>
               <h2><a onclick="showillyung('3')">3일령</a></h2>
                  <div id="illyunghere3"></div>
               <h2><a onclick="showillyung('4')">4일령</a></h2>
                  <div id="illyunghere4"></div>
               <h2><a onclick="showillyung('5')">5일령</a></h2>
                  <div id="illyunghere5"></div>                  
               <h2><a onclick="showillyung('6')">6일령</a></h2>
                  <div id="illyunghere6"></div>
               <h2><a onclick="showillyung('7')">7일령</a></h2>
                  <div id="illyunghere7"></div>
         </div>
         
         <div id="tab2" class="tab-content">
               <h2><a onclick="showillyung('8')">8일령</a></h2>
                  <div id="illyunghere8"></div>
               <h2><a onclick="showillyung('9')">9일령</a></h2>
                  <div id="illyunghere9"></div>
               <h2><a onclick="showillyung('10')">10일령</a></h2>
                  <div id="illyunghere10"></div>
               <h2><a onclick="showillyung('11')">11일령</a></h2>
                  <div id="illyunghere11"></div>
               <h2><a onclick="showillyung('12')">12일령</a></h2>
                  <div id="illyunghere12"></div>                  
               <h2><a onclick="showillyung('13')">13일령</a></h2>
                  <div id="illyunghere13"></div>
               <h2><a onclick="showillyung('14')">14일령</a></h2>
                  <div id="illyunghere14"></div>
         </div>
         
         <div id="tab3" class="tab-content">
               <h2><a onclick="showillyung('15')">15일령</a></h2>
                  <div id="illyunghere15"></div>
               <h2><a onclick="showillyung('16')">16일령</a></h2>
                  <div id="illyunghere16"></div>
               <h2><a onclick="showillyung('17')">17일령</a></h2>
                  <div id="illyunghere17"></div>
               <h2><a onclick="showillyung('18')">18일령</a></h2>
                  <div id="illyunghere18"></div>
               <h2><a onclick="showillyung('19')">19일령</a></h2>
                  <div id="illyunghere19"></div>                  
               <h2><a onclick="showillyung('20')">20일령</a></h2>
                  <div id="illyunghere20"></div>
               <h2><a onclick="showillyung('21')">21일령</a></h2>
                  <div id="illyunghere21"></div>
         </div>
         
         
         <div id="tab4" class="tab-content">
               <h2><a onclick="showillyung('22')">22일령</a></h2>
                  <div id="illyunghere22"></div>
               <h2><a onclick="showillyung('23')">23일령</a></h2>
                  <div id="illyunghere23"></div>
               <h2><a onclick="showillyung('24')">24일령</a></h2>
                  <div id="illyunghere24"></div>
               <h2><a onclick="showillyung('25')">25일령</a></h2>
                  <div id="illyunghere25"></div>
               <h2><a onclick="showillyung('26')">26일령</a></h2>
                  <div id="illyunghere26"></div>                  
               <h2><a onclick="showillyung('27')">27일령</a></h2>
                  <div id="illyunghere27"></div>
               <h2><a onclick="showillyung('28')">28일령</a></h2>
                  <div id="illyunghere28"></div>
         </div>
         
         
         <div id="tab5" class="tab-content">
               <h2><a onclick="showillyung('29')">29일령</a></h2>
                  <div id="illyunghere29"></div>
               <h2><a onclick="showillyung('30')">30일령</a></h2>
                  <div id="illyunghere30"></div>
               <h2><a onclick="showillyung('31')">31일령</a></h2>
                  <div id="illyunghere31"></div>
               <h2><a onclick="showillyung('32')">32일령</a></h2>
                  <div id="illyunghere32"></div>
               <h2><a onclick="showillyung('33')">33일령</a></h2>
                  <div id="illyunghere33"></div>                  
               <h2><a onclick="showillyung('34')">34일령</a></h2>
                  <div id="illyunghere34"></div>
               <h2><a onclick="showillyung('35')">35일령</a></h2>
                  <div id="illyunghere35"></div>
         </div>
         
         <div id="tab6" class="tab-content">
               <h2><a onclick="showillyung('36')">36일령</a></h2>
                  <div id="illyunghere36"></div>
               <h2><a onclick="showillyung('37')">37일령</a></h2>
                  <div id="illyunghere37"></div>
               <h2><a onclick="showillyung('38')">38일령</a></h2>
                  <div id="illyunghere38"></div>
               <h2><a onclick="showillyung('39')">39일령</a></h2>
                  <div id="illyunghere39"></div>
               <h2><a onclick="showillyung('40')">40일령</a></h2>
                  <div id="illyunghere40"></div>                  
               <h2><a onclick="showillyung('41')">41일령</a></h2>
                  <div id="illyunghere41"></div>
               <h2><a onclick="showillyung('42')">42일령</a></h2>
                  <div id="illyunghere42"></div>
         </div>
         
         <div id="tab7" class="tab-content">
               <h2><a onclick="showillyung('43')">43일령</a></h2>
                  <div id="illyunghere43"></div>
               <h2><a onclick="showillyung('44')">44일령</a></h2>
                  <div id="illyunghere44"></div>
               <h2><a onclick="showillyung('45')">45일령</a></h2>
                  <div id="illyunghere45"></div>
               <h2><a onclick="showillyung('46')">46일령</a></h2>
                  <div id="illyunghere46"></div>
               <h2><a onclick="showillyung('47')">47일령</a></h2>
                  <div id="illyunghere47"></div>                  
               <h2><a onclick="showillyung('48')">48일령</a></h2>
                  <div id="illyunghere48"></div>
               <h2><a onclick="showillyung('49')">49일령</a></h2>
                  <div id="illyunghere49"></div>
         </div>
         
      </div>


<!-- rightDiv-->
 		<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</html>