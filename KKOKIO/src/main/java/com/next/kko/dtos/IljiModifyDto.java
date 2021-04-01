package com.next.kko.dtos;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <h1>일지수정 DTO</h1>
 * <p>설명
 * private String[] deathcount_seq;			 //폐사수 SEQ, 길이 : 동 수
 * private String[] deathcount_value;        //폐사수 값, 길이 : 동 수
 * private String[] weight_seq;              //중량 SEQ, 길이 : 동 수
 * private String[] weight_value;            //중량 값, 길이 : 동 수
 * private String[] buildtemp_seq;           //사육장 온도 SEQ
 * private String[] buildtemp_value;         //사육장 온도 값
 * private String[] etc_seq;                 //비고 SEQ
 * private String[] etc_value;               //비고 값
 * private String[] medicine_seq;            //약품 SEQ
 * private String[] medicine_value;          //약품 값
 * </p>
 * @author 박지연
 * @since 2018.12.04
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class IljiModifyDto implements Serializable{

	private static final long serialVersionUID = 4642051771955566372L;
	
	private String[] deathcount_seq;
	private String[] deathcount_value;
	private String[] weight_seq;
	private String[] weight_value;
	private String[] buildtemp_seq;
	private String[] buildtemp_value;
	private String[] etc_seq;
	private String[] etc_value;
	private String[] medicine_seq;
	private String[] medicine_value;
	
	public IljiModifyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IljiModifyDto(String[] deathcount_seq, String[] deathcount_value, String[] weight_seq, String[] weight_value,
			String[] buildtemp_seq, String[] buildtemp_value, String[] etc_seq, String[] etc_value,
			String[] medicine_seq, String[] medicine_value) {
		super();
		this.deathcount_seq = deathcount_seq;
		this.deathcount_value = deathcount_value;
		this.weight_seq = weight_seq;
		this.weight_value = weight_value;
		this.buildtemp_seq = buildtemp_seq;
		this.buildtemp_value = buildtemp_value;
		this.etc_seq = etc_seq;
		this.etc_value = etc_value;
		this.medicine_seq = medicine_seq;
		this.medicine_value = medicine_value;
	}

	public String[] getDeathcount_seq() {
		return deathcount_seq;
	}

	public void setDeathcount_seq(String[] deathcount_seq) {
		this.deathcount_seq = deathcount_seq;
	}

	public String[] getDeathcount_value() {
		return deathcount_value;
	}

	public void setDeathcount_value(String[] deathcount_value) {
		this.deathcount_value = deathcount_value;
	}

	public String[] getWeight_seq() {
		return weight_seq;
	}

	public void setWeight_seq(String[] weight_seq) {
		this.weight_seq = weight_seq;
	}

	public String[] getWeight_value() {
		return weight_value;
	}

	public void setWeight_value(String[] weight_value) {
		this.weight_value = weight_value;
	}

	public String[] getBuildtemp_seq() {
		return buildtemp_seq;
	}

	public void setBuildtemp_seq(String[] buildtemp_seq) {
		this.buildtemp_seq = buildtemp_seq;
	}

	public String[] getBuildtemp_value() {
		return buildtemp_value;
	}

	public void setBuildtemp_value(String[] buildtemp_value) {
		this.buildtemp_value = buildtemp_value;
	}

	public String[] getEtc_seq() {
		return etc_seq;
	}

	public void setEtc_seq(String[] etc_seq) {
		this.etc_seq = etc_seq;
	}

	public String[] getEtc_value() {
		return etc_value;
	}

	public void setEtc_value(String[] etc_value) {
		this.etc_value = etc_value;
	}

	public String[] getMedicine_seq() {
		return medicine_seq;
	}

	public void setMedicine_seq(String[] medicine_seq) {
		this.medicine_seq = medicine_seq;
	}

	public String[] getMedicine_value() {
		return medicine_value;
	}

	public void setMedicine_value(String[] medicine_value) {
		this.medicine_value = medicine_value;
	}

	@Override
	public String toString() {
		return "IljiModifyDto [deathcount_seq=" + Arrays.toString(deathcount_seq) + ", deathcount_value="
				+ Arrays.toString(deathcount_value) + ", weight_seq=" + Arrays.toString(weight_seq) + ", weight_value="
				+ Arrays.toString(weight_value) + ", buildtemp_seq=" + Arrays.toString(buildtemp_seq)
				+ ", buildtemp_value=" + Arrays.toString(buildtemp_value) + ", etc_seq=" + Arrays.toString(etc_seq)
				+ ", etc_value=" + Arrays.toString(etc_value) + ", medicine_seq=" + Arrays.toString(medicine_seq)
				+ ", medicine_value=" + Arrays.toString(medicine_value) + "]";
	}
	
	
}
