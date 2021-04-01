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
<title>비품관리_비품</title>
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

	$(function() {
		
		/* $("#productInfo").modal({backdrop: 'static', keyboard: false}); */
		
		
		
	 /* 비품 jqGrid Table */
		
		
	 //jqGrid 생성
	    $("#list").jqGrid({
	    	
	    	// 제품리스트 데이터 호출
	    	url:'./goodsInfSelectAll.do',
	    	loadtext:'로딩중..',
	    	datatype:"json",
	    	jsonReader:{
 	       		page: "page", 
	            total: "total", 
	            root: "user", 
	            records: function(obj){return obj.length;},      
	            repeatitems: false, 
	            id: 'productcode' 
	            
	    	}, 

	        //jqGrid 높이
	        height: 300, 
	        //컬럼명 설정
	        colNames:['제품코드','제품명', '가격', '상세정보'],
	        //컬럼모델(컬럼별 설정)
	        colModel:[
	            {name:'productcode', editable:true, edittype:"text",
	            	/*	edittype:"select",
	            		editoptions:{value:{"0":"FEED", "1":"MEDI"}}, */
	            		align:'center' ,width:100,  editoptions: {readonly:"readonly"}},
	            {name:'pname',editable:true, edittype:"text" ,width:100},
	            {name:'pprice',editable:true, edittype:"text",width:100,search: false },
	            {name:'pdetail',editable:true, edittype:"text",width:300,search: false}    
	        ],
	        //그리드타이틀
	        caption: "비품 목록",
	        //연번
	        rownumbers:true,
	        //정렬기준
	        sortname:'productcode',
	        viewrecords:true,
	        //페이지 초기 설정
	        rowNum:10,
	        rowList:[10,15,20],
	        pager:'#pager',
//	        pager:'#paginate',
	        loadonce:true,
	        //페이지 setting
	        loadComplete:function(){
	        	 // 그리드 데이터 총 갯수
	              var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
	             
	            // 처음 currentPage는 널값으로 세팅 (=1)
	            initPage("list",allRowsInGrid,"");
	        },
	       	
	        
			//edit(추가, 수정, 삭제) 선택시 이동할 url	        
	        editurl: "./productedit.do"
	        
	
	        
	    });
	     
	 	
	 	/*localData 사용시 gridData에 값 넣어줌*/
 	    // 스크립트 변수에 담겨있는 json데이터의 길이만큼 
			   /* for(var i=0;i<=gridData.length;i++){
			            //jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
			            $("#list").jqGrid('addRowData',i+1,gridData[i]);
			    	} */
			    	
		//navGrid caption 변경
		$.jgrid.regional["kr"].view.caption = '제품 상세 조회';
		$.jgrid.regional["kr"].edit.addCaption = '제품 추가';
		$.jgrid.regional["kr"].edit.editCaption = '제품 수정';
			   		
	    
	    /* jqGrid navGrid 설정*/
	    $('#list').jqGrid('navGrid', '#pager',
	        {
	            edit : true,   	// 수정
	            add : false,  	// 추가
	            del : true,  	// 삭제
	            view: true, 		// 상세조회
	            refresh: true,	// 갱신
	            search : true  	//검색 아이콘
	         },
	         
	          /* edit 작업 설정 */
	         {  // 수정
	           //onclickSubmit:post_list,
	           	 closeAfterEdit:true,
	             reloadAfterSubmit:true
	         },{// 추가
	            //	 onclickSubmit:post_list,
	             closeAfterAdd:true,
	             reloadAfterSubmit:true
	         },{// 삭제
	             closeAfterDel:true,
	             reloadAfterSubmit:true
	         }
	     );
	    
	  	   		/*    function post_list(params, postData) {
					var list = $("#list");
					var selectedRow = list.getGridParam("selrow");
					return postData;
					}  */
					
					
			
		$("#list").jqGrid('filterToolbar',
				{stringResult: true, searchOnEnter:true, defaultSearch:'cn',
				clearSearch:false}
		);
				
		/* Toolbar size 변경 */
//		var $toolbar = $("tr.ui-search-toolbar", grid[0].grid.hDiv);  
//		$toolbar.height(30);  
			

	});
	

</script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">비품</div>
			<div id="passInfo">기본파스정보</div>
			
			<div id="jqGridarea">	
			<table id="list"></table>
			<div id="pager"></div>
			</div>	
			
			
			<!-- Modal -->
			<p></p>
			 <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#productInfo">제품등록</button>

			  <div class="modal fade" id="productInfo" role="dialog">
			    <div class="modal-dialog modal-sm">
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h5 class="modal-title">제품등록</h5>
			        </div>
			        
			        <form action="./goodsInfInput.do" method="post">
			        <div class="modal-body">
			        	
			        	제품 구분:
			        	<select name="productcode">
							<option selected="selected">---</option>
							<option value="FEED">사료</option>
							<option value="MEDI">약품</option>
							<option value="DIES">기름</option>
							<option value="MACH">기계</option>
						</select><br>
						
						제품명:
						<input type="text" name="pname"/><br>
						
						제품 가격:
						<input type="text" name="pprice"/><br>
						
						상세정보:
						<input type="text"name="pdetail"/><br>
			          
			        </div>
			        <div class="modal-footer">
			          <input type="submit" class="btn btn-warning" value="입력"/>
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