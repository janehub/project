package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>파스 DTO</h1>
 * <p>설명
 * private String passcode;			파스CODE
 * private String id;				아이디
 * private String startpass;		파스시작일
 * private String endpass;			파스종료일
 * private int incount;				입추수수
 * private int indum;				덤 수
 * private String intype;			품종
 * private String inbuhwa;			부화장
 * private String inactivity;		활발도
 * private String dongchicknum;		동별병아리수 '/'로 구분 ex) 10/20
 * </p>
 * <pre>
 * <b>History</b>
 * 		박지연, 버전 1.0, 2018.11.08 최초작성
 * <p>변경사항 : 멤버필드 dongchicknum 추가</p>
 * </pre>
 * @author 박지연
 * @since 2018.11.10
 * @version 1.1
 * @package com.next.kko.dtos
 */
public class PassDto implements Serializable{

	private static final long serialVersionUID = -5523315210469323774L;
	
	private String passcode;		//파스CODE
	private String id;				//아이디
	private String startpass;		//파스시작일
	private String endpass;			//파스종료일
	private int incount;			//입추수수
	private int indum;				//덤 수
	private String intype;			//품종
	private String inbuhwa;			//부화장
	private String inactivity;		//활발도
	private String dongchicknum;	//동별병아리수
	
	public PassDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PassDto(String passcode, String id, String startpass, String endpass, int incount, int indum, String intype,
			String inbuhwa, String inactivity, String dongchicknum) {
		super();
		this.passcode = passcode;
		this.id = id;
		this.startpass = startpass;
		this.endpass = endpass;
		this.incount = incount;
		this.indum = indum;
		this.intype = intype;
		this.inbuhwa = inbuhwa;
		this.inactivity = inactivity;
		this.dongchicknum = dongchicknum;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartpass() {
		return startpass;
	}

	public void setStartpass(String startpass) {
		this.startpass = startpass;
	}

	public String getEndpass() {
		return endpass;
	}

	public void setEndpass(String endpass) {
		this.endpass = endpass;
	}

	public int getIncount() {
		return incount;
	}

	public void setIncount(int incount) {
		this.incount = incount;
	}

	public int getIndum() {
		return indum;
	}

	public void setIndum(int indum) {
		this.indum = indum;
	}

	public String getIntype() {
		return intype;
	}

	public void setIntype(String intype) {
		this.intype = intype;
	}

	public String getInbuhwa() {
		return inbuhwa;
	}

	public void setInbuhwa(String inbuhwa) {
		this.inbuhwa = inbuhwa;
	}

	public String getInactivity() {
		return inactivity;
	}

	public void setInactivity(String inactivity) {
		this.inactivity = inactivity;
	}

	public String getDongchicknum() {
		return dongchicknum;
	}

	public void setDongchicknum(String dongchicknum) {
		this.dongchicknum = dongchicknum;
	}

	@Override
	public String toString() {
		return "PassDto [passcode=" + passcode + ", id=" + id + ", startpass=" + startpass + ", endpass=" + endpass
				+ ", incount=" + incount + ", indum=" + indum + ", intype=" + intype + ", inbuhwa=" + inbuhwa
				+ ", inactivity=" + inactivity + ", dongchicknum=" + dongchicknum + "]";
	}
	
	
}
