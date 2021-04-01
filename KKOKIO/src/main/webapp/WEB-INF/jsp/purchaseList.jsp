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
<title>비품관리_구매내역</title>
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
/* <!-- jqGrid 선택한 행 색깔변화 --> */
.ui-jqgrid .ui-state-highlight {
          background: #FAED7D; 
 }
 
 #modal_body{
 	font-size: 8px;
 }
 
 #purchasI{
 	width: 30px;
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
<!-- jqGrid Paging -->
<script type="text/javascript" src="./js/jqGridPaging.js"></script>


<script type="text/javascript">
	
	$(function () {

		
		/* $("#purchaseInfo").modal({backdrop: 'static', keyboard: false}); */
		
		// jqGrid 생성
		$('#list').jqGrid({
			
			// 구매내역리스트 데이터 호출
			url:'./purchaseAll.do',
			loadtext:'로딩중..',
			datatype:"json",
			jsonReader:{
				page: "page", 
	            total: "total", 
	            root: "user", 
	            records: function(obj){return obj.length;},
				repeatitems:false,
				id:'PURCHASECODE'
			},
			
			//jqGrid 넓이
			width: 600,
	     	// 컬럼명 설정
	        colNames:['구매코드','파스코드','일령','구매일자','제품번호','제품명', '수량', '비고'],
	        // 컬럼모델(컬럼별 설정)
/* 	        colModel:[
	        	{name:'purchase_Dto.purchasecode', editable:true, edittype:"text"},
	        	{name:'purchase_Dto.passcode', editable:true, edittype:"text", width:200},
	        	{name:'purchase_Dto.illyung', editable:true, edittype:"text", width:50, align:'center'},
	        	{name:'purchase_Dto.productin', editable:true, edittype:"text"},
	        	{name:'productcode', editable:true, edittype:"text"},
	        	{name:'pname', editable:true, edittype:"text"},
	        	{name:'purchase_Dto.productnum', editable:true, edittype:"text",  width:50, align:'center'},
	        	{name:'purchase_Dto.etc', editable:true, edittype:"text"},
	        ], */
	        
	        colModel:[
	        	{name:'PURCHASECODE', editable:true, edittype:"text",  editoptions: {readonly:"readonly"}},
	        	{name:'PASSCODE', hidden:true /* editable:true, edittype:"text", width:200 */},
	        	{name:'ILLYUNG', editable:true, edittype:"text", width:50, align:'center'},
	        	{name:'PRODUCTIN', editable:true, edittype:"text"},
	        	{name:'PRODUCTCODE', editable:true, edittype:"text"},
	        	{name:'PNAME', editable:true, edittype:"text"},
	        	{name:'PRODUCTNUM', editable:true, edittype:"text",  width:50, align:'center'},
	        	{name:'ETC', editable:true, edittype:"text"},
	        ],
	        
	        //그리드타이틀
	        caption: "구매내역 목록",
	        //연번
	        rownumbers:true,
	        //정렬기준
	        sortname:'PURCHASECODE',
	        viewrecords:true,
	        //페이지 초기 설정
	        rowNum:10,
	        rowList:[10,15,20],
	        pager:'#pager',
	        loadonce:true,
	        //페이지 setting
	        loadComplete:function(){
	        	 // 그리드 데이터 총 갯수
	              var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
	             
	            // 처음 currentPage는 널값으로 세팅 (=1)
	            initPage("list",allRowsInGrid,"");
	        },
	       	
			//edit(추가, 수정, 삭제) 선택시 이동할 url	        
	        editurl: "./purchaseedit.do"
	        
			
		});
		
		 //navGrid caption 변경
		 $.jgrid.regional["kr"].view.caption = '구매내역 상세 조회';
		 $.jgrid.regional["kr"].edit.addCaption = '구매내역 추가';
		 $.jgrid.regional["kr"].edit.editCaption = '구매내역 수정';
		
		 
		// jqGrid navGrid 설정
		$('#list').jqGrid('navGrid', '#pager',
			{
				edit : true,   	// 수정
	            add : false,  	// 추가
	            del : true,  	// 삭제
	            view: true, 	// 상세조회
	            refresh: true,	// 갱신
	            search : false  //검색 아이콘
			},
			
			/* edit 작업 설정 */
			{ // 수정
            	  closeAfterEdit:true,
            	  reloadAfterSubmit:true
            },{// 추가
            	  closeAfterAdd:true,
            	  reloadAfterSubmit:true
            },{// 삭제
            	//onclickSubmit:post_list_del,
            	closeAfterDel:true,
            	reloadAfterSubmit:true
            }
		);
		
	
/* 		function post_list_del() {
			var rowId = $("#purchaselist").jqGrid('getGridParam','selrow');
			var rowData = $("#purchaselist").getRowData(rowId);
			
			return rowData;
		} */
		
		
	});
	
	
	
	
	function productSortSelect() {
		var productSort= $("select[name=productSort]").val();
		//alert(productSort);
		var product_html = "";

		if(productSort == "약품"){

			product_html += "<option value=\"MEDI0001\">초기 약품</option>";
			product_html += "<option value=\"MEDI0002\">올스타트2</option>";
			product_html += "<option value=\"MEDI0003\">비타민</option>";
			
		}else if(productSort == "사료"){
			
			product_html += "<option value=\"FEED0001\">초기 사료</option>";
			product_html += "<option value=\"FEED0002\">전기 사료</option>";
			product_html += "<option value=\"FEED0003\">후기 사료</option>";
			
		}else if(productSort == "기계"){
			
			product_html += "<option value=\"MACH0001\">센서</option>";
			product_html += "<option value=\"MACH0002\">환풍기 부품</option>";
			product_html += "<option value=\"MACH0003\">열풍기 부품</option>";
			
		}else if(productSort == "기름"){
			
			product_html += "<option value=\"DIES0001\">등유</option>";
			
		}
		
		
		$("#productChoose").html(product_html);
		
	}
	
</script>

<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">구매내역</div>
			<div id="passInfo">기본파스정보</div>
			
			<div id="jqGridarea">
			<table id="list"></table>
			<div id="pager"></div>
			</div>
			
			
			 <!-- Modal -->
			<p></p>
			  <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#purchaseInfo">구매내역 입력</button>
			
			  <div class="modal fade" id="purchaseInfo" role="dialog">
			    <div class="modal-dialog modal-sm">
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			        	<h5>구매내역 정보 입력하기</h5>
			        </div>
			        	
			        <form action="./purchanseInfInput.do" method="post">
			        <div class="modal-body">
			        
			          	사용할 일령:
			         	<input type="text" name="illyung" id="purchasI"><br>
			         	
			         	제품 종류:
			         	<select name="productSort" id="productSort">
			         		<option selected="selected">------</option>
			         		<option id="MEDI">약품</option>
			         		<option id="FEED">사료</option>
			         		<option id="MACH">기계</option>
			         		<option id="DIES">기름</option>
			         	</select>
			         	<button type="button" class="btn btn-default btn-sm" onclick="productSortSelect()">선택</button><br>
			         	
			         	제품:
			         	<select name="productcode" id="productChoose">
			
			         	</select><br>
			         	
			         	구매일자:
			         	<input type="date" name="productin"/><br>
			         	
			         	구매수량:
			         	<input type="number" name="productnum"/><br>
			         	
			         	비고:
			         	<input type="text" name="etc"/><br>
			         	
			         	   
			        </div>
			        <div class="modal-footer">
			          <input type="submit" class="btn btn-warning btn-sm" value="입력"/>
			       	  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			        </div>
			        </form>
			              
			      </div>
			    </div>
		
			  </div>
			
		</div>

<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</html>