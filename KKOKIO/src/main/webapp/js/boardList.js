//글수정 모달
			function modify(noticecode_seq) {
//				alert("작동");
//				 ajax 실행
				 ajaxModify(noticecode_seq);
				$("#modify").modal();
			}
			//수정 ajax
			var ajaxModify = function(noticecode_seq) {
//				alert(noticecode_seq);
				$.ajax({
					url : "./noticemodify.do",
					type : "post",
					dataType : "json",
					data : {"noticecode_seq":noticecode_seq},
					success : function(j) {  
//						alert("작동");
						var html = "";
						html += "<input type='hidden' name='noticecode_seq' value='"+ j.noticecode_seq +"'>";
						html += "<input type='hidden' name='writer' value='"+ j.writer +"'>";
						html += "<div class='form-group'>";
						html += "	<label>글번호</label><br/>";
						html += "	<p class='form-control'>";
						html += "	<strong>"+j.noticecode_seq+"</strong>";
						html += "	</p>";
						html += "</div>";
						html += "<div class='form-group'>";
						html += "	<label>아이디</label><br/>";
						html += "	<p class='form-control'>"+j.writer+"</p>";
						html += "</div>";
						html += "<div class='form-group'>";
						html += "	<label>제목</label><br/>";
						html += "	<input type='text' class='form-control' id='moTitle'";
						html += "	name='title' value='"+j.title+"' required='required'> <br/>";
						html += "</div>";
						html += "<div class='form-group'>";
						html += "	<label>내용</label><br/>";
						html += "	<textarea rows='7' class='form-control' id='moContent' name='content' required='required'>"+j.content+"</textarea>";
						html += "</div>";
						html += "<div class='modal-footer'>";
						html += "	<input class='btn btn-sm btn-warning btn-center' type='button' value='글수정확인' onclick='update()'>";
						html += "	<input class='btn btn-sm btn-warning btn-center' type='reset' value='초기화'>";
						html += "	<button type='button' class='btn btn-sm btn-warning btn-center' data-dismiss='modal'>닫기</button>";
						html += "</div>";
						$("#frmModify").html(html);
					}
				});
				};
				function update() { 
					var frm = document.getElementById("frmModify");
					frm.action ='./modifyBoard.do';
					var title = $("#moTitle").val();
					var content =$("#moContent").val();
					if(title == ''){
						swal("글수정", "제목을 입력해 주세요");
					}else if(content == '') {
						swal("내용수정", "내용을 입력해 주세요")
					}else{
						frm.submit();
					}
				}