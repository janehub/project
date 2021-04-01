//var json_m;
//$(function(){
//	var data = [
//  {"name":"John", "city": "Seattle"},
//  {"name":"Mike", "city": "Los Angeles"},
//  {"name":"Zach", "city": "New York"}
//	];
//	
//	var data2 = [{"firstName":"Mayuresh","middleName":"Dinkar ","lastName":"Joshi","age":24},
//		{"firstName":"Arun","middleName":"Vikas","lastName":"Pathak","age":25},
//		{"firstName":"Narendra","middleName":"Damodardas","lastName":"Modi","age":50}];
//
//	
//	var json_m={"first":data, "second":data2};
//	
//	$("#jsonTest").text(JSON.stringify(json_m));
//});

$(function(){
	$("#excelDownloadForm").hide();
	
	$("#sendJson").hide();
});

function excelUploadPage(){
	$("#excelDownloadForm").hide();
	$("#excelUploadForm").show();
	
	$("#sendJson").hide();
}

function excelDownloadPage(){
	$.ajax({
		url:"./passList.do",
		type:"get",
		dataType:"json",
		success:function(list){
// 			alert(JSON.stringify(list));
// 			$("#passlist").text(JSON.stringify(list[0]));
			
			var html="";
			html+="<select name='pass' id='pass'>";
			for(var i=0 ;i<list.length;i++){
				html+="<option value='"+list[i].passcode+"'>"+list[i].passcode+"</option>";
			}
			html+="</select>";
			$("#passlist").html(html);
		}
	});
	
	$("#excelUploadForm").hide();
	$("#excelDownloadForm").show();	
	
}

function downloadExcelTemplate(){
	alert("엑셀파일 서식을 다운로드합니다.");
	
	location.href="./downloadExcelTemplate.do";
}

function download(){
	var passcode=document.getElementById("pass").value;
	//alert(pass.value);
	$.ajax({
		url:"./getExcel.do",
		type:"get",
		dataType:"json",
		data:{"passcode":passcode},
		success:function(msg){
//			$("#jsonfromDB").text(JSON.stringify(msg[0].excelpass)+"**"+JSON.stringify(msg[0].excelilji));

			  //파스시작일	파스종료일	입추수수	동별 수수(/로 구분)	덤 수	품종	부화장	활발도
			   var excelpass = JSON.stringify(msg[0].excelpass);
			   excelpass = excelpass.replace('startpass','파스시작일');
			   excelpass = excelpass.replace('endpass','파스종료일');
			   excelpass = excelpass.replace('incount','입추수수');
			   excelpass = excelpass.replace('dongchicknum','동별 수수(/로 구분)');
			   excelpass = excelpass.replace('indum','덤 수');
			   excelpass = excelpass.replace('intype','품종');
			   excelpass = excelpass.replace('inbuhwa','부화장');
			   excelpass = excelpass.replace('inactivity','활발도');
			   excelpass = JSON.parse("["+excelpass+"]");
			   
			 //날짜	일령	동별구분(/로 구분)	폐사수	사육장온도	중량	약품	날씨상태	비고
			   var excelilji = msg[0].excelilji;
				  var parsedArr = JSON.stringify(msg[0].excelilji).split('}');
				  for(var i=0;i<parsedArr.length;i++){
					 // console.log(parsedArr[i]);
					  var replacingS = parsedArr[i].replace('recorddate','날짜');
					  replacingS = replacingS.replace('illyung','일령');
					  replacingS = replacingS.replace('distinctdong','동별구분(/로 구분)');
					  replacingS = replacingS.replace('deathcount','폐사수');
					  replacingS = replacingS.replace('buildtemp','사육장온도');
					  replacingS = replacingS.replace('weight','중량');
					  replacingS = replacingS.replace('medicine','약품');
					  replacingS = replacingS.replace('weathercon','날씨상태');
					  replacingS = replacingS.replace('etc','비고');
					  
					  parsedArr[i] = replacingS;
					  console.log(parsedArr[i]);
				  }
				  
				  excelilji = parsedArr.join("}");
				  
				//JSON obj로 만들기 
				  excelilji = JSON.parse(excelilji);
				  
				 //hightemp, lowtemp 삭제
				  for(var i=0;i<excelilji.length;i++){
					  delete excelilji[i]["hightemp"];
					  delete excelilji[i]["lowtemp"];
				  }
			   
//				  alert(JSON.stringify(excelilji));
				  
				  
			   excelDownload(passcode,excelpass,excelilji);
		}
	});
	
}


