//그리드 페이징 
function initPage(gridId,totalSize,currentPage){
	// 변수로 그리드아이디, 총 데이터 수, 현재 페이지를 받는다
	
	if(currentPage==""){
		var currentPage = $('#'+gridId).getGridParam('page');
	}
	// 한 페이지에 보여줄 페이지 수 (ex:1 2 3 4 5)
	var pageCount = 10;
	// 그리드 데이터 전체의 페이지 수
	var totalPage = Math.ceil(totalSize/$('#'+gridId).getGridParam('rowNum'));
	// 전체 페이지 수를 한화면에 보여줄 페이지로 나눈다.
	var totalPageList = Math.ceil(totalPage/pageCount);
	// 페이지 리스트가 몇번째 리스트인지
	var pageList=Math.ceil(currentPage/pageCount);
	
//	alert("currentPage="+currentPage+"/ totalPage="+totalSize);
//	alert("pageCount="+pageCount+"/ pageList="+pageList);
	
	// 페이지 리스트가 1보다 작으면 1로 초기화
	if(pageList<1) pageList=1;
	// 페이지 리스트가 총 페이지 리스트보다 커지면 총 페이지 리스트로 설정
	if(pageList>totalPageList) pageList = totalPageList;
	// 시작 페이지
	var startPageList=((pageList-1)*pageCount)+1;
	// 끝 페이지
	var endPageList=startPageList+pageCount-1;
	
//	alert("startPageList="+startPageList+"/ endPageList="+endPageList);
	
	// 시작 페이지와 끝페이지가 1보다 작으면 1로 설정
	// 끝 페이지가 마지막 페이지보다 클 경우 마지막 페이지값으로 설정
	if(startPageList<1) startPageList=1;
	if(endPageList>totalPage) endPageList=totalPage;
	if(endPageList<1) endPageList=1;
	
	// 페이징 DIV에 넣어줄 태그 생성변수 
	var pageInner="";
	
	// 페이지 리스트가 1이나 데이터가 없을 경우 (링크 빼고 흐린 이미지로 변경)
	if(pageList<2){
		
		pageInner+="<img src='firstPage2.gif'>";
		pageInner+="<img src='prePage2.gif'>";
		
	}
	// 이전 페이지 리스트가 있을 경우 (링크넣고 뚜렷한 이미지로 변경)
	if(pageList>1){
		
		pageInner+="<a class='first' href='javascript:firstPage()'><img src='firstPage.gif'></a>";
		pageInner+="<a class='pre' href='javascript:prePage("+totalSize+")'><img src='prePage.gif'></a>";
		
	}
	// 페이지 숫자를 찍으며 태그생성 (현재페이지는 강조태그) 
	for(var i=startPageList; i<=endPageList; i++){
		if(i==currentPage){
			pageInner = pageInner +"<a href='javascript:goPage("+(i)+")' id='"+(i)+"'><strong>"+(i)+"</strong></a> ";
		}else{
			pageInner = pageInner +"<a href='javascript:goPage("+(i)+")' id='"+(i)+"'>"+(i)+"</a> ";
		}
		
	}
//	alert("총페이지 갯수"+totalPageList);
//	alert("현재페이지리스트 번호"+pageList);
	
	// 다음 페이지 리스트가 있을 경우
	if(totalPageList>pageList){
		
		pageInner+="<a class='next' href='javascript:nextPage("+totalSize+")'><img src='nextPage.gif'></a>";
		pageInner+="<a class='last' href='javascript:lastPage("+totalPage+")'><img src='lastPage.gif'></a>";
	}
	// 현재 페이지리스트가 마지막 페이지 리스트일 경우
	if(totalPageList==pageList){
		
		pageInner+="<img src='nextPage2.gif'>";
		pageInner+="<img src='lastPage2.gif'>";
	}   
	//alert(pageInner);
	// 페이징할 DIV태그에 우선 내용을 비우고 페이징 태그삽입
	$("#paginate").html("");
	$("#paginate").append(pageInner);
	
}

//----------------------------------------------------------------------------------------------------------------

// 그리드 첫페이지로 이동 
function firstPage(){
		
		$("#list").jqGrid('setGridParam', {
							page:1
						}).trigger("reloadGrid");
		
}
// 그리드 이전페이지 이동 
function prePage(totalSize){
		
		var currentPage = $('#list').getGridParam('page');
		var pageCount = 10;
		
		currentPage-=pageCount;
		pageList=Math.ceil(currentPage/pageCount);
		currentPage=(pageList-1)*pageCount+pageCount;
		
		initPage("list",totalSize,currentPage);
		
		$("#list").jqGrid('setGridParam', {
							page:currentPage
						}).trigger("reloadGrid");
		
}
// 그리드 다음페이지 이동 	
function nextPage(totalSize){
		
		var currentPage = $('#list').getGridParam('page');
		var pageCount = 10;
		
		currentPage+=pageCount;
		pageList=Math.ceil(currentPage/pageCount);
		currentPage=(pageList-1)*pageCount+1;
		
		initPage("list",totalSize,currentPage);
		
		$("#list").jqGrid('setGridParam', {
							page:currentPage
						}).trigger("reloadGrid");
}
// 그리드 마지막페이지 이동 
function lastPage(totalSize){
		
		$("#list").jqGrid('setGridParam', {
							page:totalSize
						}).trigger("reloadGrid");
}
// 그리드 페이지 이동 
function goPage(num){

//		$("#list").jqGrid("clearGridData", true);
		
		$("#list").jqGrid('setGridParam', {
							page:num
						}).trigger("reloadGrid");
		
}


function gridReload() {
$('#list').trigger('reloadGrid');
}
