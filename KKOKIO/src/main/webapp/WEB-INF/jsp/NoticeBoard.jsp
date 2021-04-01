<%@page import="org.springframework.ui.Model"%>
<%@page import="com.next.kko.dtos.NoticeBoardDto"%>
<%@page import="com.next.kko.dtos.RecordIljiDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 공지사항</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&amp;subset=korean" rel="stylesheet">
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
 table{
 margin: 10px auto;
 	width: auto;
 	height: auto;
 }
 img{
 	float: right;
 }
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<!-- Modal -->
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
<script type="text/javascript" src="./js/boardList.js"></script>
<script type="text/javascript">
$(function() {
	
/* 공지사항 jqGrid Table */
$.jgrid.regional["kr"].view.caption = '공지사항 상세 조회';

$("#list").jqGrid({
	//url 설정
	url : './NoticeBoardresult.do',
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
		id: 'noticecode_seq'
	},
    //로컬그리드이용
	//datatype: "local",
    //그리드 높이
    height: 250,
    //컬럼명들
    colNames:['공지번호','작성자', '제목', '내용','작성일'],
    //컬럼모델
    colModel:[
        {name:'noticecode_seq', hidden:true},
        {name:'writer', align:'center',edittype:"text" ,width:30,height:150},
        {name:'title',width:100,align:'center',height:150},
        {name:'content', width:100,align:'center',height:150},
        {name:'regdate',align:'center',width:50,height:150, formatter: "date", formatoptions: { newformat: " Y/m/d" }}
    ],
    //그리드타이틀
    caption: "공지사항",
    width : 630,
    height : 300,
    //데이타 전송방법
	//mtype: 'post',
    //연번
    rownumbers:true,
	//정렬할 기준을 오름차순인지, 내림차순인지 설정한다.
	sortorder:"desc",
    //정렬기준
    sortname:'noticecode_seq',
//     viewrecords : true,
    rowNum:10,
    rowList:[5,10,20],
    pager:'#pager',
//     editurl:"./issueedit.do",
       //데이터 없을 시 나타내주는 값
		emptyrecords : "데이터가 없습니다.",
        //체크박스 여부
		//multiselect : true,
		//셀 선택한 뒤 수정가능
		//cellEdit : true,
//	        hidegrid: false,
//	        altRows: true
    loadonce: true,
    loadComplete : function(data){  
        // 페이지 셋팅
        // 그리드 데이터 총 갯수
          var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
        // 처음 currentPage는 널값으로 세팅 (=1)
        initPage("list",allRowsInGrid,"");
    }
});
$('#list').jqGrid('navGrid', '#pager',
	    { edit : false,  // 수정 
	     add : false,  // 추가
	     del : false,  // 삭제
	     search : false,  //조회
	     view : true // 상세조회
	    });
/*   스크립트 변수에 담겨있는 json데이터의 길이만큼 
for(var i=0;i<=gridData.length;i++){
        jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
        $("#list").jqGrid('addRowData',i+1,gridData[i]);
}
}); */
//$("#list")
//.jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
//.jqGrid('setSelection', '3'); 
});
</script>
<body>
	<div id="container">
<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">공지사항</div>
			<div id="jqgridDiv">
		<table id="list"></table>
		<div id="pager"></div>
  		</div>
		</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
	<c:if test="${fn:trim(memid.AUTH) eq 'A'}">
		<form action="#" method="post">
		<table border="1px solid black">
		<tr>
		<td>연번</td><td>작성자</td><td>제목</td><td>내용</td>
		<td>작성일</td><td>삭제여부</td><td>글삭제</td>
		</tr>
		<c:if test="${fn:length(lists) eq 0}">
		<tr>
				<td style="color:red;" colspan="12" align="center">작성된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:set var="maxLen" value="${fn:length(lists)}"></c:set>
		<c:if test="${fn:length(lists) ne 0}">
		<c:forEach var="dto" items="${lists}" varStatus="vs">
		<tr>
		<td>${maxLen-vs.index}</td>
		<td>${dto.writer}</td>
		<td>${dto.title}</td>
		<td>
		${dto.content}
		<img alt="수정이미지" src="./images/edit.png" name="noticecode_seq" onclick="modify('${dto.noticecode_seq}')">
		</td>
		<td>
		<fmt:parseDate var="dateString" value="${dto.regdate}" pattern="yyyy-MM-dd"/>
		<fmt:formatDate value="${dateString}" pattern="yyyy년 MM월 dd일"/>
		</td>
		<td align="center">${dto.delflag}</td>
<%-- 		<td><input class="btn btn-sm btn-primary btn-center" value="글 수정" name="noticecode_seq" type="button" onclick="modify('${dto.noticecode_seq}')"></td> --%>
		<td><a href="./deleteOneBoard.do?seq=${dto.noticecode_seq}">삭제하기</a></td>
		</tr>
		</c:forEach>
		</c:if>
<!-- 		<tr> -->
<!-- 		<td colspan="3"><a href="./makeboard.do"><input type="button" style="width: auto" value="공지사항 추가"/></a></td> -->
<!-- 		<td colspan="2"><input type="button" style="width: auto" value="공지사항 수정" onclick="noticemodify()"/></td> -->
<!-- 		<td colspan="2"><input type="button" style="width: auto" value="공지사항 삭제" onclick="noticedelete()"/></td> -->
<!-- 		</tr> -->
		<tr><td colspan="8"><a href="./makeboard.do"><input type="button" width="367px" value="글추가"></a></td></tr>
		</table>
		</form>
		</c:if>
		<!-- 수정폼 -->
  <div class="modal fade" id="modify" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">게시글 수정</h4>
        </div>
        <div class="modal-body">
        	<form action="#" method="post" class="form-margin40" role="form" id="frmModify"></form>
        </div>
    </div>
  </div>
	</div>
</body>
</html>