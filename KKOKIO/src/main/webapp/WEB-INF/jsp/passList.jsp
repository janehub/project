<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 파스 관리</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
<style type="text/css">
.ui-jqgrid .ui-state-highlight {
 			background: #FAED7D; 
 }
 
#jqgridDiv {
 	padding-top:20px;
 	padding-left: 50px;
 }
 
#jqgridDiv .ui-jqgrid{
	cursor: pointer;
}
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="./js/jqGridPaging.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>
<script type="text/javascript">
$(function() {
     
    //jqGrid껍데기 생성
    $("#list").jqGrid({
    	url:'./passList.do',
		datatype: "json",
		jsonReader : { 
			page: "page", 
			total: "total", 
			root: "user", 
			records: function(obj){return obj.length;},
			repeatitems: false, 
			id: "passcode"
		},
        colNames:['파스CODE','파스시작일','파스종료일','입추수수','덤 수','품종','부화장','활발도','동별병아리수'
        	],
        //컬럼모델
        colModel:[
            {name:'passcode', align:'center' ,width:100, key:true, editable:true, editoptions: { readonly: "readonly" }},
            {name:'startpass',width:62, editable:true, editoptions: { readonly: "readonly" }},
            {name:'endpass', width:62, editable:true},
            {name:'incount',align:'right', width:50, editable:true},    
            {name:'indum', align:'right', width:50, editable:true},    
            {name:'intype',width:60, editable:true},    
            {name:'inbuhwa',width:60, editable:true},    
            {name:'inactivity',width:60, editable:true},    
            {name:'dongchicknum',width:100, editable:true}    
        ],
        //그리드타이틀
        caption: "파스 리스트",
        //연번
        rownumbers:true,
        //정렬기준
        sortname:'passcode',
        sortorder:"desc",
        //
        height:300,
        width: 630,
        
	    rowNum:15,
	    rowList:[10,15,20],
         pager:'#pager',
//        pager:'#paginate',
        loadonce: true,
        loadComplete : function(data){  
            // 페이지 셋팅
 
				// 그리드 데이터 총 갯수
		        var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
             
				// 처음 currentPage는 널값으로 세팅 (=1)
				initPage("list",allRowsInGrid,"");
         },
         
         //수정, 삭제시 이동할 url
         editurl : './editPass.do'
 		 //delurl : './deletePass.do' //호출되지 않음. editurl이 호출됨

        
    });
     
    // 스크립트 변수에 담겨있는 json데이터의 길이만큼 
//     for(var i=0;i<=gridData.length;i++){
//             //jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
//             $("#list").jqGrid('addRowData',i+1,gridData[i]);
//     }
    
    
    // jqGrid navGrid 설정
    $('#list').jqGrid('navGrid', '#pager',
              {
               edit : true,   	// 수정
               add : false,  	// 추가
               del : true,  	// 삭제
               view: true, 		// 상세조회
               refresh: true,	// 갱신
               search : false  	//검색 아이콘
               
              },
              	// edit 작업 설정
              { // 수정
	            	closeAfterEdit:true,
	            	reloadAfterSubmit:true
              },{//삭제
//             	  onclickSubmit:post_list_del,
            	    closeAfterEdit:true,
	            	reloadAfterSubmit:true
              }
    
              
             );
    
  	   		/*    function post_list(params, postData) {
				var list = $("#list");
				var selectedRow = list.getGridParam("selrow");
				return postData;
				}  */
	 			
	 			
// 				 function post_list_del(params, postData)
// 				 {
// 				  /* var post_data = new Object();
// 				  post_data.id = postData;
// 				  post_data.url_table='url_list_1';
// 				  return post_data; */
				  
// 					var list = $("#list");
// 					var selectedRow = list.getGridParam("selrow");
// 					return postData;
// 				 }
				
		});

</script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">전체 파스 관리</div>
			<div id="jqgridDiv">
				<table id="list"></table>
				<div id="pager"></div>
		<!--  새롭게 구현할 커스텀 페이징 태그 -->
		<!-- <div id="paginate"></div> -->
			</div>
		</div>
<!-- rightDiv-->		
	<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</body>
</html>