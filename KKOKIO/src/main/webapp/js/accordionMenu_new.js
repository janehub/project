/*아코디언 메뉴*/
$(function() {
	
	$("#accordion").accordion();
	$("#iljiTabs").tabs();
	$(".excelBtn").button();
	$( "#datepicker" ).datepicker({
		inline: true
	});
	
	$("#accordion p").eq(0).show();
	$("#accordion p").eq(0).siblings("p").hide();
	$("#accordion h3").eq(0).addClass("active");
	
	$("#accordion h3").click(function(){
		$(this).next().stop().slideToggle();
		$(this).next().siblings("p").stop().slideUp();
		$(this).toggleClass("active");
		
		$(this).siblings("h3").each(function(){
			if($(this).hasClass("active")){
				$(this).removeClass("active");
			}
		});
	});
});

//엑셀파일 업로드/다운로드 페이지로 이동
function excelPage(){
	location.href="./excelPage.do";
}
