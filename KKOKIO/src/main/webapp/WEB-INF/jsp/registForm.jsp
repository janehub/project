<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 회원가입</title>
<link rel="stylesheet" type="text/css" href="./css/registForm.css">
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript">
function check() {
	var pw = document.getElementById("pw").value;
	var passOk = document.getElementById("passOk").value;
// 	alert(pw + ":::" + passOk);
	var frm = document.forms[0];
	var chkId = document.getElementById("chkval").value;		//아이디 검수 중복// 유효값
	var dong = document.getElementById("dongnumber").value;
	
	var telregExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	var farmtelregExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	var emailregExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
	
	var name = document.getElementById("name").value;
	var farmaddress = document.getElementById("farmaddress").value;
	var farmname= document.getElementById("farmname").value;
	var detailaddr = document.getElementById("sample6_address2").value;
	
	if(pw != passOk) {
		swal("회원가입 오류", "비밀번호를 확인해주세요");
		return false;
	} else if(chkId=="0") {			//화면에서 다 긁어오면 text 이다
		swal("회원가입 오류", "아이디 중복 체크를 해주세요");
		return false;
	} else if (grecaptcha.getResponse() == ""){ 
		alert("캡챠를 체크해야 합니다."); 
		return false; 
	} else if (!(telregExp.test($("#phone").val()))) {
		swal("회원가입 오류", "휴대전화를 -를 포함한 숫자만 입력하세요");
		return false;
	}  else if (!(emailregExp.test($("#email").val()))) {
		swal("회원가입 오류", "정확한 이메일 주소를 입력해주세요");
		return false;
	} else if (!isNaN(name)) {
		swal("회원가입 오류", "정확한 농장주 성함을 기제하세요");
		return false;
	} else if (!isNaN(farmaddress)) {
		swal("회원가입 오류", "정확한 농장 주소를 입력하세요");
		return false;
	} else if (!isNaN(farmname)) {
		swal("회원가입 오류", "정확한 농장명을 입력하세요");
		return false;
	} else {
		return true; 
	}
}



$(document).ready(function(){
	
	
	$("#email").keyup(function(){
		var inputLength = $(this).val().length;
		$("#email").html("");
		var email = "";
		email = $(this).val();
		
		if($("#email").val().length == "") {
			$("#resultemail").css("color", "white");
		} else if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(email)) {
			$("#resultemail").css("color", "red")
			$("#resultemail").html("이메일 형식이 맞지 않습니다.");			
		} else {
			$("#resultemail").css("color", "blue")
			$("#resultemail").html("사용가능한 이메일 입니다.");			
		}
	});
	
	
	$("#phone").keyup(function(){
		var inputLength = $(this).val().length;
		$("#phone").html("");
		var phone = "";
		phone = $(this).val();
		
		if($("#phone").val().length == "") {
			$("#resultphone").css("color","white");
		} else if(!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test(phone)) {
			$("#resultphone").css("color", "red");
			$("#resultphone").html("형식에 맞지 않는 전화번호입니다.");
		} else {
			$("#resultphone").css("color", "blue");
			$("#resultphone").html("사용가능한 전화번호입니다");
		}
	});
	
	
	
	
	//=======================================================
	$("#pw").keyup(function(){
		var inputLength = $(this).val().length;
		$("#pw").html("");
		var pw = "";
		pw = $(this).val();
		
		
	
		if($("#pw").val().length == "") {
			$("#resultpw1").css("color","white");
		} else if(!/^[a-zA-Z0-9]{10,15}$/.test(pw)){
			$("#resultpw1").css("color","red");
			$("#resultpw1").html("영어,숫자 조합으로 10~15자리만 사용가능.");			
		}  else {
			$("#resultpw1").css("color","blue");
			$("#resultpw1").html("비밀번호를 사용할 수 있습니다.");			
		}
		
		
		
	});
		
	//비밀번호 확인해주는 javascript
	$("#passOk").keyup(function(){
		var inputLength2 = $(this).val().length;
		$("#passOk").html("");
		var passOk = "";
		passOk = $(this).val();
		
		if(passOk.indexOf(" ")!=-1) {
			$("#resultpw2").css("color","red");
			$("#resultpw2").html("공백을 사용할 수 없습니다.");
		} else if($("#pw").val()!=$("#passOk").val()) {
			$("#resultpw2").css("color","red");
			$("#resultpw2").html("비밀번호가 일치하지 않습니다.");			
		} else if(inputLength2 == "") {
			$("#resultpw2").css("color","white");
		} else {
			$("#resultpw2").css("color","blue");
			$("#resultpw2").html("비밀번호가 일치합니다.");			
		}
	});
	//=======================================================

	$("#id").keyup(function(){
		var inputLength = $(this).val().length;
		$("#result").html("");
// 		alert(inputLength);
		var id = "";
		id = $(this).val();
		
		//이미 while문처럼 돌아가서 while을 쓰면 안된다.
		//그래서 while을 쓰면은 무한루프처럼 돌아간다. 그래서 if로 벗어나는 코드 작성
		//공백이면은 -1 이 나온다
		if(id.indexOf(" ")!=-1) {
			$("#result").css("color","red");
			$("#result").html("공백이 포함된 아이디는 사용할 수 없습니다.");

			document.getElementById("chkval").value=0;
			
		} else if(inputLength>=5) {
// 			alert("agkgalkengkolraen");
			$.ajax({
				type: "post",
				url: "./idDuplicateCheck.do",
				data: "id="+$(this).val(),
				//비동기식을 걸어주면 좋다. 동기식을 입력하면은
				//다른 정보들이 들어올 때 까지 기다려야한다.
				async:true,
				success:function(msg) {
					$("#result").html(msg);
					if(msg == "사용가능한 아이디") {
// 						alert("확인");
						//chkval 은 이중체크를 하기 위한 변수.
//						1이면은 아이디와 중복검사가 다 됨
						document.getElementById("chkval").value = "1";
						$("#result").css("color", "blue");
					} else {
						document.getElementById("chkval").value = "0";
						$("#result").css("color", "red");
					}
				}
			}); 
			//id의 
		} else if(inputLength == "") {
			$("#result").css("color","white");
			document.getElementById("chkval").value = "0";
		} else {
			//5자리 전까지는 ajax 처리 나머지는 java에서 처리 해서 속도가 빠르다.
			$("#result").css("color", "red");
			$("#result").html("5자 이상만 사용가능합니다.");
			document.getElementById("chkval").value = "0";
		}
	});
});
</script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<div id="container">

