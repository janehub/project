<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@page import="com.next.kko.dtos.IssueDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 이슈사항</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- swal알림. -->
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<!-- mainPage CSS -->
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
<!-- jqGrid 선택한 행 색깔변화 -->
<style type="text/css">
.ui-jqgrid .ui-state-highlight {
          background: #FAED7D; 
 }
 #jqgridDiv {
 	padding-top:20px;
 	padding-left: 50px;
 }
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="./js/jqGridPaging.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- Modal JavaScript 파일 -->
<script type="text/javascript">

	$(function() {
		
 		/* 이슈 jqGrid Table */
		$.jgrid.regional["kr"].view.caption = '이슈 상세 조회';
		$.jgrid.regional["kr"].edit.addCaption = '이슈 추가';
		$.jgrid.regional["kr"].edit.editCaption = '이슈 수정';

	    $("#list").jqGrid({
	    	//url 설정
	    	url : './Issueresult.do',
	    	//로딩 텍스트
	    	loadtext: '로딩중..',
	    	//데이터 타입 json으로 사용
	    	datatype: "json",
	    	jsonReader : {
	    		page: "page",
	    		total : "total",
	    		root : "user",
	    		records : function(obj){return obj.length;},
	    		repeatitems : false,
	    		id: "contentcode"
	    	},
	        //로컬그리드이용
			//datatype: "local",
	        //그리드 높이
	        height: 250,
	        //컬럼명들
	        colNames:['일령','이슈내용', '비고', '조치사항','조치결과','일지코드','파스코드','컨텐츠코드','이슈코드'],
	        //컬럼모델
	        colModel:[
	            {index: 'illyung',			name:'illyung',			sortable:true,	align:'center' ,width:100,height:150},
	            {index: 'issuedetail',		name:'issuedetail',	editable:true,	sortable:true,editoptions : {value : 'issuedetail'},edittype:"text" ,width:100,align:'center',height:150},
	            {index: 'issueetc',			name:'issueetc',		editable:true,	sortable:true,editoptions : {value : 'issueetc'},edittype:"text", width:100,align:'center',height:150},
	            {index: 'actiondetail',		name:'actiondetail',	editable:true,	sortable:true,editrules: {edithidden: true},editoptions : {value : 'actiondetail'},edittype:"text",width:300,align:'center',height:150},
	            {index: 'actionresult',		name:'actionresult',	editable:true, sortable:true,width:50,height:150,align:'center'},
	            {index: 'recordilji_seq',	name:'recordilji_seq',	hidden:true},
	            {index: 'passcode',		name:'passcode', 	hidden:true},
	            {index: 'contentcode',	name:'contentcode',hidden:true},
	            {index: 'issuecode',		name:'issuecode', /* hidden:true, */sortable:true, editable:true, editoptions:{readonly:true}}
	        ],
	        //그리드타이틀
	        caption: "이슈 사항",
	        width : 630,
	        height : 300,
	        gridview: false,
	        //데이타 전송방법
// 	        mtype: 'post',
	        //연번
	        rownumbers:true,
			//정렬할 기준을 오름차순인지, 내림차순인지 설정한다.
			sortorder:"desc",
	        //정렬기준
	        sortname:'issuecode',
// 	        viewrecords : true,
	        rowNum:10,
	        rowList:[5,10,20],
	        pager:'#pager',
	        editurl:"./issueedit.do",
		       //데이터 없을 시 나타내주는 값
				emptyrecords : "데이터가 없습니다.",
		        //체크박스 여부
				//multiselect : true,
				//셀 선택한 뒤 수정가능
				//cellEdit : true,
//	 	        hidegrid: false,
//	 	        altRows: true
	        loadonce: true,
	        loadComplete : function(data){  
	            // 페이지 셋팅
	            // 그리드 데이터 총 갯수
	              var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
	            // 처음 currentPage는 널값으로 세팅 (=1)
	            initPage("list",allRowsInGrid,"");
		    },
		});
	    $('#list').jqGrid('navGrid', '#pager',
	    	    { edit : true,  // 수정 
	    	     add : false,  // 추가
	    	     del : true,  // 삭제
	    	     view: true, // 상세조회
	    	     search : false  //조회
	    	    },
	    	    //edit 작업 설정
	    	    {//수정
	    	    closeAfterEdit:true,
	    	    reloadAfterSubmit:true
	    	    }/*,{//추가
	    	    	closeAfterAdd:true,
	    	    	reloadAfterSubmit:true
	    	    }*/);
	  /*   스크립트 변수에 담겨있는 json데이터의 길이만큼 
	    for(var i=0;i<=gridData.length;i++){
	            jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
	            $("#list").jqGrid('addRowData',i+1,gridData[i]);
	    }
	}); */
// 	 $("#list")
// 	.jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
// 	.jqGrid('setSelection', '3'); 
	});
 		
</script>

<body>
	<div id="container">
	   <%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">이슈사항</div>
			<div id="jqgridDiv">
		<table id="list"></table>
		<div id="pager"></div>
		</div>	
		</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
		<!-- 글 추가폼 -->
  <div class="modal fade" id="insert" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">게시글 수정</h4>
        </div>
        <div class="modal-body">
        	<form action="#" method="post" class="form-margin40" role="form" id="frmInsert"></form>
        </div>
    </div>
  </div>
	</div>
</body>
</html>