function excelDownload(passcode,excelpass,excelilji) {
//	alert("excelDownload");
    	var wb = XLSX.utils.book_new();
    	wb.Props = {
    		Title : "SheetJS Smart Farm record",
    		Subject : "Smart Farm record",
    		Author : "KKOKIO",
    		CreatedDate : new Date()
    	};

    	wb.SheetNames.push("파스정보");
    	var ws = XLSX.utils.json_to_sheet(excelpass, 
    			{header:['파스시작일','파스종료일','입추수수'	,'동별 수수(/로 구분)','덤 수','품종','부화장','활발도']});
    	wb.Sheets["파스정보"] = ws;
    	var wbout = XLSX.write(wb, {
    		bookType : 'xlsx',
    		type : 'binary'
    	});
    	
    	wb.SheetNames.push("육계일지");
    	var ws = XLSX.utils.json_to_sheet(excelilji, {header:['날짜',
		  '일령','동별구분(/로 구분)','폐사수','사육장온도','중량',
		  '약품','날씨상태','비고']});
    	
    	wb.Sheets["육계일지"] = ws;
    	var wbout = XLSX.write(wb, {
    		bookType : 'xlsx',
    		type : 'binary'
    	});
    	
    	
    	function s2ab(s) {

    		var buf = new ArrayBuffer(s.length);
    		var view = new Uint8Array(buf);
    		for (var i = 0; i < s.length; i++)
    			view[i] = s.charCodeAt(i) & 0xFF;
    		return buf;

    	}
    	saveAs(new Blob([ s2ab(wbout) ], {
    		type : "application/octet-stream"
    	}), '육계일지_'+passcode+'.xlsx');
 }





var json;	//엑셀파일 첫번째 시트 - 파스정보
var json2;	//엑셀파일 두번째 시트 - 육계일지정보

var result;	//JSON -> string
var result_s2;

var jsonObj;	//string -> JSON
var jsonObj_2;


function fileUpload(){
//	alert("클릭");
	var frm = document.getElementById("uploadFrm");
	var formData = new FormData(frm);
	
	$.ajax({ 
	    url:"./fileUpload.do",
	    type:"POST",
	    	processData:false,
	    	contentType: false,
	    data: formData,
	    cache: false,
	     success: function(msg){
	    	 //alert(msg);
	    	 if(msg=="file_not_selected"){
	    		 $("#uploadResult").text("파일을 선택해 주세요.");
	    		 $("#uploadResult").css("color","red");
	    		 clearContent();
	    	 }else if(msg=="not_xlsx_file"){
	    		 $("#uploadResult").text("엑셀파일을 선택해 주세요.");
	    		 $("#uploadResult").css("color","red");
	    		 clearContent();
	    	 }else{
	    		 $("#uploadResult").text("파일 업로드에 성공했습니다.");
	    		 $("#uploadResult").css("color","blue");
//	    		 alert(msg);
	    		 url=msg;
	    		 excelToJson(msg);
	    	 }
	    }
	});

}



