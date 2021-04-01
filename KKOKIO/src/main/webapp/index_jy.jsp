<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쿼리테스트_지연</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<script type="text/javascript">
function insertExcelPass(){
	alert("insertExcelPass");
	var json= [{"id":"ONG001","startpass":20180903,"endpass":20181016,"incount":37360,"dongchicknum":"18760/18600","indum":1121,"intype":"하바드","inactivity":"상"}];
	
	$.ajax({ 
	    url:"./insertExcelPass.do",
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(json), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser  
	     processData:false, //To avoid making query String instead of JSON
	     success: function(resposeJsonObject){
	        // Success Action
	    	 alert("작동");
	    }
	});
}

function insertExcelIlji(){
	alert("insertExcelIlji");
	
// 	recordilji_seq, passcode, illyung, hightemp, lowtemp, recorddate, recordtime
// 	medicine, weathercon, etc, deathcount, weight, buildtemp, distinctdong
	
	
	var json=//[{"illyung":1}];
		[{"recorddate":20180903,"illyung":1,"distinctdong":"1/2","buildtemp":30,"etc":"좋아요."},
		{"recorddate":20180903,"illyung":1,"distinctdong":1,"deathcount":7},
		{"recorddate":20180904,"illyung":2,"distinctdong":"1/2","deathcount":9,"medicine":"소독함","etc":"재미짐"}];

	$.ajax({ 
	    url:"./insertExcelIlji.do",
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(json), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser  
	     processData:false, //To avoid making query String instead of JSON
	     success: function(resposeJsonObject){
	        // Success Action
	    	 alert("작동");
	    }
	});
}
</script>
<body>

<h1>날씨 -주소코드</h1>
	<h2>회원가입시 입력하기</h2>
		<button onclick="location.href='./insertAddressCode.do?addresscode=5013061000'">입력</button>
	<h2>모달창에서 수정하기</h2>
		<button onclick="location.href='./updateAddressCode.do?addresscode=4888031000'">수정</button>
	<h2>로그인시 가져오기</h2>
		<button onclick="location.href='./getAddressCode.do'">가져오기</button>
		
<h1>엑셀 업로드/다운로드</h1>	
	<h2>업로드된 엑셀파일의 내용을 DB에 입력하는 기능</h2>
<!-- 	passcode -->
<!-- 	id -->
<!-- 	startpass -->
<!-- 	endpass -->
<!-- 	incount -->
<!-- 	indum -->
<!-- 	intype -->
<!-- 	inbuhwa -->
<!-- 	inactivity -->
<!-- dongchicknum -->

<!-- 파스시작일	  파스종료일	  입추수수	동별 수수 (/로 구분)	덤 수	품종	부화장	활발도 -->
	<h3>첫번째 시트 - 파스 정보</h3>
		
		 <button onclick="insertExcelPass()">첫번째 시트 - 파스 정보</button>
		 
	<h3>두번째 시트 - 일지 정보</h3>
<!--  * 	private String recordilji_seq;	                  육계일지작성SEQ -->
<!--  * 	private String passcode;            	파스CODE -->
<!--  * 	private int illyung;                    일령 -->
<!--  * 	private double hightemp;                최고온도(기상청) -->
<!--  * 	private double lowtemp;                 최저온도(기상청) -->
<!--  * 	private String recorddate;              작성날짜 -->
<!--  * 	private String recordtime;              작성시간 -->
<!--  * 	private String medicine;                약품 -->
<!--  * 	private String weathercon;              날씨상태 -->
<!--  * 	private String etc;                     시간별 비고 -->
<!--  * 	private int deathcount;                 폐사수 -->
<!--  * 	private double weight;                  중량 -->
<!--  * 	private double buildtemp;               사육장 온도 -->
<!--  * 	private String distinctdong;            동별구분  '/'로 구분 ex) 1/3/4 -->

<!-- 날짜	일령	동별구분	폐사수	사육장온도	중량	약품	날씨상태	비고		 -->
	
		<button onclick="insertExcelIlji()">두번째 시트 - 일지 정보</button>
	
	<h2>선택한 파스의 정보를 json으로 마이그레이션하는 기능</h2>
	<h3>첫번째 시트 - 파스 정보</h3>
		<button onclick="location.href='./getExcelPass.do?passcode=ONG00120180903'">첫번째 시트 - 파스 정보</button>
	<h3>두번째 시트 - 일지 정보</h3>
		<button onclick="location.href='./getExcelIlji.do?passcode=ONG00120180903'">두번째 시트 - 일지 정보</button>

