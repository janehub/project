package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>제품 DTO</h1>
 * <p>설명
 * private String productcode;			//제품코드
 * private String pname;				//제품명
 * private int pprice;					//제품가격
 * private String pdetail;				//상세내용
 * private PurchaseDto purchase_Dto;	//조인을 위한 구매DTO 멤버변수
 * </p>
 * @author 박지연
 * @since 2018.11.15
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class ProductDto implements Serializable{

	private static final long serialVersionUID = -56891870727715581L;
	
	private String productcode;			//제품코드
	private String pname;				//제품명
	private int pprice;					//제품가격
	private String pdetail;				//상세내용
	private PurchaseDto purchase_Dto;	//조인을 위한 구매DTO 멤버변수
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(String productcode, String pname, int pprice, String pdetail, PurchaseDto purchase_Dto) {
		super();
		this.productcode = productcode;
		this.pname = pname;
		this.pprice = pprice;
		this.pdetail = pdetail;
		this.purchase_Dto = purchase_Dto;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getPdetail() {
		return pdetail;
	}

	public void setPdetail(String pdetail) {
		this.pdetail = pdetail;
	}

	public PurchaseDto getPurchase_Dto() {
		return purchase_Dto;
	}

	public void setPurchase_Dto(PurchaseDto purchase_Dto) {
		this.purchase_Dto = purchase_Dto;
	}

	@Override
	public String toString() {
		return "ProductDto [productcode=" + productcode + ", pname=" + pname + ", pprice=" + pprice + ", pdetail="
				+ pdetail + ", purchase_Dto=" + purchase_Dto + "]";
	}
	
	
}
