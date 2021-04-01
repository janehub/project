package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>이슈사항 DTO</h1>
 * <p>설명
 * private String issuecode;             //이슈사항CODE
 * private String recordilji_seq;        //육계일지작성SEQ
 * private String passcode;              //파스CODE
 * private int illyung;                  //일령
 * private String contentcode;           //내용CODE
 * private int actioncode;               //조치CODE
 * private String issuedetail;           //이슈사항
 * private String actiondetail;          //조치사항
 * private String issueetc;              //비고
 * private String actionresult;          //조치결과
 * </p>
 * @author 박지연
 * @since 2018.11.15
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class IssueDto implements Serializable{

	private static final long serialVersionUID = -752212312321197800L;
	
	private String issuecode;             //이슈사항CODE
	private String recordilji_seq;        //육계일지작성SEQ
	private String passcode;              //파스CODE
	private int illyung;                  //일령
	private String contentcode;           //내용CODE
	private int actioncode;               //조치CODE
	private String issuedetail;           //이슈사항
	private String actiondetail;          //조치사항
	private String issueetc;              //비고
	private String actionresult;          //조치결과
	
	public IssueDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIssuecode() {
		return issuecode;
	}

	public void setIssuecode(String issuecode) {
		this.issuecode = issuecode;
	}

	public String getRecordilji_seq() {
		return recordilji_seq;
	}

	public void setRecordilji_seq(String recordilji_seq) {
		this.recordilji_seq = recordilji_seq;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public int getIllyung() {
		return illyung;
	}

	public void setIllyung(int illyung) {
		this.illyung = illyung;
	}

	public String getContentcode() {
		return contentcode;
	}

	public void setContentcode(String contentcode) {
		this.contentcode = contentcode;
	}

	public int getActioncode() {
		return actioncode;
	}

	public void setActioncode(int actioncode) {
		this.actioncode = actioncode;
	}

	public String getIssuedetail() {
		return issuedetail;
	}

	public void setIssuedetail(String issuedetail) {
		this.issuedetail = issuedetail;
	}

	public String getActiondetail() {
		return actiondetail;
	}

	public void setActiondetail(String actiondetail) {
		this.actiondetail = actiondetail;
	}

	public String getIssueetc() {
		return issueetc;
	}

	public void setIssueetc(String issueetc) {
		this.issueetc = issueetc;
	}

	public String getActionresult() {
		return actionresult;
	}

	public void setActionresult(String actionresult) {
		this.actionresult = actionresult;
	}

	@Override
	public String toString() {
		return "IssueDto [issuecode=" + issuecode + ", recordilji_seq=" + recordilji_seq + ", passcode=" + passcode
				+ ", illyung=" + illyung + ", contentcode=" + contentcode + ", actioncode=" + actioncode
				+ ", issuedetail=" + issuedetail + ", actiondetail=" + actiondetail + ", issueetc=" + issueetc
				+ ", actionresult=" + actionresult + "]";
	}
	
	
}
