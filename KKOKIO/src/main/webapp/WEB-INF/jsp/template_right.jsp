<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<script type="text/javascript">
$(function(){
// 	alert(${memid.ADDRESSCODE});
	if(!(${memid.ADDRESSCODE}==null||${memid.ADDRESSCODE}=="")){
// 		alert(${memid.ADDRESSCODE});
		showWeatherInfo(${memid.ADDRESSCODE});
	}
});
</script>
<!-- rightDiv-->
<div id="rightDiv">
	<div id="weatherInfo">
		<div id="weatherTitle">
			날씨정보
			<input type="button" id="WeatherBtn" onclick="openWeatherInfo()" value="설정">
		</div>
		<div id="weatherRight">
			<div id=weatherImgDiv>
			</div>
			날씨: <span id="wfkor"></span><br>
			강수확률: <span id="pop"></span><br>
		</div>
		<div id="weatherLeft">
			<div id="temp"></div>
			<span id="tmn"></span>
			/
			<span id="tmx"></span><br>
		</div>
	</div>
	<div id="datepicker"></div>
   <button id="excelBtn" onclick='location.href="./excelPage.do"'>엑셀파일<br> 업로드/다운로드</button>
   
   
   
	 	<!-- 파스 선택 모달 폼 -->
		<div class="modal fade" id="weatherModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content myContent">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">날씨 정보 설정하기</h4>
					</div>
					<div class="modal-body">
<!-- 						<form action="#" method="post" class="form-margin40" role="form" id="weatherModalBody"> -->
								시 :
								<select name="siArea" id="siArea"></select>
								<button onclick="siSelect()">선택</button>
								
								구 :
								<select name="guArea" id="guArea">
									<option>---------</option>
								</select>
								<button onclick="guSelect()">선택</button>
							
								동 :	
								<select name="dongArea" id="dongArea">
									<option>---------</option>
								</select>
								<button onclick="dongSelect()">선택</button>
								
								<div id="addressValue"></div>
<!-- 						</form> -->
					</div>
				</div>
			</div>
		</div>
</div>