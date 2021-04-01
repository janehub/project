<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<script type="text/javascript">
<%-- alert('<%=request.getRequestURI()%>'); --%>
<%-- alert('<%=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1,request.getRequestURI().lastIndexOf("."))%>'); --%>
<%
	String pageName=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1,request.getRequestURI().lastIndexOf("."));
%>
$(function(){
// 	alert("hi");
	<%
// 		System.out.println("pageName: "+pageName);
	//페이지별 분기 - 아코디언 열림
		if(pageName.equals("main")||pageName.equals("makePass")||pageName.equals("passList")){
	%>
	$("#collapseOne").addClass('in');
	$("#a1").addClass('activeTitle');
	<%
		}else if(pageName.equals("iljiWrite")||pageName.equals("iljiDetail")){
	%>
	$("#collapseTwo").addClass('in');
	$("#a2").addClass('activeTitle');
	<%
		}else if(pageName.equals("makeIssue")||pageName.equals("IssueList")){
	%>
 	$("#collapseThree").addClass('in');
 	$("#a3").addClass('activeTitle');
	<%
		}else if(pageName.equals("ProductList")||pageName.equals("purchaseList")){
	%>
	$("#collapseFour").addClass('in');
	$("#a4").addClass('activeTitle');
	<%
		}else if(pageName.equals("searchBoard")){
	%>
	$("#collapseFive").addClass('in');
	$("#a5").addClass('activeTitle');
	<%
		}else if(pageName.equals("staticChart")){
	%>
	$("#collapseSix").addClass('in');
	$("#a6").addClass('activeTitle');
	<%
		}else if(pageName.equals("NoticeBoard")||pageName.equals("newsPage")){
	%>
	$("#collapseSeven").addClass('in');
	$("#a7").addClass('activeTitle');
	<%
		}
	%>
});
function logOut() {
		var con = confirm("로그아웃 하시겠습니까?");
	
		if(con) {
			location.href="./logOut.do";
		} else {
			return false;
		}
	}
</script>
<div id="topDiv">
	<!-- homeDiv -->
		<div id="homeDiv">
			<a href="./homePage.do">KKOKIO | 육계농장관리시스템</a>
		</div>
	<!-- colorDiv -->
		<div id="colorDiv">
			<div id="welcomeID">${memid.ID}님 환영합니다</div>
		</div>
	<!-- topDiv-->
		<div id="topMenu">
			<div class="topSubMenu">
				<a href="./userSearch.do"><span>회원정보</span></a> |
				<a onclick="logOut()">로그아웃</a>
			</div>
		</div>
</div>
<!-- leftDiv -->
		<div id="leftDiv">
			<div class="container_accordion">
			  <div>
			    <div id="farmInfo">
				    <p>${memid.FARMNAME}<br>
				    	<span id="mainPasscode">[ ${memid.passcode} ]</span><br>
				    </p>
				    <button id="chnagePassBtn" onclick='location.href="./changePass.do"'>파스 변경하기</button>
			    </div>
			    <div class="panel-group wrap" id="accordion" role="tablist" aria-multiselectable="true">
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingOne">
			          <h4 class="panel-title">
			        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
			        	 <span id="a1">파스정보</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
			          <div class="panel-body">
			        	  <p><a href="./makePassPage.do">파스 생성</a><br>
 							<a href="./infoPassList.do">전체 파스 관리</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingTwo">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			    	     <span id="a2">농장일지</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			          <div class="panel-body">
			       	   <p><a href="./iljiWrite.do">일지 작성</a><br>
 						<a href="./iljiDetail.do">일지 정보</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingThree">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
			      	    <span id="a3">이슈사항</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
			          <div class="panel-body">
			          <p><a href="./makeIssue.do">이슈작성</a><br>
			          <a href="./IssueList.do">이슈조회</a></p> 
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingFour">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
			        	  <span id="a4">비품 관리</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
			          <div class="panel-body">
			          	<p><a href="./productList.do">비품</a><br>
						<a href="./purchaseList.do">구매 내역</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingFive">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
			        	 <span id="a5"> 조회</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
			          <div class="panel-body">
			          	<p><a href="./searchBoard.do">조회</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingSix">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
			        	  <span id="a6">통계</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
			          <div class="panel-body">
			          	<p><a href="./staticChart.do">통계</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			      <div class="panel">
			        <div class="panel-heading" role="tab" id="headingSeven">
			          <h4 class="panel-title">
			        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
			        	 <span id="a7">게시판</span>
			        </a>
			      </h4>
			        </div>
			        <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
			          <div class="panel-body">
			          	<p><a href="./getAllboard.do">공지사항</a><br>
						<a href="./gonewsPage.do">뉴스</a></p>
			          </div>
			        </div>
			      </div>
				<!--end of panel -->
			
			    </div>
			<!--end of #accordion -->
			
			  </div>
			<!-- end of wrap -->
			
			</div>
			<!--end of container -->



<!-- 			<button class="excelBtn" onclick="excelPage()">엑셀파일 업로드/다운로드</button> -->

		</div>