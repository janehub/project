package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>회원 DTO</h1>
 * <p>설명
 * private String id;			// 아이디
 * private String pw;			// 비밀번호
 * private String email;		// 이메일
 * private String phone;		// 연락처
 * private String address;		// 주소
 * private String name;		// 농장주
 * private String farmname;	// 농장명
 * private String farmaddress;	// 농장주소
 * private String farmtel;		// 농장전화번호
 * private int dongnumber; 		// 동수
 * private String recentdate;	// 최근 접속일
 * private String auth;		// 권한
 * </p>
 * @author 박지연
 * @since 2018.11.15
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class AccountDto implements Serializable{
	
	private static final long serialVersionUID = -1816088187693536270L;

	private String id;			// 아이디
	private String pw;			// 비밀번호
	private String email;		// 이메일
	private String phone;		// 연락처
	private String address;		// 주소
	private String name;		// 농장주
	private String farmname;	// 농장명
	private String farmaddress;	// 농장주소
	private String farmtel;		// 농장전화번호
	private int dongnumber; 		// 동수
	private String recentdate;	// 최근 접속일
	private String auth;		// 권한
	
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDto(String id, String pw, String email, String phone, String address, String name, String farmname,
			String farmaddress, String farmtel, int dongnumber, String recentdate, String auth) {
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.name = name;
		this.farmname = farmname;
		this.farmaddress = farmaddress;
		this.farmtel = farmtel;
		this.dongnumber = dongnumber;
		this.recentdate = recentdate;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFarmname() {
		return farmname;
	}

	public void setFarmname(String farmname) {
		this.farmname = farmname;
	}

	public String getFarmaddress() {
		return farmaddress;
	}

	public void setFarmaddress(String farmaddress) {
		this.farmaddress = farmaddress;
	}

	public String getFarmtel() {
		return farmtel;
	}

	public void setFarmtel(String farmtel) {
		this.farmtel = farmtel;
	}

	public int getdongnumber() {
		return dongnumber;
	}

	public void setdongnumber(int dongnumber) {
		this.dongnumber = dongnumber;
	}

	public String getRecentdate() {
		return recentdate;
	}

	public void setRecentdate(String recentdate) {
		this.recentdate = recentdate;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", pw=" + pw + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", name=" + name + ", farmname=" + farmname + ", farmaddress=" + farmaddress + ", farmtel=" + farmtel
				+ ", dongnumber=" + dongnumber + ", recentdate=" + recentdate + ", auth=" + auth + "]";
	}
	
}
