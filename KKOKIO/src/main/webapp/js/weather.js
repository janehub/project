
//날씨 설정(주소코드설정)하는 모달 띄워주기
function openWeatherInfo(){
	$("#weatherModal").modal({backdrop: 'static'});
	getSiInfo();
}

//JSON에서 시 정보를 가져오는 함수
function getSiInfo(){
	$.getJSON("json/addresscode.json",function(data){
//		alert("작동");
//		alert(data.length);
//		alert(JSON.stringify(data));
		var html="";
		$.each(data,function(key,val){
//			alert(key);	//SICODE
			if(key=="SICODE"){
//				alert("작동");
				var list=val;		//[{},{}]
				for(var i=0;i<list.length;i++){
					var obj = list[i];		//{"code":"11","value":"서울특별시"}
//					alert(obj.code+" : "+obj.value);
					html+="<option value='"+obj.code+"'>"+obj.value+"</option>";
				}
				
			}
			
		});
		
		$("#siArea").html(html);
		
	});
}

function siSelect(){
	var siCode = $("#siArea option:selected").val();
	
	$.getJSON("json/addresscode.json",function(data){
//		alert("작동");
//		alert(data.length);
//		alert(JSON.stringify(data));
		var html="";
		$.each(data,function(key,val){
//			alert(key);	//SICODE, GUCODE
			if(key=="GUCODE"){
//				alert("작동");
				var list=val;		// [{"11":[{"code":"11110","value":"종로구"}]}]
				for(var i=0;i<list.length;i++){
					var gu = list[i];		//{"11":[{"code":"11110","value":"종로구"}]}
//					alert(JSON.stringify(gu));
					$.each(gu,function(gu_key,gu_val){
//						alert(gu_key);
//						alert(JSON.stringify(gu_val)); //[{"code":"11110","value":"종로구"}]
						if(gu_key==siCode){
//							alert(JSON.stringify(gu_val));
							var gu_list=gu_val;
							for(var i=0;i<gu_list.length;i++){	//{"code":"11110","value":"종로구"}
								var gu_item = gu_list[i];
//								alert(gu_item.code+":"+dong_item.value);
								html+="<option value='"+gu_item.code+"'>"+gu_item.value+"</option>";
							}
						}
					});
				}
			}
			
		});
		
		$("#guArea").html(html);
		
	});
}

function guSelect(){
	var guCode = $("#guArea option:selected").val();
//	alert(guCode);
	$.getJSON("json/addresscode.json",function(data){
//		alert("작동");
		var html="";
		$.each(data,function(key,val){
//			alert(key);	//SICODE, GUCODE,DONGCODE
			if(key=="DONGCODE"){
//				alert("작동");
				var list=val;	//[{"11110":[{"code":"1111051000","value":"청운동","x":"60","y":"127"}]}]
				for(var i=0;i<list.length;i++){
					var dong = list[i];		//{"11110":[{"code":"1111051000","value":"청운동","x":"60","y":"127"}]}
//					alert(JSON.stringify(dong));
					$.each(dong,function(dong_key,dong_val){
//						alert(dong_key);
//						alert(JSON.stringify(dong_val)); //[{"code":"1111051000","value":"청운동","x":"60","y":"127"}]
						if(dong_key==guCode){
//							alert(JSON.stringify(dong_val));
							var dong_list=dong_val;
							for(var i=0;i<dong_list.length;i++){	//{"code":"1111051000","value":"청운동","x":"60","y":"127"}
								var dong_item = dong_list[i];
//								alert(dong_item.code+":"+dong_item.value);
								html+="<option value='"+dong_item.code+"'>"+dong_item.value+"</option>";
							}
						}
					});
				}
			}
		});
		
		$("#dongArea").html(html);
	});
	
}

//동 선택
function dongSelect(){
	var guCode = $("#dongArea option:selected").val();
	
	var html="";
	html+=$("#siArea option:selected").text()+" ";
	html+=$("#guArea option:selected").text()+" ";
	html+=$("#dongArea option:selected").text();
	html+="<button onclick=\"setAddressInfo()\">날씨 설정하기</button>";
	$("#addressValue").html(html);
	
}

//시,구,동 모두 선택하고 난 후 뜨는 '날씨 설정하기'버튼
function setAddressInfo(){
	var guCode = $("#dongArea option:selected").val();
	
	var chk = confirm("선택하신 주소로 날씨 정보를 설정하시겠 습니까?");
	if(chk){
//		alert(guCode);
		//세션에 저장,DB에 저장
		$.ajax({
			url:"./updateAddressCode.do",
			data:{"addresscode":guCode},
			success:function(msg){
//				alert("작동");
				$('#weatherModal').modal('hide');
			}
		});
		
		showWeatherInfo(guCode);
	}
}


function showWeatherInfo(addressCode){
//	alert(addressCode);
	
		var url="./weatherOpen.do";
		
		$.ajax({
			type: "GET",
			url: url+"?code="+addressCode,
			datatype: "text",
			success: function(data){
//				alert(JSON.stringify(data));
				
				var temp=$.trim(data);
				var obj=JSON.parse(temp);

//				alert(obj.pubDate);
//				$("#pubDate").text(obj.pubDate);
				$("#wfkor").text(obj.wfkor);
				$("#temp").html(Math.round(obj.temp)+"<span id='celsius'>"+"\u2103"+"</span>");
				$("#pop").html(obj.pop+"&#37;");
				if(obj.tmx<0){
					$("#tmx").text("-");
				}else{
					$("#tmx").html(Math.round(obj.tmx)+"<span class='littleCelsius'>"+"\u2103"+"</span>");
				}
				
				if(obj.tmn<0){
					$("#tmn").text("-");
				}else{
					$("#tmn").html(Math.round(obj.tmn)+"<span class='littleCelsius'>"+"\u2103"+"</span>");
				}
				
				var weather_condition = obj.wfkor;
				
				if(weather_condition=="맑음"){
					$("#weather_img").attr("src","./image/sun.png");
				}else if(weather_condition=="구름 조금"){
					$("#weather_img").attr("src","./image/cloud_sun.png");
				}else if(weather_condition=="구름 많음"){
					$("#weather_img").attr("src","./image/cloud.png");
				}else if(weather_condition=="흐림"){
					$("#weather_img").attr("src","./image/cloud.png");
				}else if(weather_condition=="비"){
					$("#weather_img").attr("src","./image/rain.png");
				}else if(weather_condition=="눈/비"){
					$("#weather_img").attr("src","./image/etc.png");
				}else{
					$("#weather_img").attr("src","./image/snow.png");
				}
				
			}, error: function(){
				alert("정보를 불러오는데 실패했습니다.");
			}
		});
}