<!-- 		<div id="title"> -->
<!-- 			<img id="titleImg" alt="title" src="./images/walkingchick.png"> <br/> -->
<!-- 		</div> -->
		
		<!-- 이번에는 onsubmit -->
		<input type="hidden" id="chkval" value="0">
		
		<form action="./registUser.do" method="POST" id="frm" onsubmit="return check()">
		<div id="info">	
					<label><span style="display:inline-block; width:100px;">아이디<a style="color:red;">*</a></span></label>
					<input type="text" id="id" name="id" placeholder="아이디" required="required">
					
					<span id="result"></span><br/> 
					
					<label><span style="display:inline-block; width:105px;">비밀번호<a style="color:red;">*</a></span></label><input type="text" id="pw" name="pw" placeholder="비밀번호" required="required"><span id="resultpw1"></span>

					<div>
						<label><span style="display:inline-block; width:105px;">비밀번호 확인<a style="color:red;">*</a></span></label><input type="password" id="passOk" required="required" placeholder="비밀번호 확인" /><span id="resultpw2"></span>
					</div>
					
					<label><span style="display:inline-block; width:100px;">이메일<a style="color:red;">*</a></span></label>
					<input type="text" id="email" name="email" placeholder="ex) kkokio@gmail.com" required="required"><span id="resultemail"></span><br/>
					
					<label><span style="display:inline-block; width:100px;">핸드폰<a style="color:red;">*</a></span></label>
					<input type="text" id="phone" name="phone" placeholder="ex) 010-1234-5678" required="required"><span id="resultphone"></span><br/> 
					
					<label><span style="display:inline-block; width:105px;">주소<a style="color:red;">*</a></span></label><input type="text" id="sample6_postcode" placeholder="우편번호">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					<label><span style="display:inline-block; width:105px;"></span></label><input type="text" id="sample6_address" name="sample6_address" placeholder="주소" readonly="readonly"  required="required"><br/>
					<label><span style="display:inline-block; width:105px;"></span></label><input type="text" id="sample6_address2" name="sample6_address2" placeholder="상세주소"  required="required"><br/>
					
					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
					    function sample6_execDaumPostcode() {
					        new daum.Postcode({
					            oncomplete: function(data) {
					                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
					
					                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
					                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					                var fullAddr = ''; // 최종 주소 변수
					                var extraAddr = ''; // 조합형 주소 변수
					
					                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					                    fullAddr = data.roadAddress;
					
					                } else { // 사용자가 지번 주소를 선택했을 경우(J)
					                    fullAddr = data.jibunAddress;
					                }
					
					                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
					                if(data.userSelectedType === 'R'){
					                    //법정동명이 있을 경우 추가한다.
					                    if(data.bname !== ''){
					                        extraAddr += data.bname;
					                    }
					                    // 건물명이 있을 경우 추가한다.
					                    if(data.buildingName !== ''){
					                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					                    }
					                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
					                }
					
					                // 우편번호와 주소 정보를 해당 필드에 넣는다.
					                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
					                document.getElementById('sample6_address').value = fullAddr;
					
					                // 커서를 상세주소 필드로 이동한다.
					                document.getElementById('sample6_address2').focus();
					            }
					        }).open();
					    }
					</script>
					<hr size="2">
					<a>농장 정보</a><br/>
					<label>농장주<a style="color:red;">*</a>&nbsp;&nbsp;</label><input type="text" id="name" name="name" placeholder="ex) 이정은" required="required">
					<label>농장 주소<a style="color:red;">*</a><span style="display:inline-block; width:30px;"></span></label><input type="text" id="farmaddress" name="farmaddress" placeholder="ex) 경기도 용인시 수지구" required="required"><br/>

					<label>농장명<a style="color:red;">*</a>&nbsp;&nbsp;</label><input type="text" id="farmname" name="farmname" placeholder="ex) 우수한 육계농장 " required="required">
					<label>전화번호<a style="font-size: 10px; color:red;">(선택)</a>&nbsp;&nbsp;</label><span style="display:inline-block; width:5px;"></span><input type="text" id="farmtel" name="farmtel" placeholder="ex) 010-1234-5678"><br/>
					
					<label>동 갯수<a style="color:red;">*</a>&nbsp;</label><input type="number" min="1" max="9" id="dongnumber" name="dongnumber" placeholder="ex) 5" required="required">
					<div class="g-recaptcha" data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>
					<br><br>
						※ 해당 정보들은 육계관리 시스템 이외의 용도로 사용하지 않습니다.<br>
					<strong id="bottomstrong">
							약관과 개인정보 수집에 대해서 확인하였으며 이에 동의하십니까?
					</strong><br>
				<div id="button">
					<input class="btn btn-success" type="submit" value="동의하고 회원가입"/>
					<input class="btn btn-sm btn-primary btn-center" type="button" value="돌아가기" onclick="javascript:history.back(-1);"/>
				</div>
			</div>
		</form>
		
</div>	<!-- id="info" -->


</body>
</html>