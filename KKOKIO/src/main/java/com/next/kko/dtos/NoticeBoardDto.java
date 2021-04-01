package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>공지사항 DTO</h1>
 * <p>설명
 * noticecode_seq: 시퀀스
 * writer		:작성자
 * title		:제목
 * content		:내용
 * regdate		:작성일
 * viewcount	:조회수	//비워놓기
 * </p>
 * @author 박지연
 * @since 2018.11.07
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class NoticeBoardDto implements Serializable{

	private static final long serialVersionUID = 3132868745755295480L;

	private String noticecode_seq;   //시퀀스        
	private String writer;           //작성자          
	private String title;            //제목           
	private String content;          //내용           
	private String regdate;          //작성일          
	private int viewcount;           //조회수	//비워놓기
	private String delflag; // 삭제여부
	public NoticeBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeBoardDto(String noticecode_seq, String writer, String title, String content, String regdate,
			int viewcount, String delflag) {
		super();
		this.noticecode_seq = noticecode_seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.viewcount = viewcount;
		this.delflag = delflag;
	}
	@Override
	public String toString() {
		return "NoticeBoardDto [noticecode_seq=" + noticecode_seq + ", writer=" + writer + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", viewcount=" + viewcount + ", delflag=" + delflag
				+ "]";
	}
	public String getNoticecode_seq() {
		return noticecode_seq;
	}
	public void setNoticecode_seq(String noticecode_seq) {
		this.noticecode_seq = noticecode_seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
}