<h1>게시판</h1>	
	<h2>공지사항을 입력하는 기능</h2>
<!--  * writer		:작성자 -->
<!--  * title		:제목 -->
<!--  * content		:내용 -->
<!--  * regdate		:작성일 -->
<!--  * viewcount	:조회수 -->
<!--  * delflag		:삭제여부 -->
	<form action="./insertBoard.do" method="post">
		<input type="text" value="관리자1" name="writer">
		<input type="text" value="공지사항이요" name="title">
		<input type="text" value="공지합니다." name="content">
		<input type="submit" value="전송">
	</form>
	
	<h2>공지사항을 수정하는 기능</h2>
		<form action="./modifyBoard.do" method="post">
			<input type="text" value="NOTICE11" name="noticecode_seq">
			<input type="text" value="공지사항이요_수정" name="title">
			<input type="text" value="공지합니다_수정." name="content">
			<input type="submit" value="전송">
		</form>
	
	<h2>공지사항을 전체조회하는 기능</h2>
		<input type="button" onclick='location.href="./getAllboard.do"' value="조회하기">

	<h2>공지사항을 상세조회하는 기능</h2>
		<input type="button" onclick='location.href="./getOneBoard.do?seq=NOTICE11"' value="조회하기">

	<h2>공지사항 삭제하는 기능</h2>
		<input type="button" onclick='location.href="./deleteOneBoard.do?seq=NOTICE11"' value="공지사항 하나 삭제">

<h1>이슈사항</h1>
                         
	<h2>이슈사항 등록</h2>
		<form action="./insertIssue.do" method="post">
		<input type="text" value="ILJI46" name="recordilji_seq">
		<input type="text" value="ONG00120180903" name="passcode">
		<input type="number" value="1" name="illyung">
		<input type="text" value="문제 발생 ㅜ" name="issuedetail">
		<input type="text" value="" name="issueetc">
		<input type="submit" value="전송">
		</form>
	
	<h2>이슈사항 수정</h2>
		<form action="./modifyIssue.do" method="post">
		<input type="text" value="IS33" name="issuecode">
		<input type="text" value="문제 발생_수정" name="issuedetail">
		<input type="text" value="" name="issueetc">
		<input type="submit" value="전송">
		</form>	
	
	<h2>이슈사항 삭제</h2>
		<form action="./deleteIssue.do" method="get">
		<input type="text" value="IS33" name="contentcode">
		<input type="submit" value="전송">
		</form>	
	
		
	<h2>조치사항 등록</h2>
		<form action="./InsertAction.do" method="post">
		<input type="text" value="ILJI46" name="recordilji_seq">
		<input type="text" value="ONG00120180903" name="passcode">
		<input type="number" value="1" name="illyung">
		<input type="text" value="IS33" name="contentcode">
		<input type="text" value="조치사항은 이러이러함" name="actiondetail">
		<input type="text" value="잘안됨 ㅜ" name="issueetc">
		<input type="submit" value="전송">
		</form>	
	<h2>조치사항 수정</h2>
		<form action="./modifyAction.do" method="post">
		<input type="text" value="IS35" name="issuecode">
		<input type="text" value="조치사항_수정" name="actiondetail">
		<input type="text" value="잘안됨 ㅜ_2" name="issueetc">
		<input type="submit" value="전송">
		</form>	
	<h2>조치사항 삭제</h2>
		<form action="./deleteAction.do" method="get">
		<input type="text" value="IS35" name="issuecode">
		<input type="submit" value="전송">
		</form>	
	<h2>이슈사항 / 조치사항 전체조회 </h2>
		<input type="button" onclick='location.href="./getAllissue.do"' value="조회하기">
	<h2>이슈사항 /조치사항 상세조회</h2>
		<input type="button" onclick='location.href="./getOneIssue.do?issuecode=IS33"' value="조회하기">
	<h2>이슈사항/조치사항 결과 수정</h2>
		<form action="./modifyActionResult.do" method="get">
		<input type="text" value="IS33" name="contentcode">
		<input type="submit" value="전송">
		</form>	

</body>
</html>