var excelToJson=function(url){
	/* set up XMLHttpRequest */
		
	// AJAX 요청을 보내는 XMLHttpRequest 객체를 생성
	//이 객체는 window 객체 아래에 위치
		var oReq = new XMLHttpRequest();
	//open 메소드로 요청을 열고 주소와 HTTP 메소드를 설정한 뒤, send 메소드로 요청을 서버로 보냄
		oReq.open("GET", url, true);
	//arraybuffer: responseType이 binary data.
		oReq.responseType = "arraybuffer";
	    	
	//요청에 대한 응답을 받는 이벤트 리스너
		oReq.onload = function(e) {	//처리할 함수
			var arraybuffer = oReq.response;
		
		/* convert data to binary string */
		  var data = new Uint8Array(arraybuffer);
		  var arr = new Array();
		  for(var i = 0; i != data.length; i++)
			  arr[i] = String.fromCharCode(data[i]);
		  var bstr = arr.join("");
		
		  /* Call XLSX */
		  var workbook = XLSX.read(bstr, {type:"binary"});
		
		  /* DO SOMETHING WITH workbook HERE */
		  var first_sheet_name = workbook.SheetNames[0];
		  var second_sheet_name = workbook.SheetNames[1];
		 
		  /* Get worksheet */
		  var worksheet = workbook.Sheets[first_sheet_name];
		  console.log(XLSX.utils.sheet_to_json(worksheet,{raw:true}));

		  var worksheet2 = workbook.Sheets[second_sheet_name];
		  console.log(XLSX.utils.sheet_to_json(worksheet2,{raw:true}));
		  
		  json =XLSX.utils.sheet_to_json(worksheet,{raw:true});
		  json2 =XLSX.utils.sheet_to_json(worksheet2,{raw:true});

		  
//유효성 검사- 내용 없음
		  if(JSON.stringify(json)=="[]"||JSON.stringify(json)==null){
			  $("#uploadResult").text("첫번째 시트의 파일 내용이 없습니다.");
	    		 $("#uploadResult").css("color","red");
	    		 clearContent();
			  return;
		  }
		
		  if(JSON.stringify(json2)=="[]"||JSON.stringify(json2)==null){
			  $("#uploadResult").text("두번째 시트의 파일 내용이 없습니다.");
	    		 $("#uploadResult").css("color","red");
	    		 clearContent();
			  return;
		  }

		  
//유효성 검사- 엑셀 헤더 
		  var firstSheetHeader = ['파스시작일','파스종료일','입추수수','동별 수수(/로 구분)','덤 수','품종','부화장','활발도'];

		  for(var k in json[0]){
//			  if(firstSheetHeader.includes((k).replace(/(\s*)/g,""))==false){
			  if(firstSheetHeader.includes(k)==false){
				  $("#uploadResult").text("잘못된 엑셀파일서식입니다.-첫번째 시트");
		    		 $("#uploadResult").css("color","red");
		    		 clearContent();
				  return;
			  }
		  }

		  var secondSheetHeader = ['날짜','일령','동별구분(/로 구분)','폐사수','사육장온도','중량','약품','날씨상태','비고'];
		  for(var i in json2){
			  for(var k in json2[i]){
				  if(secondSheetHeader.includes(k)==false){
					  $("#uploadResult").text("잘못된 엑셀파일서식입니다. -두번째 시트");
			    		 $("#uploadResult").css("color","red");
			    		 clearContent();
					  return;
				  }
			  }
		  }
		  

		  
		  //파스시작일	파스종료일	입추수수	동별 수수(/로 구분)	덤 수	품종	부화장	활발도
		   var json_s = JSON.stringify(json);
		   result = json_s.replace('파스시작일', 'startpass');
		   result = result.replace('파스종료일', 'endpass');
		   result = result.replace('입추수수', 'incount');
		   result = result.replace('동별 수수(/로 구분)', 'dongchicknum');
		   result = result.replace('덤 수', 'indum');
		   result = result.replace('품종', 'intype');
		   result = result.replace('부화장', 'inbuhwa');
		   result = result.replace('활발도', 'inactivity');
		   
		 //JSON obj로 만들기 
		  jsonObj = JSON.parse(result);
		console.log("--------------JSON.parse(obj) result ----------");
		console.log(jsonObj);

		var pass_html="";
		
//		pass_html+="<p>"+"파스시작일: "+jsonObj[0].startpass+"<br>"+
//		"파스종료일: "+jsonObj[0].endpass+"<br>"+
//		"입추수수: "+jsonObj[0].incount+"<br>"+
//		"동별 수수(/로 구분): "+jsonObj[0].dongchicknum+"<br>"+
//		"덤 수: "+jsonObj[0].indum+"<br>"+
//		"품종: "+jsonObj[0].intype+"<br>"+
//		"부화장: "+jsonObj[0].inbuhwa+"<br>"+
//		"활발도: "+jsonObj[0].inactivity
//		+"</p>"
		
		//파스시작일, 파스종료일
		pass_html+="<div id='excelTablesDiv'>";
		pass_html+="<table id='excelTable01'><tr><th class='exT1C1'>파스시작일</th><th class='exT1C2'>파스종료일</th></tr>";
		pass_html+="<tr><td>"+jsonObj[0].startpass+"</td><td>";
		if(jsonObj[0].endpass==undefined){
			pass_html+="-";
		}else{
			pass_html+=jsonObj[0].endpass;
		}
		pass_html+="</td></tr></table>";
		
		//입추수수, 덤 수, 동별수수
		pass_html+="<table id='excelTable02'><tr><th class='exT2C1'>입추수수</th><th class='exT2C2'>덤 수</th><th class='exT2C3'>동별 수수</th></tr>";
		pass_html+="<tr><td>"+jsonObj[0].incount+"</td><td>";
		pass_html+=jsonObj[0].indum+"</td><td>";
		pass_html+=jsonObj[0].dongchicknum;
		pass_html+="</td></tr></table>";
		
		//품종, 부화장, 활발도
		pass_html+="<table id='excelTable03'><tr><th class='exT3C1'>품종</th><th class='exT3C2'>부화장</th><th class='exT3C3'>활발도</th></tr>";
		pass_html+="<tr><td>"+jsonObj[0].intype+"</td><td>";
		pass_html+=jsonObj[0].inbuhwa+"</td><td>";
		pass_html+=jsonObj[0].inactivity;
		pass_html+="</td></tr></table>";
		pass_html+="</div>";
			
		//화면에 출력
		$("#resultPass").html(pass_html);
		
		
		  //날짜	일령	동별구분(/로 구분)	폐사수	사육장온도	중량	약품	날씨상태	비고
		  var parsedArr = JSON.stringify(json2).split('}');
		  for(var i=0;i<parsedArr.length;i++){
			 // console.log(parsedArr[i]);
			  var replacingS = parsedArr[i].replace('날짜', 'recorddate');
			  replacingS = replacingS.replace('일령', 'illyung');
			  replacingS = replacingS.replace('동별구분(/로 구분)', 'distinctdong');
			  replacingS = replacingS.replace('폐사수', 'deathcount');
			  replacingS = replacingS.replace('사육장온도', 'buildtemp');
			  replacingS = replacingS.replace('중량', 'weight');
			  replacingS = replacingS.replace('약품', 'medicine');
			  replacingS = replacingS.replace('날씨상태', 'weathercon');
			  replacingS = replacingS.replace('비고', 'etc');
			  
			  parsedArr[i] = replacingS;
			  console.log(parsedArr[i]);
		  }
		  
		  result_s2 = parsedArr.join("}");
		  
		//JSON obj로 만들기 
		  jsonObj_2 = JSON.parse(result_s2);
		  
		  //alert(result_s2);
		  
		  
//jqGrid
//		var mydata = [
//			{temp_date:"2018",distinct_dong:"",illyung:"1",medicine:"",weathercon:"",etc:"",deathcount:"",weight:"",buildtemp:""}];
		  
		//jqGrid껍데기 생성
		    $("#list").jqGrid({
		    	data: jsonObj_2,
		    	datatype: "local",
		        colNames:['날짜','일령','동별구분','폐사수','사육장온도','중량','약품','날씨상태','비고'
		        	],
		        //컬럼모델
		        colModel:[
		            {name:'recorddate', align:'center' ,width:100},
		            {name:'illyung',width:57},
		            {name:'distinctdong',width:57},
		            {name:'deathcount',width:50},    
		            {name:'buildtemp',width:50},    
		            {name:'weight',width:60},    
		            {name:'medicine',width:60},    
		            {name:'weathercon',width:60},    
		            {name:'etc',width:100}    
		        ],
		        //그리드타이틀
		        caption: "육계 일지",
		        //연번
		        rownumbers:true,
		        //정렬기준
		        sortname:'recorddate',
		        //
		        width: 720,
		        
			    rowNum:5,
			    rowList:[5,10,15],
		        pager:'#pager',
		        sortorder:"asc"
		        
		    });
		     
		    // 스크립트 변수에 담겨있는 json데이터의 길이만큼 
		    for(var i=0;i<=gridData.length;i++){
		            //jqgrid의 addRowData를 이용하여 각각의 row에 gridData변수의 데이터를 add한다
		            $("#list").jqGrid('addRowData',i+1,gridData[i]);
		    }		  
		  
		    
		  
		  
		}
		
		
		//send 메소드로 요청을 서버로 보냅니다.
		oReq.send();
	    
		$("#gbox_list").show();
		$("#sendJson").show();
		
	}






function sendJson(){

	var json_m=[{"excelpass":jsonObj, "excelilji":jsonObj_2}];
	
//	alert("json: "+JSON.stringify(json_m));
	
	$.ajax({ 
	    url:"./insertExcel.do",
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(json_m),
	    	//JSON.stringify(json), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser  
	     processData:false, //To avoid making query String instead of JSON
	     success: function(resposeJsonObject){
	        // Success Action
	    	 alert("작동");
	    }
	});
	
}

function clearContent(){
	$("#resultPass").html("");
	$("#gbox_list").hide();
	$("#sendJson").hide();
}
