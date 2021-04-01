package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>구매 DTO</h1>
 * <p>설명
 * private String purchasecode; 	// 구매
 * private String passcode;			// 파스
 * private int illyung; 			// 일령
 * private String productcode; 		// 제품
 * private String productin; 		// 일자
 * private int productnum; 			// 수량
 * private String etc; 				// 비고
 * </p>
 * @author 박지연
 * @since 2018.11.15
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class PurchaseDto implements Serializable{

	private static final long serialVersionUID = 5597690031426891809L;
	
	private String purchasecode; 	// 구매
	private String passcode;		// 파스
	private int illyung; 			// 일령
	private String productcode; 	// 제품
	private String productin; 		// 일자
	private int productnum; 		// 수량
	private String etc; 			// 비고
	
	public PurchaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseDto(String purchasecode, String passcode, int illyung, String productcode, String productin,
			int productnum, String etc) {
		super();
		this.purchasecode = purchasecode;
		this.passcode = passcode;
		this.illyung = illyung;
		this.productcode = productcode;
		this.productin = productin;
		this.productnum = productnum;
		this.etc = etc;
	}

	public String getPurchasecode() {
		return purchasecode;
	}

	public void setPurchasecode(String purchasecode) {
		this.purchasecode = purchasecode;
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

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getProductin() {
		return productin;
	}

	public void setProductin(String productin) {
		this.productin = productin;
	}

	public int getProductnum() {
		return productnum;
	}

	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "PurchaseDto [purchasecode=" + purchasecode + ", passcode=" + passcode + ", illyung=" + illyung
				+ ", productcode=" + productcode + ", productin=" + productin + ", productnum=" + productnum + ", etc="
				+ etc + "]";
	}
	
	
}
